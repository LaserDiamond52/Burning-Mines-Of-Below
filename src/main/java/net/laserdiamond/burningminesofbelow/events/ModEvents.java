package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.heat.HeatModifier;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeatProvider;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatS2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID)
public class ModEvents
{

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof Player)
        {
            if (!event.getObject().getCapability(PlayerHeatProvider.PLAYER_HEAT).isPresent())
            {
                event.addCapability(new ResourceLocation(BurningMinesOfBelow.MODID, "properties"), new PlayerHeatProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if (event.isWasDeath())
        {
            event.getOriginal().getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(oldStore ->
            {
                event.getOriginal().getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(newStore ->
                {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerHeat.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        final Player player = event.player;
        if (event.phase == TickEvent.Phase.END)
        {
            return;
        }

        if (event.side == LogicalSide.SERVER)
        {
            player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(heat ->
            {
                int playerTicks = player.tickCount;
                if (player.isCreative() || player.isSpectator())
                {
                    BMOBPackets.sendToPlayer(new HeatS2CPacket(heat.getHeat(), player), ((ServerPlayer) player));
                    return; // Player's heat should not change if they are in creative/spectator
                }

                final int heatInterval = (int) getAttributeValue(player.getAttribute(BMOBAttributes.PLAYER_HEAT_INTERVAL.get()), "Heat Interval");

                if (playerTicks % heatInterval == 0) // Time to heat up
                {
                    HeatModifier heatModifier = heatUpPlayer(player, heat);
                    heat.addHeat(heatModifier.value(), heatModifier.canBypass());

                    BMOBPackets.sendToPlayer(new HeatS2CPacket(heat.getHeat(), player), (ServerPlayer) player);
                }

                final int freezeInterval = (int) getAttributeValue(player.getAttribute(BMOBAttributes.PLAYER_FREEZE_INTERVAL.get()), "Freeze Interval");

                if (playerTicks % freezeInterval == 0) // Time to cool down
                {
                    HeatModifier coolModifier = coolDownPlayer(player, heat);
                    heat.removeHeat(coolModifier.value(), coolModifier.canBypass());

                    BMOBPackets.sendToPlayer(new HeatS2CPacket(heat.getHeat(), player), ((ServerPlayer) player));
                }

            });
        }
    }

    /**
     * Gets the {@link Attribute}'s value from the {@link AttributeInstance}
     * @param attributeInstance The {@link AttributeInstance} to get a value from
     * @param attributeName The name of the attribute
     * @return The value stored in the attribute. Throws an {@link IllegalStateException} if the {@link AttributeInstance} is null
     */
    private static double getAttributeValue(AttributeInstance attributeInstance, String attributeName)
    {
        if (attributeInstance == null)
        {
            throw new IllegalStateException("!!! Player DOES NOT HAVE " + attributeName.toUpperCase() + " ATTRIBUTE !!!");
        }
        return attributeInstance.getValue();
    }

    private static HeatModifier heatUpPlayer(Player player, PlayerHeat playerHeat)
    {
        int heatPoints = 0;
        boolean canOverheat = false;

        final BlockPos playerBlockPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
        final Level level = player.level();

        if (player.position().y <= PlayerHeat.Y_LEVEL_FREEZE) // Check if player is within a valid y-level to gain heat
        {
            if (playerHeat.getHeat() < PlayerHeat.SAFE_HEAT) // Accumulate heat if the player is too cold
            {
                heatPoints++;
            }
            if (player.position().y <= PlayerHeat.Y_LEVEL_HEAT) // Accumulate heat if the player is under y-level
            {
                heatPoints++;
                canOverheat = true; // Player can overheat from this
            }
        }

        if (player.isOnFire() || player.isInLava()) // Check if player is on fire/in lava
        {
            if (!player.fireImmune()) // Player shouldn't accumulate heat from this if they have fire resistance
            {
                heatPoints++;
                canOverheat = true; // Add heat; Player can overheat from this
            }
        }

        for (TagKey<Biome> biomeTagKey : PlayerHeat.hotBiomes())
        {
            if (level.getBiome(playerBlockPos).containsTag(biomeTagKey))
            {
                heatPoints++;
                break;
            }
        }

        if (level.dimension() == Level.NETHER)
        {
            // TODO: Player shouldn't gain heat if in the Cocytus Tundra
            heatPoints++;
        }

        return new HeatModifier(heatPoints, canOverheat);
    }

    private static HeatModifier coolDownPlayer(Player player, PlayerHeat playerHeat)
    {
        int coolPoints = 0;
        boolean canFreeze = false;

        final BlockPos playerBlockPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
        final Level level = player.level();

        if (player.position().y >= PlayerHeat.Y_LEVEL_HEAT)
        {
            if (playerHeat.getHeat() > PlayerHeat.SAFE_HEAT)
            {
                coolPoints++;
            }
            if (player.position().y >= PlayerHeat.Y_LEVEL_FREEZE)
            {
                coolPoints++;
                canFreeze = true;
            }
        }

        if (player.isInPowderSnow)
        {
            coolPoints++;
            canFreeze = true;
        }

        for (TagKey<Biome> biomeTagKey : PlayerHeat.coldBiomes())
        {
            if (level.getBiome(playerBlockPos).containsTag(biomeTagKey))
            {
                coolPoints++;
                canFreeze = true;
                break;
            }
        }

        return new HeatModifier(coolPoints, canFreeze);
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event)
    {
        if (!event.getLevel().isClientSide)
        {
            if (event.getEntity() instanceof ServerPlayer player)
            {
                player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat ->
                {
                    BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
                });
            }
        }
    }
}
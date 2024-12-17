package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.RefinedOreBlock;
import net.laserdiamond.burningminesofbelow.block.VanillaRefinedOreBlock;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.BlaziumFireBall;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeatProvider;
import net.laserdiamond.burningminesofbelow.item.equipment.tools.BlaziumSwordItem;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatS2CPacket;
import net.laserdiamond.burningminesofbelow.util.BMOBMath;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers and listens for events on the Forge bus of this mod </li>
 * <li>Methods with the {@link SubscribeEvent} annotation are listening for events</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Custom Thirst System in Forge Minecraft 1.19.1 Modding<a href="https://www.youtube.com/watch?v=NN-k74NMKRc&list=PLKGarocXCE1EquNdk4WsX-VZTmbeOZ7MS">...</a></p>
 * <p>Forge Documentation:<a href="https://docs.minecraftforge.net/en/1.20.x/">...</a></p>
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID)
public class ModEvents
{

    /**
     * Called when it is time to attach new capabilities to the player
     * @param event the {@link AttachCapabilitiesEvent} to listen for
     */
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

    /**
     * Called when a {@link Player} is cloned
     * @param event The {@link PlayerEvent.Clone} to listen for
     */
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

    /**
     * Called when it is time to register capabilities
     * @param event The {@link RegisterCapabilitiesEvent} to listen for
     */
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {
        event.register(PlayerHeat.class);
    }

    /**
     * Called every tick for the {@link Player}. Heat functionality is managed here
     * @param event The {@link TickEvent.PlayerTickEvent} to listen for
     */
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
            int playerTicks = player.tickCount;
            player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(heat ->
            {
                if (player.isCreative() || player.isSpectator())
                {
                    BMOBPackets.sendToPlayer(new HeatS2CPacket(heat.getHeat(), player), ((ServerPlayer) player));
                    return; // Player's heat should not change if they are in creative/spectator
                }

                // Apply heat up/cool down effects to the player
                PlayerHeat.heatUpPlayerBiome(player, heat);
                PlayerHeat.coolDownPlayerBiome(player, heat);
                PlayerHeat.heatUpPlayerOnFire(player, heat);
                PlayerHeat.coolDownPlayerFreezing(player, heat);
                PlayerHeat.heatUpPlayerNether(player, heat);
                PlayerHeat.heatUpPlayerElevation(player, heat);
                PlayerHeat.coolDownPlayerElevation(player, heat);

                // Apply heat stroke/heat exhaustion effects to the player
                PlayerHeat.giveHeatStroke(player, heat);
                PlayerHeat.giveHeatExhaustion(player, heat);
                PlayerHeat.giveFrostbite(player, heat);
                PlayerHeat.giveHypothermia(player, heat);

            });

            final BlaziumSwordItem.BlaziumFireBallAbility blaziumFireBallAbility = BlaziumSwordItem.BlaziumFireBallAbility.INSTANCE;

            if (blaziumFireBallAbility.isAbilityActive(player)) // Player can launch fireballs
            {
                if (blaziumFireBallAbility.getTickTimeToNextLaunch(player) + 1 == playerTicks) // Player is ready to launch
                {
                    int fireballsLaunched = blaziumFireBallAbility.getFireBallsLaunched(player); // Amount of fireballs launched

                    // Launch fireball
                    final Level level = player.level();
                    level.playSound(null, player.getOnPos(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 100, 1);
                    double x = player.getX();
                    double eyeY = player.getEyeY();
                    double z = player.getZ();

                    BlaziumFireBall blaziumFireBall = new BlaziumFireBall(level, player, player.getLookAngle());
                    blaziumFireBall.setPos(x, eyeY, z);
                    level.addFreshEntity(blaziumFireBall);

                    blaziumFireBallAbility.setTickTimeToNextLaunch(player, playerTicks + 5); // Set interval for next launch
                    blaziumFireBallAbility.setFireBallsLaunched(player, fireballsLaunched + 1); // Update amount of fireballs launched

                    if (blaziumFireBallAbility.hasLaunchedMaxFireBalls(player)) // Check if player has launched the maximum amount of fireballs
                    {
                        blaziumFireBallAbility.setFireBallsLaunched(player, 0); // Reset fireballs launched
                        blaziumFireBallAbility.setAbilityActive(player, false); // Ability is no longer active
                    }
                }
            } else // Player is not ready to launch
            {
                blaziumFireBallAbility.setTickTimeToNextLaunch(player, playerTicks); // Keep the player updated on when to launch the first fireball
            }


        }
    }

    /**
     * Called when a player joins the world
     * @param event {@link EntityJoinLevelEvent}
     */
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

    /**
     * Called when a player breaks a block
     * @param event {@link net.minecraftforge.event.level.BlockEvent.BreakEvent}
     */
    @SubscribeEvent
    public static void blockBreakEvent(BlockEvent.BreakEvent event)
    {
        final Player player = event.getPlayer();

        if (!event.getLevel().isClientSide())
        {
            if (!ForgeHooks.isCorrectToolForDrops(event.getState(), player)) // Check if player is using the correct tool for drops
            {
                return; // Don't continue if the correct tool is not being used
            }
            Item itemToDrop = getRefinedOreRefinedItemDrop(event); // Get item to drop

            if (itemToDrop != ItemStack.EMPTY.getItem())
            {
                final AttributeInstance refinedOreDropChanceAttribute = player.getAttribute(BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get());
                if (refinedOreDropChanceAttribute == null)
                {
                    return; // Don't continue if attribute instance is null
                }
                final double attributeValue = refinedOreDropChanceAttribute.getValue(); // Attribute value

                // Last two digits are the drop chance
                // Every 100 guarantees +1 refined ore drop

                final double dropChance = BMOBMath.getLastTwoDigitsFromChance(attributeValue); // Chance for +1
                final double guaranteedChance = BMOBMath.getGuaranteedFromChance(attributeValue); // Guaranteed +1

                int random = BMOBMath.getRandomInteger(100);
                int amountToDrop = 0;

                if (random <= dropChance) // If we can drop
                {
                    amountToDrop++;
                }

                final ItemStack droppedItem = itemToDrop.getDefaultInstance(); // Item Stack for refined ore drop
                droppedItem.setCount((int) (amountToDrop + guaranteedChance)); // Set amount

                player.level().addFreshEntity(new ItemEntity(player.level(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), droppedItem)); // Add entity to world

            }
        }
    }

    /**
     * Gets the refined item to drop if a refined ore is mined
     * @param event The {@link BlockEvent.BreakEvent} being listened
     * @return The {@link Item} to drop if the refined ore is mined
     */
    private static Item getRefinedOreRefinedItemDrop(BlockEvent.BreakEvent event) {
        Item itemToDrop = ItemStack.EMPTY.getItem();

        if (event.getState().getBlock() instanceof RefinedOreBlock refinedOreBlock) // Check if player is mining a refined ore block
        {
            itemToDrop = refinedOreBlock.getRefinedDrop().get();
        } else if (event.getState().getBlock() instanceof VanillaRefinedOreBlock vanillaRefinedOreBlock) // Check if player is mining a refined ore block
        {
            itemToDrop = vanillaRefinedOreBlock.getRefinedDrop().get();
        }
        return itemToDrop;
    }
}

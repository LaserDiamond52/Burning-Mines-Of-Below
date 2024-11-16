package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.RefinedOreBlock;
import net.laserdiamond.burningminesofbelow.block.VanillaRefinedOreBlock;
import net.laserdiamond.burningminesofbelow.effects.BMOBEffects;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.BlaziumFireBall;
import net.laserdiamond.burningminesofbelow.heat.HeatModifier;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeatProvider;
import net.laserdiamond.burningminesofbelow.item.equipment.tools.BlaziumSwordItem;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatS2CPacket;
import net.laserdiamond.burningminesofbelow.util.BMOBMath;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
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
            int playerTicks = player.tickCount;
            player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(heat ->
            {
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

                final int heatStrokeInterval = 20;
                if (playerTicks % heatStrokeInterval == 0) // Give player heatstroke effects
                {
                    giveHeatStroke(player, heat);
                }

                final int heatExhaustionInterval = 100;
                if (playerTicks % heatExhaustionInterval == 0) // Give player heat exhaustion effects
                {
                    giveHeatExhaustion(player, heat);
                }

                final int frostbiteInterval = 20;
                if (playerTicks % frostbiteInterval == 0) // Give player frostbite effects
                {
                    giveFrostbite(player, heat);
                }

                final int hypothermiaInterval = 100;
                if (playerTicks % hypothermiaInterval == 0) // Give player hypothermia effects
                {
                    giveHypothermia(player, heat);
                }

                // TODO:
                // - Player should rapidly gain heat if they are on fire or in lava

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
                    Vec3 playerView = player.getLookAngle();
                    double x = player.getX();
                    double eyeY = player.getEyeY();
                    double z = player.getZ();

                    BlaziumFireBall blaziumFireBall = new BlaziumFireBall(level, player, playerView.x, playerView.y, playerView.z);
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

    /**
     * Helper method for heating up the player
     * @param player The player to heat up
     * @param playerHeat The player's heat data
     * @return A {@link HeatModifier} that contains the amount of heat to add and whether it should bypass the safe zone
     */
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

    /**
     * Helper method for cooling down the player
     * @param player The player to cool down
     * @param playerHeat The player's heat data
     * @return A {@link HeatModifier} that contains the amount of heat to remove and whether it should bypass the safe zone
     */
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

    /**
     * Gives the player the debuffs for heat stroke
     * @param player The player receiving the effects
     * @param playerHeat The player's heat data
     */
    private static void giveHeatStroke(Player player, PlayerHeat playerHeat)
    {
        if (PlayerHeat.isHot(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240));
        }
    }

    /**
     * Gives the player Heat Exhaustion
     * @param player The player receiving heat exhaustion
     * @param playerHeat The player's heat data
     */
    private static void giveHeatExhaustion(Player player, PlayerHeat playerHeat)
    {
        if (PlayerHeat.isHeatExhaustion(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(BMOBEffects.HEAT_EXHAUSTION.get(), 240));
        }

        if (player.hasEffect(BMOBEffects.HEAT_EXHAUSTION.get()))
        {
            player.hurt(player.damageSources().dryOut(), (float) (player.getMaxHealth() * 0.3));
        }
    }

    /**
     * Gives the player the debuffs for frostbite
     * @param player The player receiving the effects
     * @param playerHeat The player's heat data
     */
    private static void giveFrostbite(Player player, PlayerHeat playerHeat)
    {
        if (PlayerHeat.isFrostBite(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 240));
            player.setTicksFrozen(200);
        }
    }

    /**
     * Gives the player Hypothermia
     * @param player The player receiving Hypothermia
     * @param playerHeat The player's heat data
     */
    private static void giveHypothermia(Player player, PlayerHeat playerHeat)
    {
        if (PlayerHeat.isHypothermia(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(BMOBEffects.HYPOTHERMIA.get(), 240));
        }

        if (player.hasEffect(BMOBEffects.HYPOTHERMIA.get()))
        {
            player.hurt(player.damageSources().freeze(), (float) (player.getMaxHealth() * 0.3));
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
            Item itemToDrop = getItem(event); // Get item to drop

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

    private static Item getItem(BlockEvent.BreakEvent event) {
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

    private static void dropRefinedDrop(BlockEvent.BreakEvent event, RefinedOreBlock refinedOreBlock, Player player)
    {
        if (ForgeHooks.isCorrectToolForDrops(event.getState(), player)) // Check if player is using the correct tool for drops
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
            int itemsToDrop = 0;

            if (random <= dropChance) // If we can drop
            {
                itemsToDrop++;
            }

            final ItemStack droppedItem = refinedOreBlock.getRefinedDrop().get().getDefaultInstance(); // Item Stack for refined ore drop
            droppedItem.setCount((int) (itemsToDrop + guaranteedChance)); // Set amount

            player.level().addFreshEntity(new ItemEntity(player.level(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), droppedItem)); // Add entity to world

        }
    }
}

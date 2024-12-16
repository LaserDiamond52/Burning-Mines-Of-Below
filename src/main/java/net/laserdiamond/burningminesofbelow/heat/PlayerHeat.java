package net.laserdiamond.burningminesofbelow.heat;

import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.effects.BMOBEffects;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatS2CPacket;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Manages operations the player's heat, such as adding, subtracting, and setting a value to it</li>
 * <li>Contains fields specifying what heat levels are considered safe, hypothermia, frostbite, heat stroke, and heat exhaustion</li>
 * @author Allen Malo
 */
public class PlayerHeat {

    private int heat;

    /**
     * Highest value the player's heat can reach
     */
    public static final int MAX_HEAT = 200;

    /**
     * Safest value for the player's heat
     */
    public static final int SAFE_HEAT = 100;

    /**
     * Lowest value the player's heat can reach
     */
    public static final int MIN_HEAT = 0;

    /**
     * Maximum Y-Level for the player to accumulate heat underground
     */
    public static final int Y_LEVEL_HEAT = 10;

    /**
     * Minimum Y-Level for the player to start freeze above ground
     */
    public static final int Y_LEVEL_FREEZE = 200;

    /**
     * Minimum heat value for the player's heat to be in the hot zone
     */
    public static final int HOT_ZONE = SAFE_HEAT + 50;

    /**
     * Minimum heat value for the player's heat to be in the heat exhaustion zone
     */
    public static final int HEAT_EXHAUSTION_ZONE = SAFE_HEAT + 75;

    /**
     * Minimum value for the player's heat to be in the frostbite zone
     */
    public static final int FROSTBITE_ZONE = SAFE_HEAT - 50;

    /**
     * Minimum value for the player's heat to be in the hypothermia zone
     */
    public static final int HYPOTHERMIA_ZONE = SAFE_HEAT - 75;

    /**
     * Interval for applying heat stroke effects
     */
    public static final int HEAT_STROKE_INTERVAL = 20;

    /**
     * Interval for applying heat exhaustion effects
     */
    public static final int HEAT_EXHAUSTION_INTERVAL = 100;

    /**
     * Interval for applying frost bite effects
     */
    public static final int FROST_BITE_INTERVAL = 20;

    /**
     * Interval for applying hypothermia effects
     */
    public static final int HYPOTHERMIA_INTERVAL = 100;

    public PlayerHeat()
    {
        this.heat = SAFE_HEAT; // Player's heat should start at the most safe value
    }

    /**
     * Gets the player's current heat
     * @return The player's current heat
     */
    public int getHeat()
    {
        return this.heat;
    }

    /**
     * Adds heat to the player
     * @param add The amount of heat to add
     * @throws IllegalArgumentException If the heat amount to add is less than 0
     */
    public void addHeat(int add) throws IllegalArgumentException
    {
        if (add < 0)
        {
            throw new IllegalArgumentException("Amount to add cannot be negative!");
        }
        int newHeat = this.heat + add;
        this.heat = Math.min(newHeat, MAX_HEAT);
    }

    /**
     * Removes heat from the player
     * @param remove The amount of heat to remove
     * @throws IllegalArgumentException If the heat amount to remove is less than 0
     */
    public void removeHeat(int remove) throws IllegalArgumentException
    {
        if (remove < 0)
        {
            throw new IllegalArgumentException("Amount to remove cannot be negative");
        }
        int newHeat = this.heat - remove;
        this.heat = Math.max(newHeat, MIN_HEAT);
    }

    /**
     * Checks if the integer amount is in the hypothermia zone
     * @param heat The heat amount as an integer
     * @return True if the integer is within the hypothermia zone, false otherwise
     */
    public static boolean isHypothermia(int heat)
    {
        return heat <= HYPOTHERMIA_ZONE && heat >= MIN_HEAT;
    }

    /**
     * Checks if the integer amount is in the frostbite zone
     * @param heat The heat amount as an integer
     * @return True if the integer is within the frostbite zone, false otherwise
     */
    public static boolean isFrostBite(int heat)
    {
        return heat <= FROSTBITE_ZONE && heat >= HYPOTHERMIA_ZONE;
    }

    /**
     * Checks if the integer amount is in the safe zone
     * @param heat The heat amount as an integer
     * @return True if the integer is within the frostbite zone, false otherwise
     */
    public static boolean isSafe(int heat)
    {
        return heat <= HOT_ZONE && heat > FROSTBITE_ZONE;
    }

    /**
     * Checks if the heat amount is in the hot zone
     * @param heat The heat amount as an integer
     * @return True if the integer is within the frostbite zone, false otherwise
     */
    public static boolean isHot(int heat)
    {
        return heat >= HOT_ZONE && heat <= HEAT_EXHAUSTION_ZONE;
    }

    /**
     * Checks if the heat amount is in the heat exhaustion zone
     * @param heat The heat amount as an integer
     * @return True if the integer is within the frostbite zone, false otherwise
     */
    public static boolean isHeatExhaustion(int heat)
    {
        return heat <= MAX_HEAT && heat >= HEAT_EXHAUSTION_ZONE;
    }

    /**
     * Copies the {@link PlayerHeat} passed through to the player's heat
     * @param playerSource The {@link PlayerHeat} to copy to the player
     */
    public void copyFrom(PlayerHeat playerSource)
    {
        this.heat = playerSource.heat;
    }

    /**
     * Saves the player's heat to a {@link CompoundTag}
     * @param nbt The {@link CompoundTag} to save the heat to
     */
    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("heat", this.heat);
    }

    /**
     * Loads the player's heat from a {@link CompoundTag}
     * @param nbt the {@link CompoundTag} to load the heat from
     */
    public void loadNBTData(CompoundTag nbt)
    {
        this.heat = nbt.getInt("heat");
    }

    /**
     *
     * @return A list of {@link Biome} {@link TagKey}s for biomes that should decrease the player's heat
     */
    public static List<TagKey<Biome>> coldBiomes()
    {
        return List.of(Tags.Biomes.IS_COLD, Tags.Biomes.IS_COLD_OVERWORLD, Tags.Biomes.IS_COLD_END, Tags.Biomes.IS_COLD_NETHER, BMOBTags.Biomes.COCYTUS, Tags.Biomes.IS_SNOWY);
    }

    /**
     *
     * @return A list of {@link Biome} {@link TagKey}s for biomes that should increase the player's heat
     */
    public static List<TagKey<Biome>> hotBiomes()
    {
        return List.of(Tags.Biomes.IS_DESERT, Tags.Biomes.IS_DRY_OVERWORLD, BMOBTags.Biomes.MAGMA, Tags.Biomes.IS_HOT, Tags.Biomes.IS_HOT_OVERWORLD, Tags.Biomes.IS_HOT_NETHER, Tags.Biomes.IS_HOT_END);
    }

    /**
     * Heats up the player if they are in a biome that makes them hot
     * @param player the player to heat up
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to add to the player
     */
    public static int heatUpPlayerBiome(Player player, PlayerHeat playerHeat)
    {
        int points = 0;
        if (player.tickCount % biomeHeatInterval(player) == 0) // Time to heat up?
        {
            for (TagKey<Biome> biomeTagKey : hotBiomes()) // Loop through biomes
            {
                if (player.level().getBiome(player.getOnPos()).containsTag(biomeTagKey)) // Does the current biome the player is in match one of the hot biomes?
                {
                    points++; // Add to player's heat
                    break; // Exit loop
                }
            }
            playerHeat.addHeat(points);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }
        return points;
    }

    /**
     * Cools down the player if they are in a biome that makes them cold
     * @param player The player to cool down
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to remove
     */
    public static int coolDownPlayerBiome(Player player, PlayerHeat playerHeat)
    {
        int points = 0;
        if (player.tickCount % biomeFreezeInterval(player) == 0)
        {
            for (TagKey<Biome> biomeTagKey : coldBiomes())
            {
                if (player.level().getBiome(player.getOnPos()).containsTag(biomeTagKey))
                {
                    points++;
                    break;
                }
                playerHeat.removeHeat(points);
                BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
            }
        }
        return points;
    }

    /**
     * Heats up the player if they are on fire or submerged in lava
     * @param player The player to heat up
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to add
     */
    public static int heatUpPlayerOnFire(Player player, PlayerHeat playerHeat)
    {
        int points = 0;

        if (player.hasEffect(MobEffects.FIRE_RESISTANCE)) // Check if player has fire resistance
        {
            return points; // Player has fire resistance. No heat should be added to them from this source
        }

        if (player.tickCount % fireHeatInterval(player) == 0) // Time to heat up?
        {
            if (player.isInLava()) // Is the player in lava?
            {
                points += 9; // Add 9
            } else if (player.isOnFire()) // Is the player on fire?
            {
                points += 4; // Add 4
            }

            playerHeat.addHeat(points); // Add to player's heat
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }
        return points;
    }

    /**
     * Cools down the player if they are submerged in powdered snow, currently freezing, or are fully frozen.
     * @param player The player to cool down
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to remove
     */
    public static int coolDownPlayerFreezing(Player player, PlayerHeat playerHeat)
    {
        int points = 0;

        if (player.tickCount % freezeFreezeInterval(player) == 0)
        {
            if (player.isInPowderSnow)
            {
                points++;
            }
            if (player.isFreezing())
            {
                points += 3;
            } else if (player.isFullyFrozen())
            {
                points += 7;
            }
            playerHeat.removeHeat(points);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }
        return points;
    }

    /**
     * Heats up the player if they are in the Nether dimension
     * @param player The {@link Player} to heat up
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to add
     */
    public static int heatUpPlayerNether(Player player, PlayerHeat playerHeat)
    {
        int points = 0;

        if (player.tickCount % netherHeatInterval(player) == 0)
        {
            if (player.level().dimension() == Level.NETHER) // Is the player in the nether?
            {
                if (player.level().getBiome(player.getOnPos()).containsTag(BMOBTags.Biomes.COCYTUS))
                {
                    return points;
                }
                points++;
            }
            playerHeat.addHeat(points);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }

        return points;
    }

    /**
     * Heats up the player based on their altitude (y-level)
     * @param player The player to heat up
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to add
     */
    public static int heatUpPlayerElevation(Player player, PlayerHeat playerHeat)
    {
        int points = 0;

        if (player.tickCount % altitudeHeatInterval(player) == 0)
        {
            if (player.position().y <= Y_LEVEL_FREEZE) // Is player's altitude under the freezing level
            {
                if (player.position().y <= Y_LEVEL_HEAT)  // Is player's altitude under the heat level
                {
                    points++;
                } else if (playerHeat.getHeat() != PlayerHeat.SAFE_HEAT) // Player isn't at the altitude to gain heat. Level the player's heat back to normal
                {
                    points++;
                }
            }
            playerHeat.addHeat(points);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }

        return points;
    }

    /**
     * Cools down the player based on their altitude (y-level)
     * @param player The player to cool down
     * @param playerHeat The player's {@link PlayerHeat}
     * @return The amount of heat to remove
     */
    public static int coolDownPlayerElevation(Player player, PlayerHeat playerHeat)
    {
        int points = 0;

        if (player.tickCount % altitudeFreezeInterval(player) == 0)
        {
            if (player.position().y >= Y_LEVEL_HEAT)
            {
                if (player.position().y >= Y_LEVEL_FREEZE)
                {
                    points++;
                } else if (playerHeat.getHeat() != PlayerHeat.SAFE_HEAT)
                {
                    points++;
                }
            }
            playerHeat.removeHeat(points);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        }
        return points;
    }

    /**
     * Adds heat to the player
     * @param player The player to add heat to
     * @param heat The amount of heat to add
     */
    public static void addHeat(Player player, int heat)
    {
        if (player.isCreative() || player.isSpectator())
        {
            return;
        }
        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat ->
        {
            playerHeat.addHeat(heat);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        });
    }

    /**
     * Removes heat from the player
     * @param player The player to remove heat from
     * @param heat The amount of heat to remove
     */
    public static void removeHeat(Player player, int heat)
    {
        if (player.isCreative() || player.isSpectator())
        {
            return;
        }
        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat ->
        {
            playerHeat.removeHeat(heat);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), ((ServerPlayer) player));
        });
    }

    /**
     * Gets the value stored in the player's Heat Interval attribute
     * @param player the player to get the attribute value from
     * @return The value stored in the player's Heat Interval attribute
     */
    public static double playerHeatAttributeValue(Player player)
    {
       return player.getAttributeValue(BMOBAttributes.PLAYER_HEAT_INTERVAL.get());
    }

    /**
     * Gets the value stored in the player's Freeze Interval attribute
     * @param player The player to get the attribute value from
     * @return The value stored in the player's Freeze Interval attribute
     */
    public static double playerFreezeAttributeValue(Player player)
    {
        return player.getAttributeValue(BMOBAttributes.PLAYER_FREEZE_INTERVAL.get());
    }

    // Heat and Freeze intervals for heating up/cooling down the player

    // Larger Value -> Longer to heat up/cool down
    // Smaller Value -> Shorter to heat up/cool down

    /**
     * The interval for heating up the player based on the biome they're in
     * @param player The player to heat up
     * @return The interval for when to heat up
     */
    public static int biomeHeatInterval(Player player)
    {
        return (int) (playerHeatAttributeValue(player) * 1.1);
    }

    /**
     * The interval for cooling down the player based on the biome they're in
     * @param player The player to cool down
     * @return The interval for when to cool down
     */
    public static int biomeFreezeInterval(Player player)
    {
        return (int) (playerFreezeAttributeValue(player) * 1.1);
    }

    /**
     * The interval for heating up the player based on their altitude (y-level)
     * @param player The player to heat up
     * @return The interval for when to heat up
     */
    public static int altitudeHeatInterval(Player player)
    {
        return (int) (playerHeatAttributeValue(player) * 0.95);
    }

    /**
     * The interval for cooling down the player based on their altitude (y-level)
     * @param player The player to cool down
     * @return The interval for when to cool down
     */
    public static int altitudeFreezeInterval(Player player)
    {
        return (int) (playerFreezeAttributeValue(player) * 0.95);
    }

    /**
     * The interval for heating up the player when they are on fire/in lava
     * @param player The player to heat up
     * @return The interval for when to heat up
     */
    public static int fireHeatInterval(Player player)
    {
        return (int) (playerHeatAttributeValue(player) * 0.05);
    }

    /**
     * The interval for cooling down the player when they are freezing/in powdered snow
     * @param player The player to cool down
     * @return The interval for when to cool down
     */
    public static int freezeFreezeInterval(Player player)
    {
        return (int) (playerFreezeAttributeValue(player) * 0.05);
    }

    /**
     * The interval for heating up the player if they are in the Nether
     * @param player The player to heat up
     * @return The interval for when to heat up
     */
    public static int netherHeatInterval(Player player)
    {
        return (int) (playerHeatAttributeValue(player) * 0.75);
    }

    // Heat Effects for when the player's heat reaches a certain level

    /**
     * Gives the player the debuffs for heat stroke
     * @param player The player receiving the effects
     * @param playerHeat The player's heat data
     */
    public static void giveHeatStroke(Player player, PlayerHeat playerHeat)
    {
        if (player.tickCount % HEAT_STROKE_INTERVAL == 0)
        {
            if (PlayerHeat.isHot(playerHeat.getHeat()))
            {
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240));
            }
        }
    }

    /**
     * Gives the player Heat Exhaustion
     * @param player The player receiving heat exhaustion
     * @param playerHeat The player's heat data
     */
    public static void giveHeatExhaustion(Player player, PlayerHeat playerHeat)
    {

        if (PlayerHeat.isHeatExhaustion(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(BMOBEffects.HEAT_EXHAUSTION.get(), 240));
        }

        if (player.tickCount % HEAT_EXHAUSTION_INTERVAL == 0)
        {
            if (player.hasEffect(BMOBEffects.HEAT_EXHAUSTION.get()))
            {
                int pAmplifier = player.getEffect(BMOBEffects.HEAT_EXHAUSTION.get()).getAmplifier();
                float damageAmount = player.getMaxHealth() * 0.3F;
                player.setHealth(Math.max(0, player.getHealth() - damageAmount));

                player.setSecondsOnFire(200 * (1 + pAmplifier));
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, pAmplifier));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, pAmplifier));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, pAmplifier));

            }
        }
    }

    /**
     * Gives the player the debuffs for frostbite
     * @param player The player receiving the effects
     * @param playerHeat The player's heat data
     */
    public static void giveFrostbite(Player player, PlayerHeat playerHeat)
    {
        if (player.tickCount % FROST_BITE_INTERVAL == 0)
        {
            if (PlayerHeat.isFrostBite(playerHeat.getHeat()))
            {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 240));
                player.setTicksFrozen(200);
            }
        }
    }

    /**
     * Gives the player Hypothermia
     * @param player The player receiving Hypothermia
     * @param playerHeat The player's heat data
     */
    public static void giveHypothermia(Player player, PlayerHeat playerHeat)
    {
        if (PlayerHeat.isHypothermia(playerHeat.getHeat()))
        {
            player.addEffect(new MobEffectInstance(BMOBEffects.HYPOTHERMIA.get(), 240));
        }

        if (player.tickCount % HYPOTHERMIA_INTERVAL == 0)
        {
            if (player.hasEffect(BMOBEffects.HYPOTHERMIA.get()))
            {
                int pAmplifier = player.getEffect(BMOBEffects.HYPOTHERMIA.get()).getAmplifier();
                float damageAmount = player.getMaxHealth() * 0.3F;
                player.setHealth(Math.max(0, player.getHealth() - damageAmount));

                player.setTicksFrozen(200);
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, pAmplifier));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, pAmplifier));
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 200, pAmplifier));

            }
        }
    }
}

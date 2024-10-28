package net.laserdiamond.burningminesofbelow.heat;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;

import java.util.List;

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
     * @param canOverheat Whether the player can overheat or not. If the player cannot overheat, the player's heat level cannot go above the safe level
     */
    public void addHeat(int add, boolean canOverheat)
    {
        int newHeat = this.heat + add;
        if (canOverheat) // Player can overheat
        {
            this.heat = Math.min(newHeat, MAX_HEAT); // Player's heat cap is now the max heat
            return;
        } else if (this.heat > HEAT_EXHAUSTION_ZONE) // Player is already overheated, but they can no longer overheat
        {
            this.heat = Math.min(this.heat, MAX_HEAT); // Player's heat should not change.
            return;
        }
        this.heat = Math.min(newHeat, HOT_ZONE); // Player is not overheated and does not have a heat value above the hot zone
    }

    /**
     * Removes heat from the player
     * @param remove The amount of heat to remove
     * @param canFreeze Whether the player can freeze or not. If the player cannot freeze, the player's heat level cannot go below the safe level
     */
    public void removeHeat(int remove, boolean canFreeze)
    {
        int newHeat = this.heat - remove;
        if (canFreeze) // Player can freeze
        {
            this.heat = Math.max(newHeat, MIN_HEAT); // Player's heat floor is now the minimum heat
            return;
        } else if (this.heat < HYPOTHERMIA_ZONE) // Player is already freezing, but they can no longer freeze
        {
            this.heat = Math.max(this.heat, MIN_HEAT); // Player's heat should not change
            return;
        }
        this.heat = Math.max(newHeat, FROSTBITE_ZONE); // Player is not freezing and does not have a heat value below the frostbite zone
    }

    public static boolean isHypothermia(int heat)
    {
        return heat <= HYPOTHERMIA_ZONE && heat >= MIN_HEAT;
    }

    public static boolean isFrostBite(int heat)
    {
        return heat <= FROSTBITE_ZONE && heat >= HYPOTHERMIA_ZONE;
    }

    public static boolean isSafe(int heat)
    {
        return heat <= HOT_ZONE && heat > FROSTBITE_ZONE;
    }

    public static boolean isHot(int heat)
    {
        return heat >= HOT_ZONE && heat <= HEAT_EXHAUSTION_ZONE;
    }

    public static boolean isHeatExhaustion(int heat)
    {
        return heat <= MAX_HEAT && heat >= HEAT_EXHAUSTION_ZONE;
    }

    public void copyFrom(PlayerHeat playerSource)
    {
        this.heat = playerSource.heat;
    }

    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("heat", this.heat);
    }

    public void loadNBTData(CompoundTag nbt)
    {
        this.heat = nbt.getInt("heat");
    }

    public static List<TagKey<Biome>> coldBiomes()
    {
        return List.of(Tags.Biomes.IS_COLD, Tags.Biomes.IS_COLD_END, Tags.Biomes.IS_COLD_NETHER);
    }

    public static List<TagKey<Biome>> hotBiomes()
    {
        return List.of(Tags.Biomes.IS_DESERT, Tags.Biomes.IS_DRY_OVERWORLD);
    }
}

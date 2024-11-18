package net.laserdiamond.burningminesofbelow.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BMOBOrePlacements {

    /**
     * Helper method for creating ore placements
     * @param countPlacement The amount of ore veins in the chunk of the world
     * @param heightRange The highest y-level the ores can naturally generate in the world
     * @return The {@link PlacementModifier}s for the ore placement
     */
    public static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange)
    {
        return List.of(countPlacement, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    /**
     * Generates the ore as a common ore, similarly to coal, copper, etc.
     * @param count The amount of ore veins in any given chunk of the world
     * @param heightRange The highest y-level the ores can naturally generate in the world
     * @return The {@link PlacementModifier}s for the common ore placement
     */
    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange)
    {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    /**
     * Generates the ore as a rare ore, similarly to diamond
     * @param chance The chance of the ore generating in any given chunk of the world
     * @param heightRange The highest y-level the ores can naturally generate in the world
     * @return The {@link PlacementModifier}s for the rare ore placement
     */
    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}

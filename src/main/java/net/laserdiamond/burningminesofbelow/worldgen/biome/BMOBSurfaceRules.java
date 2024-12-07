package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

/**
 * Responsibilities:
 * <li>Contains the {@link SurfaceRules.RuleSource}s for creating the block make-up of the biomes</li>
 * <li>Applies the {@link SurfaceRules.RuleSource}s for the respective biomes</li>
 */
public class BMOBSurfaceRules {

    /**
     * A {@link SurfaceRules.ConditionSource} that checks for the water level. Used when applying {@link net.minecraft.world.level.levelgen.SurfaceRules.RuleSource}s for biomes
     */
    private static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    /**
     * The {@link SurfaceRules.RuleSource} for applying Frozen Soul Sand
     */
    private static final SurfaceRules.RuleSource FROZEN_SOUL_SAND = makeStateRule(BMOBBlocks.FROZEN_SOUL_SAND.get());

    /**
     * The {@link SurfaceRules.RuleSource} for applying Frozen Soul Soil
     */
    private static final SurfaceRules.RuleSource FROZEN_SOUL_SOIL = makeStateRule(BMOBBlocks.FROZEN_SOUL_SOIL.get());

    /**
     * The {@link SurfaceRules.RuleSource} for applying Magnite Stone
     */
    private static final SurfaceRules.RuleSource MAGNITE_STONE = makeStateRule(BMOBBlocks.MAGNITE_STONE.get());

    /**
     * The {@link SurfaceRules.RuleSource} for applying Magma
     */
    private static final SurfaceRules.RuleSource MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);

    /**
     * The {@link SurfaceRules.RuleSource} for applying Frozen Netherrack
     */
    private static final SurfaceRules.RuleSource FROZEN_NETHERRACK = makeStateRule(BMOBBlocks.FROZEN_NETHERRACK.get());

    /**
     * The {@link SurfaceRules.RuleSource} for applying Netherrack
     */
    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);

    /**
     * The {@link SurfaceRules.RuleSource} for applying Obsidian
     */
    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);

    /**
     * The {@link SurfaceRules.RuleSource} for applying Bedrock
     */
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    /**
     *
     * @return The {@link SurfaceRules.RuleSource} for the Cocytus Tundra biome.
     */
    private static SurfaceRules.RuleSource makeCocytusTundraSurfaceRules()
    {
        SurfaceRules.RuleSource surfaceRule = createFloorSurfaceRule(FROZEN_SOUL_SAND, FROZEN_SOUL_SOIL);
        return createSurfaceRule(BMOBBiomes.COCYTUS_TUNDRA, surfaceRule, FROZEN_SOUL_SOIL, FROZEN_NETHERRACK, FROZEN_NETHERRACK, FROZEN_NETHERRACK, BEDROCK);
    }

    /**
     *
     * @return The {@link SurfaceRules.RuleSource} for the Cocytus Wastelands biome.
     */
    private static SurfaceRules.RuleSource makeCocytusWastelandsSurfaceRules()
    {
        return createSurfaceRule(BMOBBiomes.COCYTUS_WASTELAND, FROZEN_NETHERRACK, FROZEN_NETHERRACK, FROZEN_NETHERRACK, FROZEN_NETHERRACK, FROZEN_NETHERRACK, BEDROCK);
    }

    /**
     *
     * @return The {@link SurfaceRules.RuleSource} for the Magnite Fields biome
     */
    private static SurfaceRules.RuleSource makeMagniteFieldsSurfaceRules()
    {
        return createSurfaceRule(BMOBBiomes.MAGNITE_FIELDS, MAGNITE_STONE, MAGNITE_STONE, MAGMA, NETHERRACK, MAGMA, BEDROCK);
    }

    /**
     *
     * @return The {@link SurfaceRules.RuleSource} for the Magnite Caves biome
     */
    private static SurfaceRules.RuleSource makeMagniteCavesSurfaceRules()
    {
        return createSurfaceRule(BMOBBiomes.MAGNITE_CAVES, MAGNITE_STONE, MAGNITE_STONE, MAGMA, OBSIDIAN, MAGMA, OBSIDIAN);
    }

    /**
     * Creates a {@link SurfaceRules.RuleSource} that checks for Water. This is primarily used for creating Surface Rules for the floor of a biome.
     * @param waterTrueRule The {@link SurfaceRules.RuleSource} to apply when the Water Check returns true
     * @param defaultRule The {@link SurfaceRules.RuleSource} to apply when the Water Check returns false
     * @return A {@link SurfaceRules.RuleSource} that checks for Water.
     */
    private static SurfaceRules.RuleSource createFloorSurfaceRule(SurfaceRules.RuleSource waterTrueRule, SurfaceRules.RuleSource defaultRule)
    {
        return SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, waterTrueRule), defaultRule);
    }

    /**
     * Creates a surface rule for a biome
     * @param biome The {@link ResourceKey} for the biome
     * @param floorRule The floor {@link SurfaceRules.RuleSource} for the biome
     * @param underFloorRule The under floor {@link SurfaceRules.RuleSource} for the biome
     * @param deepUnderFloorRule The deep under floor {@link SurfaceRules.RuleSource} for the biome
     * @param veryDeepUnderFloorRule The very deep under floor {@link SurfaceRules.RuleSource} for the biome
     * @param underCeilingRule The under ceiling {@link SurfaceRules.RuleSource} for the biome
     * @param onCeilingRule The on ceiling {@link SurfaceRules.RuleSource} for the biome
     * @return A {@link SurfaceRules.RuleSource} that contains a List of all the surface rules for the biome
     */
    private static SurfaceRules.RuleSource createSurfaceRule(ResourceKey<Biome> biome, SurfaceRules.RuleSource floorRule, SurfaceRules.RuleSource underFloorRule, SurfaceRules.RuleSource deepUnderFloorRule, SurfaceRules.RuleSource veryDeepUnderFloorRule, SurfaceRules.RuleSource underCeilingRule, SurfaceRules.RuleSource onCeilingRule)
    {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, floorRule)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, underFloorRule)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, deepUnderFloorRule)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, veryDeepUnderFloorRule)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, underCeilingRule)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biome), SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, onCeilingRule))
        );
    }

    /**
     * Creates all the {@link SurfaceRules.RuleSource}s for the Overworld biomes of this mod
     * @return A {@link SurfaceRules.RuleSource} that contains a List of all the surface rules for the Overworld biomes of this mod
     */
    public static SurfaceRules.RuleSource createOverworldSurfaceRules()
    {
        return SurfaceRules.sequence(makeMagniteCavesSurfaceRules());
    }

    /**
     * Creates all the {@link SurfaceRules.RuleSource}s for the Nether biomes of this mod
     * @return A {@link net.minecraft.world.level.levelgen.SurfaceRules.RuleSource} that contains a List of all the surface rules for the Nether biomes of this mod
     */
    public static SurfaceRules.RuleSource createNetherSurfaceRules()
    {
        return SurfaceRules.sequence(makeCocytusTundraSurfaceRules(), makeCocytusWastelandsSurfaceRules(), makeMagniteFieldsSurfaceRules());
    }

    /**
     * Helper method used to create surface rules for biomes
     * @param block The block being used in the surface rule
     * @return The {@link SurfaceRules.RuleSource} that can be used for biome creation
     */
    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}

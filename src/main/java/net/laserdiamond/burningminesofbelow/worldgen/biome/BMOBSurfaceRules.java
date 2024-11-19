package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class BMOBSurfaceRules {

    public static final SurfaceRules.ConditionSource WATER_CHECK = SurfaceRules.waterBlockCheck(-1, 0);

    private static final SurfaceRules.RuleSource FROZEN_SOUL_SAND = makeStateRule(BMOBBlocks.FROZEN_SOUL_SAND.get());

    private static final SurfaceRules.RuleSource FROZEN_SOUL_SOIL = makeStateRule(BMOBBlocks.FROZEN_SOUL_SOIL.get());

    private static final SurfaceRules.RuleSource MAGNITE_STONE = makeStateRule(BMOBBlocks.MAGNITE_STONE.get());

    private static final SurfaceRules.RuleSource MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);

    private static final SurfaceRules.RuleSource FROZEN_NETHERRACK = makeStateRule(BMOBBlocks.FROZEN_NETHERRACK.get());

    private static final SurfaceRules.RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);

    private static SurfaceRules.RuleSource makeCocytusTundraSurfaceRules()
    {
        SurfaceRules.RuleSource soulSandSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(WATER_CHECK, FROZEN_SOUL_SAND), FROZEN_SOUL_SOIL);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(BMOBBiomes.COCYTUS_TUNDRA),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, soulSandSurface)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FROZEN_SOUL_SOIL)),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, FROZEN_NETHERRACK),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, FROZEN_NETHERRACK),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, FROZEN_NETHERRACK)
        );
    }

    private static SurfaceRules.RuleSource makeCocytusWastelandsSurfaceRules()
    {
        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(BMOBBiomes.COCYTUS_WASTELAND),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FROZEN_NETHERRACK)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FROZEN_NETHERRACK)),
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, FROZEN_NETHERRACK),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, FROZEN_NETHERRACK),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, FROZEN_NETHERRACK)
        );
    }

    private static SurfaceRules.RuleSource makeMagmaFieldsSurfaceRules()
    {
        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(BMOBBiomes.MAGMA_FIELDS),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MAGNITE_STONE)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MAGMA)),
                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, NETHERRACK)
        );
    }

    public static SurfaceRules.RuleSource createSurfaceRules()
    {
        return SurfaceRules.sequence(makeCocytusTundraSurfaceRules(), makeCocytusWastelandsSurfaceRules(), makeMagmaFieldsSurfaceRules());
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

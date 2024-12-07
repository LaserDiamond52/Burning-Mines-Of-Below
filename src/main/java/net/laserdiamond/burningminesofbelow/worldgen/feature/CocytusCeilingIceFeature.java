package net.laserdiamond.burningminesofbelow.worldgen.feature;

import com.mojang.serialization.Codec;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CocytusCeilingIceFeature extends Feature<NoneFeatureConfiguration> {

    public CocytusCeilingIceFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource randomSource = featurePlaceContext.random();

        if (!worldGenLevel.isEmptyBlock(blockPos))
        {
            return false;
        } else
        {
            BlockState blockState = worldGenLevel.getBlockState(blockPos.above());
            if (!blockState.is(BMOBTags.Blocks.COCYTUS_ICE_SPIKE_BASE_BLOCK))
            {
                return false;
            } else {
                worldGenLevel.setBlock(blockPos, Blocks.PACKED_ICE.defaultBlockState(), 2);

                for(int i = 0; i < 3000; i++) {
                    BlockPos offset = blockPos.offset(randomSource.nextInt(8) - randomSource.nextInt(8), -randomSource.nextInt(12), randomSource.nextInt(8) - randomSource.nextInt(8));
                    if (worldGenLevel.getBlockState(offset).isAir())
                    {
                        int j = 0;
                        Direction[] directions = Direction.values();

                        for (Direction direction : directions)
                        {
                            if (worldGenLevel.getBlockState(offset.relative(direction)).is(Blocks.PACKED_ICE)) {
                                j++;
                            }

                            if (j > 1) {
                                break;
                            }
                        }

                        if (j == 1) {
                            worldGenLevel.setBlock(offset, Blocks.PACKED_ICE.defaultBlockState(), 2);
                        }
                    }
                }

                return true;
            }
        }
    }
}

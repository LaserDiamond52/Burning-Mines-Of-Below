package net.laserdiamond.burningminesofbelow.worldgen.feature;

import com.mojang.serialization.Codec;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CocytusIceSpikeFeature extends Feature<NoneFeatureConfiguration> {

    public CocytusIceSpikeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        BlockPos blockPos = featurePlaceContext.origin();
        RandomSource randomSource = featurePlaceContext.random();

        WorldGenLevel worldGenLevel = featurePlaceContext.level();

        if (!worldGenLevel.getBlockState(blockPos).is(BMOBTags.Blocks.COCYTUS_ICE_SPIKE_BASE_BLOCK))
        {
            return false;
        } else {
            blockPos = blockPos.above(randomSource.nextInt(4));
            int random1 = randomSource.nextInt(4) + 7;
            int random2 = random1 / 4 + randomSource.nextInt(2);
            if (random2 > 1 && randomSource.nextInt(60) == 0) {
                blockPos = blockPos.above(10 + randomSource.nextInt(30));
            }

            int i;
            int j;
            for(i = 0; i < random1; i++) {
                float val1 = (1.0F - (float) i / (float) random1) * (float) random2;
                j = Mth.ceil(val1);

                for(int k = -j; k <= j; k++) {
                    float absVal1 = (float) Mth.abs(k) - 0.25F;

                    for(int l = -j; l <= j; l++) {
                        float absVal2 = (float) Mth.abs(l) - 0.25F;
                        if ((k == 0 && l == 0 || !(absVal1 * absVal1 + absVal2 * absVal2 > val1 * val1)) && (k != -j && k != j && l != -j && l != j || !(randomSource.nextFloat() > 0.75F))) {
                            BlockState blockState = worldGenLevel.getBlockState(blockPos.offset(k, i, l));
                            if (blockState.isAir() || isDirt(blockState) || blockState.is(BMOBTags.Blocks.COCYTUS_ICE_SPIKE_BASE_BLOCK) || blockState.is(Blocks.ICE)) {
                                this.setBlock(worldGenLevel, blockPos.offset(k, i, l), Blocks.PACKED_ICE.defaultBlockState());
                            }

                            if (i != 0 && j > 1)
                            {
                                blockState = worldGenLevel.getBlockState(blockPos.offset(k, -i, l));
                                if (blockState.isAir() || isDirt(blockState) || blockState.is(BMOBTags.Blocks.COCYTUS_ICE_SPIKE_BASE_BLOCK) || blockState.is(Blocks.ICE))
                                {
                                    this.setBlock(worldGenLevel, blockPos.offset(k, -i, l), Blocks.PACKED_ICE.defaultBlockState());
                                }
                            }
                        }
                    }
                }
            }

            i = random2 - 1;
            if (i < 0) {
                i = 0;
            } else if (i > 1) {
                i = 1;
            }

            for(int m = -i; m <= i; ++m) {
                for(j = -i; j <= i; ++j) {
                    BlockPos blockPos2 = blockPos.offset(m, -1, j);
                    int val2 = 50;
                    if (Math.abs(m) == 1 && Math.abs(j) == 1) {
                        val2 = randomSource.nextInt(5);
                    }

                    while(blockPos2.getY() > 50) {
                        BlockState $$19 = worldGenLevel.getBlockState(blockPos2);
                        if (!$$19.isAir() && !isDirt($$19) && !$$19.is(BMOBTags.Blocks.COCYTUS_ICE_SPIKE_BASE_BLOCK) && !$$19.is(Blocks.ICE) && !$$19.is(Blocks.PACKED_ICE)) {
                            break;
                        }

                        this.setBlock(worldGenLevel, blockPos2, Blocks.PACKED_ICE.defaultBlockState());
                        blockPos2 = blockPos2.below();
                        --val2;
                        if (val2 <= 0) {
                            blockPos2 = blockPos2.below(randomSource.nextInt(5) + 1);
                            val2 = randomSource.nextInt(5);
                        }
                    }
                }
            }

            return true;
        }
    }
}

package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeBlockEntityLevel1 extends AbstractForgeBlockEntity {

    public ForgeBlockEntityLevel1(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_1.get(), blockPos, blockState, 1);
    }
}

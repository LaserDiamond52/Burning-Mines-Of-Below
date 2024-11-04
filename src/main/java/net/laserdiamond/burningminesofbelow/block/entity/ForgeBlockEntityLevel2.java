package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeBlockEntityLevel2 extends AbstractForgeBlockEntity {
    public ForgeBlockEntityLevel2(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_2.get(), blockPos, blockState, 2);
    }
}

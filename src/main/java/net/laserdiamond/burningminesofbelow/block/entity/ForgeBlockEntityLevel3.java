package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeBlockEntityLevel3 extends AbstractForgeBlockEntity {
    public ForgeBlockEntityLevel3(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_3.get() ,blockPos, blockState, 3);
    }
}

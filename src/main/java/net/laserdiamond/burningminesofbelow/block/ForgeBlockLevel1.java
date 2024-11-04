package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntityLevel1;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ForgeBlockLevel1 extends AbstractForgeBlock<ForgeBlockEntityLevel1> {
    protected ForgeBlockLevel1(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties, tags);
    }

    @Override
    protected BlockEntityType<ForgeBlockEntityLevel1> forgeBlockEntity() {
        return BMOBBlockEntities.FORGE_LEVEL_1.get();
    }

    @Override
    protected ForgeBlockEntityLevel1 newForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntityLevel1(blockPos, blockState);
    }


}

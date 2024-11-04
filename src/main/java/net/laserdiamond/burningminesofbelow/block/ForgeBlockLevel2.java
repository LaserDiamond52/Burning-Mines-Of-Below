package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntityLevel2;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ForgeBlockLevel2 extends AbstractForgeBlock<ForgeBlockEntityLevel2> {

    protected ForgeBlockLevel2(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties, tags);
    }

    @Override
    protected BlockEntityType<ForgeBlockEntityLevel2> forgeBlockEntity() {
        return BMOBBlockEntities.FORGE_LEVEL_2.get();
    }

    @Override
    protected ForgeBlockEntityLevel2 newForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntityLevel2(blockPos, blockState);
    }


}

package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntityLevel3;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ForgeBlockLevel3 extends AbstractForgeBlock<ForgeBlockEntityLevel3> {

    protected ForgeBlockLevel3(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties, tags);
    }

    @Override
    protected BlockEntityType<ForgeBlockEntityLevel3> forgeBlockEntity() {
        return BMOBBlockEntities.FORGE_LEVEL_3.get();
    }

    @Override
    protected ForgeBlockEntityLevel3 newForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntityLevel3(blockPos, blockState);
    }


}

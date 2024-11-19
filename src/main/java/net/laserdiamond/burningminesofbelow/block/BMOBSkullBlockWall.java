package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BMOBSkullBlockWall extends WallSkullBlock implements Taggable<Block> {

    protected final List<TagKey<Block>> tags;

    public BMOBSkullBlockWall(SkullBlock.Type pType, Properties pProperties, List<TagKey<Block>> tags) {
        super(pType, pProperties);
        this.tags = tags;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BMOBSkullBlockEntity(pPos, pState);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }
}
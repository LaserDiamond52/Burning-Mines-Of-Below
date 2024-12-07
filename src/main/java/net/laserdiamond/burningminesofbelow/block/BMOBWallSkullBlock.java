package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * Responsibilities:
 * <li>Create and manage the wall block variant of the {@link BMOBSkullBlock}</li>
 */
public class BMOBWallSkullBlock extends WallSkullBlock implements Taggable<Block> {

    /**
     * The {@link List} of tags to apply to the block
     */
    protected final List<TagKey<Block>> tags;

    /**
     * Creates a new {@link BMOBSkullBlock}
     * @param pType The {@link net.minecraft.world.level.block.SkullBlock.Type} to make
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block.
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public BMOBWallSkullBlock(SkullBlock.Type pType, Properties pProperties, List<TagKey<Block>> tags) {
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

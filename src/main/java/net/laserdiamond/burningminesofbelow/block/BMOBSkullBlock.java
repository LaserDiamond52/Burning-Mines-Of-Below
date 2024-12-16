package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and manage a skull block for use with this mod</li>
 * <li>A {@link BMOBSkullBlock} is-a {@link SkullBlock}</li>
 * <li>A {@link BMOBSkullBlock} is-a {@link Taggable}</li>
 * @author Allen Malo
 */
public class BMOBSkullBlock extends SkullBlock implements Taggable<Block> {

    /**
     * {@link List} of tags to apply to the block
     */
    protected final List<TagKey<Block>> tags; // BMOBSkullBlock has-a List

    /**
     * Creates a new {@link BMOBSkullBlock}
     * @param pType The {@link SkullBlock.Type} to make
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public BMOBSkullBlock(Type pType, Properties pProperties, List<TagKey<Block>> tags) {
        super(pType, pProperties);
        this.tags = tags;
    }

    /**
     * Creates a new instance of the {@link BlockEntity} for the block
     * @param pPos The {@link BlockPos} of the block
     * @param pState The {@link BlockState} of the block
     * @return A new instance of this block's {@link BlockEntity}
     */
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BMOBSkullBlockEntity(pPos, pState);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    /**
     * The types of skull blocks in this mod
     */
    public enum Types implements SkullBlock.Type
    {
        FROZEN_WITHER_SKULL,
        BLAZE_SKULL
    }
}

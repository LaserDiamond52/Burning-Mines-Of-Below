package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.AbstractForgeBlockEntity;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for creating Forge blocks
 * @param <F> The Forge block entity
 * @see AbstractForgeBlockEntity
 */
public abstract class AbstractForgeBlock<F extends AbstractForgeBlockEntity> extends BaseEntityBlock implements Taggable<Block> {

    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);
    private final List<TagKey<Block>> tags;

    protected AbstractForgeBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties);
        this.tags = new ArrayList<>(tags);
        this.tags.add(BMOBTags.Blocks.FORGE_BLOCK);
    }

    /**
     * The {@link BlockEntityType} of the {@link AbstractForgeBlockEntity}
     * @return A {@link BlockEntityType} instance of the Forge block entity
     */
    protected abstract BlockEntityType<F> forgeBlockEntity();

    /**
     * Returns a new object instance of the Forge block entity
     * @param blockPos The position of the block
     * @param blockState The {@link BlockState} of the block
     * @return A new object instance of the Forge block entity
     */
    protected abstract F newForgeBlockEntity(BlockPos blockPos, BlockState blockState);

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return this.newForgeBlockEntity(blockPos, blockState);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {

        if (pState.getBlock() != pNewState.getBlock())
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof AbstractForgeBlockEntity forgeBlockEntity)
            {
                forgeBlockEntity.drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) throws IllegalStateException
    {
        if (!pLevel.isClientSide)
        {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof AbstractForgeBlockEntity forgeBlockEntity)
            {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), forgeBlockEntity, pPos);
            } else
            {
                throw new IllegalStateException("Could not use block entity: Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide)
        {
            return null;
        }
        return createTickerHelper(pBlockEntityType, this.forgeBlockEntity(), ((level, blockPos, blockState, blockEntity) -> blockEntity.tick(level, blockPos, blockState)));
    }
}

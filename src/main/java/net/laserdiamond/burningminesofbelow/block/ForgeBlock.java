package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntity;
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

public class ForgeBlock extends BaseEntityBlock implements Taggable<Block> {

    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);
    private final List<TagKey<Block>> tags;
    private final int forgeLevel;

    protected ForgeBlock(Properties pProperties, List<TagKey<Block>> tags, int forgeLevel) {
        super(pProperties);
        this.tags = new ArrayList<>(tags);
        this.forgeLevel = forgeLevel;
        this.tags.add(BMOBTags.Blocks.FORGE_BLOCK);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntity(blockPos, blockState, this.forgeLevel);
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
            if (be instanceof ForgeBlockEntity forgeBlockEntity)
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
            if (be instanceof ForgeBlockEntity forgeBlockEntity)
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
        return createTickerHelper(pBlockEntityType, BMOBBlockEntities.FORGE.get(), ((level, blockPos, blockState, blockEntity) -> blockEntity.tick(level, blockPos, blockState)));
    }

    public int getForgeLevel() {
        return forgeLevel;
    }
}

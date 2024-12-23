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
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Act as the root class for creating Forge blocks</li>
 * <li>Contains the bounding box of the Forge block</li>
 * <li>Connects functionality of the {@link AbstractForgeBlockEntity} to this block</li>
 * <li>An {@link AbstractForgeBlock} is-a {@link BaseEntityBlock}</li>
 * <li>An {@link AbstractForgeBlock} is-a {@link Taggable}</li>
 * @author Allen Malo
 * @see AbstractForgeBlockEntity
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public abstract class AbstractForgeBlock<F extends AbstractForgeBlockEntity> extends BaseEntityBlock implements Taggable<Block> {

    /**
     * The bounding box dimensions for the block
     */
    public static final VoxelShape SHAPE = Block.box(0,0,0,16,16,16);

    /**
     * {@link List} of tags that can be applied to the block
     */
    private final List<TagKey<Block>> tags; // AbstractForgeBlock has-a List

    /**
     * Creates a new {@link AbstractForgeBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
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

    /**
     * Returns a new object instance of the Forge block entity
     * @param blockPos The position of the block
     * @param blockState The {@link BlockState} of the block
     * @return A new object instance of the Forge block entity
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return this.newForgeBlockEntity(blockPos, blockState);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    /**
     * Gets the bounding box shape of the block
     * @param pState The {@link BlockState} of the block
     * @param pLevel The {@link Level} the block is on
     * @param pPos The {@link BlockPos} of the block
     * @param pContext The {@link CollisionContext}
     * @return The {@link VoxelShape} that determines the bounding box shape for the block
     */
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    /**
     * Drops any items stored inside the block when broken
     * @param pState The {@link BlockState} of the block
     * @param pLevel The {@link Level} of the block
     * @param pPos The {@link BlockPos} of the block
     * @param pNewState The updated {@link BlockState} of the block
     * @param pMovedByPiston Whether the block was moved by a piston
     */
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {

        if (pState.getBlock() != pNewState.getBlock()) // Are the current and new block states the same (was it destroyed?)
        {
            BlockEntity be = pLevel.getBlockEntity(pPos); // Get the block entity at the current position
            if (be instanceof AbstractForgeBlockEntity forgeBlockEntity) // Is the block entity an AbstractForgeBlockEntity
            {
                forgeBlockEntity.drops(); // Drop the items inside the block
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston); // Run the superclass method
    }

    /**
     * Displays the Forge GUI when a player interacts with the block by right-clicking
     * @param pState The current {@link BlockState} of the block
     * @param pLevel The {@link Level} the block is on
     * @param pPos The {@link BlockPos} of the block
     * @param pPlayer The {@link Player} interacting with the block
     * @param pHand The {@link InteractionHand} of the player interacting with the block
     * @param pHit The {@link BlockHitResult} of the interaction
     * @return Success if on the client. Returns Consume on the server.
     * @see InteractionResult
     * @throws IllegalStateException If the block entity of the block is not a subclass of {@link AbstractForgeBlockEntity}
     */
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) throws IllegalStateException
    {
        if (!pLevel.isClientSide) // Run this only on the server
        {
            BlockEntity be = pLevel.getBlockEntity(pPos); // Get the block entity at the current position
            if (be instanceof AbstractForgeBlockEntity forgeBlockEntity) // Ensure it is an instance of the AbstractForgeBlockEntity. Cannot use Generic Type
            {
                // Our AbstractForgeBlockEntity is-a MenuProvider, and contains the method for creating the menu we want to open
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), forgeBlockEntity, pPos); // Open the Screen
            } else
            {
                // Block Entity isn't an AbstractForgeBlockEntity. Can't get the container provider, so we throw an exception to indicate something is wrong.
                throw new IllegalStateException("Could not use block entity: Container provider is missing!");
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide); // Return the InteractionResult of the event
    }

    /**
     * Runs the ticker for the block. Responsible for the Forge block's ability to run its crafting process
     * @param pLevel The {@link Level} of the block
     * @param pState The {@link BlockState} of the block
     * @param pBlockEntityType The {@link BlockEntityType} of the {@link BlockEntity}
     * @return The {@link BlockEntityTicker} for the block. Returns null if on the client.
     * @param <T> The {@link BlockEntity} type
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide)
        {
            return null; // On the client. Return null
        }
        // The ticker should only be running on the server
        return createTickerHelper(pBlockEntityType, this.forgeBlockEntity(), ((level, blockPos, blockState, blockEntity) -> blockEntity.tick(level, blockPos, blockState)));
    }
}

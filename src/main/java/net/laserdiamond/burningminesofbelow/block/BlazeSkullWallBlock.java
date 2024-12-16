package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and manage the wall block variant of the {@link BlazeSkullBlock}</li>
 * <li>A {@link BlazeSkullWallBlock} is-a {@link BMOBWallSkullBlock}</li>
 * @author Allen Malo
 */
public class BlazeSkullWallBlock extends BMOBWallSkullBlock {

    /**
     * Creates a new {@link BlazeSkullWallBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} to give the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public BlazeSkullWallBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(BMOBSkullBlock.Types.BLAZE_SKULL, pProperties, tags);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        BMOBBlocks.BLAZE_SKULL.get().setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }
}

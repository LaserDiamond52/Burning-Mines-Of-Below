package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlazeSkullWallBlock extends BMOBSkullBlockWall {

    public BlazeSkullWallBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(BMOBSkullBlock.Types.BLAZE_SKULL, pProperties, tags);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        BMOBBlocks.BLAZE_SKULL.get().setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }
}

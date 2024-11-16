package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// TODO: Implement SummoningBlock
public class FrozenWitherSkullBlock extends BMOBSkullBlock {

    public FrozenWitherSkullBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(Types.FROZEN_WITHER_SKULL, pProperties, tags);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof SkullBlockEntity skullBlockEntity)
        {
            // TODO: SummoningBlock#checkSpawn
        }
    }
}

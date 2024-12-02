package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class BlazeSkullBlock extends BMOBSkullBlock implements SummoningBlock<KingInferniusEntity, BMOBSkullBlockEntity> {

    private static BlockPattern kingInferniusSummonPattern;
    private static BlockPattern kingInferniusBasePattern;

    public BlazeSkullBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(Types.BLAZE_SKULL, pProperties, tags);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof BMOBSkullBlockEntity skullBlockEntity)
        {
            this.checkSpawn(pLevel, pPos, skullBlockEntity);
        }
    }

    @Override
    public TagKey<Block> summoningBlock() {
        return BMOBTags.Blocks.KING_INFERNIUS_SUMMON_BLOCK;
    }

    @Override
    public TagKey<Block> baseBlocks() {
        return BMOBTags.Blocks.KING_INFERNIUS_BASE_BLOCK;
    }

    @Override
    public TagKey<Item> summoningItem() {
        return BMOBTags.Items.KING_INFERNIUS_SUMMON_ITEM;
    }

    @Override
    public KingInferniusEntity entityToSpawn(Level level) {
        return BMOBEntities.KING_INFERNIUS.get().create(level);
    }

    @Override
    public BlockPattern getOrCreateEntityFull() {
        if (kingInferniusSummonPattern == null)
        {
            kingInferniusSummonPattern = BlockPatternBuilder.start().aisle("AHA", "BBB", "ABA")
                    .where('A', blockInWorld -> blockInWorld.getState().isAir())
                    .where('H', blockInWorld -> blockInWorld.getState().is(this.summoningBlock()))
                    .where('B', blockInWorld -> blockInWorld.getState().is(this.baseBlocks())).build();
        }

        return kingInferniusSummonPattern;
    }

    @Override
    public BlockPattern getOrCreateEntityBase() {
        if (kingInferniusBasePattern == null)
        {
            kingInferniusBasePattern = BlockPatternBuilder.start().aisle("   ", "BBB", "ABA")
                    .where('A', blockInWorld -> blockInWorld.getState().isAir())
                    .where('B', blockInWorld -> blockInWorld.getState().is(this.baseBlocks()))
                    .build();
        }

        return kingInferniusBasePattern;
    }
}

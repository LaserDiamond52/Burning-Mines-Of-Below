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

/**
 * Responsibilities:
 * <li>Create and manage a Blaze Skull block</li>
 * <li>Allow this block to summon a {@link KingInferniusEntity} when placed on the appropriate block pattern</li>
 */
public class BlazeSkullBlock extends BMOBSkullBlock implements SummoningBlock<KingInferniusEntity, BMOBSkullBlockEntity> {

    /**
     * The {@link BlockPattern} to create to summon the {@link KingInferniusEntity}
     */
    private static BlockPattern kingInferniusSummonPattern;

    /**
     * The base {@link BlockPattern} for summoning the {@link KingInferniusEntity}
     */
    private static BlockPattern kingInferniusBasePattern;

    /**
     * Creates a new {@link BlazeSkullBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
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

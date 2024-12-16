package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
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
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and manage a Frozen Wither Skull block</li>
 * <li>Allow this block to summon a {@link FreezingReaperEntity} when placed on the appropriate block pattern</li>
 * <li>A {@link FrozenWitherSkullBlock} is-a {@link BMOBSkullBlock}</li>
 * <li>A {@link FrozenWitherSkullBlock} is-a {@link SummoningBlock}</li>
 * @author Allen Malo
 * @see net.minecraft.world.level.block.WitherSkullBlock
 */
public class FrozenWitherSkullBlock extends BMOBSkullBlock implements SummoningBlock<FreezingReaperEntity, BMOBSkullBlockEntity> {

    /**
     * The {@link BlockPattern} to create to summon the {@link FreezingReaperEntity}
     */
    private BlockPattern freezingReaperSummonPattern; // FrozenWitherSkullBlock has-a BlockPattern (one-to-many)

    /**
     * The base {@link BlockPattern} for summoning the {@link FreezingReaperEntity}
     */
    private BlockPattern freezingReaperBasePattern; // FrozenWitherSkullBlock has-a BlockPattern (one-to-many)

    /**
     * Creates a new {@link FrozenWitherSkullBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public FrozenWitherSkullBlock(Properties pProperties, List<TagKey<Block>> tags)
    {
        super(Types.FROZEN_WITHER_SKULL, pProperties, tags);
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
        return BMOBTags.Blocks.FREEZING_REAPER_SUMMON_BLOCK;
    }

    @Override
    public TagKey<Item> summoningItem() {
        return BMOBTags.Items.FREEZING_REAPER_SUMMON_ITEM;
    }

    @Override
    public TagKey<Block> baseBlocks() {
        return BMOBTags.Blocks.FREEZING_REAPER_BASE_BLOCK;
    }

    @Override
    public FreezingReaperEntity entityToSpawn(Level level) {
        return BMOBEntities.FREEZING_REAPER.get().create(level);
    }

    @Override
    public BlockPattern getOrCreateEntityFull() {
        if (freezingReaperSummonPattern == null)
        {
            freezingReaperSummonPattern = BlockPatternBuilder.start().aisle("AHA", "BBB", "ABA")
                    .where('A', blockInWorld -> blockInWorld.getState().isAir())
                    .where('H', blockInWorld -> blockInWorld.getState().is(this.summoningBlock()))
                    .where('B', blockInWorld -> blockInWorld.getState().is(this.baseBlocks())).build();
        }
        return freezingReaperSummonPattern;
    }

    @Override
    public BlockPattern getOrCreateEntityBase() {
        if (freezingReaperBasePattern == null)
        {
            freezingReaperBasePattern = BlockPatternBuilder.start().aisle("   ", "BBB", "ABA")
                    .where('A', blockInWorld -> blockInWorld.getState().isAir())
                    .where('B', blockInWorld -> blockInWorld.getState().is(this.baseBlocks()))
                    .build();
        }
        return freezingReaperBasePattern;
    }
}

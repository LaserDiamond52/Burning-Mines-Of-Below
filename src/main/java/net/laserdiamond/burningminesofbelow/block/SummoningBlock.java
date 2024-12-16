package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Interface used for blocks that trigger an entity to spawn</li>
 * @author Allen Malo
 * @param <LE> The {@link LivingEntity} type that will be spawned
 * @param <BE> The {@link BlockEntity} type of the block being used to summon the entity
 * @see net.minecraft.world.level.block.WitherSkullBlock
 */
public interface SummoningBlock<LE extends LivingEntity, BE extends BlockEntity> {

    /**
     * The blocks that can be used to trigger the entity to spawn
     * @return The block tag for the blocks that can be used to summon the entity
     */
    TagKey<Block> summoningBlock();

    /**
     * The item tag of the block that can be used to trigger the entity to spawn
     * @return The item tag of the block that cna be used to summon the entity.
     */
    TagKey<Item> summoningItem();

    /**
     * The base blocks that can be used to help spawn the entity
     * @return The base blocks that can be used to help spawn the entity
     */
    TagKey<Block> baseBlocks();

    /**
     * The entity to spawn. {@link net.minecraft.world.entity.EntityType#create(Level)} needs to be invoked here
     * @param level The {@link Level} to spawn the entity in
     * @return The entity to spawn when all the blocks are placed
     */
    LE entityToSpawn(Level level);

    /**
     * Spawns the entity at the block position in the level
     * @param level The level to spawn the entity in
     * @param pos The position to spawn the entity at
     * @param blockEntity The {@link BlockEntity} of the block being used to spawn
     */
    default void checkSpawn(Level level, BlockPos pos, BE blockEntity)
    {
        if (!level.isClientSide)
        {
            BlockState blockEntityState = blockEntity.getBlockState();
            boolean isSpawnBlock = blockEntityState.is(summoningBlock());
            if (isSpawnBlock && pos.getY() >= level.getMinBuildHeight() && level.getDifficulty() != Difficulty.PEACEFUL)
            {
                BlockPattern.BlockPatternMatch blockPatternMatch = getOrCreateEntityFull().find(level, pos);
                if (blockPatternMatch != null)
                {
                    LE entity = entityToSpawn(level);
                    if (entity != null)
                    {
                        CarvedPumpkinBlock.clearPatternBlocks(level, blockPatternMatch);
                        BlockPos spawnPos = blockPatternMatch.getBlock(1, 2, 0).getPos();
                        entity.moveTo(spawnPos.getX() + 0.5, spawnPos.getY() + 0.55, spawnPos.getZ() + 0.5, blockPatternMatch.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F, 0.0F);
                        entity.yBodyRot = blockPatternMatch.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;

                        level.addFreshEntity(entity);
                        CarvedPumpkinBlock.updatePatternBlocks(level, blockPatternMatch);
                    }
                }
            }
        }
    }

    /**
     * Validates that the mob can be spawned at the location
     * @param level The level to spawn the entity in
     * @param pos The position to spawn the entity at
     * @param stack The item stack being used to spawn the entity
     * @return True if the entity can spawn, false otherwise
     */
    @Deprecated
    default boolean canSpawnMob(Level level, BlockPos pos, ItemStack stack)
    {
        if (stack.is(summoningItem()) && pos.getY() >= level.getMinBuildHeight() + 2 && level.getDifficulty() != Difficulty.PEACEFUL && !level.isClientSide)
        {
            return getOrCreateEntityBase().find(level, pos) != null;
        } else
        {
            return false;
        }
    }

    /**
     * The block pattern to use to create the entity
     * @return The block pattern to use to create the entity
     */
    BlockPattern getOrCreateEntityFull();

    /**
     * The block pattern for the base of the block pattern for summoning the entity
     * @return The base block pattern for summoning the entity
     */
    BlockPattern getOrCreateEntityBase();

}

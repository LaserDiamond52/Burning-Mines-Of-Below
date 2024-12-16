package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntityLevel1;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create the Level 1 variant of the Forge Block</li>
 * <li>A {@link ForgeBlockLevel1} is-a {@link AbstractForgeBlock}</li>
 * @author Allen Malo
 */
public class ForgeBlockLevel1 extends AbstractForgeBlock<ForgeBlockEntityLevel1> {

    /**
     * Creates a new Level 1 variant of the Forge Block
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    protected ForgeBlockLevel1(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties, tags);
    }

    @Override
    protected BlockEntityType<ForgeBlockEntityLevel1> forgeBlockEntity() {
        return BMOBBlockEntities.FORGE_LEVEL_1.get(); // Return the block entity
    }

    @Override
    protected ForgeBlockEntityLevel1 newForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntityLevel1(blockPos, blockState); // Return a new instance of the respective block entity
    }


}

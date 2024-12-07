package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntityLevel2;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

/**
 * Responsibilities:
 * <li>Create the Level 2 variant of the Forge Block</li>
 */
public class ForgeBlockLevel2 extends AbstractForgeBlock<ForgeBlockEntityLevel2> {

    /**
     * Creates a new Level 2 variant of the Forge Block
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    protected ForgeBlockLevel2(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties, tags);
    }

    @Override
    protected BlockEntityType<ForgeBlockEntityLevel2> forgeBlockEntity() {
        return BMOBBlockEntities.FORGE_LEVEL_2.get(); // Return the block entity
    }

    @Override
    protected ForgeBlockEntityLevel2 newForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ForgeBlockEntityLevel2(blockPos, blockState); // Return a new instance of the respective block entity
    }


}

package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as the Level 1 variant of the Forge</li>
 * <li>A {@link ForgeBlockEntityLevel1} is-a {@link AbstractForgeBlockEntity}</li>
 * @author Allen Malo
 */
public class ForgeBlockEntityLevel1 extends AbstractForgeBlockEntity {

    /**
     * Creates a new {@link ForgeBlockEntityLevel1}
     * @param blockPos the {@link BlockPos} to create the new {@link ForgeBlockEntityLevel1}
     * @param blockState The {@link BlockState} to assign to the {@link ForgeBlockEntityLevel1}
     */
    public ForgeBlockEntityLevel1(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_1.get(), blockPos, blockState, 1);
    }
}

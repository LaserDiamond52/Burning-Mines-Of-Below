package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as the Level 2 variant of the Forge</li>
 * <li>A {@link ForgeBlockEntityLevel2} is-a {@link AbstractForgeBlockEntity}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class ForgeBlockEntityLevel2 extends AbstractForgeBlockEntity {

    /**
     * Creates a new {@link ForgeBlockEntityLevel2}
     * @param blockPos the {@link BlockPos} to create the new {@link ForgeBlockEntityLevel2}
     * @param blockState The {@link BlockState} to assign to the {@link ForgeBlockEntityLevel2}
     */
    public ForgeBlockEntityLevel2(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_2.get(), blockPos, blockState, 2);
    }
}

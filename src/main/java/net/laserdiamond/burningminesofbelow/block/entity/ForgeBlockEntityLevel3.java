package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as the level 3 variant of the Forge</li>
 * <li>A {@link ForgeBlockEntityLevel3} is-a {@link AbstractForgeBlockEntity}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class ForgeBlockEntityLevel3 extends AbstractForgeBlockEntity {

    /**
     * Creates a new {@link ForgeBlockEntityLevel3}
     * @param blockPos the {@link BlockPos} to create the new {@link ForgeBlockEntityLevel3}
     * @param blockState The {@link BlockState} to assign to the {@link ForgeBlockEntityLevel3}
     */
    public ForgeBlockEntityLevel3(BlockPos blockPos, BlockState blockState) {
        super(BMOBBlockEntities.FORGE_LEVEL_3.get() ,blockPos, blockState, 3);
    }
}

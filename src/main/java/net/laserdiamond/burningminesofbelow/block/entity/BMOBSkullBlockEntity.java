package net.laserdiamond.burningminesofbelow.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as the base {@link SkullBlockEntity} of this mod</li>
 * <li>A {@link BMOBSkullBlockEntity} is-a {@link SkullBlockEntity}</li>
 * @author Allen Malo
 */
public class BMOBSkullBlockEntity extends SkullBlockEntity {

    /**
     * Creates a new {@link BMOBSkullBlockEntity}
     * @param pPos The {@link BlockPos} to create the {@link BMOBSkullBlockEntity}
     * @param pBlockState The {@link BlockState} to set to the {@link BMOBSkullBlockEntity}
     */
    public BMOBSkullBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    /**
     * Gets the {@link BlockEntityType} of the {@link BMOBSkullBlockEntity}
     * @return {@link BMOBBlockEntities#SKULL_BLOCK_ENTITY}
     */
    @Override
    public BlockEntityType<?> getType() {
        return BMOBBlockEntities.SKULL_BLOCK_ENTITY.get();
    }
}

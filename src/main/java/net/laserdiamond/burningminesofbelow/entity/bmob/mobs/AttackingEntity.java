package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.EntityInterfaceBase;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

/**
 * Interface used to help set up animations for entities with attacks
 */
public interface AttackingEntity<E extends Entity> extends EntityInterfaceBase<E> {

    /**
     * Sets the {@link Entity} to the specified attacking state
     * @param entityDataAccessor The {@link EntityDataAccessor} of the attack of the {@link Entity}
     * @param attacking The state of attacking. Set to true if the entity is attacking, false otherwise
     */
    default void setAttacking(EntityDataAccessor<Boolean> entityDataAccessor, boolean attacking)
    {
        this.entity().entityData.set(entityDataAccessor, attacking);
    }

    /**
     * Determines if the {@link Entity} is attacking
     * @param entityDataAccessor The {@link EntityDataAccessor} of the attack of the {@link Entity}
     * @return True if the {@link EntityDataAccessor} of the attack returns true, false otherwise
     */
    default boolean isAttacking(EntityDataAccessor<Boolean> entityDataAccessor)
    {
        return this.entity().entityData.get(entityDataAccessor);
    }
}

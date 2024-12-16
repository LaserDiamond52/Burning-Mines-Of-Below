package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.EntityInterfaceBase;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help start and stop attack animations for entities within their attack goals, and determine if the entity is performing said attack</li>
 * <li>This class should be inherited by the entity's class, as the {@link EntityDataAccessor} is needed in the abstract attack goals classes</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
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

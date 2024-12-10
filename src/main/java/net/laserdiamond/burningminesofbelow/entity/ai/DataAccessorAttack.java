package net.laserdiamond.burningminesofbelow.entity.ai;

import net.minecraft.network.syncher.EntityDataAccessor;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used for attack goals with entities that have an {@link EntityDataAccessor} for controlling when an animation starts</li>
 * @author Allen Malo
 */
public interface DataAccessorAttack {

    /**
     * The {@link EntityDataAccessor} for is the entity is performing said attack
     * @return The {@link EntityDataAccessor} that determines if the entity is performing said attack
     */
    EntityDataAccessor<Boolean> attackDataAccessor();
}

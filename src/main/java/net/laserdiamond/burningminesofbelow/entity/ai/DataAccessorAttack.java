package net.laserdiamond.burningminesofbelow.entity.ai;

import net.minecraft.network.syncher.EntityDataAccessor;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used for attack goals with entities that have an {@link EntityDataAccessor} for controlling when an animation starts</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public interface DataAccessorAttack {

    /**
     * The {@link EntityDataAccessor} for is the entity is performing said attack
     * @return The {@link EntityDataAccessor} that determines if the entity is performing said attack
     */
    EntityDataAccessor<Boolean> attackDataAccessor();
}

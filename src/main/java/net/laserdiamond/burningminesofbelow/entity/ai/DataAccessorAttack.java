package net.laserdiamond.burningminesofbelow.entity.ai;

import net.minecraft.network.syncher.EntityDataAccessor;

public interface DataAccessorAttack {

    /**
     * The {@link EntityDataAccessor} for is the entity is performing said attack
     * @return The {@link EntityDataAccessor} that determines if the entity is performing said attack
     */
    EntityDataAccessor<Boolean> attackDataAccessor();
}

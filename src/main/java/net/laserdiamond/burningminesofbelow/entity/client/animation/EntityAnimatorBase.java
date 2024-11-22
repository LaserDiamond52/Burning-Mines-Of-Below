package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

/**
 * Base interface for helping with animation interfaces for mobs
 * @param <E> The
 */
public interface EntityAnimatorBase<E extends LivingEntity> {

    E entity();
}

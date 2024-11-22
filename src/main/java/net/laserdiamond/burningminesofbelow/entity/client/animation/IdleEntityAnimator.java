package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

// TODO: Needs rework
public interface IdleEntityAnimator<E extends LivingEntity> extends EntityAnimatorBase<E> {

    RandomSource random();

    IdleAnimation idleAnimation();

    default void setUpIdleAnimation()
    {
        if (this.idleAnimation().getTimeout() <= 0)
        {
            int timeout = random().nextInt(idleAnimation().getTimeout() / 2) + idleAnimation().getDuration();
            this.idleAnimation().setTimeout(timeout);
        } else
        {
            this.idleAnimation().decrementTimeout();
        }
    }
}

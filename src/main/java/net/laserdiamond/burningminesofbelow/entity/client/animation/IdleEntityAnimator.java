package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public interface IdleEntityAnimator<E extends LivingEntity> extends EntityAnimatorBase<E> {

    RandomSource random();

    IdleAnimation idleAnimation();

    default void setUpIdleAnimation()
    {
        if (this.idleAnimation().getTimeout() <= 0)
        {
            this.idleAnimation().setTimeout(this.random().nextInt(this.idleAnimation().getDuration() / 2) + this.idleAnimation().getDuration());
            this.idleAnimation().getAnimationState().start(this.entity().tickCount);
        } else
        {
            this.idleAnimation().decrementTimeout();
        }
    }
}

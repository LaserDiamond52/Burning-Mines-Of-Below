package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.laserdiamond.burningminesofbelow.entity.EntityInterfaceBase;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

// TODO: Needs rework
@Deprecated
public interface IdleEntityAnimator<LE extends LivingEntity> extends EntityInterfaceBase<LE> {

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

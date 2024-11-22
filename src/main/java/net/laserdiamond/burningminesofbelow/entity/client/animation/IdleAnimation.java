package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.minecraft.world.entity.AnimationState;

public final class IdleAnimation {

    private final AnimationState animationState;
    private int timeout;
    private final int duration;

    public IdleAnimation(AnimationState animationState, int timeout, int duration) {
        this.animationState = animationState;
        this.timeout = timeout;
        this.duration = duration;
    }

    public AnimationState getAnimationState() {
        return animationState;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getDuration() {
        return duration;
    }

    public int decrementTimeout()
    {
        return this.timeout--;
    }
}

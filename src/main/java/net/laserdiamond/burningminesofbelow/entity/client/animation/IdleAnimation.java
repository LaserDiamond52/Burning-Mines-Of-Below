package net.laserdiamond.burningminesofbelow.entity.client.animation;

import net.minecraft.world.entity.AnimationState;

public final class IdleAnimation {

    private final AnimationState animationState;
    private int timeout;
    private final int duration;

    /**
     * Used to help link animations to an entity that should have a timeout phase
     * @param animationState The {@link AnimationState} for the entity's animation
     * @param timeout The entity animation state time out variable
     * @param duration The duration of the animation
     */
    public IdleAnimation(AnimationState animationState, int timeout, int duration) {
        this.animationState = animationState;
        this.timeout = timeout;
        this.duration = duration;
    }

    /**
     * Used to help link animations to an entity that should play continuously uninterrupted
     * @param animationState The {@link AnimationState} for the entity's animation
     */
    public IdleAnimation(AnimationState animationState)
    {
        this.animationState = animationState;
        this.timeout = 0;
        this.duration = 0;
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

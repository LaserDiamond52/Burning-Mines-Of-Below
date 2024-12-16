package net.laserdiamond.burningminesofbelow.entity.ai;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used by attack goal classes to determine when an attack should start/stop, and when an animation should start/stop</li>
 * <li>A {@link DelayedAnimatedAttack} is-a {@link DataAccessorAttack}</li>
 * @author Allen Malo
 */
public interface DelayedAnimatedAttack extends DataAccessorAttack {

    /**
     * The delay of the attack relative to the start of the animation
     * @return The delay of the attack relative to the start of the animation in ticks
     */
    int attackDelay();

    /**
     * The amount of ticks remaining until the next attack
     * @return The ticks remaining until the next attack
     */
    int ticksUntilNextAttack();

    /**
     * Abstract method used for resetting the attack animation timeout
     */
    void resetAttackAnimationTimeOut();

    /**
     * Determines if it is time to start the attack animation
     * @return True if the ticks until the next attack are less than or equal to the attack delay, false otherwise
     */
    boolean isTimeToStartAttackAnimation();

}

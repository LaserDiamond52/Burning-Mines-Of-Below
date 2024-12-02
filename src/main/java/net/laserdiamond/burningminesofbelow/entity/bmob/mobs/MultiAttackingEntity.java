package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.minecraft.world.entity.Entity;

/**
 * Interface used to help set up and manage entities with multiple attack animations.
 * The entity class in for the mob must have an interface that is a subclass of the {@link AttackSetUp} interface.
 * This interface also inherits the {@link AttackingEntity} interface.
 * @param <AE> The {@link AttackSetUp} interface. This class must also be an {@link Enum}
 * @param <E> The {@link Entity} class of the mob. Must also be a subclass of the {@link AttackingEntity} interface
 */
public interface MultiAttackingEntity<AE extends Enum<?> & AttackSetUp, E extends Entity & AttackingEntity<E>> extends AttackingEntity<E> {

    /**
     * The attack timeouts for each attack
     * @return An array that contains each attack's timeout
     */
    int[] attackTimeouts();

    /**
     * Gets the attack timeout for the specified attack
     * @param attack The attack to get the timeout for
     * @return An integer specifying the attack's timeout in ticks. Returns 0 if an {@link ArrayIndexOutOfBoundsException} was thrown.
     */
    default int getAttackTimeout(AE attack)
    {
        try
        {
            return this.attackTimeouts()[attack.ordinal()];
        } catch (ArrayIndexOutOfBoundsException e)
        {
            BurningMinesOfBelow.LOGGER.info("Attempted to get attack timeout for King Infernius Entity, but index was out of bounds!");
            BurningMinesOfBelow.LOGGER.info("Index received: " + attack.ordinal());
            BurningMinesOfBelow.LOGGER.info("Index size: " + this.attackTimeouts().length);
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Sets the attack timeout for the specified attack
     * @param attack The attack set the timeout for
     * @param timeout The duration in time to set the timeout for
     */
    default void setAttackTimeout(AE attack, int timeout)
    {
        try
        {
            this.attackTimeouts()[attack.ordinal()] = timeout;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            BurningMinesOfBelow.LOGGER.info("Attempted to set attack timeout for King Infernius Entity, but index was out of bounds!");
            BurningMinesOfBelow.LOGGER.info("Index received: " + attack.ordinal());
            BurningMinesOfBelow.LOGGER.info("Index size: " + this.attackTimeouts().length);
            e.printStackTrace();
        }
    }

    /**
     * Sets up the {@link net.minecraft.world.entity.AnimationState}s for the attacks
     * @param attack The attack to set up the animation states for
     */
    default void setUpAttackAnimationStates(AE attack)
    {
        if (this.entity().isAttacking(attack.getEntityDataAccessor()) && this.attackTimeouts()[attack.ordinal()] <= 0)
        {
            this.attackTimeouts()[attack.ordinal()] = attack.getAnimationDuration();
            attack.getAnimationState().start(this.entity().tickCount);
        } else
        {
            this.attackTimeouts()[attack.ordinal()]--;
        }

        if (!this.entity().isAttacking(attack.getEntityDataAccessor()))
        {
            attack.getAnimationState().stop();
        }
    }
}

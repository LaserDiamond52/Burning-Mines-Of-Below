package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.minecraft.world.entity.Entity;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help set up and manage entities with multiple attack animations. The entities for the mob must have an {@link Enum} that is a subclass of the {@link AttackSetUp} interface</li>
 * <li>A {@link MultiAttackingEntity} is-a {@link AttackingEntity}</li>
 * @author Allen Malo
 * @param <AE> The {@link AttackSetUp} {@link Enum}.
 * @param <E> The {@link Entity} class of the mob.
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public interface MultiAttackingEntity<AE extends Enum<?> & AttackSetUp, E extends Entity> extends AttackingEntity<E> {

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
        if (this.isAttacking(attack.getEntityDataAccessor()) && this.getAttackTimeout(attack) <= 0) // Check if the entity is attacking and the timeout is less than or equal to 0
        {
            this.attackTimeouts()[attack.ordinal()] = attack.getAnimationDuration(); // Entity is attacking, set the timeout to the animation duration
            attack.getAnimationState().start(this.entity().tickCount); // start the animation
        } else
        {
            this.attackTimeouts()[attack.ordinal()]--; // Reduce the timeout by 1
        }

        if (!this.isAttacking(attack.getEntityDataAccessor())) // Is the entity not attacking?
        {
            attack.getAnimationState().stop(); // Stop the animation. Entity is no longer performing the attack
        }
    }
}

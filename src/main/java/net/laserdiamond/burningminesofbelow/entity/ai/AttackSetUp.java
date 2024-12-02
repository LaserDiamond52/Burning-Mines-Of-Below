package net.laserdiamond.burningminesofbelow.entity.ai;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.AnimationState;

/**
 * Interface used to help set up enums for storing data for mob/entities that will have multiple attacks
 */
public interface AttackSetUp {

    /**
     * The {@link AnimationState} for the attack to be performed
     * @return The {@link AnimationState} for the attack
     */
    AnimationState getAnimationState();

    /**
     * The duration of the attack animation
     * @return The duration of the attack animation in ticks
     */
    int getAnimationDuration();

    /**
     * The {@link EntityDataAccessor} for the attack that helps determine what attack for the entity to perform.
     * @return The {@link EntityDataAccessor} for the entity's attack
     */
    EntityDataAccessor<Boolean> getEntityDataAccessor();


}

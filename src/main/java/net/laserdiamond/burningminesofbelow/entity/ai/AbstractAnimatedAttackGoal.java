package net.laserdiamond.burningminesofbelow.entity.ai;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.AttackingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

/**
 * Abstract class used to help create attack goals for {@link Mob}s that perform an attack over a duration of time. This class can also be used to create delayed attacks by simply setting the start and end times to be equal.
 * @param <M> The {@link Mob} class. Said class must also be an inheritor of the {@link AttackingEntity} interface.
 */
public abstract class AbstractAnimatedAttackGoal<M extends Mob & AttackingEntity<M>> extends AbstractAttackGoal<M> implements DelayedAnimatedAttack {

    protected final int attackStartTime;
    protected final int attackEndTime;
    protected final int animationDuration;
    protected int ticksUntilNextAttack;
    protected boolean hasAttackEnded;

    public AbstractAnimatedAttackGoal(M mob)
    {
        super(mob);
        this.attackStartTime = this.attackDelay();
        this.animationDuration = this.animationDuration();
        this.attackEndTime = Math.min(Math.max(this.attackEndTime(), this.attackStartTime), this.animationDuration);
        this.ticksUntilNextAttack = this.ticksUntilNextAttack();
        this.hasAttackEnded = false;
    }

    /**
     * The animation duration of the attack
     * @return The duration of the animation for the attack in ticks
     */
    protected abstract int animationDuration();

    /**
     * The time in ticks to end the attack
     * @return The time in ticks to end the attack. Cannot be greater than the duration of the animation or less than the timestamp to start the attack.
     */
    protected abstract int attackEndTime();

    /**
     * Method called when it is time to start the attack animation. This is called before the actual attack is performed.
     */
    protected void onAttackAnimationStart()
    {
        this.mob.setAttacking(attackDataAccessor(), true);
    }

    /**
     * Method called when it is time to end the attack.
     */
    protected void endAttack(LivingEntity target, ServerLevel serverLevel, int attackTimer)
    {
        this.mob.setAttacking(this.attackDataAccessor(), false);
    }

    /**
     * Method called when the attack animation ends.
     */
    protected void onAttackAnimationEnd()
    {
        this.attackTimer = 0;
        this.resetAttackAnimationTimeOut();
        this.resetAttackCooldown();
        this.hasAttackEnded = false;
    }

    @Override
    public void tick()
    {
        final LivingEntity target = this.mob.getTarget();
        if (!this.mob.level().isClientSide)
        {
            final ServerLevel serverLevel = this.mob.getServer().getLevel(this.mob.level().dimension());
            if (target == null)
            {
                return; // End method, since the mob doesn't have a target
            }

            if (this.isTargetValid(target)) // Determine if target is in range
            { // In range
                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0); // Count down ticks until next attack

                if (this.isTimeToStartAttackAnimation()) // Time to start animation?
                {
                    this.onAttackAnimationStart(); // Start animation

                    this.attackTimer++; // Increment timer
                    if (this.isTimeToStartAttack(this.attackTimer, this.attackStartTime)) // Time to perform attack?
                    {
                        if (!this.hasAttackEnded) // Check if the attack has ended
                        {
                            this.attack(this.mob, target, serverLevel, this.attackTimer); // Attack is still going. Attack!
                        }

                        if (this.isTimeToEndAttack(this.attackTimer, this.attackEndTime)) // Time to end?
                        {
                            this.hasAttackEnded = true; // Attack has ended.
                            this.endAttack(target, serverLevel, this.attackTimer); // End attack

                            if (this.isAnimationOver(this.attackTimer, this.animationDuration)) // Is the animation over?
                            {
                                this.onAttackAnimationEnd(); // Animation should end
                            }
                        }
                    }
                }
            } else // Not in range
            {
                this.onAttackAnimationEnd(); // Animation should end
                this.mob.setAttacking(this.attackDataAccessor(), false);
            }
        }
    }

    @Override
    public boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= 0;
    }

    public void resetAttackCooldown()
    {
        this.ticksUntilNextAttack = this.adjustedTickDelay(this.ticksUntilNextAttack() * 2);
    }

    @Override
    public int ticksUntilNextAttack()
    {
        return this.animationDuration;
    }

    /**
     * Determines if it is time to perform the attack
     * @param timer The attack timer
     * @param startTime The start time of the attack
     * @return True if the current time on the attack is equal to or greater than the delay interval
     */
    protected final boolean isTimeToStartAttack(int timer, int startTime)
    {
        return timer >= startTime;
    }

    /**
     * Determines if it is time to end the attack
     * @param timer The attack timer
     * @param attackEndTime The ending time of the attack in ticks
     * @return True if the attack timer is equal to or greater than the animation duration.
     */
    protected boolean isTimeToEndAttack(int timer, int attackEndTime)
    {
        return timer >= attackEndTime;
    }

    /**
     * Determines if the animation is over
     * @param timer The attack timer
     * @param animationDuration The duration of the animation in ticks
     * @return True if the attack timer is greater than or equal to the duration of the animation
     */
    protected final boolean isAnimationOver(int timer, int animationDuration)
    {
        return timer >= animationDuration;
    }
}

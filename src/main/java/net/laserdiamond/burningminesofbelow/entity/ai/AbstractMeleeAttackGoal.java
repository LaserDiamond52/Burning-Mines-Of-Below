package net.laserdiamond.burningminesofbelow.entity.ai;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.AttackingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used as a base for mobs that require melee attack goals</li>
 * <li>Allows the subclass to define how, when, and why a melee attack should happen</li>
 * <li>An {@link AbstractMeleeAttackGoal} is-a {@link MeleeAttackGoal}</li>
 * <li>An {@link AbstractMeleeAttackGoal} is-a {@link DelayedAnimatedAttack}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public abstract class AbstractMeleeAttackGoal<PM extends PathfinderMob & AttackingEntity<PM>> extends MeleeAttackGoal implements DelayedAnimatedAttack {

    /**
     * the {@link PM} performing the attack goal
     */
    protected final PM mob; // AbstractMeleeAttackGoal has-a PM (PM is an object that is-a PathfinderMob and AttackingEntity)

    /**
     * The delay of the attack in ticks relative to the start of the animation
     */
    protected int attackDelay;

    /**
     * The ticks remaining until the next attack is performed
     */
    protected int ticksUntilNextAttack;

    /**
     * Determines if the attack goal is ready to start counting down to the next attack
     */
    protected boolean shouldCountTillNextAttack = false;

    /**
     * Creates a new {@link AbstractMeleeAttackGoal}
     * @param pMob The {@link PM} that will perform the attack goal
     * @param pSpeedModifier The speed modifier of the {@link PM} when the goal is active
     * @param pFollowingTargetEvenIfNotSeen Determines if the {@link PM} should continue chasing its target if it is not in direct sight
     */
    public AbstractMeleeAttackGoal(PM pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.mob = pMob;
    }

    /**
     * Called when the {@link AbstractMeleeAttackGoal} starts
     */
    @Override
    public void start() {
        super.start();
        this.attackDelay = this.attackDelay(); // Set the attack delay
        this.ticksUntilNextAttack = this.ticksUntilNextAttack(); // Set the ticks until the next attack
    }

    /**
     * Runs every tick the {@link AbstractMeleeAttackGoal} is active
     */
    @Override
    public void tick() {
        super.tick();
        if (this.shouldCountTillNextAttack)
        {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    /**
     * Called when the {@link AbstractMeleeAttackGoal} stops
     */
    @Override
    public void stop() {
        this.mob.setAttacking(this.attackDataAccessor(), false);
        super.stop();
    }

    /**
     * Determines if the {@link PM} is ready to perform the attack on its target
     * @param pEnemy The target of the {@link PM}
     * @param pDistToEnemySqr The distance between the {@link PM} and target, squared
     */
    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {

        if (this.isEnemyInRange(pEnemy, pDistToEnemySqr))
        {
            this.shouldCountTillNextAttack = true;

            if (this.isTimeToStartAttackAnimation())
            {
                this.mob.setAttacking(this.attackDataAccessor(), true);
            }

            if (this.isTimeToAttack())
            {
                this.mob.getLookControl().setLookAt(pEnemy);
                this.attackTarget(pEnemy);
            }
        } else
        {
            this.resetAttackCooldown();
            this.shouldCountTillNextAttack = false;
            this.mob.setAttacking(this.attackDataAccessor(), false);
            this.resetAttackAnimationTimeOut();
        }
    }

    /**
     * Checks if the target is in range for a melee attack
     * @param target The {@link LivingEntity} to attack
     * @param distanceSqr The distance between the attacking entity and target, squared.
     * @return True if the {@link LivingEntity} to attack is in range, false otherwise
     */
    protected boolean isEnemyInRange(LivingEntity target, double distanceSqr)
    {
        return distanceSqr <= this.getAttackReachSqr(target);
    }

    @Override
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(this.attackDelay * 2);
    }

    /**
     * Determines if it is time to perform the attack
     * @return True if the ticks remaining until the next attack is equal to or less than 0, false otherwise.
     */
    @Override
    public boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    @Override
    public boolean isTimeToStartAttackAnimation()
    {
        return this.ticksUntilNextAttack <= this.attackDelay;
    }

    /**
     * Gets the amount of ticks remaining until the next attack
     * @return The amount of ticks remaining until the next attack
     */
    @Override
    public int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    /**
     * Attacks the target
     * @param target The {@link LivingEntity} to attack
     */
    protected void attackTarget(LivingEntity target)
    {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(target);
    }
}

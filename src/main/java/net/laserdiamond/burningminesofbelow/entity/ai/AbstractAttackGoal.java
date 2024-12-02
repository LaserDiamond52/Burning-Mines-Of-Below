package net.laserdiamond.burningminesofbelow.entity.ai;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.AttackingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

/**
 * Abstract attack goal for living entities. This is a very basic attack goal that runs an attack on a fixed interval
 * @param <M> The {@link Mob} class
 */
public abstract class AbstractAttackGoal<M extends Mob & AttackingEntity<M>> extends Goal implements DataAccessorAttack {

    protected final M mob;
    protected int attackTimer;
    protected final int interval;

    public AbstractAttackGoal(M mob)
    {
        this.mob = mob;
        this.attackTimer = 0;
        this.interval = 0;
    }

    public AbstractAttackGoal(M mob, int interval)
    {
        this.mob = mob;
        this.attackTimer = 0;
        this.interval = Math.max(0, interval);
    }

    /**
     * Defines when the goal can be used
     * @return True if the goal can be used, false otherwise
     */
    @Override
    public boolean canUse() {
        return this.mob.isAlive(); // Goal should only be usable if the entity is alive
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mob.getTarget();
        if (target == null)
        {
            return false;
        } else if (!target.isAlive())
        {
            return false;
        } else
        {
            return !(target instanceof Player) || !target.isSpectator() && !((Player) target).isCreative();
        }
    }

    @Override
    public void start() {
        super.start();
        this.mob.setAggressive(true);
        this.mob.setAttacking(this.attackDataAccessor(), false);
    }

    @Override
    public void tick()
    {
        final LivingEntity target = this.mob.getTarget();
        if (!this.mob.level().isClientSide)
        {
            final ServerLevel serverLevel = this.mob.getServer().getLevel(this.mob.level().dimension());
            if (serverLevel == null)
            {
                return;
            }
            this.mob.setAttacking(this.attackDataAccessor(), target != null);
            this.attackTimer++;
            if ((this.mob.tickCount % this.interval == 0) && this.isTargetValid(target))
            {
                this.attack(this.mob, target, serverLevel, this.attackTimer);
                this.attackTimer = 0;
            }
        }
    }

    /**
     * Called when the goal has stopped
     */
    @Override
    public void stop() {
        super.stop();
        this.mob.setAggressive(false);
        this.mob.setAttacking(this.attackDataAccessor(), false);
    }

    /**
     * The {@link Mob}'s attack.
     * @param mob The living entity that will perform the attack
     * @param target The {@link LivingEntity} to perform the attack on
     * @param serverLevel The {@link ServerLevel} the attack is being performed on
     * @param timer The timer of the attack
     */
    protected abstract void attack(M mob, LivingEntity target, ServerLevel serverLevel, int timer);

    /**
     * Checks if the target is valid. This can also be used as the condition that will run the attack
     * @param target The mob's target
     * @return True if the target is valid, or if another specified condition is met. False otherwise. This method defaults to true.
     */
    protected boolean isTargetValid(LivingEntity target)
    {
        return true;
    }
}

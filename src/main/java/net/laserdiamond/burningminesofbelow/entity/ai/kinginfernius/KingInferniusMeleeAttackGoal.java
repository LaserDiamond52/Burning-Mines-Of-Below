package net.laserdiamond.burningminesofbelow.entity.ai.kinginfernius;

import net.laserdiamond.burningminesofbelow.entity.ai.AbstractMeleeAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.minecraft.network.syncher.EntityDataAccessor;

/**
 * Melee attack goal for {@link KingInferniusEntity}
 */
public final class KingInferniusMeleeAttackGoal extends AbstractMeleeAttackGoal<KingInferniusEntity> {

    public KingInferniusMeleeAttackGoal(KingInferniusEntity pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }

    @Override
    public int attackDelay() {
        return 15;
    }

    @Override
    public int ticksUntilNextAttack() {
        return 15;
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return KingInferniusEntity.MELEE_ATTACKING;
    }

    @Override
    public void resetAttackAnimationTimeOut() {
        this.mob.setAttackTimeout(KingInferniusEntity.Attack.MELEE, 0);
    }

    @Override
    protected void resetAttackCooldown() {
        // Override and set the ticks until the next attack as just the attack delay
        // Reasoning:
        // In the animation, the first attack should happen at 15 ticks. The second attack happens 15 ticks
        // later (the end of the animation).
        this.ticksUntilNextAttack = this.adjustedTickDelay(this.attackDelay);
    }
}

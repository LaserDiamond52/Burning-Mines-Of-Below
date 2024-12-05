package net.laserdiamond.burningminesofbelow.entity.ai.freezingreaper;

import net.laserdiamond.burningminesofbelow.entity.ai.AbstractMeleeAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
import net.minecraft.network.syncher.EntityDataAccessor;

public class FreezingReaperMeleeAttackGoal extends AbstractMeleeAttackGoal<FreezingReaperEntity> {

    public FreezingReaperMeleeAttackGoal(FreezingReaperEntity pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }

    @Override
    public int attackDelay() {
        return 10;
    }

    @Override
    public int ticksUntilNextAttack() {
        return 10;
    }

    @Override
    public void resetAttackAnimationTimeOut()
    {
        this.mob.setAttackTimeout(FreezingReaperEntity.Attack.MELEE, 0);
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return FreezingReaperEntity.MELEE_ATTACKING;
    }

    @Override
    protected void resetAttackCooldown() {

        this.ticksUntilNextAttack = this.adjustedTickDelay(this.attackDelay);
    }
}

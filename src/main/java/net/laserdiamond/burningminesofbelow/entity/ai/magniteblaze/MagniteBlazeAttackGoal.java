package net.laserdiamond.burningminesofbelow.entity.ai.magniteblaze;

import net.laserdiamond.burningminesofbelow.entity.ai.AbstractAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.MagniteBlazeFireBall;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

public final class MagniteBlazeAttackGoal extends AbstractAttackGoal<MagniteBlazeEntity> {


    public MagniteBlazeAttackGoal(MagniteBlazeEntity mob, int interval) {
        super(mob, interval);
    }

    @Override
    protected void attack(MagniteBlazeEntity mob, LivingEntity target, ServerLevel serverLevel, int timer)
    {
        float angle = 0F;
        final float angleDiff = 20F;
        for (int i = 0; i < 17; i++)
        {
            MagniteBlazeFireBall magniteBlazeFireBall = new MagniteBlazeFireBall(mob.level(), mob, mob.getLookAngle().yRot(angle));
            magniteBlazeFireBall.setPos(mob.getX(), mob.getEyeY(), mob.getZ());
            mob.level().addFreshEntity(magniteBlazeFireBall);

            mob.level().playSound(null, mob.getOnPos(), SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 100, 1F);
            angle += angleDiff;
        }
    }

    @Override
    public void tick() {
        super.tick();
        final LivingEntity target = this.mob.getTarget();
        if (target != null)
        {
            this.mob.getLookControl().setLookAt(target);
        }
        if (isOutOfRange(target)) // Make sure mob is in valid range to move
        {
            this.mob.getNavigation().moveTo(target, 0.2);
        }
    }

    private boolean isOutOfRange(LivingEntity target)
    {
        if (target == null)
        {
            return false;
        }
        final double distance = this.mob.distanceTo(target);

        return distance > 20 || distance < 15; // If our distance is greater than 20 or less than 15, then we are not in range to attack
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return MagniteBlazeEntity.FIRE_WAVE_ATTACKING;
    }
}

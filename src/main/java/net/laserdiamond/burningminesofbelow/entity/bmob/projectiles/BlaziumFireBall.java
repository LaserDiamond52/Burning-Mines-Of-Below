package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class BlaziumFireBall extends SmallFireball {

    private final LivingEntity shooter;
    public BlaziumFireBall(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
        this.shooter = pShooter;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (this.level().isClientSide)
        {
            return;
        }
        final Entity hitEntity = pResult.getEntity();
        DamageSource damageSource = this.damageSources().mobAttack(this.shooter);
        if (hitEntity instanceof LivingEntity livingEntity)
        {
            livingEntity.hurt(damageSource, 5);
        }
    }
}

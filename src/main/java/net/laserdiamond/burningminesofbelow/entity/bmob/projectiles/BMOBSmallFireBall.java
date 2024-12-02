package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class BMOBSmallFireBall extends SmallFireball {

    public BMOBSmallFireBall(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
    }

    public BMOBSmallFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos)
    {
        this(pLevel, pShooter, pos.x, pos.y, pos.z);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (this.level().isClientSide)
        {
            return;
        }
        final Entity hitEntity = pResult.getEntity();
        DamageSource damageSource = this.damageSources().mobAttack((LivingEntity) this.getOwner());
        if (hitEntity instanceof LivingEntity livingEntity)
        {
            livingEntity.hurt(damageSource, impactDamage(livingEntity));
        }
    }

    /**
     * The amount of damage to inflict on any entities that are hit
     * @param hitEntity The {@link LivingEntity} hit by the projectile
     * @return The amount of damage to inflict on hit entities
     */
    protected float impactDamage(LivingEntity hitEntity)
    {
        return 0F;
    }
}

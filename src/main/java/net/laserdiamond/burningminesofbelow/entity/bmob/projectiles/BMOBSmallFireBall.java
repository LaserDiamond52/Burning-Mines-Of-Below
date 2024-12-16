package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the basic characteristics and functionality for Small Fireballs of this mod</li>
 * <li>Act as a base class for Small Fireballs of this mod</li>
 * <li>A {@link BMOBSmallFireBall} is-a {@link SmallFireball}</li>
 * @author Allen Malo
 */
public class BMOBSmallFireBall extends SmallFireball {

    /**
     * Creates a new {@link BMOBSmallFireBall}
     * @param pLevel The {@link Level} to summon the projectile on
     * @param pShooter The {@link LivingEntity} shooting the projectile
     * @param pOffsetX The x position in the {@link Level} to summon the projectile
     * @param pOffsetY The y position in the {@link Level} to summon the projectile
     * @param pOffsetZ The z position in the {@link Level} to summon the projectile
     */
    public BMOBSmallFireBall(Level pLevel, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ) {
        super(pLevel, pShooter, pOffsetX, pOffsetY, pOffsetZ);
    }

    /**
     * Creates a new {@link BMOBSmallFireBall}
     * @param pLevel The {@link Level} to summon the projectile on
     * @param pShooter The {@link LivingEntity} shooting the projectile
     * @param pos The position of the projectile when summoned as a {@link Vec3}
     */
    public BMOBSmallFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos)
    {
        this(pLevel, pShooter, pos.x, pos.y, pos.z);
    }

    /**
     * Called when the {@link BMOBSmallFireBall} hits an entity
     * @param pResult The {@link EntityHitResult} of this projectile
     */
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (this.level().isClientSide)
        {
            return; // Do not run on client
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

package net.laserdiamond.burningminesofbelow.entity.ai.kinginfernius;

import net.laserdiamond.burningminesofbelow.entity.ai.AbstractDurationAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public final class KingInferniusHeatWaveGoal extends AbstractDurationAttackGoal<KingInferniusEntity> {

    public KingInferniusHeatWaveGoal(KingInferniusEntity mob) {
        super(mob);
    }

    @Override
    protected int animationDuration() {
        return 20;
    }

    @Override
    protected int attackEndTime() {
        return 10;
    }

    @Override
    protected void attack(KingInferniusEntity mob, LivingEntity target, ServerLevel serverLevel, int attackTimer)
    {
        mob.level().playSound(null, mob.getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.HOSTILE, 100, 1F);

        float angle = 0F; // The initial launch angle
        final float angleDiff = 30F; // The rotation amount between each ray cast on the yaw
        final float initialDamage = 40F; // The initial damage the ray cast will inflict if an entity is hit

        // TODO: While this works, it may be better to...
        // Create a ray cast that fires in a circle, where the given start position is the center (radius can be specified)
        // Fire multiple "ring" ray casts, enough to hit nearby entities
        // When a nearby entity is hit, trace a path back to the center position, and calculate damage this way
        for (int i = 0; i < 45; i++)
        {
            // Because we want damage to be reduced each time we hit a block, we need to handle damage within the loop
            RayCast<LivingEntity, LivingEntity, Float> heatWave = RayCast.createRayCast(serverLevel, mob.position().add(0, (mob.getBbHeight() / 2) + 0.5, 0), (e -> !(e instanceof KingInferniusEntity)), LivingEntity.class, List.of());
            heatWave.addParticle(ParticleTypes.FLAME).setStepIncrement(0.5).setCanPierceEntities().setCanPierceBlocks(); // Set particle, step increment, and allow ray cast to pierce through blocks and entities
            heatWave.setBlockStateHitReturnObj(initialDamage);

            heatWave.onBlockStateHitFunction(blockState -> { // Everytime we hit a solid block, we want to divide our damage in half
                if (blockState.isSolid()) // Check if hit block state is solid
                {
                    return heatWave.getBlockStateHitReturnObj() / 2; // Hit block state is solid. Divide damage in half
                }
                return heatWave.getBlockStateHitReturnObj(); // Hit block state isn't solid. Keep damage the same
            });

            heatWave.onEntityHitFunction(livingEntity -> // Because our damage changes based on WHEN we hit blocks, we'll need to handle damaging the entity DURING the ray cast
            {
                float damage = heatWave.getBlockStateHitReturnObj();
                if (livingEntity.isBlocking())
                {
                    damage = (damage * 2) / 3; // Do 2/3s of the damage if the entity is blocking
                }
                livingEntity.hurt(livingEntity.damageSources().mobAttack(mob), damage); // Damage the entity for some amount, depending on if we hit blocks or if the entity is shielding.
                return livingEntity;
            });

            heatWave.fireInDirection(this.mob.getLookAngle().yRot(angle), mob.distanceTo(target) + 15); // Fire the ray cast

            angle += angleDiff;
        }
    }

    @Override
    protected boolean isTargetValid(LivingEntity target) {
        final double distance = this.mob.distanceTo(target);

        return (distance > 7 && distance < 15) || this.mob.isAttacking(KingInferniusEntity.HEAT_WAVE_ATTACKING);
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return KingInferniusEntity.HEAT_WAVE_ATTACKING;
    }

    @Override
    public int attackDelay() {
        return 10;
    }

    @Override
    public void resetAttackAnimationTimeOut() {
        this.mob.setAttackTimeout(KingInferniusEntity.Attack.HEAT_WAVE, 0);
    }
}

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Fire Breath attack goal for King Infernius
 */
public final class KingInferniusFireBreathAttackGoal extends AbstractDurationAttackGoal<KingInferniusEntity> {

    public KingInferniusFireBreathAttackGoal(KingInferniusEntity mob) {
        super(mob);
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return KingInferniusEntity.FIRE_BREATH_ATTACKING;
    }

    @Override
    protected int animationDuration() {
        return 50;
    }

    @Override
    protected int attackEndTime() {
        return 50;
    }

    @Override
    protected void attack(KingInferniusEntity mob, LivingEntity target, ServerLevel serverLevel, int attackTimer) {

        // Set King Infernius to look at the target
        mob.getLookControl().setLookAt(target);
        mob.getLookControl().tick();

        final float damage = KingInferniusEntity.CONFIG.fireBreathDamage();

        if (this.attackTimer % 2 == 0) // Play fire charge use sound every 2 ticks
        {
            serverLevel.playSound(null, mob.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.HOSTILE, 100F, 1F);
        }

        // Main fire breath ray cast
        RayCast<LivingEntity, LivingEntity, ?> fireBreathCastMain = RayCast.createRayCast(serverLevel, mob.getEyePosition(), (e -> !(e instanceof KingInferniusEntity)), LivingEntity.class, List.of());

        fireBreathCastMain.addParticle(ParticleTypes.FLAME).addParticle(ParticleTypes.LARGE_SMOKE) // Set the particles to display
                .setCanPierceEntities() // Allow the ray casts to pierce through entities
//                .setAllowHitEntitiesPersistence()
//                .setAllowHitBlockStatesPersistence()
                .setStepIncrement(0.5) // Step increment of the Ray cast
                .onEntityHitFunction(livingEntity -> // Whenever we hit an entity, lets damage them and add to their fire ticks
                {
                    livingEntity.hurt(livingEntity.damageSources().mobAttack(mob), damage); // damage entity
                    livingEntity.setSecondsOnFire((livingEntity.getRemainingFireTicks() * 20) * KingInferniusEntity.CONFIG.attackFireDuration()); // set entity on fire
                    return livingEntity;
                })
                // Fire our ray casts
                .fireAtVec3D(target.position().add(0, target.getBbHeight() / 2, 0), 7)
                .fireAtVec3D(target.position().add(5, target.getBbHeight() / 2, 0), 7)
                .fireAtVec3D(target.position().add(-5, target.getBbHeight() / 2, 0), 7);
    }

    @Override
    protected boolean isTargetValid(LivingEntity target) {
        final double distance = this.mob.distanceTo(target);
        // Only want to do attack if one of the following is met...
        // 1. Target is within the valid distance
        // 2. The attack has already started. If the player moves closer to King Infernius without this condition, the attack stops
        return (distance > 14 && distance <= 30) || this.mob.isAttacking(KingInferniusEntity.FIRE_BREATH_ATTACKING);
    }

    @Override
    public int attackDelay() {
        return 10;
    }

    @Override
    public void resetAttackAnimationTimeOut() {
        this.mob.setAttackTimeout(KingInferniusEntity.Attack.FIRE_BREATH, 0);
    }
}

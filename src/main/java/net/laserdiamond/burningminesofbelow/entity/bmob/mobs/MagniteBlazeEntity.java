package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.ai.AbstractAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.BMOBSmallFireBall;
import net.laserdiamond.burningminesofbelow.entity.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.laserdiamond.burningminesofbelow.util.file.mob.MagniteBlazeConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Objects;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Define the properties of a {@link MagniteBlazeEntity} in-game</li>
 * <li>Define how the {@link MagniteBlazeEntity} behaves in the environment it is in</li>
 * <li>A {@link MagniteBlazeEntity} is-a {@link Blaze}</li>
 * <li>A {@link MagniteBlazeEntity} is-a {@link AttackingEntity}</li>
 * @author Allen Malo
 */
public final class MagniteBlazeEntity extends Blaze implements AttackingEntity<MagniteBlazeEntity> {

    /**
     * The {@link MagniteBlazeConfig} for this mob. This is universal to all {@link MagniteBlazeEntity} mobs
     */
    private static final MagniteBlazeConfig CONFIG = (MagniteBlazeConfig) MobConfigRegistry.getRegistryMap().get(BMOBEntities.MAGNITE_BLAZE.getId());

    /**
     * The {@link EntityDataAccessor} for the Fire Wave attack
     */
    public static final EntityDataAccessor<Boolean> FIRE_WAVE_ATTACKING = SynchedEntityData.defineId(MagniteBlazeEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for when the {@link MagniteBlazeEntity} is supporting nearby blazes
     */
    public static final EntityDataAccessor<Boolean> SUPPORTING_BLAZES = SynchedEntityData.defineId(MagniteBlazeEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link AnimationState} for the idle animation
     */
    private final AnimationState idleAnimationState; // MagniteBlazeEntity has-a AnimationState

    /**
     * Creates a new {@link MagniteBlazeEntity}
     * @param pEntityType the {@link EntityType} for the {@link MagniteBlazeEntity}
     * @param pLevel the {@link Level} to spawn the {@link MagniteBlazeEntity}
     */
    public MagniteBlazeEntity(EntityType<? extends Blaze> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.idleAnimationState = new AnimationState();
    }

    /**
     * Gets the idle animation state
     * @return The {@link AnimationState} for the idle animation
     */
    public AnimationState getIdleAnimationState() {
        return idleAnimationState;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Define synced data for attacks
        this.entityData.define(FIRE_WAVE_ATTACKING, false);
        this.entityData.define(SUPPORTING_BLAZES, false);
    }

    @Override
    public MagniteBlazeEntity entity() {
        return this;
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MagniteBlazeSupportGoal(this, CONFIG.supportInterval()));
        this.goalSelector.addGoal(1, new MagniteBlazeAttackGoal(this, CONFIG.fireballLaunchInterval()));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(Blaze.class, MagniteBlazeEntity.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void onAddedToWorld()
    {
        super.onAddedToWorld();
        this.idleAnimationState.start(this.tickCount); // Start idle animation
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Define how a {@link MagniteBlazeFireBall} should behave in-game</li>
     * <li>this fireball is only summoned by the {@link MagniteBlazeEntity}</li>
     * <li>Declared as a private static inner class because instances of this class are only ever used for the {@link MagniteBlazeEntity} and its related attack goals</li>
     * <li>A {@link MagniteBlazeFireBall} is-a {@link BMOBSmallFireBall}</li>
     * @author Allen Malo
     */
    private static class MagniteBlazeFireBall extends BMOBSmallFireBall
    {

        /**
         * Creates a new {@link MagniteBlazeFireBall}
         * @param pLevel The {@link Level} the {@link MagniteBlazeFireBall} is being created on
         * @param pShooter The {@link LivingEntity} that will be shooting the projectile
         * @param pos The position to summon the projectile at as a {@link Vec3}
         */
        public MagniteBlazeFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos) {
            super(pLevel, pShooter, pos);
        }

        @Override
        protected float impactDamage(LivingEntity hitEntity) {
            if (this.getOwner() instanceof MagniteBlazeEntity magniteBlazeEntity)
            {
                if (hitEntity instanceof Blaze || hitEntity instanceof KingInferniusEntity)
                {
                    return 0F; // Cannot damage Blazes or King Infernius with fireball
                }
                final double damageAttribute = Objects.requireNonNull(magniteBlazeEntity.getAttribute(Attributes.ATTACK_DAMAGE)).getValue();
                return (float) damageAttribute;
            }
            return 0F;
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Allow a {@link MagniteBlazeEntity} to buff nearby blazes, as well as when and how</li>
     * <li>Declared as a private static inner class because this class is only every used for instances of the {@link MagniteBlazeEntity}</li>
     * <li>A {@link MagniteBlazeSupportGoal} is-a {@link AbstractAttackGoal}</li>
     * @author Allen Malo
     */
    private static class MagniteBlazeSupportGoal extends AbstractAttackGoal<MagniteBlazeEntity>
    {

        /**
         * Creates a new {@link MagniteBlazeSupportGoal}
         * @param mob The {@link MagniteBlazeEntity} performing the goal
         * @param interval The interval in ticks of how often the action is performed
         */
        public MagniteBlazeSupportGoal(MagniteBlazeEntity mob, int interval)
        {
            super(mob, interval);
        }

        @Override
        protected void attack(MagniteBlazeEntity mob, LivingEntity target, ServerLevel serverLevel, int timer)
        {
            int blazesBuffed = 0; // Keep count of how many blazes we buff

            // Get all blazes within a 30x30x30 block distance from the Magnite Blaze. Exclude Magnite Blazes
            for (Blaze blaze : serverLevel.getEntitiesOfClass(Blaze.class, RayCast.createBBLivingEntity(mob, CONFIG.supportRange()), (e -> !(e instanceof MagniteBlazeEntity))))
            {
                // Fire ray cast at the blaze. This is purely for cosmetic purposes, as the ray casts themselves do not have any effect on the Blazes themselves
                RayCast.createRayCast(serverLevel, this.mob.position().add(0, this.mob.getBbHeight() / 2, 0), Entity::isAttackable, Blaze.class, List.of())
                       .setCanPierceBlocks()
                       .setCanPierceEntities()
                       .addParticle(ParticleTypes.FLAME)
                       .fireAtVec3D(blaze.position().add(0, blaze.getBbHeight() / 2, 0), 0);

                // Buff Blazes
                blaze.setHealth(blaze.getMaxHealth()); // Heal blaze back to max health
                blaze.setAbsorptionAmount(blaze.getAbsorptionAmount() + CONFIG.supportAbsorption()); // Grant blaze absorption hearts. Stack on top of current ones
                blaze.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 2)); // Grant blaze resistance effect
                blaze.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2)); // Grant blaze regeneration

                if (target != null)
                {
                    blaze.setTarget(target); // All buffed blazes will now target the Magnite Blaze's target
                }

                if (blazesBuffed == 1) // Have we found at least one blaze?
                {
                    this.mob.level().playSound(null, this.mob.getOnPos(), SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 100, 1F); // Play sound to warn player
                }

                if (blazesBuffed == CONFIG.supportAmount())
                {
                    break; // Can only buff a set amount of blazes. This is done to prevent players from using this mob as a "lag machine"
                }

                blazesBuffed++; // Increment counter
            }
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor()
        {
            return SUPPORTING_BLAZES;
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Allow a {@link MagniteBlazeEntity} to perform an attack, as well as when and how</li>
     * <li>Declared as a private static inner class because this class is only every used for instances of the {@link MagniteBlazeEntity}</li>
     * <li>A {@link MagniteBlazeAttackGoal} is-a {@link AbstractAttackGoal}</li>
     * @author Allen Malo
     */
    private static class MagniteBlazeAttackGoal extends AbstractAttackGoal<MagniteBlazeEntity> {

        /**
         * Creates a new {@link MagniteBlazeAttackGoal}
         * @param mob The {@link MagniteBlazeEntity} performing the goal
         * @param interval The interval in ticks of how often the action is performed
         */
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
            if (target == null) // Is there a target?
            {
                return false; // No target. Return false
            }
            final double distance = this.mob.distanceTo(target);

            return distance > 20 || distance < 15; // If our distance is greater than 20 or less than 15, then we are not in range to attack
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor() {
            return MagniteBlazeEntity.FIRE_WAVE_ATTACKING;
        }

        @Override
        protected boolean isTargetValid(LivingEntity target)
        {
            return target != null; // Only perform the attack if there is a target
        }
    }
}

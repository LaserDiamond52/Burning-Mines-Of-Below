package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.ai.AbstractAnimatedAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.AbstractMeleeAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.entity.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.laserdiamond.burningminesofbelow.util.file.mob.KingInferniusConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

import java.util.List;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines how King Infernius will behave in-game, and how the entity will respond to the environment around it</li>
 * <li>A {@link KingInferniusEntity} is-a {@link AbstractBossMob}</li>
 * <li>A {@link KingInferniusEntity} is-a {@link MultiAttackingEntity}</li>
 * @author Allen Malo
 */
public final class KingInferniusEntity extends AbstractBossMob<KingInferniusEntity> implements MultiAttackingEntity<KingInferniusEntity.Attack, KingInferniusEntity> {

    /**
     * The {@link KingInferniusConfig} for this mob. This is universal to all {@link KingInferniusEntity} mobs.
     */
    private static final KingInferniusConfig CONFIG = ((KingInferniusConfig) MobConfigRegistry.getRegistryMap().get(BMOBEntities.KING_INFERNIUS.getId()));

    /**
     * The {@link EntityDataAccessor} for the melee attack
     */
    public static final EntityDataAccessor<Boolean> MELEE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for the Fire Breath attack
     */
    public static final EntityDataAccessor<Boolean> FIRE_BREATH_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for the Heat Wave attack
     */
    public static final EntityDataAccessor<Boolean> HEAT_WAVE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for the Solar Flare attack
     */
    public static final EntityDataAccessor<Boolean> SOLAR_FLARE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The attack timeouts for the {@link KingInferniusEntity}
     */
    private final int[] attackTimeouts;

    public KingInferniusEntity(EntityType<? extends KingInferniusEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.attackTimeouts = new int[4];
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
    }

    /**
     * Called when the {@link KingInferniusEntity} hurts and {@link Entity}
     * @param pEntity The {@link Entity} being hurt
     * @return True if the {@link Entity} was hurt, false otherwise
     */
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        pEntity.setSecondsOnFire((pEntity.getRemainingFireTicks() * 20) + CONFIG.attackFireDuration());
        return super.doHurtTarget(pEntity);
    }

    @Override
    protected BossEvent.BossBarColor barColor() {
        return BossEvent.BossBarColor.RED;
    }

    @Override
    protected BossEvent.BossBarOverlay barOverlay() {
        return BossEvent.BossBarOverlay.PROGRESS;
    }

    @Override
    public KingInferniusEntity entity() {
        return this;
    }

    @Override
    public int[] attackTimeouts() {
        return this.attackTimeouts;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();

        this.goalSelector.addGoal(2, new KingInferniusMeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(2, new KingInferniusFireBreathAttackGoal(this));
        this.goalSelector.addGoal(2, new KingInferniusHeatWaveGoal(this));

        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level().isClientSide)
        {
            for (int i = 0; i < 2; i++)
            {
                this.level().addParticle(ParticleTypes.FLAME, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), 0.0, 0.0, 0.0);
                this.level().addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(1), this.getRandomY(), this.getRandomZ(1), 0.0, 0.0, 0.0);
            }
        } else
        {
            // Set fire to every block King Infernius walks on
            BlockState blockState = this.level().getBlockState(this.getOnPos().above());
            if (blockState.isAir() && blockState.getBlock() != Blocks.FIRE) // Check that the block above the one being walked on is air and isn't already on fire
            {
                this.level().setBlock(this.getOnPos().above(), Blocks.FIRE.defaultBlockState(), 2);
                this.level().blockUpdated(this.getOnPos().above(), Blocks.FIRE); // Set the block to fire
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Define synced data for attacks
        this.entityData.define(MELEE_ATTACKING, false);
        this.entityData.define(FIRE_BREATH_ATTACKING, false);
        this.entityData.define(HEAT_WAVE_ATTACKING, false);
        this.entityData.define(SOLAR_FLARE_ATTACKING, false);
    }

    @Override
    public void setUpAnimationStates() {
        super.setUpAnimationStates();

        // Set up animation states for all attacks
        this.setUpAttackAnimationStates(Attack.MELEE);
        this.setUpAttackAnimationStates(Attack.FIRE_BREATH);
        this.setUpAttackAnimationStates(Attack.HEAT_WAVE);
        this.setUpAttackAnimationStates(Attack.SOLAR_FLARE);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.WITHER_SKELETON_STEP;
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Store variables needed for the attacks of the {@link KingInferniusEntity}</li>
     * <li>Declared as an inner enum because this enum is only used for {@link KingInferniusEntity}-related classes.</li>
     * <li>The only usage of this class outside the enclosing class is for the {@link net.laserdiamond.burningminesofbelow.entity.client.model.KingInferniusModel} for setting up the animations</li>
     * <li>An {@link Attack} is-a {@link AttackSetUp}</li>
     * @author Allen Malo
     */
    public enum Attack implements AttackSetUp
    {
        MELEE (new AnimationState(), 30, MELEE_ATTACKING),
        FIRE_BREATH (new AnimationState(), 50, FIRE_BREATH_ATTACKING),
        HEAT_WAVE (new AnimationState(), 20, HEAT_WAVE_ATTACKING),
        SOLAR_FLARE (new AnimationState(), 80, SOLAR_FLARE_ATTACKING);

        private final AnimationState animationState; // Attack has-a AnimationState
        private final int animationDuration;
        private final EntityDataAccessor<Boolean> entityDataAccessor; // Attack has-a EntityDataAccessor

        /**
         * Creates a new {@link KingInferniusEntity.Attack}
         * @param animationState The {@link AnimationState} for the attack
         * @param animationDuration The duration of the animation
         * @param entityDataAccessor the {@link EntityDataAccessor} of the attack
         */
        Attack(AnimationState animationState, int animationDuration, EntityDataAccessor<Boolean> entityDataAccessor)
        {
            this.animationState = animationState;
            this.animationDuration = animationDuration;
            this.entityDataAccessor = entityDataAccessor;
        }

        @Override
        public AnimationState getAnimationState() {
            return animationState;
        }

        @Override
        public int getAnimationDuration() {
            return animationDuration;
        }

        @Override
        public EntityDataAccessor<Boolean> getEntityDataAccessor() {
            return entityDataAccessor;
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Allow the {@link KingInferniusEntity} to perform a melee attack and damage its target based on the time in the melee attack animation</li>
     * <li>Declared as a private static inner class because this class is only ever used for instances of the {@link KingInferniusEntity}</li>
     * <li>A {@link KingInferniusMeleeAttackGoal} is-a {@link AbstractMeleeAttackGoal}</li>
     * @author Allen Malo
     */
    private static class KingInferniusMeleeAttackGoal extends AbstractMeleeAttackGoal<KingInferniusEntity>
    {

        /**
         * Creates a new {@link KingInferniusMeleeAttackGoal}
         * @param pMob The {@link KingInferniusEntity} performing the goal
         * @param pSpeedModifier The speed modifier of the {@link KingInferniusEntity} when the goal is active
         * @param pFollowingTargetEvenIfNotSeen If the {@link KingInferniusEntity} should continue following this goal even if the target is not seen
         */
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
            return MELEE_ATTACKING;
        }

        @Override
        public void resetAttackAnimationTimeOut() {
            this.mob.setAttackTimeout(Attack.MELEE, 0);
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

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Allow the {@link KingInferniusEntity} to perform a fire breath attack and start the attack towards the target based on the relative time in the attack animation</li>
     * <li>Declared as a private static inner class because this class is only ever used for instances of the {@link KingInferniusEntity}</li>
     * <li>A {@link KingInferniusFireBreathAttackGoal} is-a {@link AbstractAnimatedAttackGoal}</li>
     * @author Allen Malo
     */
    private static class KingInferniusFireBreathAttackGoal extends AbstractAnimatedAttackGoal<KingInferniusEntity>
    {

        /**
         * Creates a new {@link KingInferniusFireBreathAttackGoal}
         * @param mob the {@link KingInferniusEntity} performing the goal
         */
        public KingInferniusFireBreathAttackGoal(KingInferniusEntity mob) {
            super(mob);
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor() {
            return FIRE_BREATH_ATTACKING;
        }

        @Override
        protected int animationDuration() {
            return Attack.FIRE_BREATH.getAnimationDuration();
        }

        @Override
        protected int attackEndTime() {
            return Attack.FIRE_BREATH.getAnimationDuration();
        }

        @Override
        protected void attack(KingInferniusEntity mob, LivingEntity target, ServerLevel serverLevel, int attackTimer) {

            // Set King Infernius to look at the target
            mob.getLookControl().setLookAt(target);
            mob.getLookControl().tick();

            final float damage = CONFIG.fireBreathDamage();

            if (this.attackTimer % 2 == 0) // Play fire charge use sound every other ticks
            {
                serverLevel.playSound(null, mob.getOnPos(), SoundEvents.FIRECHARGE_USE, SoundSource.HOSTILE, 100F, 1F);
            }

            // Launch fire breath!
            RayCast<LivingEntity, LivingEntity, BlockState> fireBreath = RayCast.createRayCast(serverLevel, mob.getEyePosition(), (e -> !(e instanceof KingInferniusEntity)), LivingEntity.class, List.of());
            fireBreath.addParticle(ParticleTypes.FLAME).addParticle(ParticleTypes.LARGE_SMOKE) // Set the particles to display
                      .setCanPierceEntities() // Allow the ray casts to pierce through entities
                      .setStepIncrement(0.5) // Step increment of the Ray cast
                      .onEntityHitFunction(livingEntity -> // Whenever we hit an entity, lets damage them and add to their fire ticks
                      {
                          livingEntity.hurt(livingEntity.damageSources().mobAttack(mob), damage); // damage entity
                          livingEntity.setSecondsOnFire((livingEntity.getRemainingFireTicks() * 20) * KingInferniusEntity.CONFIG.attackFireDuration()); // set entity on fire
                          return livingEntity;
                      })
                      .onBlockStateHitFunction(blockState ->
                      {
                          if (blockState.isSolid())
                          {
                              BlockPos blockPos = fireBreath.getCurrentBlockPos();
                              for (Direction direction : Direction.values())
                              {
                                  blockState.onCaughtFire(serverLevel, blockPos, direction, mob); // Set block hit on fire!
                              }
                              BlockState aboveState = serverLevel.getBlockState(blockPos.above()); // Get block state for the block above the hit block state
                              if (aboveState.isAir() && aboveState.getBlock() != Blocks.FIRE) // Is the above block state air and not already fire?
                              {
                                  // Set on fire!
                                  serverLevel.setBlock(blockPos.above(), Blocks.FIRE.defaultBlockState(), 2);
                                  serverLevel.blockUpdated(blockPos.above(), Blocks.FIRE);
                              }
                          }
                          return blockState; // Return the block state
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
            return (distance > 14 && distance <= 30) || this.mob.isAttacking(this.attackDataAccessor());
        }

        @Override
        public int attackDelay() {
            return 10;
        }

        @Override
        public void resetAttackAnimationTimeOut() {
            this.mob.setAttackTimeout(Attack.FIRE_BREATH, 0);
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Allow the {@link KingInferniusEntity} to perform a heat wave attack and damage its target based on the time in the heat wave attack animation</li>
     * <li>Declared as a private static class because this class is only ever used for instances of the {@link KingInferniusEntity}</li>
     * <li>A {@link KingInferniusHeatWaveGoal} is-a {@link AbstractAnimatedAttackGoal}</li>
     * @author Allen Malo
     */
    private static class KingInferniusHeatWaveGoal extends AbstractAnimatedAttackGoal<KingInferniusEntity>
    {

        /**
         * Creates a new {@link KingInferniusHeatWaveGoal}
         * @param mob The {@link KingInferniusEntity} performing the goal
         */
        public KingInferniusHeatWaveGoal(KingInferniusEntity mob) {
            super(mob);
        }

        @Override
        protected int animationDuration() {
            return Attack.HEAT_WAVE.getAnimationDuration();
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
            final float initialDamage = CONFIG.heatWaveDamage(); // The initial damage the ray cast will inflict if an entity is hit

            // TODO: While this works, it may be better to...
            // Create a ray cast that fires in a circle, where the given start position is the center (radius can be specified)
            // Fire multiple "ring" ray casts, enough to hit nearby entities
            // When a nearby entity is hit, trace a path back to the center position, and calculate damage this way
            for (int i = 0; i < 45; i++)
            {
                // Because we want damage to be reduced each time we hit a block, we need to handle damage within the ray cast
                RayCast<LivingEntity, LivingEntity, Float> heatWave = RayCast.createRayCast(serverLevel, mob.position().add(0, (mob.getBbHeight() / 2) + 0.5, 0), (e -> !(e instanceof KingInferniusEntity)), LivingEntity.class, List.of());
                heatWave.addParticle(ParticleTypes.FLAME)
                        .setStepIncrement(0.5) // Set step increment
                        .setCanPierceEntities() // Allow to pierce entities
                        .setCanPierceBlocks() // Allow to pierce through blocks
                        .setBlockStateHitReturnObj(initialDamage) // Initialize the damage
                        .onBlockStateHitFunction(blockState -> // Everytime we hit a solid block, we want to divide our damage in half
                        {
                            if (blockState.isSolid()) // Check if hit block state is solid
                            {
                                return heatWave.getBlockStateHitReturnObj() / 2; // Hit block state is solid. Divide damage in half
                            }
                            return heatWave.getBlockStateHitReturnObj(); // Hit block state isn't solid. Keep damage the same
                        })
                        .onEntityHitFunction(livingEntity -> // Because our damage changes based on WHEN we hit blocks, we'll need to handle damaging the entity DURING the ray cast
                        {
                            float damage = heatWave.getBlockStateHitReturnObj();
                            if (livingEntity.isBlocking())
                            {
                                damage = (damage * 2) / 3; // Do 2/3s of the damage if the entity is blocking
                                ShieldBlockEvent ev = ForgeHooks.onShieldBlock(livingEntity, livingEntity.damageSources().mobAttack(mob), damage);
                                ev.setCanceled(true); // Player should not be able to block this damage

                                if (livingEntity instanceof Player player)
                                {
                                    if (player.getMainHandItem().getItem() instanceof ShieldItem shieldItem)
                                    {
                                        player.getCooldowns().addCooldown(shieldItem, 100);
                                    }
                                    if (player.getOffhandItem().getItem() instanceof ShieldItem shieldItem)
                                    {
                                        player.getCooldowns().addCooldown(shieldItem, 100);
                                    }
                                }
                            }

                            livingEntity.hurt(livingEntity.damageSources().mobAttack(mob), damage); // Damage the entity for some amount, depending on if we hit blocks or if the entity is shielding.
                            livingEntity.setSecondsOnFire((livingEntity.getRemainingFireTicks() * 20) + KingInferniusEntity.CONFIG.attackFireDuration()); // Set the entity on fire
                            return livingEntity;
                        })
                        .fireInDirection(this.mob.getLookAngle().yRot(angle), mob.distanceTo(target) + 15); // Fire the ray cast

                angle += angleDiff;
            }
        }

        @Override
        protected boolean isTargetValid(LivingEntity target) {
            final double distance = this.mob.distanceTo(target);

            return (distance > 7 && distance < 15) || this.mob.isAttacking(HEAT_WAVE_ATTACKING);
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor() {
            return HEAT_WAVE_ATTACKING;
        }

        @Override
        public int attackDelay() {
            return 10;
        }

        @Override
        public void resetAttackAnimationTimeOut() {
            this.mob.setAttackTimeout(Attack.HEAT_WAVE, 0);
        }
    }
}

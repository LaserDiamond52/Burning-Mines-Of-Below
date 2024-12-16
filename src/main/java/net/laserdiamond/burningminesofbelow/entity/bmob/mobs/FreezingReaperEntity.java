package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.ai.AbstractAnimatedAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.AbstractMeleeAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cryobolt;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.entity.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.laserdiamond.burningminesofbelow.util.file.mob.FreezingReaperConfig;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Define how the Freezing Reaper of the Damned will behave in-game, and how the entity will respond to the environment around it</li>
 * <li>A {@link FreezingReaperEntity} is-a {@link AbstractBossMob}</li>
 * <li>A {@link FreezingReaperEntity} is-a {@link MultiAttackingEntity}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public final class FreezingReaperEntity extends AbstractBossMob<FreezingReaperEntity> implements MultiAttackingEntity<FreezingReaperEntity.Attack, FreezingReaperEntity> {

    /**
     * The {@link FreezingReaperConfig} for this mob. This is universal to all {@link FreezingReaperEntity} mobs
     */
    private static final FreezingReaperConfig CONFIG = (FreezingReaperConfig) MobConfigRegistry.getRegistryMap().get(BMOBEntities.FREEZING_REAPER.getId());

    /**
     * The {@link EntityDataAccessor} for the melee attack
     */
    public static final EntityDataAccessor<Boolean> MELEE_ATTACKING = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for the Cryobolt Blast attack
     */
    public static final EntityDataAccessor<Boolean> CRYOBOLT_BLAST = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The {@link EntityDataAccessor} for the Sudden Blizzard attack
     */
    public static final EntityDataAccessor<Boolean> SUDDEN_BLIZZARD = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);

    /**
     * The attack timeouts for the {@link FreezingReaperEntity}
     */
    private final int[] attackTimeouts;

    /**
     * Creates a new {@link FreezingReaperEntity}
     * @param pEntityType The {@link EntityType} for the {@link FreezingReaperEntity}
     * @param pLevel The {@link Level} to spawn the {@link FreezingReaperEntity}
     */
    public FreezingReaperEntity(EntityType<? extends FreezingReaperEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.attackTimeouts = new int[4];
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(BMOBItems.CRYONITE_SCYTHE.get()));
    }

    /**
     * Called when the {@link FreezingReaperEntity} hurts an {@link Entity}
     * @param pEntity The {@link Entity} being hurt
     * @return True if the {@link Entity} was hurt, false otherwise
     */
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        pEntity.setTicksFrozen(pEntity.getTicksFrozen() + CONFIG.attackFreezeDuration());
        return super.doHurtTarget(pEntity);
    }

    @Override
    protected BossEvent.BossBarColor barColor() {
        return BossEvent.BossBarColor.BLUE;
    }

    @Override
    protected BossEvent.BossBarOverlay barOverlay() {
        return BossEvent.BossBarOverlay.PROGRESS;
    }

    @Override
    public FreezingReaperEntity entity() {
        return this;
    }

    @Override
    public int[] attackTimeouts() {
        return this.attackTimeouts;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FreezingReaperMeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(1, new FreezingReaperCryoboltBlastAttackGoal(this));
        this.goalSelector.addGoal(1, new FreezingReaperSuddenBlizzardAttackGoal(this));

        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Define synced data for attacks
        this.entityData.define(MELEE_ATTACKING, false);
        this.entityData.define(CRYOBOLT_BLAST, false);
        this.entityData.define(SUDDEN_BLIZZARD, false);
    }

    @Override
    public void setUpAnimationStates() {
        super.setUpAnimationStates();

        // Set up animation states for all attacks
        this.setUpAttackAnimationStates(Attack.MELEE);
        this.setUpAttackAnimationStates(Attack.CRYOBOLT_BLAST);
        this.setUpAttackAnimationStates(Attack.SUDDEN_BLIZZARD);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_DEATH;
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Store variables needed for the attacks of the {@link FreezingReaperEntity}</li>
     * <li>Declared as an inner enum because this enum is only used for {@link FreezingReaperEntity}-related classes.</li>
     * <li>The only usage of this class outside the enclosing class is for the {@link net.laserdiamond.burningminesofbelow.entity.client.model.FreezingReaperModel} for setting up the animations</li>
     * <li>An {@link Attack} is-a {@link AttackSetUp}</li>
     * @author Allen Malo
     */
    public enum Attack implements AttackSetUp
    {
        MELEE (new AnimationState(), 20, FreezingReaperEntity.MELEE_ATTACKING),
        CRYOBOLT_BLAST(new AnimationState(), 40, FreezingReaperEntity.CRYOBOLT_BLAST),
        SUDDEN_BLIZZARD (new AnimationState(), 20, FreezingReaperEntity.SUDDEN_BLIZZARD);

        private final AnimationState animationState; // Attack has-a AnimationState
        private final int animationDuration;
        private final EntityDataAccessor<Boolean> entityDataAccessor; // Attack has-a EntityDataAccessor

        /**
         * Creates a new {@link Attack}
         * @param animationState The {@link AnimationState} for the attack
         * @param animationDuration The duration of the animation
         * @param entityDataAccessor The {@link EntityDataAccessor} of the attack
         */
        Attack(AnimationState animationState, int animationDuration, EntityDataAccessor<Boolean> entityDataAccessor) {
            this.animationState = animationState;
            this.animationDuration = animationDuration;
            this.entityDataAccessor = entityDataAccessor;
        }

        @Override
        public AnimationState getAnimationState() {
            return this.animationState;
        }

        @Override
        public int getAnimationDuration() {
            return this.animationDuration;
        }

        @Override
        public EntityDataAccessor<Boolean> getEntityDataAccessor() {
            return this.entityDataAccessor;
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Defines how the {@link FreezingReaperEntity}'s melee attack should operate, as well as the animation associated with it</li>
     * <li>Declared as a private static inner class because this class is only ever used for instances of the {@link FreezingReaperEntity} </li>
     * <li>A {@link FreezingReaperMeleeAttackGoal} is-a {@link AbstractMeleeAttackGoal}</li>
     * @author Allen Malo
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     */
    private static class FreezingReaperMeleeAttackGoal extends AbstractMeleeAttackGoal<FreezingReaperEntity> {

        /**
         * Creates a new {@link FreezingReaperMeleeAttackGoal}
         * @param pMob The {@link FreezingReaperEntity} performing the goal
         * @param pSpeedModifier The speed modifier of the {@link FreezingReaperEntity} when the goal is active
         * @param pFollowingTargetEvenIfNotSeen If the {@link FreezingReaperEntity} should continue following this goal even if the target is not seen
         */
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
            return MELEE_ATTACKING;
        }

        @Override
        protected void resetAttackCooldown() {

            this.ticksUntilNextAttack = this.adjustedTickDelay(this.attackDelay);
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Defines how the {@link FreezingReaperEntity}'s Cryobolt Blast attack goal should operate, as well as the animation associated with it</li>
     * <li>Declared as a private static inner class because this class is only ever used for instances of the {@link FreezingReaperEntity}</li>
     * <li>A {@link FreezingReaperCryoboltBlastAttackGoal} is-a {@link AbstractAnimatedAttackGoal}</li>
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     * @author Allen Malo
     */
    private static class FreezingReaperCryoboltBlastAttackGoal extends AbstractAnimatedAttackGoal<FreezingReaperEntity> {

        /**
         * Creates a new {@link FreezingReaperCryoboltBlastAttackGoal}
         * @param mob the {@link FreezingReaperEntity} performing the attack goal
         */
        public FreezingReaperCryoboltBlastAttackGoal(FreezingReaperEntity mob)
        {
            super(mob);
        }

        @Override
        protected int animationDuration() {
            return Attack.CRYOBOLT_BLAST.getAnimationDuration();
        }

        @Override
        protected int attackEndTime() {
            return Attack.CRYOBOLT_BLAST.getAnimationDuration() - 5;
        }

        @Override
        protected void attack(FreezingReaperEntity mob, LivingEntity target, ServerLevel serverLevel, int timer)
        {
            mob.getLookControl().setLookAt(target);
            mob.getLookControl().tick();

            if (timer % 5 == 0)
            {
                serverLevel.playSound(null, mob.getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.HOSTILE, 100F, 1F);
                float launchYaw = -20;
                for (int i = 0; i < 5; i++)
                {
                    Cryobolt cryobolt = new Cryobolt(mob, serverLevel, CONFIG.cyroBlastDamage(), CONFIG.attackFreezeDuration());
                    cryobolt.shootFromRotation(mob, mob.getXRot(), mob.getYRot() + launchYaw, 0.0F, 3.75F, 0.0F);
                    serverLevel.addFreshEntity(cryobolt);
                    launchYaw += 10;
                }
            }
        }

        @Override
        public int attackDelay() {
            return 10;
        }

        @Override
        public void resetAttackAnimationTimeOut() {
            this.mob.setAttackTimeout(Attack.CRYOBOLT_BLAST, 0);
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor() {
            return CRYOBOLT_BLAST;
        }

        @Override
        protected boolean isTargetValid(LivingEntity target)
        {
            final double distance = this.mob.distanceTo(target);

            return (distance >= 7) || this.mob.isAttacking(CRYOBOLT_BLAST);
        }
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Defines how the {@link FreezingReaperEntity}'s Sudden Blizzard attack should operate, as well as the animation associated with it</li>
     * <li>Declared as a private static inner class because this class is only ever used for instances of the {@link FreezingReaperEntity}</li>
     * <li>A {@link FreezingReaperSuddenBlizzardAttackGoal} is-a {@link AbstractAnimatedAttackGoal}</li>
     * @author Allen Malo
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     */
    private static class FreezingReaperSuddenBlizzardAttackGoal extends AbstractAnimatedAttackGoal<FreezingReaperEntity>
    {

        /**
         * Creates a new {@link FreezingReaperSuddenBlizzardAttackGoal}
         * @param mob The {@link FreezingReaperEntity} performing the attack goal
         */
        public FreezingReaperSuddenBlizzardAttackGoal(FreezingReaperEntity mob)
        {
            super(mob);
        }

        @Override
        protected int animationDuration() {
            return Attack.SUDDEN_BLIZZARD.getAnimationDuration();
        }

        @Override
        protected int attackEndTime() {
            return 10;
        }

        @Override
        protected void attack(FreezingReaperEntity mob, LivingEntity target, ServerLevel serverLevel, int timer)
        {
            mob.getLookControl().setLookAt(target);
            mob.getLookControl().tick();

            mob.moveTo(target.position()); // Move mob to the target

            // Loop through all living entities within the specified range of the Freezing Reaper
            // Don't want to strike other Freezing Reapers, so filter them out
            for (LivingEntity hitEntity : serverLevel.getEntitiesOfClass(LivingEntity.class, RayCast.createBBLivingEntity(mob, CONFIG.suddenBlizzardBlastRange()), (e -> !(e instanceof FreezingReaperEntity))))
            {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (lightningBolt != null)
                {
                    lightningBolt.moveTo(hitEntity.position());
                    serverLevel.addFreshEntity(lightningBolt);
                }

                hitEntity.hurt(hitEntity.damageSources().mobAttack(mob), CONFIG.suddenBlizzardDamage());
                hitEntity.setTicksFrozen(hitEntity.getTicksFrozen() + CONFIG.attackFreezeDuration());
            }

            serverLevel.playSound(null, mob.getOnPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 100F, 1F);
            serverLevel.explode(mob, mob.getX(), mob.getY(), mob.getZ(), FreezingReaperEntity.CONFIG.suddenBlizzardExplosionPower(), Level.ExplosionInteraction.MOB);
        }

        @Override
        public int attackDelay() {
            return 10;
        }

        @Override
        public void resetAttackAnimationTimeOut() {
            this.mob.setAttackTimeout(Attack.SUDDEN_BLIZZARD, 0);
        }

        @Override
        public EntityDataAccessor<Boolean> attackDataAccessor() {
            return SUDDEN_BLIZZARD;
        }

        @Override
        protected boolean isTargetValid(LivingEntity target) {
            if (target == null)
            {
                return false;
            }
            final double distance = this.mob.distanceTo(target);

            return (distance > this.mob.getAttributeValue(Attributes.FOLLOW_RANGE)) || this.mob.isAttacking(this.attackDataAccessor());
        }
    }
}

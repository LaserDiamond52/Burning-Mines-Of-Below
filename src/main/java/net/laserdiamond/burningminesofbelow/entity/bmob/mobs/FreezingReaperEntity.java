package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public final class FreezingReaperEntity extends AbstractBossMob<FreezingReaperEntity> implements MultiAttackingEntity<FreezingReaperEntity.Attack, FreezingReaperEntity> {

    public static final EntityDataAccessor<Boolean> MELEE_ATTACKING = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> ICE_BARRAGE = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SUDDEN_BLIZZARD = SynchedEntityData.defineId(FreezingReaperEntity.class, EntityDataSerializers.BOOLEAN);

    public final int[] attackTimeouts;

    public FreezingReaperEntity(EntityType<? extends FreezingReaperEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.attackTimeouts = new int[4];
        // TODO: Create Scythe item for mob to hold
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

        // TODO: Goals

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
        this.entityData.define(ICE_BARRAGE, false);
        this.entityData.define(SUDDEN_BLIZZARD, false);
    }

    @Override
    public void setUpAnimationStates() {
        super.setUpAnimationStates();

        // Set up animation states for all attacks
        this.setUpAttackAnimationStates(Attack.MELEE);
        this.setUpAttackAnimationStates(Attack.ICE_BARRAGE);
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

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 500D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 10D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.17D)
                .add(Attributes.FOLLOW_RANGE, 100D);
    }

    public enum Attack implements AttackSetUp
    {
        MELEE (new AnimationState(), 0, FreezingReaperEntity.MELEE_ATTACKING),
        ICE_BARRAGE (new AnimationState(), 0, FreezingReaperEntity.ICE_BARRAGE),
        SUDDEN_BLIZZARD (new AnimationState(), 0, FreezingReaperEntity.SUDDEN_BLIZZARD);

        private final AnimationState animationState;
        private final int animationDuration;
        private final EntityDataAccessor<Boolean> entityDataAccessor;

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
}

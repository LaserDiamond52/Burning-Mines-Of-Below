package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.ai.magniteblaze.MagniteBlazeAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.magniteblaze.MagniteBlazeSupportBlazesGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
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

public final class MagniteBlazeEntity extends Blaze implements AttackingEntity<MagniteBlazeEntity> {

    public static final EntityDataAccessor<Boolean> FIRE_WAVE_ATTACKING = SynchedEntityData.defineId(MagniteBlazeEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SUPPORTING_BLAZES = SynchedEntityData.defineId(MagniteBlazeEntity.class, EntityDataSerializers.BOOLEAN);
    private final AnimationState idleAnimationState;
    public static final int SUPPORT_INTERVAL = 300;
    public static final int SUPPORT_AMOUNT = 7;
    public static final int FIRE_BALL_LAUNCH_INTERVAL = 100;

    public MagniteBlazeEntity(EntityType<? extends Blaze> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.idleAnimationState = new AnimationState();
    }

    public AnimationState getIdleAnimationState() {
        return idleAnimationState;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
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
        this.goalSelector.addGoal(1, new MagniteBlazeSupportBlazesGoal(this, SUPPORT_INTERVAL));
        this.goalSelector.addGoal(1, new MagniteBlazeAttackGoal(this, FIRE_BALL_LAUNCH_INTERVAL));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(Blaze.class, MagniteBlazeEntity.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void onAddedToWorld() {
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

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 10D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 50D);
    }


}

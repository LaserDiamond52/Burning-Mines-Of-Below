package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.ai.AttackSetUp;
import net.laserdiamond.burningminesofbelow.entity.ai.kinginfernius.KingInferniusFireBreathAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.kinginfernius.KingInferniusHeatWaveGoal;
import net.laserdiamond.burningminesofbelow.entity.ai.kinginfernius.KingInferniusMeleeAttackGoal;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.file.mob.KingInferniusConfig;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class KingInferniusEntity extends AbstractBossMob<KingInferniusEntity> implements MultiAttackingEntity<KingInferniusEntity.Attack, KingInferniusEntity> {

    public static final KingInferniusConfig CONFIG = ((KingInferniusConfig) MobConfigRegistry.getRegistryMap().get(BMOBEntities.KING_INFERNIUS.getId()));

    public static final EntityDataAccessor<Boolean> MELEE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> FIRE_BREATH_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> HEAT_WAVE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> SOLAR_FLARE_ATTACKING = SynchedEntityData.defineId(KingInferniusEntity.class, EntityDataSerializers.BOOLEAN);

    public final int[] attackTimeouts;

    public KingInferniusEntity(EntityType<? extends KingInferniusEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.attackTimeouts = new int[4];
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        pEntity.setSecondsOnFire((pEntity.getRemainingFireTicks() * 20) + CONFIG.attackFireDuration());
        return super.doHurtTarget(pEntity);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(BMOBItems.BLAZIUM_SWORD.get()));
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

        this.goalSelector.addGoal(2, new KingInferniusMeleeAttackGoal(this, 2.0, true));
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

    public enum Attack implements AttackSetUp
    {
        MELEE (new AnimationState(), 30, MELEE_ATTACKING),
        FIRE_BREATH (new AnimationState(), 50, FIRE_BREATH_ATTACKING),
        HEAT_WAVE (new AnimationState(), 20, HEAT_WAVE_ATTACKING),
        SOLAR_FLARE (new AnimationState(), 80, SOLAR_FLARE_ATTACKING);

        private final AnimationState animationState;
        private final int animationDuration;
        private final EntityDataAccessor<Boolean> entityDataAccessor;

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
}

package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.file.mob.FrozenSoulConfig;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidType;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines how the Frozen Soul will behave in-game, and how the entity will respond to the environment around it</li>
 * @author Allen Malo
 */
public final class FrozenSoulEntity extends AbstractMonster<FrozenSoulEntity> implements AttackingEntity<FrozenSoulEntity> {

    private static final FrozenSoulConfig CONFIG = (FrozenSoulConfig) MobConfigRegistry.getRegistryMap().get(BMOBEntities.FROZEN_SOUL.getId());

    public FrozenSoulEntity(EntityType<? extends FrozenSoulEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public FrozenSoulEntity entity()
    {
        return this;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();

        // This entity should run away from Blazes, Magnite Blazes, and King Infernius
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Blaze.class, 60F, 1.5, 1.7));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, MagniteBlazeEntity.class, 60F, 1.5, 1.7));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, KingInferniusEntity.class, 60F, 1.5, 1.7));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(FrozenSoulEntity.class));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WitherSkeleton.class, true)); // If a Frozen Soul kills a Wither Skeleton, there should be a small chance of the Wither Skull dropping
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.GLASS_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    protected boolean isSunSensitive()
    {
        return true;
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity);
    }

    @Override
    public MobType getMobType()
    {
        return MobType.UNDEAD; // This should be an undead mob. The Smite enchantment will deal more damage to it
    }
}

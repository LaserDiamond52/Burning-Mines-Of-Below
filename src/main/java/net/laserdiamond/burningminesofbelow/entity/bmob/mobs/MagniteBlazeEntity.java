package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.client.animation.IdleAnimation;
import net.laserdiamond.burningminesofbelow.entity.client.animation.IdleEntityAnimator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MagniteBlazeEntity extends AbstractMonster<MagniteBlazeEntity> {

    public MagniteBlazeEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public int idleAnimationDuration() {
        return 80;
    }

    @Override
    public MagniteBlazeEntity entity() {
        return this;
    }

    @Override
    public RandomSource random() {
        return this.random;
    }



    @Override
    protected void registerGoals() {
        // TODO: Pathfinders
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    public boolean fireImmune() {
        return true;
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
                .add(Attributes.ATTACK_DAMAGE, 5D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 50D);
    }


}

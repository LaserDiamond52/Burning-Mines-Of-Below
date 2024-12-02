package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class AbstractMonster<M extends Monster> extends Monster {

    protected final AnimationState idleAnimationState;
    protected int idleTimeout;

    public AbstractMonster(EntityType<? extends M> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.idleAnimationState = new AnimationState();
        this.idleTimeout = 0;
    }

    public AnimationState getIdleAnimationState() {
        return idleAnimationState;
    }

    public void setUpAnimationStates()
    {
        if (this.idleTimeout <= 0)
        {
            this.idleTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else
        {
            this.idleTimeout--;
        }
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this)); // This goal should be used by all mobs. We want them to be able to swim afloat
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide)
        {
            this.setUpAnimationStates();
        }
    }

    @Override
    public void aiStep() {
        if (this.isAlive())
        {
            boolean flag = this.isSunSensitive() && this.isSunBurnTick(); // Check if the mob is vulnerable to sunlight and a sun burn tick is occurring
            if (flag)
            {
                ItemStack itemstack = this.getItemBySlot(EquipmentSlot.HEAD); // Get the armor piece on their head
                if (!itemstack.isEmpty()) // Check if something is in the slot
                {
                    // An item is in the head slot
                    if (itemstack.isDamageableItem()) // Is the item in the head slot damageable?
                    {
                        itemstack.setDamageValue(itemstack.getDamageValue() + this.random.nextInt(2)); // Damage the item
                        if (itemstack.getDamageValue() >= itemstack.getMaxDamage()) // Has the item broken yet?
                        {
                            this.broadcastBreakEvent(EquipmentSlot.HEAD); // Item breaks
                            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY); // Nothing should be in the mob's head slot now
                        }
                    }

                    flag = false; // Mob is not set on fire
                }

                if (flag) // Should we set the mob on fire?
                {
                    this.setSecondsOnFire(8); // Set on fire
                }
            }
        }
        super.aiStep();
    }

    /**
     * Determines if the mob should burn during the day, similar to most undead mobs (ex: zombies, skeletons)
     * @return True if the mob should burn during the day, false otherwise
     */
    protected boolean isSunSensitive()
    {
        return false;
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING)
        {
            f = Math.min(pPartialTick * 6F, 1F);
        } else
        {
            f = 0;
        }
        this.walkAnimation.update(f, 0.2F);
    }

    /**
     * The {@link SoundEvent} to play when the mob takes a step
     * @return The {@link SoundEvent} for when the mob takes a step
     */
    @Nullable
    protected SoundEvent getStepSound()
    {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pState) {
        if (this.getStepSound() != null)
        {
            this.playSound(this.getStepSound(), 0.15F, 1.0F);
            return;
        }
        super.playStepSound(pPos, pState);
    }
}

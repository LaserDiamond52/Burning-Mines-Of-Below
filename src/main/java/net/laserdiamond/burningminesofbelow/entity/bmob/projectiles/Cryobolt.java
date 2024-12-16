package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Determines the properties and characteristics of a {@link Cryobolt} projectile</li>
 * <li>A {@link Cryobolt} is-a {@link AbstractArrow}</li>
 * @author Allen Malo
 */
public class Cryobolt extends AbstractArrow {

    /**
     * The amount of time in ticks the hit {@link Entity} will be frozen for
     */
    private final int frozenTicks;

    /**
     * Creates a new {@link Cryobolt}
     * @param pEntityType The {@link EntityType} of the {@link Cryobolt}
     * @param pLevel The {@link Level} to summon the projectile on
     */
    public Cryobolt(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.frozenTicks = 120;
    }

    /**
     * Summons a new {@link Cryobolt}
     * @param shooter The {@link LivingEntity} shooting the projectile
     * @param level The {@link Level} to summon the projectile on
     * @param damage The base damage the projectile inflicts on hit entities
     * @param frozenTicks The amount of time in ticks the hit {@link Entity} will be frozen for
     */
    public Cryobolt(LivingEntity shooter, Level level, float damage, int frozenTicks)
    {
        super(BMOBEntities.CYROBOLT.get(), shooter, level);
        this.baseDamage = damage;
        this.frozenTicks = frozenTicks;
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY; // No item on pickup
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (this.level().isClientSide)
        {
            return; // Don't run on client
        }
        final Entity hitEntity = pResult.getEntity();
        if (hitEntity instanceof LivingEntity livingEntity)
        {
            livingEntity.setTicksFrozen(livingEntity.getTicksFrozen() + this.frozenTicks);
        }
        this.discard(); // Discard Entity
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        this.discard(); // Discard Entity
    }

    @Override
    public void tick() {
        super.tick();
        final Level level = this.level();
        if (!level.isClientSide)
        {
            ServerLevel serverLevel = (ServerLevel) level;
            ParticleOptions particleOptions = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.ICE.defaultBlockState());
            serverLevel.sendParticles(particleOptions, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
            if (this.isOnFire())
            {
                level.playSound(null, this.blockPosition(), SoundEvents.LAVA_EXTINGUISH, SoundSource.AMBIENT, 100, 1);
                this.discard(); // Cryobolt should not be able to be set on fire. Discard entity if so
            }
        }

    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.GLASS_BREAK;
    }
}

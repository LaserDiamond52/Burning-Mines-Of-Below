package net.laserdiamond.burningminesofbelow.entity.ai.magniteblaze;

import net.laserdiamond.burningminesofbelow.entity.ai.AbstractAttackGoal;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.MagniteBlazeFireBall;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MagniteBlazeSupportBlazesGoal extends AbstractAttackGoal<MagniteBlazeEntity> {

    public MagniteBlazeSupportBlazesGoal(MagniteBlazeEntity mob, int interval)
    {
        super(mob, interval);
    }

    @Override
    protected void attack(MagniteBlazeEntity mob, LivingEntity target, ServerLevel serverLevel, int timer)
    {
        ArrayList<Blaze> blazes = new ArrayList<>(); // Create list to store blazes we want to buff
        for (Blaze blaze : serverLevel.getEntitiesOfClass(Blaze.class, new AABB(new BlockPos(this.mob.getBlockX() - 15, this.mob.getBlockY() - 15, this.mob.getBlockZ() - 15), new BlockPos(this.mob.getBlockX() + 15, this.mob.getBlockY() + 15, this.mob.getBlockZ() + 15)))) // Get all blazes within a 30x30x30 block distance from the Magnite Blaze
        {
            if (blaze instanceof MagniteBlazeEntity)
            {
                continue; // Do not add Magnite Blazes here
            }
            blazes.add(blaze); // Add blaze
            if (blazes.size() == MagniteBlazeEntity.SUPPORT_AMOUNT)
            {
                break; // Can only buff a set amount of blazes. This is done to prevent players from using this mob as a "lag machine"
            }
        }

        if (blazes.isEmpty())
        {
            return; // No blazes found. End method
        }

        this.mob.level().playSound(null, this.mob.getOnPos(), SoundEvents.WITHER_SPAWN, SoundSource.HOSTILE, 100, 1F); // Play sound to warn player

        // Loop through all blazes detected
        blazes.forEach(blaze ->
        {
            // Fire ray cast at them. This is purely for cosmetic purposes, as the ray casts themselves do not have any effect on the Blazes themselves
            RayCast.createRayCast(serverLevel, this.mob.position().add(0, this.mob.getBbHeight() / 2, 0), Entity::isAttackable, Blaze.class, List.of())
                    .setCanPierceBlocks()
                    .setCanPierceEntities()
                    .addParticle(ParticleTypes.FLAME)
                    .fireAtVec3D(blaze.position().add(0, blaze.getBbHeight() / 2, 0), 0);

            // Buff Blazes
            blaze.setHealth(blaze.getMaxHealth()); // Heal blaze
            blaze.setAbsorptionAmount(blaze.getAbsorptionAmount() + 10F); // Grant blaze absorption hearts. Stack on top of current ones
            blaze.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 2)); // Grant blaze resistance effect
            blaze.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2)); // Grant blaze regeneration

            if (target != null)
            {
                blaze.setTarget(target); // All buffed blazes will now target the Magnite Blaze's target
            }
        });
    }

    @Override
    public EntityDataAccessor<Boolean> attackDataAccessor() {
        return MagniteBlazeEntity.SUPPORTING_BLAZES;
    }
}

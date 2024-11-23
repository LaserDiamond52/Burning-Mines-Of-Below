package net.laserdiamond.burningminesofbelow.entity.ai;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.util.RayCast;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MagniteBlazeAttackGoal extends Goal {

    private final MagniteBlazeEntity magniteBlazeEntity;

    public MagniteBlazeAttackGoal(MagniteBlazeEntity magniteBlazeEntity)
    {
        this.magniteBlazeEntity = magniteBlazeEntity;
    }

    @Override
    public boolean canUse() {
        return this.magniteBlazeEntity.isAlive();
    }

    @Override
    public void stop() {
        super.stop();
        this.magniteBlazeEntity.setAttacking(false);
    }

    @Override
    public void tick()
    {
        if (!this.magniteBlazeEntity.level().isClientSide) // Ensure we are on the server
        {
            if (this.magniteBlazeEntity.tickCount % this.magniteBlazeEntity.getSupportInterval() == 0) // Only fire this move when on the correct interval
            {
                // TODO: Play a sound to notify the player
                ServerLevel serverLevel = this.magniteBlazeEntity.level().getServer().getLevel(this.magniteBlazeEntity.getCommandSenderWorld().dimension()); // Get the ServerLevel
                ArrayList<Blaze> blazes = new ArrayList<>(); // Create list to store blazes we want to buff
                for (Blaze blaze : this.magniteBlazeEntity.level().getEntitiesOfClass(Blaze.class, new AABB(this.magniteBlazeEntity.getOnPos(), new BlockPos(10, 10, 10)))) // Get all blazes within a 10x10x10 block distance from the Magnite Blaze
                {
                    if (blaze instanceof MagniteBlazeEntity)
                    {
                        continue; // Do not add Magnite Blazes here
                    }
                    blazes.add(blaze); // Add blaze
                    if (blazes.size() == this.magniteBlazeEntity.getSupportAmount())
                    {
                        break; // Can only buff a set amount of blazes. This is done to prevent players from using this mob as a "lag machine"
                    }
                }

                if (blazes.isEmpty()) // No nearby blazes. Do something to defend
                {
                    this.magniteBlazeEntity.setAttacking(true);
                    // TODO: Summon blazes nearby, launch fire balls, etc.
                    return;
                }
                // Nearby blazes found!
                this.magniteBlazeEntity.setAttacking(false); // Magnite Blaze is no longer attacking

                // Loop through all blazes detected
                blazes.forEach(blaze ->
                {
                    // Fire ray cast at them
                    RayCast.createRayCast(serverLevel, this.magniteBlazeEntity.position(), Optional.of(Entity::isAttackable), Blaze.class, List.of())
                            .setCanPierceBlocks()
                            .setCanPierceEntities()
                            .setParticle(ParticleTypes.FLAME)
                            .fireAtVec3D(blaze.position().add(0, blaze.getBbHeight() / 2, 0), 0);

                    blaze.setHealth(blaze.getMaxHealth()); // Heal blaze
                    blaze.setAbsorptionAmount(15F); // Grant blaze absorption hearts
                    blaze.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 2)); // Grant blaze resistance effect
                });
            }
        }
    }
}

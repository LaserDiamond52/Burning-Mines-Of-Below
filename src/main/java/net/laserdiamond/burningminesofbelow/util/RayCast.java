package net.laserdiamond.burningminesofbelow.util;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Class used for creating ray casts/lasers
 * @param <E> {@link Entity} class to target
 */
public class RayCast<E extends Entity> {

    private final ServerLevel serverLevel;
    private final Vec3 startPos;
    private double stepIncrement;
    private final Optional<Predicate<E>> entityFilter;
    private final Class<E> entityClazz;
    private final List<Class<? extends Block>> blockClazzes;
    private SimpleParticleType particle;
    private boolean pierceBlocks;
    private boolean pierceEntities;
    private final List<E> hitEntities;
    private final List<BlockState> hitBlockStates;

    private RayCast(ServerLevel serverLevel, Vec3 startPos, Optional<Predicate<E>> entityFilter, Class<E> entityClazz, List<Class<? extends Block>> blockClazzes)
    {
        this.serverLevel = serverLevel;
        this.startPos = startPos;
        this.entityFilter = entityFilter;
        this.entityClazz = entityClazz;
        this.blockClazzes = new ArrayList<>();
        this.stepIncrement = 0.3;
        this.particle = null;
        this.hitEntities = new ArrayList<>();
        this.hitBlockStates = new ArrayList<>();
        this.pierceBlocks = false;
        this.pierceEntities = false;
    }

    public static <EN extends Entity> RayCast<EN> createRayCast(ServerLevel serverLevel, Vec3 startPos, Optional<Predicate<EN>> entityFilter, Class<EN> entityClazz, List<Class<? extends Block>> blockClazzes)
    {
        return new RayCast<>(serverLevel, startPos, entityFilter, entityClazz, blockClazzes);
    }

    /**
     * Allows the ray cast to go through blocks
     * @return {@link RayCast} instance
     */
    public RayCast<E> setCanPierceBlocks()
    {
        this.pierceBlocks = true;
        return this;
    }

    /**
     * Allows the ray cast to go through entities
     * @return {@link RayCast} instance
     */
    public RayCast<E> setCanPierceEntities()
    {
        this.pierceEntities = true;
        return this;
    }

    /**
     * Sets the step increment of the ray cast.
     * A smaller step increment can result in a shorter firing distance,
     * and a larger step increment will result in a longer firing distance.
     * Visible if particles are not null
     * @param stepIncrement the step increment
     * @return {@link RayCast} instance
     * @throws IllegalStateException If the step increment entered is equal to or less than 0
     */
    public RayCast<E> setStepIncrement(double stepIncrement) throws IllegalStateException
    {
        this.stepIncrement = stepIncrement;
        if (this.stepIncrement <= 0)
        {
            throw new IllegalStateException("Step increment for ray cast cannot be less than 0! Value offered: " + this.stepIncrement);
        }
        return this;
    }

    /**
     * Sets the particle to be displayed at each step of the ray cast
     * @param particle The {@link SimpleParticleType} to display at each step
     * @return {@link RayCast} instance
     */
    public RayCast<E> setParticle(SimpleParticleType particle)
    {
        this.particle = particle;
        return this;
    }

    /**
     * Fires the {@link RayCast} in the direction of the ray
     * @param ray The ray to shoot the ray cast from
     * @param distance The distance of the {@link RayCast}
     * @return {@link RayCast} instance
     */
    public RayCast<E> fireInDirection(Vec3 ray, double distance)
    {
        Vec3 normalizeRay = ray.normalize();
        rayCast(normalizeRay, distance);
        return this;
    }

    /**
     * Fires the {@link RayCast} to the destination {@link Vec3}, and past if the overshoot distance is greater than 0
     * @param destination The destination of the {@link RayCast}
     * @param overshootDistance The distance to overshoot after reaching the destination vector
     * @return {@link RayCast} instance
     */
    public RayCast<E> fireAtVec3D(Vec3 destination, double overshootDistance)
    {
        Vec3 sub = destination.subtract(this.startPos);
        Vec3 normalizeDestination = sub.normalize();
        rayCast(normalizeDestination, Mth.floor(sub.length()) + overshootDistance);
        return this;
    }

    /**
     * Helper method that contains the logic of the {@link RayCast}
     * @param rayCastVec The starting position of the {@link RayCast}
     * @param distance The distance for the {@link RayCast} to travel
     */
    private void rayCast(Vec3 rayCastVec, double distance)
    {
        if (!this.hitEntities.isEmpty())
        {
            this.hitEntities.clear();
        }
        if (!this.hitBlockStates.isEmpty())
        {
            this.hitBlockStates.clear();
        }

        for (double i = 0; i < distance; i += stepIncrement)
        {
            Vec3 rayCast = startPos.add(rayCastVec.scale(i));
            AABB aabb = new AABB(rayCast, rayCast);

            BlockPos blockPos = new BlockPos((int) rayCast.x, (int) rayCast.y, (int) rayCast.z);
            BlockState blockState = serverLevel.getBlockState(blockPos);
            Block hitBlock = blockState.getBlock();

            if (!blockClazzes.contains(hitBlock.getClass()))
            {
                hitBlockStates.add(blockState);
            }

            if (!hitBlockStates.contains(blockState))
            {
                hitBlockStates.add(blockState);
            }

            if (!pierceBlocks && blockState.isSolid())
            {
                return; // Pierce blocks is false and the blockState hit is solid
            }

            for (E e : serverLevel.getEntitiesOfClass(entityClazz, aabb, entityFilter.orElse(Predicates.alwaysTrue())))
            {
                AABB entityBB = e.getBoundingBox();
                if (entityBB.intersects(aabb) && !hitEntities.contains(e))
                {
                    hitEntities.add(e);
                    if (!pierceEntities)
                    {
                        return; // Pierce entities is false and entity was hit
                    }
                }
            }

            if (particle != null)
            {
                serverLevel.sendParticles(particle, rayCast.x, rayCast.y, rayCast.z, 1,0.0,0.0,0.0,0.0);
            }
        }
    }

    /**
     * Gets a copy of the {@link ArrayList} that contains all the hit entities from the {@link RayCast}.
     * This {@link ArrayList} is cleared and refilled with all hit entities when a new ray cast is fired.
     * @return An {@link ArrayList} containing all the hit entities in the ray cast
     */
    public List<E> getHitEntities()
    {
        return new ArrayList<>(this.hitEntities);
    }

    /**
     * Gets a copy of the {@link ArrayList} that contains all the hit block states from the {@link RayCast}.
     * This {@link ArrayList} is cleared and refilled with all hit entities when a new ray cast is fired.
     * @return An {@link ArrayList} containing all the hit block states in the ray cast
     */
    public List<BlockState> getHitBlockStates()
    {
        return new ArrayList<>(this.hitBlockStates);
    }
}

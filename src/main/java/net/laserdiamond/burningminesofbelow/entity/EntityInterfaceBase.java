package net.laserdiamond.burningminesofbelow.entity;

import net.minecraft.world.entity.Entity;

/**
 * Base interface for helping with other interfaces that involve the use of an {@link Entity} object.
 * @param <E> The {@link Entity} class
 */
public interface EntityInterfaceBase<E extends Entity> {

    /**
     * The {@link Entity} object for use with this interface
     * @return An object instance of the {@link Entity}
     */
    E entity();
}

package net.laserdiamond.burningminesofbelow.entity;

import net.minecraft.world.entity.Entity;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Act as a root interface for subclass interfaces that require an {@link Entity} object for their functionality</li>
 * @author Allen Malo
 * @param <E> The {@link Entity} class
 */
public interface EntityInterfaceBase<E extends Entity> {

    /**
     * The {@link Entity} object for use with this interface
     * @return An object instance of the {@link Entity}
     */
    E entity();
}

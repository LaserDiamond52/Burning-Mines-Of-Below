package net.laserdiamond.burningminesofbelow.util;

import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * Class used to help map names to new objects and features of this mod
 * @param <T> The {@link RegistryObject} type
 */
public final class NameRegistry<T> {

    private final HashMap<String, RegistryObject<T>> registryMap;

    public NameRegistry()
    {
        this.registryMap = new HashMap<>();
    }

    /**
     * Adds a new entry to the name registry
     * @param name The name of the object
     * @param registryObject The object being named
     */
    public void addEntry(String name, RegistryObject<T> registryObject)
    {
        this.registryMap.put(name, registryObject);
    }

    /**
     * Returns a deep copy of the name registry map
     * @return A deep copy of the name registry map
     */
    public HashMap<String, RegistryObject<T>> getRegistryMap() {
        return new HashMap<>(this.registryMap);
    }
}

package net.laserdiamond.burningminesofbelow.util;

import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * Class used to help map names to new objects and features of this mod
 * @param <T> The {@link RegistryObject} type
 */
public class NameRegistry<T> {

    /**
     * The {@link HashMap} containing all the {@link RegistryObject}s and their names
     */
    private final HashMap<RegistryObject<T>, String> registryMap; // Can be multiple instances of this registryMap

    protected NameRegistry()
    {
        this.registryMap = new HashMap<>(); // Create the HashMap to map the Name to the RegistryObject
    }

    /**
     * Adds a new entry to the name registry
     * @param name The name of the object
     * @param registryObject The object being named
     */
    public void addEntry(String name, RegistryObject<T> registryObject)
    {
        this.registryMap.put(registryObject, name);
    }

    /**
     * Returns a deep copy of the name registry map
     * @return A deep copy of the name registry map
     */
    public HashMap<RegistryObject<T>, String> getRegistryMap()
    {
        final HashMap<RegistryObject<T>, String> returnMap = new HashMap<>();
        for (RegistryObject<T> object : this.registryMap.keySet())
        {
            returnMap.put(object, this.registryMap.get(object));
        }
        return returnMap;
    }
}

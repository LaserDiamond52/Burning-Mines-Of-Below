package net.laserdiamond.burningminesofbelow.util;

import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to register names to new assets of this mod for use with the {@link LanguageRegistry}</li>
 * @author Allen Malo
 * @param <T> The object type that will be the key for the names
 */
public class NameRegistry<T> {

    /**
     * The {@link HashMap} containing all the {@link RegistryObject}s and their names
     */
    private final HashMap<T, String> registryMap; // A NameRegistry has-a HashMap

    /**
     * Creates a new {@link NameRegistry}
     */
    protected NameRegistry()
    {
        this.registryMap = new HashMap<>(); // Create the HashMap to map the Name to the RegistryObject
    }

    /**
     * Adds a new entry to the name registry
     * @param name The name of the object
     * @param object The object being named
     * @throws IllegalArgumentException If the object already has a name mapped to it
     */
    public void addEntry(String name, T object) throws IllegalArgumentException
    {
        if (this.registryMap.get(object) != null || this.registryMap.containsKey(object))
        {
            throw new IllegalArgumentException("A name has already been given to this object!");
        }
        this.registryMap.put(object, name);
    }

    /**
     * Returns a deep copy of the name registry map
     * @return A deep copy of the name registry map
     */
    public HashMap<T, String> getRegistryMap()
    {
        final HashMap<T, String> returnMap = new HashMap<>();
        for (T object : this.registryMap.keySet())
        {
            returnMap.put(object, this.registryMap.get(object));
        }
        return returnMap;
    }
}

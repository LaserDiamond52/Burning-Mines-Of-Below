package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.world.item.Item;

import java.util.HashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to allow Vanilla items to be used as fuel items for the Forge</li>
 * @author Allen Malo
 */
public final class FuelItemRegistry {

    /**
     * A {@link HashMap} that maps an {@link Item} to the amount of fuel it will add to the Forge
     */
    private final HashMap<Item, Integer> fuelItemMap; // A FuelItemRegistry has-a HashMap

    /**
     * Creates a new FuelItemRegistry
     */
    public FuelItemRegistry()
    {
        this.fuelItemMap = new HashMap<>();
    }

    /**
     * Adds a new entry to the {@link FuelItemRegistry}
     * @param fuelItem The {@link Item} that will be used as a fuel item
     * @param fuelAmount The amount of fuel it will add
     * @return {@link FuelItemRegistry} instance
     */
    public FuelItemRegistry addEntry(Item fuelItem, Integer fuelAmount)
    {
        if (this.fuelItemMap.get(fuelItem) == null || !this.fuelItemMap.containsKey(fuelItem))
        {
            this.fuelItemMap.put(fuelItem, fuelAmount);
        }
        return this;
    }

    /**
     * Gets the amount of fuel the {@link Item} adds
     * @param fuelItem The {@link Item} to get the fuel amount for
     * @return The amount of fuel the {@link Item} can add to the Forge. Returns 0 if the {@link Item} isn't in the registry
     */
    public Integer getFuelAmount(Item fuelItem)
    {
        if (this.fuelItemMap.get(fuelItem) == null || !this.fuelItemMap.containsKey(fuelItem))
        {
            return 0;
        }
        return this.fuelItemMap.get(fuelItem);
    }

    /**
     * Gets a copy of the {@link HashMap}
     * @return A copy of the {@link HashMap} for the fuel items
     */
    public HashMap<Item, Integer> getFuelItemMap()
    {
        final HashMap<Item, Integer> returnMap = new HashMap<>();
        for (Item item : this.fuelItemMap.keySet())
        {
            returnMap.put(item, this.fuelItemMap.get(item));
        }
        return returnMap;
    }
}

package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.world.item.Item;

import java.util.HashMap;

public final class FuelItemRegistry {

    private final HashMap<Item, Integer> fuelItemMap;

    public FuelItemRegistry()
    {
        this.fuelItemMap = new HashMap<>();
    }

    public FuelItemRegistry addEntry(Item fuelItem, Integer fuelAmount)
    {
        if (this.fuelItemMap.get(fuelItem) == null || !this.fuelItemMap.containsKey(fuelItem))
        {
            this.fuelItemMap.put(fuelItem, fuelAmount);
        }
        return this;
    }

    public Integer getFuelAmount(Item fuelItem)
    {
        if (this.fuelItemMap.get(fuelItem) == null || !this.fuelItemMap.containsKey(fuelItem))
        {
            return 0;
        }
        return this.fuelItemMap.get(fuelItem);
    }

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

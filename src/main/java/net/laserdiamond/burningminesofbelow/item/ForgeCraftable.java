package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.world.item.Item;

import java.util.List;

/**
 * Interface for items that can be crafted in the Forge. The subclass of this interface is the crafting result
 */
public interface ForgeCraftable {

    /**
     * The main item that will be used to craft the desired item
     * @return An {@link Item} depicting the main crafting ingredient
     */
    Item mainItem();

    /**
     * The different options of miscellaneous items that can be used to complete the recipe
     * @return A {@link List} of {@link Item} objects that depict the different choices of ingredient to complete the recipe
     */
    List<Item> miscItems();

    /**
     * The crafting level of the Forge required to run the recipe
     * @return An integer depicting the crafting level of the Forge required to run the recipe
     */
    int forgeCraftingLevel();

    /**
     * The heat fuel cost
     * @return The fuel cost as an integer
     */
    int heatFuelCost();

    /**
     * The freeze fuel cost
     * @return The fuel cost as an integer
     */
    int freezeFuelCost();
}

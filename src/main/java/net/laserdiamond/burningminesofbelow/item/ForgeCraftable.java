package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

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
    Ingredient miscItems();

    /**
     * The crafting level of the Forge required to run the recipe
     * @return An integer depicting the crafting level of the Forge required to run the recipe
     */
    int forgeCraftingLevel();

    /**
     * The amount of the result item to create when the crafting recipe is complete.
     * This value cannot be less than 1, or greater than the maximum stack size of the item.
     * Values greater than the max stack size of the item will set the result count to the max stack size of the item.
     * Values less than 1 will set the result count to 1.
     * @return The amount of the result item to make for completing the recipe.
     */
    default int resultCount()
    {
        return 1;
    }

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

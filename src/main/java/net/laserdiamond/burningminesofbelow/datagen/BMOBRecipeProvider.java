package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.laserdiamond.burningminesofbelow.recipe.ForgeRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates the recipes of this mod</li>
 * <li>A {@link BMOBRecipeProvider} is-a {@link RecipeProvider}</li>
 * <li>A {@link BMOBRecipeProvider} is-a {@link IConditionBuilder}</li>
 * @author Allen Malo
 */
public class BMOBRecipeProvider extends RecipeProvider implements IConditionBuilder {

    /**
     * Creates a new {@link BMOBRecipeProvider}
     * @param pOutput The {@link PackOutput} of this mod
     */
    public BMOBRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    /**
     * Creates all the recipes
     * @param consumer the {@link FinishedRecipe} {@link Consumer}
     */
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        for (RegistryObject<Item> itemRegistryObject : BMOBItems.ITEMS.getEntries()) // Loop through all items in the registry
        {
            Item item = itemRegistryObject.get(); // Get the item from the registry object
            if (item instanceof ForgeCraftable forgeCraftable) // Create a forge recipe for the item if it is-a ForgeCraftable
            {
                this.forgeRecipe(forgeCraftable.mainItem(), forgeCraftable.miscItems(), item, forgeCraftable.resultCount(), forgeCraftable.heatFuelCost(), forgeCraftable.freezeFuelCost(), forgeCraftable.forgeCraftingLevel(), consumer);
            }
        }
    }

    /**
     * Creates a new Forge crafting recipe
     * @param mainItem The main crafting item
     * @param miscItems The miscellaneous items that can be added
     * @param resultItem The result item
     * @param resultItemCount The amount of the result item to output
     * @param heatFuelCost The amount of heat fuel required to start the recipe
     * @param freezeFuelCost The amount of freeze fuel required to start the recipe
     * @param forgeLevel The minimum Forge level required to start the recipe
     * @param finishedRecipeConsumer The {@link Consumer} for the {@link FinishedRecipe}
     */
    private void forgeRecipe(Item mainItem, Ingredient miscItems, Item resultItem, int resultItemCount, int heatFuelCost, int freezeFuelCost, int forgeLevel, Consumer<FinishedRecipe> finishedRecipeConsumer)
    {
        resultItemCount = Math.max(1, Math.min(resultItemCount, resultItem.getMaxStackSize(resultItem.getDefaultInstance())));
        ForgeRecipeBuilder.createRecipe(Ingredient.of(mainItem), miscItems, resultItem, resultItemCount, heatFuelCost, freezeFuelCost, forgeLevel).save(finishedRecipeConsumer, BurningMinesOfBelow.MODID + ":" + getItemName(resultItem) + "_from_forge_level_" + forgeLevel);
    }
}

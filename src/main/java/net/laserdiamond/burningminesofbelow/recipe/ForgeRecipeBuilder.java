package net.laserdiamond.burningminesofbelow.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Helps with creating a {@link ForgeRecipe} for the {@link net.laserdiamond.burningminesofbelow.datagen.BMOBRecipeProvider} to create in data generation</li>
 * <li>A {@link ForgeRecipeBuilder} is-a {@link RecipeBuilder}</li>
 * @author Allen Malo
 * @see net.minecraft.data.recipes.SimpleCookingRecipeBuilder
 */
public class ForgeRecipeBuilder implements RecipeBuilder {

    /**
     * The main {@link Ingredient} of the recipe
     */
    private final Ingredient mainItem;

    /**
     * The miscellaneous {@link Ingredient} of the recipe
     */
    private final Ingredient miscItem;

    /**
     * The result {@link Item} of the recipe
     */
    private final Item resultItem;

    /**
     * The amount of the result item to create
     */
    private final int resultItemCount;

    /**
     * The amount of heat fuel needed to complete the recipe
     */
    private final int heatFuelCost;

    /**
     * The amount of freeze fuel needed to complete the recipe
     */
    private final int freezeFuelCost;

    /**
     * The minimum Forge level required to complete the recipe
     */
    private final int forgeLevel;

    /**
     * Creates a new {@link ForgeRecipeBuilder}
     * @param mainItem The main item in the recipe
     * @param miscItem the miscellaneous item in the recipe
     * @param resultItem The result item in the recipe
     * @param resultItemCount The amount of the result item to make in the recipe
     * @param heatFuelCost The required amount of heat fuel to complete the recipe
     * @param freezeFuelCost The required amount of freeze fuel to complete the recipe
     * @param forgeLevel The Forge level required to complete the recipe
     */
    private ForgeRecipeBuilder(Ingredient mainItem, Ingredient miscItem, Item resultItem, int resultItemCount, int heatFuelCost, int freezeFuelCost, int forgeLevel) {
        this.mainItem = mainItem;
        this.miscItem = miscItem;
        this.resultItem = resultItem;
        this.resultItemCount = resultItemCount;
        this.heatFuelCost = heatFuelCost;
        this.freezeFuelCost = freezeFuelCost;
        this.forgeLevel = forgeLevel;
    }

    /**
     * Starts a new Forge crafting recipe
     * @param mainItem The main item of the recipe. This is the item being upgraded
     * @param miscItem The miscellaneous item of the recipe.
     * @param resultItem The output item of the recipe
     * @param resultItemCount The amount of the result item to deliver
     * @param heatFuelCost The heat fuel cost to conduct the recipe
     * @param freezeFuelCost The freeze fuel cost to conduct the recipe
     * @param forgeLevel The Forge level required to start the recipe
     * @return {@link ForgeRecipeBuilder} instance
     */
    public static ForgeRecipeBuilder createRecipe(Ingredient mainItem, Ingredient miscItem, Item resultItem, int resultItemCount, int heatFuelCost, int freezeFuelCost, int forgeLevel)
    {
        return new ForgeRecipeBuilder(mainItem, miscItem, resultItem, resultItemCount, heatFuelCost, freezeFuelCost, forgeLevel);
    }

    @Override
    public RecipeBuilder unlockedBy(String s, CriterionTriggerInstance criterionTriggerInstance) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String s) {
        return this;
    }

    @Override
    public Item getResult() {
        return this.resultItem;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new Result(resourceLocation, this));
    }

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Serializer for the {@link ForgeRecipeBuilder}</li>
     * <li>Saves the {@link ForgeRecipe} to a json file in the data folder</li>
     * <li>This class is declared as a static inner class because it is only every used for the encompassing class</li>
     * <li>A {@link Result} is-a {@link FinishedRecipe}</li>
     * @author Allen Malo
     */
    static class Result implements FinishedRecipe
    {
        /**
         * The {@link ResourceLocation} ID of the recipe
         */
        private final ResourceLocation id;

        /**
         * The {@link ForgeRecipeBuilder} of the recipe
         */
        private final ForgeRecipeBuilder recipeBuilder;

        /**
         * Creates a new {@link Result}
         * @param id The {@link ResourceLocation} ID for the recipe
         * @param recipeBuilder The {@link ForgeRecipeBuilder} of the recipe
         */
        private Result(ResourceLocation id, ForgeRecipeBuilder recipeBuilder) {
            this.id = id;
            this.recipeBuilder = recipeBuilder;
        }

        /**
         * Writes the recipe to the {@link JsonObject}.
         * This {@link JsonObject} is saved to a json file in the data folder
         * @param jsonObject The {@link JsonObject} being written to
         */
        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            jsonObject.addProperty("type", ForgeRecipe.Serializer.ID.toString());

            jsonObject.add("main_ingredient", this.recipeBuilder.mainItem.toJson());
            jsonObject.add("misc_ingredient", this.recipeBuilder.miscItem.toJson());

            jsonObject.addProperty("heat_fuel_cost", this.recipeBuilder.heatFuelCost);
            jsonObject.addProperty("freeze_fuel_cost", this.recipeBuilder.freezeFuelCost);
            jsonObject.addProperty("forge_level", this.recipeBuilder.forgeLevel);

            JsonObject resultObj = new JsonObject();
            resultObj.addProperty("item", BuiltInRegistries.ITEM.getKey(this.recipeBuilder.resultItem).toString());
            resultObj.addProperty("count", this.recipeBuilder.resultItemCount);

            jsonObject.add("result", resultObj);

        }

        /**
         * Gets the {@link ResourceLocation} ID of the recipe
         * @return The ID of the recipe as a {@link ResourceLocation}
         */
        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        /**
         * Gets the {@link RecipeSerializer} for the recipe
         * @return The {@link RecipeSerializer} of the recipe
         */
        @Override
        public RecipeSerializer<?> getType() {
            return BMOBRecipes.FORGE_RECIPE.get();
        }

        /**
         * Serializes the advancement for unlocking the recipe
         * @return A {@link JsonObject} that contains the serialized advancement for the recipe
         */
        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        /**
         * Gets the {@link ResourceLocation} of the advancement ID
         * @return The ID of the advancement for the recipe
         */
        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}

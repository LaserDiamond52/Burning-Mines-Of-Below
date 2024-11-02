package net.laserdiamond.burningminesofbelow.recipe;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
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
 * Recipe builder for a Forge crafting recipe
 */
public class ForgeRecipeBuilder implements RecipeBuilder {

    private final Ingredient mainItem;
    private final Ingredient miscItem;
    private final Item resultItem;
    private final int resultItemCount;
    private final int heatFuelCost;
    private final int freezeFuelCost;
    private final int forgeLevel;

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
     * Forge Recipe Builder Serializer
     */
    static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final ForgeRecipeBuilder recipeBuilder;

        private Result(ResourceLocation id, ForgeRecipeBuilder recipeBuilder) {
            this.id = id;
            this.recipeBuilder = recipeBuilder;
        }

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

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return BMOBRecipes.FORGE_RECIPE.get();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}

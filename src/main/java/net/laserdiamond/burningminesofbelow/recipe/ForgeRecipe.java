package net.laserdiamond.burningminesofbelow.recipe;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.entity.AbstractForgeBlockEntity;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties of a {@link ForgeRecipe}, and what is required for one</li>
 * <li>A {@link ForgeRecipe} is-a {@link Recipe}</li>
 * @author Allen Malo
 */
public class ForgeRecipe implements Recipe<SimpleContainer> {

    /**
     * The main ingredient of the recipe
     */
    private final Ingredient mainIngredient; // A ForgeRecipe has-a Ingredient (one-to-many)

    /**
     * The misc ingredient of the recipe
     */
    private final Ingredient miscIngredient; // A ForgeRecipe has-a Ingredient (one-to-many)

    /**
     * The output item stack of the recipe
     */
    private final ItemStack output; // A ForgeRecipe has-a ItemStack

    /**
     * The ID of the recipe
     */
    private final ResourceLocation id; // A ForgeRecipe has-a ResourceLocation

    /**
     * The heat fuel cost of the recipe
     */
    private final int heatFuelCost;

    /**
     * The freeze fuel cost of the recipe
     */
    private final int freezeFuelCost;

    /**
     * The minimum Forge level required for the recipe
     */
    private final int forgeLevel;

    /**
     * Creates a new {@link ForgeRecipe}
     * @param mainIngredient The main {@link Ingredient} for the recipe
     * @param miscIngredient The miscellaneous {@link Ingredient} for the recipe
     * @param output The output {@link ItemStack} of the recipe
     * @param id The {@link ResourceLocation} ID of the recipe
     * @param heatFuelCost The heat fuel cost of the recipe
     * @param freezeFuelCost The freeze fuel cost of the recipe
     * @param forgeLevel The minimum Forge level required for the recipe
     */
    public ForgeRecipe(Ingredient mainIngredient, Ingredient miscIngredient, ItemStack output, ResourceLocation id, int heatFuelCost, int freezeFuelCost, int forgeLevel) {
        this.mainIngredient = mainIngredient;
        this.miscIngredient = miscIngredient;
        this.output = output;
        this.id = id;
        this.heatFuelCost = heatFuelCost;
        this.freezeFuelCost = freezeFuelCost;
        this.forgeLevel = forgeLevel;
    }

    /**
     * Determines if the contents in the {@link SimpleContainer} match a recipe
     * @param simpleContainer The {@link SimpleContainer} to check
     * @param level The {@link Level} the recipe will be made on
     * @return True if a matching recipe was found. False otherwise
     */
    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return !level.isClientSide && mainIngredient.test(simpleContainer.getItem(AbstractForgeBlockEntity.MAIN_ITEM_INPUT_SLOT)) && miscIngredient.test(simpleContainer.getItem(AbstractForgeBlockEntity.MISC_ITEM_INPUT_SLOT));
    }

    /**
     * Creates and assembles the output item
     * @param simpleContainer The {@link SimpleContainer} to make the item from
     * @param registryAccess The {@link RegistryAccess} of the recipe
     * @return
     */
    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true; // Can craft in any dimension
    }

    /**
     * Gets the result item of the recipe
     * @param registryAccess The {@link RegistryAccess} of the recipe
     * @return
     */
    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output.copy();
    }

    /**
     * Gets the ID of the recipe
     * @return The {@link ResourceLocation} of the recipe
     */
    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    /**
     * Gets the {@link RecipeSerializer} of the recipe
     * @return The {@link RecipeSerializer} of the recipe
     */
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Gets the {@link RecipeType} of the recipe
     * @return The {@link RecipeType} of the recipe
     */
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    /**
     * Gets the Forge Level required for the recipe
     * @return The Forge Level required for the recipe
     */
    public int getForgeLevel() {
        return forgeLevel;
    }

    /**
     * Gets the heat fuel cost of the recipe
     * @return The heat fuel cost of the recipe
     */
    public int getHeatFuelCost() {
        return heatFuelCost;
    }

    /**
     * Gets the freeze fuel cost of the recipe
     * @return The freeze fuel cost of the recipe
     */
    public int getFreezeFuelCost() {
        return freezeFuelCost;
    }

    /**
     * A {@link HashMap} that contains the heat and freeze fuel capacities for each level of the Forge
     */
    public static final HashMap<Integer, Integer[]> FORGE_LEVEL_MAX_FUELS = new HashMap<>();
    static
    {
        FORGE_LEVEL_MAX_FUELS.put(1, new Integer[]{100, 100});
        FORGE_LEVEL_MAX_FUELS.put(2, new Integer[]{200, 200});
        FORGE_LEVEL_MAX_FUELS.put(3, new Integer[]{350, 350});
    }

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Serves as the {@link RecipeType} for the {@link ForgeRecipe}</li>
     * <li>Declared as a static inner class because it is closely used/related to the encompassing class</li>
     * <li>A {@link Type} is-a {@link RecipeType}</li>
     * @author Allen Malo
     */
    public static class Type implements RecipeType<ForgeRecipe>
    {
        /**
         * Creates a new {@link Type}.
         * Declared as private because there should only be one instance of it in the workspace
         */
        private Type(){}

        /**
         * The instance of the {@link Type}
         */
        public static final Type INSTANCE = new Type();

        /**
         * The {@link String} ID of the recipe type
         */
        public static final String ID = "forge";
    }

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Retrieves the recipes from the json file in the data folder of this mod</li>
     * <li>Synchronizes the recipes to and from the network</li>
     * <li>A {@link Serializer} is-a {@link RecipeSerializer}</li>
     * @author Allen Malo
     */
    public static class Serializer implements RecipeSerializer<ForgeRecipe>
    {
        /**
         * Creates a new {@link Serializer}.
         * Declared as private because there should only be one instance of it in the workspace
         */
        private Serializer(){}

        /**
         * The instance of the {@link Serializer}
         */
        public static final Serializer INSTANCE = new Serializer();

        /**
         * The {@link ResourceLocation} ID of the {@link ForgeRecipe}
         */
        public static final ResourceLocation ID = new ResourceLocation(BurningMinesOfBelow.MODID, Type.ID);

        /**
         * Retrieves the {@link ForgeRecipe} from its json file in the data folder
         * @param resourceLocation The {@link ResourceLocation} of the recipe
         * @param jsonObject The {@link JsonObject} from the recipe's json file
         * @return The {@link ForgeRecipe} from its json file
         */
        @Override
        public ForgeRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject)
        {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

            Ingredient mainIngredient = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "main_ingredient"));
            Ingredient miscIngredient = Ingredient.fromJson(GsonHelper.getNonNull(jsonObject, "misc_ingredient"));

            int forgeLevelReq = GsonHelper.getAsInt(jsonObject, "forge_level");
            int heatFuelCost = GsonHelper.getAsInt(jsonObject, "heat_fuel_cost");
            int freezeFuelCost = GsonHelper.getAsInt(jsonObject, "freeze_fuel_cost");

            return new ForgeRecipe(mainIngredient, miscIngredient, output, resourceLocation, heatFuelCost, freezeFuelCost, forgeLevelReq);
        }

        /**
         * Retrieves a {@link ForgeRecipe} from the network
         * @param resourceLocation The {@link ResourceLocation} of the recipe
         * @param friendlyByteBuf The {@link FriendlyByteBuf} that contains data about the recipe
         * @return The {@link ForgeRecipe} from the network
         */
        @Override
        public @Nullable ForgeRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {

            Ingredient main = Ingredient.fromNetwork(friendlyByteBuf); // Main item
            Ingredient misc = Ingredient.fromNetwork(friendlyByteBuf); // Misc item

            ItemStack output = friendlyByteBuf.readItem(); // Result item

            int[] recipeInfo = friendlyByteBuf.readVarIntArray(); // Recipe info for heat cost, freeze cost, and forge level

            return new ForgeRecipe(main, misc, output, resourceLocation, recipeInfo[0], recipeInfo[1], recipeInfo[2]);
        }

        /**
         * Sends a {@link ForgeRecipe} to the network
         * @param friendlyByteBuf  The {@link FriendlyByteBuf} to write the recipe to
         * @param forgeRecipe The {@link ForgeRecipe} to write to the network
         */
        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, ForgeRecipe forgeRecipe) {
            forgeRecipe.mainIngredient.toNetwork(friendlyByteBuf);
            forgeRecipe.miscIngredient.toNetwork(friendlyByteBuf);

            friendlyByteBuf.writeVarIntArray(new int[]{forgeRecipe.heatFuelCost, forgeRecipe.freezeFuelCost, forgeRecipe.forgeLevel});

            friendlyByteBuf.writeItemStack(forgeRecipe.getResultItem(null), false);
        }
    }
}

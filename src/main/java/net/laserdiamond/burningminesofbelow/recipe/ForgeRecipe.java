package net.laserdiamond.burningminesofbelow.recipe;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntity;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class ForgeRecipe implements Recipe<SimpleContainer> {

    private final Ingredient mainIngredient;
    private final Ingredient miscIngredient;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int heatFuelCost;
    private final int freezeFuelCost;
    private final int forgeLevel;

    public ForgeRecipe(Ingredient mainIngredient, Ingredient miscIngredient, ItemStack output, ResourceLocation id, int heatFuelCost, int freezeFuelCost, int forgeLevel) {
        this.mainIngredient = mainIngredient;
        this.miscIngredient = miscIngredient;
        this.output = output;
        this.id = id;
        this.heatFuelCost = heatFuelCost;
        this.freezeFuelCost = freezeFuelCost;
        this.forgeLevel = forgeLevel;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        return !level.isClientSide && mainIngredient.test(simpleContainer.getItem(ForgeBlockEntity.MAIN_ITEM_INPUT_SLOT)) && miscIngredient.test(simpleContainer.getItem(ForgeBlockEntity.MISC_ITEM_INPUT_SLOT));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getForgeLevel() {
        return forgeLevel;
    }

    public int getHeatFuelCost() {
        return heatFuelCost;
    }

    public int getFreezeFuelCost() {
        return freezeFuelCost;
    }


    public static final HashMap<Integer, Integer[]> FORGE_LEVEL_MAX_FUELS = new HashMap<>();
    static
    {
        FORGE_LEVEL_MAX_FUELS.put(1, new Integer[]{100, 100});
        FORGE_LEVEL_MAX_FUELS.put(2, new Integer[]{200, 200});
        FORGE_LEVEL_MAX_FUELS.put(3, new Integer[]{350, 350});
    }

    public static class Type implements RecipeType<ForgeRecipe>
    {
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "forge";
    }

    public static class Serializer implements RecipeSerializer<ForgeRecipe>
    {
        private Serializer(){}

        public static final Serializer INSTANCE = new Serializer();

        public static final ResourceLocation ID = new ResourceLocation(BurningMinesOfBelow.MODID, Type.ID);

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

        @Override
        public @Nullable ForgeRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {

            Ingredient main = Ingredient.fromNetwork(friendlyByteBuf); // Main item
            Ingredient misc = Ingredient.fromNetwork(friendlyByteBuf); // Misc item

            ItemStack output = friendlyByteBuf.readItem(); // Result item

            int[] recipeInfo = friendlyByteBuf.readVarIntArray(); // Recipe info for heat cost, freeze cost, and forge level

            return new ForgeRecipe(main, misc, output, resourceLocation, recipeInfo[0], recipeInfo[1], recipeInfo[2]);
        }

        @Override
        public void toNetwork(FriendlyByteBuf friendlyByteBuf, ForgeRecipe forgeRecipe) {
            forgeRecipe.mainIngredient.toNetwork(friendlyByteBuf);
            forgeRecipe.miscIngredient.toNetwork(friendlyByteBuf);

            friendlyByteBuf.writeVarIntArray(new int[]{forgeRecipe.heatFuelCost, forgeRecipe.freezeFuelCost, forgeRecipe.forgeLevel});

            friendlyByteBuf.writeItemStack(forgeRecipe.getResultItem(null), false);
        }
    }
}

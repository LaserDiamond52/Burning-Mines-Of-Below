package net.laserdiamond.burningminesofbelow.recipe;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMOBRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BurningMinesOfBelow.MODID);

    public static final RegistryObject<RecipeSerializer<ForgeRecipe>> FORGE_RECIPE = SERIALIZERS.register("forge", () -> ForgeRecipe.Serializer.INSTANCE);

    public static void registerSerializers(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}

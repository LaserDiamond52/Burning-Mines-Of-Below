package net.laserdiamond.burningminesofbelow.recipe;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates and registers new {@link RecipeSerializer}s to the mod's {@link RecipeSerializer} registry</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBRecipes {

    /**
     * {@link RecipeSerializer} registry of this mod
     */
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, BurningMinesOfBelow.MODID);

    /**
     * The Forge {@link RecipeSerializer}
     */
    public static final RegistryObject<RecipeSerializer<ForgeRecipe>> FORGE_RECIPE = SERIALIZERS.register("forge", () -> ForgeRecipe.Serializer.INSTANCE);

    /**
     * Registers all the {@link RecipeSerializer}s under the {@link BMOBRecipes#SERIALIZERS} registry
     * @param eventBus The {@link IEventBus} of this mod
     */
    public static void registerSerializers(IEventBus eventBus)
    {
        SERIALIZERS.register(eventBus);
    }
}

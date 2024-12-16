package net.laserdiamond.burningminesofbelow.screen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.screen.forge.ForgeMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates and registers {@link MenuType}s to this mod's {@link MenuType} registry</li>
 * @author Allen Malo
 */
public class BMOBMenuTypes {

    /**
     * Menu registry of this mod
     */
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BurningMinesOfBelow.MODID);

    /**
     * Forge Menu menu type
     */
    public static final RegistryObject<MenuType<ForgeMenu>> FORGE_MENU = registerMenuType("forge_menu", ForgeMenu::new);

    /**
     * Registers a new menu type for use with this mod
     * @param localName The local name of the menu type
     * @param factory The {@link MenuType} to create. Can be a static method reference to the class's constructor.
     * @return A new {@link RegistryObject} of the {@link MenuType} specified
     * @param <T> The {@link AbstractContainerMenu} type
     */
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String localName, IContainerFactory<T> factory)
    {
        return MENUS.register(localName, () -> IForgeMenuType.create(factory));
    }

    /**
     * Registers all menus under the {@link BMOBMenuTypes#MENUS} registry
     * @param eventBus The {@link IEventBus} of this mod
     */
    public static void registerMenuTypes(IEventBus eventBus)
    {
        MENUS.register(eventBus); // Register all the menus specified
    }
}

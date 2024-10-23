package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BMOBItems {

    public static final NameRegistry<Item> ITEM_NAME_REGISTRY = new NameRegistry<>();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurningMinesOfBelow.MODID);

    /**
     * Registers a new item under the mod's item registry
     * @param name The name of the item in-game
     * @param pathName The path-name of the item
     * @param itemSupplier The item supplier
     * @return A {@link RegistryObject} representing the newly created {@link Item}
     */
    private static RegistryObject<Item> registerItem(String name, String pathName, Supplier<Item> itemSupplier)
    {
        RegistryObject<Item> registryItem = ITEMS.register(pathName, itemSupplier);
        ITEM_NAME_REGISTRY.addEntry(name, registryItem);
        return registryItem;
    }

    /**
     * Registers all items under the item registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerItems(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

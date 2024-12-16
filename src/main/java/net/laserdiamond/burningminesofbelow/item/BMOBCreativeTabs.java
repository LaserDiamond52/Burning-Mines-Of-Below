package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and register new {@link CreativeModeTab}s to the mod's {@link CreativeModeTab} registry</li>
 * @author Allen Malo
 */
public class BMOBCreativeTabs {

    /**
     * The {@link String} ID for creative tabs
     */
    private static final String CREATIVE_TAB_ID = "creative_tab.";

    /**
     * Creative Mode Tab registry of this mod
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BurningMinesOfBelow.MODID);

    /**
     * The main creative mode tab that contains all items and blocks of this mod
     */
    public static final RegistryObject<CreativeModeTab> MAIN_TAB = registerCreativeTab("Burning Mines of Below", "burning_mines_of_below_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BMOBItems.BLAZIUM_INGOT.get())).title(Component.translatable(CREATIVE_TAB_ID + BurningMinesOfBelow.MODID + ".burning_mines_of_below_tab")).build());


    /**
     * Registers a new creative mode tab under the mod's creative mode tab registry
     * @param name The name of the creative mode tab in-game
     * @param localName The local name of the creative mode tab
     * @param creativeModeTabSupplier The creative mode tab supplier
     * @return A {@link RegistryObject} representing the newly created {@link CreativeModeTab}
     */
    private static RegistryObject<CreativeModeTab> registerCreativeTab(String name, String localName, Supplier<CreativeModeTab> creativeModeTabSupplier)
    {
        RegistryObject<CreativeModeTab> registryCreativeTab = CREATIVE_MODE_TABS.register(localName, creativeModeTabSupplier);
        LanguageRegistry.instance(Language.EN_US).getCreativeModeTabNameRegistry().addEntry(name, registryCreativeTab);
        return registryCreativeTab;
    }

    /**
     * Adds an item to a creative mode tab
     * @param item The {@link RegistryObject} of the item
     * @param creativeModeTab The {@link RegistryObject} of the creative mode tab
     * @param event The {@link BuildCreativeModeTabContentsEvent}
     * @return True if the item was added, false otherwise
     */
    public static boolean addItemToTab(RegistryObject<Item> item, RegistryObject<CreativeModeTab> creativeModeTab, BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == creativeModeTab.getKey())
        {
            event.accept(item);
            return true;
        }
        return false;
    }

    /**
     * Adds a block to a creative mode tab
     * @param block The {@link RegistryObject} of the block
     * @param creativeModeTab The {@link RegistryObject} of the creative mode tab
     * @param event The {@link BuildCreativeModeTabContentsEvent}
     * @return True if the block was added, false otherwise
     */
    public static boolean addBlockToTab(RegistryObject<Block> block, RegistryObject<CreativeModeTab> creativeModeTab, BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == creativeModeTab.getKey())
        {
            event.accept(block);
            return true;
        }
        return false;
    }

    /**
     * Registers all creative mode tabs under the creative mode tab registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerCreativeTabs(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

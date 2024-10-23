package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BMOBBlocks {

    public static final NameRegistry<Block> BLOCK_NAME_REGISTRY = new NameRegistry<>();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BurningMinesOfBelow.MODID);

    /**
     * Registers a new block under this mod's registry
     * @param name The name of the block in-game
     * @param pathName The path name of the block
     * @param blockSupplier The {@link Supplier} for the block
     * @return A {@link RegistryObject} representing the newly created {@link Block}
     * @param <T> The {@link Block} type
     */
    private static <T extends Block> RegistryObject<Block> registerBlock(String name, String pathName, Supplier<T> blockSupplier)
    {
        RegistryObject<Block> block = BLOCKS.register(pathName, blockSupplier);
        registerBlockItem(name, pathName, block);
        BLOCK_NAME_REGISTRY.addEntry(name, block);
        return block;
    }

    /**
     * Registers the block item of the block
     * @param name The name of the block in-game
     * @param pathName The path name of the block
     * @param block The {@link RegistryObject} of the {@link Block}
     * @return A {@link RegistryObject} representing the newly created {@link Item} of the {@link Block}
     * @param <T> the {@link Block} type
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, String pathName, RegistryObject<T> block)
    {
        final RegistryObject<Item> blockItem = BMOBItems.ITEMS.register(pathName, () -> new BlockItem(block.get(), new Item.Properties()));
        BMOBItems.ITEM_NAME_REGISTRY.addEntry(name, blockItem);
        return blockItem;
    }

    /**
     * Registers all blocks under the block registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerBlocks(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}

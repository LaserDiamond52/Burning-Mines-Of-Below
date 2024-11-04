package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class BMOBBlocks {

    /**
     * Block registry of this mod
     */
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BurningMinesOfBelow.MODID);

    /**
     * Level 1 Forge block
     */
    public static final RegistryObject<Block> FORGE_LEVEL_1 = registerBlock("Forge Level 1", "forge_level_1", () -> new ForgeBlockLevel1(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion(), List.of(BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.NEEDS_WOOD_TOOL)));

    /**
     * Level 2 Forge block
     */
    public static final RegistryObject<Block> FORGE_LEVEL_2 = registerBlock("Forge Level 2", "forge_level_2", () -> new ForgeBlockLevel2(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion(), List.of(BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.NEEDS_WOOD_TOOL)));

    /**
     * Level 3 Forge block
     */
    public static final RegistryObject<Block> FORGE_LEVEL_3 = registerBlock("Forge Level 3", "forge_level_3", () -> new ForgeBlockLevel3(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion(), List.of(BlockTags.MINEABLE_WITH_PICKAXE, Tags.Blocks.NEEDS_WOOD_TOOL)));

    /**
     * Registers a new block under this mod's registry
     * @param name The name of the block in-game
     * @param localName The local name of the block
     * @param blockSupplier The {@link Supplier} for the block
     * @return A {@link RegistryObject} representing the newly created {@link Block}
     * @param <T> The {@link Block} type
     */
    private static <T extends Block> RegistryObject<Block> registerBlock(String name, String localName, Supplier<T> blockSupplier)
    {
        RegistryObject<Block> block = BLOCKS.register(localName, blockSupplier);
        registerBlockItem(localName, block);
        LanguageRegistry.instance(Language.EN_US).getBlockNameRegistry().addEntry(name, block);
        return block;
    }

    /**
     * Registers the block item of the block
     * @param localName The local name of the block
     * @param block The {@link RegistryObject} of the {@link Block}
     * @return A {@link RegistryObject} representing the newly created {@link Item} of the {@link Block}
     * @param <T> the {@link Block} type
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String localName, RegistryObject<T> block)
    {
        return BMOBItems.ITEMS.register(localName, () -> new BlockItem(block.get(), new Item.Properties()));
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

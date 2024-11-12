package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
     * Garnet Ore block
     */
    public static final RegistryObject<Block> GARNET_ORE = registerBlock("Garnet Ore", "garnet_ore", () -> new BMOBOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.GARNET.get(), UniformInt.of(6, 8), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)));

    /**
     * Deep Garnet Ore block
     */
    public static final RegistryObject<Block> DEEP_GARNET_ORE = registerBlock("Deep Garnet Ore", "deep_garnet_ore", () -> new RefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.GARNET.get(), BMOBItems.REFINED_GARNET.get(), UniformInt.of(14, 17), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_DIAMONITE_TOOL)));

    /**
     * Peridot Ore block
     */
    public static final RegistryObject<Block> PERIDOT_ORE = registerBlock("Peridot Ore", "peridot_ore", () -> new BMOBMultiOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.PERIDOT.get(), 2, 5, UniformInt.of(7, 9), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)));

    /**
     * Deep Peridot Ore block
     */
    public static final RegistryObject<Block> DEEP_PERIDOT_ORE = registerBlock("Deep Peridot Ore", "deep_peridot_ore", () -> new RefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.PERIDOT.get(), BMOBItems.REFINED_PERIDOT.get(), UniformInt.of(16, 19), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_DIAMONITE_TOOL)));

    /**
     * Deep Diamond Ore block
     */
    public static final RegistryObject<Block> DEEP_DIAMOND_ORE = registerBlock("Deep Diamond Ore", "deep_diamond_ore", () -> new RefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), Items.DIAMOND, BMOBItems.REFINED_DIAMOND.get(), UniformInt.of(14, 17), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)));

    /**
     * Cyronite Ore block
     */
    public static final RegistryObject<Block> CYRONITE_ORE = registerBlock("Cyronite Ore", "cyronite_ore", () -> new BMOBMultiOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.CYRONITE_SHARD.get(), 1, 3, UniformInt.of(9, 12), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_REFINED_DIAMONITE_TOOL)));


    // TODO: Blocks for:
    // Soul bricks (and its family of blocks)
    // Frozen Soul Sand/Soil

    /**
     * Registers a new block under this mod's registry
     * @param name The name of the block in-game
     * @param localName The local name of the block
     * @param blockSupplier The {@link Supplier} for the block
     * @return A {@link RegistryObject} representing the newly created {@link Block}
     * @param <T> The {@link Block} type
     */
    @SuppressWarnings("unchecked")
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

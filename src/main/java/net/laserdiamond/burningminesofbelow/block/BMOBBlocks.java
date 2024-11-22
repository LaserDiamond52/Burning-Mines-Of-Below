package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
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
     * Frozen Netherrack block
     */
    public static final RegistryObject<Block> FROZEN_NETHERRACK = registerBlock("Frozen Netherrack", "frozen_netherrack", () -> new BMOBBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK), List.of(BlockTags.SOUL_FIRE_BASE_BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)));

    /**
     * Magnite Stone block
     */
    public static final RegistryObject<Block> MAGNITE_STONE = registerBlock("Magnite Stone", "magnite_stone", () -> new BMOBBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 3), List.of(BlockTags.INFINIBURN_NETHER, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL)));

    /**
     * Garnet Ore block
     */
    public static final RegistryObject<Block> GARNET_ORE = registerBlock("Garnet Ore", "garnet_ore", () -> new BMOBOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.GARNET, UniformInt.of(6, 8), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL, Tags.Blocks.ORES_IN_GROUND_STONE)));

    /**
     * Deepslate Garnet Ore block
     */
    public static final RegistryObject<Block> DEEPSLATE_GARNET_ORE = registerBlock("Deepslate Garnet Ore", "deepslate_garnet_ore", () -> new BMOBOreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), BMOBItems.GARNET, UniformInt.of(8, 9), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL, Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)));

    /**
     * Deep Garnet Ore block
     */
    public static final RegistryObject<Block> DEEP_GARNET_ORE = registerBlock("Deep Garnet Ore", "deep_garnet_ore", () -> new RefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 3), BMOBItems.GARNET, BMOBItems.REFINED_GARNET, UniformInt.of(14, 17), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_DIAMONITE_TOOL)));

    /**
     * Peridot Ore block
     */
    public static final RegistryObject<Block> PERIDOT_ORE = registerBlock("Peridot Ore", "peridot_ore", () -> new BMOBMultiOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.PERIDOT, 2, 5, UniformInt.of(7, 9), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL, Tags.Blocks.ORES_IN_GROUND_STONE)));

    /**
     * Deepslate Peridot Ore block
     */
    public static final RegistryObject<Block> DEEPSLATE_PERIDOT_ORE = registerBlock("Deepslate Peridot Ore", "deepslate_peridot_ore", () -> new BMOBMultiOreBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), BMOBItems.PERIDOT, 2, 5, UniformInt.of(9, 11), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL, Tags.Blocks.ORES_IN_GROUND_DEEPSLATE)));

    /**
     * Deep Peridot Ore block
     */
    public static final RegistryObject<Block> DEEP_PERIDOT_ORE = registerBlock("Deep Peridot Ore", "deep_peridot_ore", () -> new RefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 3), BMOBItems.PERIDOT, BMOBItems.REFINED_PERIDOT, UniformInt.of(16, 19), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_DIAMONITE_TOOL)));

    /**
     * Deep Diamond Ore block
     */
    public static final RegistryObject<Block> DEEP_DIAMOND_ORE = registerBlock("Deep Diamond Ore", "deep_diamond_ore", () -> new VanillaRefinedOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 3), Items.DIAMOND, BMOBItems.REFINED_DIAMOND, UniformInt.of(14, 17), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_DIAMOND_TOOL)));

    /**
     * Cyronite Ore block
     */
    public static final RegistryObject<Block> CYRONITE_ORE = registerBlock("Cyronite Ore", "cyronite_ore", () -> new BMOBMultiOreBlock(BlockBehaviour.Properties.copy(Blocks.STONE), BMOBItems.CYRONITE_SHARD, 1, 3, UniformInt.of(9, 12), List.of(BlockTags.MINEABLE_WITH_PICKAXE, BMOBTags.Blocks.NEEDS_REFINED_DIAMONITE_TOOL)));

    /**
     * Frozen Soul Sand block
     */
    public static final RegistryObject<Block> FROZEN_SOUL_SAND = registerBlock("Frozen Soul Sand", "frozen_soul_sand", () -> new BMOBBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SAND).speedFactor(0.4F), List.of(BlockTags.SOUL_FIRE_BASE_BLOCKS, Tags.Blocks.NEEDS_WOOD_TOOL, BlockTags.MINEABLE_WITH_SHOVEL, BMOBTags.Blocks.FREEZING_REAPER_BASE_BLOCK)));

    /**
     * Frozen Soul Soil block
     */
    public static final RegistryObject<Block> FROZEN_SOUL_SOIL = registerBlock("Frozen Soul Soil", "frozen_soul_soil", () -> new BMOBBlock(BlockBehaviour.Properties.copy(Blocks.SOUL_SOIL), List.of(BlockTags.SOUL_FIRE_BASE_BLOCKS, Tags.Blocks.NEEDS_WOOD_TOOL, BlockTags.MINEABLE_WITH_SHOVEL, BMOBTags.Blocks.FREEZING_REAPER_BASE_BLOCK)));

    /**
     * Blaze block
     */
    public static final RegistryObject<Block> BLAZE_BLOCK = registerBlock("Blaze Block", "blaze_block", () -> new BMOBBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(value -> 5), List.of(BlockTags.INFINIBURN_OVERWORLD, BlockTags.INFINIBURN_NETHER, BlockTags.INFINIBURN_END, BMOBTags.Blocks.KING_INFERNIUS_BASE_BLOCK, BlockTags.NEEDS_STONE_TOOL, BlockTags.MINEABLE_WITH_PICKAXE)));

    /**
     * Frozen Wither Skull block
     */
    public static final RegistryObject<Block> FROZEN_WITHER_SKULL = registerHeadBlock("Frozen Wither Skull", "frozen_wither_skull",
            () -> new FrozenWitherSkullBlock(BlockBehaviour.Properties.copy(Blocks.WITHER_SKELETON_SKULL), List.of(BMOBTags.Blocks.FREEZING_REAPER_SUMMON_BLOCK)));

    /**
     * Frozen Wither Skull Wall block
     */
    public static final RegistryObject<Block> FROZEN_WITHER_SKULL_WALL = registerHeadBlockWall("frozen_wither_skull_wall",
            () -> new FrozenWitherSkullWallBlock(BlockBehaviour.Properties.copy(Blocks.WITHER_SKELETON_WALL_SKULL).dropsLike(FROZEN_WITHER_SKULL.get()),
                    List.of(BMOBTags.Blocks.FREEZING_REAPER_SUMMON_BLOCK)));

    /**
     * Blaze Skull block
     */
    public static final RegistryObject<Block> BLAZE_SKULL = registerHeadBlock("Blaze Skull", "blaze_skull",
            () -> new BlazeSkullBlock(BlockBehaviour.Properties.copy(Blocks.WITHER_SKELETON_SKULL), List.of(BMOBTags.Blocks.KING_INFERNIUS_SUMMON_BLOCK)));

    /**
     * Blaze Skull Wall block
     */
    public static final RegistryObject<Block> BLAZE_SKULL_WALL = registerHeadBlockWall("blaze_skull_wall",
            () -> new BlazeSkullWallBlock(BlockBehaviour.Properties.copy(Blocks.WITHER_SKELETON_WALL_SKULL).dropsLike(BLAZE_SKULL.get()),
                    List.of(BMOBTags.Blocks.KING_INFERNIUS_SUMMON_BLOCK)));

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
     * Registers the default head block. No block item is registered on its own, and one must be registered in {@link BMOBItems}
     * @see BMOBItems
     * @param name The name of the head block
     * @param localName The local name of the head block
     * @param blockSupplier The {@link Supplier} for the Skull block
     * @return A {@link RegistryObject} representing the newly created {@link SkullBlock}
     * @param <T> The {@link SkullBlock} type
     */
    @SuppressWarnings("unchecked")
    private static <T extends SkullBlock> RegistryObject<Block> registerHeadBlock(String name, String localName, Supplier<T> blockSupplier)
    {
        RegistryObject<Block> block = BLOCKS.register(localName, blockSupplier);
        LanguageRegistry.instance(Language.EN_US).getBlockNameRegistry().addEntry(name, block);
        return block;
    }

    /**
     * Registers the wall variant of the head block. The translation name is provided by the default variant of this block. Similarly, no block item is registered on its own, and one must be registered in {@link BMOBItems}
     * @see BMOBItems
     * @param localName The local name of the head block
     * @param blockSupplier The {@link Supplier} for the Skull block
     * @return A {@link RegistryObject} representing the newly created {@link SkullBlock}
     * @param <T> The {@link WallSkullBlock} type
     */
    private static <T extends WallSkullBlock> RegistryObject<Block> registerHeadBlockWall(String localName, Supplier<T> blockSupplier)
    {
        return BLOCKS.register(localName, blockSupplier);
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

package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.*;
import net.laserdiamond.burningminesofbelow.item.equipment.tools.*;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and register new {@link Item}s to the mod's {@link Item} registry</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBItems {

    /**
     * Item registry of this mod
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurningMinesOfBelow.MODID);

    /**
     * Blaze Flames item
     */
    public static final RegistryObject<Item> BLAZE_FLAMES = registerItem("Blaze Flames", "blaze_flames", () -> new BMOBItem(new Item.Properties().fireResistant(), List.of()) {
                @Override
                public int heatFuel() {
                    return 12;
                }
            });

    /**
     * Blazium Ingot item
     */
    public static final RegistryObject<Item> BLAZIUM_INGOT = registerItem("Blazium Ingot", "blazium_ingot", () -> new ForgeCraftableMiscItem(new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MISC_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.NETHERITE_INGOT;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(BLAZE_FLAMES.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 50;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Infernal Flame item
     */
    public static final RegistryObject<Item> INFERNAL_FLAME = registerItem("Infernal Flame", "infernal_flame", () -> new BMOBItem(new Item.Properties().fireResistant(), List.of()));

    /**
     * Infernal Flame Ingot item
     */
    public static final RegistryObject<Item> INFERNAL_FLAME_INGOT = registerItem("Infernal Flame Ingot", "infernal_flame_ingot", () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of(BMOBTags.Items.FORGE_MISC_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return BLAZIUM_INGOT.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(INFERNAL_FLAME.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 80;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamond item
     */
    public static final RegistryObject<Item> REFINED_DIAMOND = registerItem("Refined Diamond", "refined_diamond", () -> new BMOBItem(new Item.Properties(), List.of(BMOBTags.Items.FORGE_MISC_INGREDIENT)));

    /**
     * Chunk of Blue Ice item
     */
    public static final RegistryObject<Item> BLUE_ICE_CHUNK = registerItem("Chunk of Blue Ice", "blue_ice_chunk", () -> new BMOBItem(new Item.Properties(), List.of()) {
                @Override
                public int freezeFuel() {
                    return 4;
                }
            });

    /**
     * Cyronite Shard item
     */
    public static final RegistryObject<Item> CRYONITE_SHARD = registerItem("Cryonite Shard", "cryonite_shard", () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of(BMOBTags.Items.FORGE_MISC_INGREDIENT)) {
        @Override
        public Item mainItem() {
            return REFINED_DIAMOND.get();
        }

        @Override
        public Ingredient miscItems() {
            return Ingredient.of(BLUE_ICE_CHUNK.get());
        }

        @Override
        public int forgeCraftingLevel() {
            return 1;
        }

        @Override
        public int heatFuelCost() {
            return 0;
        }

        @Override
        public int freezeFuelCost() {
            return 50;
        }
    });

    /**
     * Frozen-Encased Soul item
     */
    public static final RegistryObject<Item> FROZEN_SOUL = registerItem("Frozen-Encased Soul", "frozen_soul", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Frigid Cryonite Crystal item
     */
    public static final RegistryObject<Item> FRIGID_CRYONITE_CRYSTAL = registerItem("Frigid Cryonite Crystal", "frigid_cryonite_crystal", () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of(BMOBTags.Items.FORGE_MISC_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return CRYONITE_SHARD.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(FROZEN_SOUL.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 0;
                }

                @Override
                public int freezeFuelCost() {
                    return 100;
                }
            });

    /**
     * Garnet item
     */
    public static final RegistryObject<Item> GARNET = registerItem("Garnet", "garnet", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Peridot item
     */
    public static final RegistryObject<Item> PERIDOT = registerItem("Peridot", "peridot", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Refined Garnet item
     */
    public static final RegistryObject<Item> REFINED_GARNET = registerItem("Refined Garnet", "refined_garnet", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Refined Peridot item
     */
    public static final RegistryObject<Item> REFINED_PERIDOT = registerItem("Refined Peridot", "refined_peridot", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Blazium Helmet item
     */
    public static final RegistryObject<Item> BLAZIUM_HELMET = registerItem("Blazium Helmet", "blazium_helmet", () -> new BlaziumArmorItem(ArmorItem.Type.HELMET, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Blazium Chestplate item
     */
    public static final RegistryObject<Item> BLAZIUM_CHESTPLATE = registerItem("Blazium Chestplate", "blazium_chestplate", () -> new BlaziumArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Blazium Leggings item
     */
    public static final RegistryObject<Item> BLAZIUM_LEGGINGS = registerItem("Blazium Leggings", "blazium_leggings", () -> new BlaziumArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Blazium Boots item
     */
    public static final RegistryObject<Item> BLAZIUM_BOOTS = registerItem("Blazium Boots", "blazium_boots", () -> new BlaziumArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Cryonite Helmet item
     */
    public static final RegistryObject<Item> CRYONITE_HELMET = registerItem("Cryonite Helmet", "cryonite_helmet", () -> new CryoniteArmorItem(ArmorItem.Type.HELMET, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Cryonite Chestplate item
     */
    public static final RegistryObject<Item> CRYONITE_CHESTPLATE = registerItem("Cryonite Chestplate", "cryonite_chestplate", () -> new CryoniteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Cryonite Leggings item
     */
    public static final RegistryObject<Item> CRYONITE_LEGGINGS = registerItem("Cryonite Leggings", "cryonite_leggings", () -> new CryoniteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Cryonite Boots item
     */
    public static final RegistryObject<Item> CYRONITE_BOOTS = registerItem("Cryonite Boots", "cryonite_boots", () -> new CryoniteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Diamonite Helmet item
     */
    public static final RegistryObject<Item> DIAMONITE_HELMET = registerItem("Diamonite Helmet", "diamonite_helmet", () -> new DiamoniteArmorItem(ArmorItem.Type.HELMET, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Diamonite Chestplate item
     */
    public static final RegistryObject<Item> DIAMONITE_CHESTPLATE = registerItem("Diamonite Chestplate", "diamonite_chestplate", () -> new DiamoniteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Diamonite Leggings item
     */
    public static final RegistryObject<Item> DIAMONITE_LEGGINGS = registerItem("Diamonite Leggings", "diamonite_leggings", () -> new DiamoniteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Diamonite Boots item
     */
    public static final RegistryObject<Item> DIAMONITE_BOOTS = registerItem("Diamonite Boots", "diamonite_boots", () -> new DiamoniteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)));

    /**
     * Infernal Blazium Helmet item
     */
    public static final RegistryObject<Item> INFERNAL_BLAZIUM_HELMET = registerItem("Infernal Blazium Helmet", "infernal_blazium_helmet", () -> new InfernalBlaziumArmorItem(ArmorItem.Type.HELMET, new Item.Properties().fireResistant(), List.of()));

    /**
     * Infernal Blazium Chestplate item
     */
    public static final RegistryObject<Item> INFERNAL_BLAZIUM_CHESTPLATE = registerItem("Infernal Blazium Chestplate", "infernal_blazium_chestplate", () -> new InfernalBlaziumArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant(), List.of()));

    /**
     * Infernal Blazium Leggings item
     */
    public static final RegistryObject<Item> INFERNAL_BLAZIUM_LEGGINGS = registerItem("Infernal Blazium Leggings", "infernal_blazium_leggings", () -> new InfernalBlaziumArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant(), List.of()));

    /**
     * Infernal Blazium Boots item
     */
    public static final RegistryObject<Item> INFERNAL_BLAZIUM_BOOTS = registerItem("Infernal Blazium Boots", "infernal_blazium_boots", () -> new InfernalBlaziumArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant(), List.of()));

    /**
     * Frigid Cryonite Helmet item
     */
    public static final RegistryObject<Item> FRIGID_CRYONITE_HELMET = registerItem("Frigid Cryonite Helmet", "frigid_cryonite_helmet", () -> new FrigidCyroniteArmorItem(ArmorItem.Type.HELMET, new Item.Properties().fireResistant(), List.of()));

    /**
     * Frigid Cryonite Chestplate item
     */
    public static final RegistryObject<Item> FRIGID_CRYONITE_CHESTPLATE = registerItem("Frigid Cryonite Chestplate", "frigid_cryonite_chestplate", () -> new FrigidCyroniteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant(), List.of()));

    /**
     * Frigid Cryonite Leggings item
     */
    public static final RegistryObject<Item> FRIGID_CRYONITE_LEGGINGS = registerItem("Frigid Cryonite Leggings", "frigid_cryonite_leggings", () -> new FrigidCyroniteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant(), List.of()));

    /**
     * Frigid Cryonite Boots item
     */
    public static final RegistryObject<Item> FRIGID_CRYONITE_BOOTS = registerItem("Frigid Cryonite Boots", "frigid_cryonite_boots", () -> new FrigidCyroniteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant(), List.of()));

    /**
     * Refined Diamonite Helmet item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_HELMET = registerItem("Refined Diamonite Helmet", "refined_diamonite_helmet", () -> new RefinedDiamoniteArmorItem(ArmorItem.Type.HELMET, new Item.Properties(), List.of()));

    /**
     * Refined Diamonite Chestplate item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_CHESTPLATE = registerItem("Refined Diamonite Chestplate", "refined_diamonite_chestplate", () -> new RefinedDiamoniteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties(), List.of()));

    /**
     * Refined Diamonite Leggings item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_LEGGINGS = registerItem("Refined Diamonite Leggings", "refined_diamonite_leggings", () -> new RefinedDiamoniteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties(), List.of()));

    /**
     * Refined Diamonite Boots item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_BOOTS = registerItem("Refined Diamonite Boots", "refined_diamonite_boots", () -> new RefinedDiamoniteArmorItem(ArmorItem.Type.BOOTS, new Item.Properties(), List.of()));

    /**
     * Blazium Sword item
     */
    public static final RegistryObject<Item> BLAZIUM_SWORD = registerItem("Blazium Sword", "blazium_sword", () -> new BlaziumSwordItem(2, -2.4F, new Item.Properties(), List.of()));

    /**
     * Cryonite Sword item
     */
    public static final RegistryObject<Item> CRYONITE_SWORD = registerItem("Cryonite Sword", "cryonite_sword", () -> new CryoniteSwordItem(1, -2.4F, new Item.Properties().fireResistant(), List.of()));

    /**
     * Cryonite Scythe item
     */
    public static final RegistryObject<Item> CRYONITE_SCYTHE = registerItem("Cryonite Scythe", "cryonite_scythe", () -> new CryoniteScytheItem(1, -3F, new Item.Properties().fireResistant(), List.of()));

    /**
     * Diamonite Sword item
     */
    public static final RegistryObject<Item> DIAMONITE_SWORD = registerItem("Diamonite Sword", "diamonite_sword", () -> new BMOBSwordItem(BMOBToolTiers.DIAMONITE, 4, -2.4F, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.DIAMOND_SWORD;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(BMOBItems.REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 75;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Diamonite Pickaxe item
     */
    public static final RegistryObject<Item> DIAMONITE_PICKAXE = registerItem("Diamonite Pickaxe", "diamonite_pickaxe", () -> new BMOBPickaxeItem(BMOBToolTiers.DIAMONITE, 1, -2.8F, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.DIAMOND_SWORD;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(BMOBItems.REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 75;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Diamonite Axe item
     */
    public static final RegistryObject<Item> DIAMONITE_AXE = registerItem("Diamonite Axe", "diamonite_axe", () -> new BMOBAxeItem(BMOBToolTiers.DIAMONITE, 5, -3F, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.DIAMOND_AXE;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 75;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Diamonite Shovel item
     */
    public static final RegistryObject<Item> DIAMONITE_SHOVEL = registerItem("Diamonite Shovel", "diamonite_shovel", () -> new BMOBShovelItem(BMOBToolTiers.DIAMONITE, 1, -2.8F, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.DIAMOND_SHOVEL;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 75;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Diamonite Hoe item
     */
    public static final RegistryObject<Item> DIAMONITE_HOE = registerItem("Diamonite Hoe", "diamonite_hoe", () -> new BMOBHoeItem(BMOBToolTiers.DIAMONITE, 1, 0F, new Item.Properties(), List.of(BMOBTags.Items.FORGE_MAIN_INGREDIENT)) {
                @Override
                public Item mainItem() {
                    return Items.DIAMOND_SHOVEL;
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 1;
                }

                @Override
                public int heatFuelCost() {
                    return 75;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamonite Sword item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_SWORD = registerItem("Refined Diamonite Sword", "refined_diamonite_sword", () -> new BMOBSwordItem(BMOBToolTiers.REFINED_DIAMONITE, 2, -2.4F, new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return DIAMONITE_SWORD.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 250;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamonite Pickaxe item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_PICKAXE = registerItem("Refined Diamonite Pickaxe", "refined_diamonite_pickaxe", () -> new BMOBPickaxeItem(BMOBToolTiers.REFINED_DIAMONITE, 1, -2.8F, new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return DIAMONITE_PICKAXE.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 250;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamonite Axe item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_AXE = registerItem("Refined Diamonite Axe", "refined_diamonite_axe", () -> new BMOBAxeItem(BMOBToolTiers.REFINED_DIAMONITE, 6, -3F, new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return DIAMONITE_AXE.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 250;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamonite Shovel item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_SHOVEL = registerItem("Refined Diamonite Shovel", "refined_diamonite_shovel", () -> new BMOBShovelItem(BMOBToolTiers.REFINED_DIAMONITE, 1, -2.8F, new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return DIAMONITE_SHOVEL.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 250;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Refined Diamonite Hoe item
     */
    public static final RegistryObject<Item> REFINED_DIAMONITE_HOE = registerItem("Refined Diamonite Hoe", "refined_diamonite_hoe", () -> new BMOBHoeItem(BMOBToolTiers.REFINED_DIAMONITE, 0, 0F, new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return DIAMONITE_HOE.get();
                }

                @Override
                public Ingredient miscItems() {
                    return Ingredient.of(REFINED_DIAMOND.get());
                }

                @Override
                public int forgeCraftingLevel() {
                    return 3;
                }

                @Override
                public int heatFuelCost() {
                    return 250;
                }

                @Override
                public int freezeFuelCost() {
                    return 0;
                }
            });

    /**
     * Frozen Wither Skull item
     */
    public static final RegistryObject<Item> FROZEN_WITHER_SKULL = registerHeadBlockItem("frozen_wither_skull", BMOBBlocks.FROZEN_WITHER_SKULL, BMOBBlocks.FROZEN_WITHER_SKULL_WALL);

    /**
     * Blaze Skull item
     */
    public static final RegistryObject<Item> BLAZE_SKULL = registerHeadBlockItem("blaze_skull", BMOBBlocks.BLAZE_SKULL, BMOBBlocks.BLAZE_SKULL_WALL);

    /**
     * Heat fuel items
     */
    public static final FuelItemRegistry HEAT_FUEL_ITEMS = new FuelItemRegistry()
            .addEntry(Items.LAVA_BUCKET, 10)
            .addEntry(Items.BLAZE_ROD, 15);

    /**
     * Freeze fuel items
     */
    public static final FuelItemRegistry FREEZE_FUEL_ITEMS = new FuelItemRegistry()
            .addEntry(Items.BLUE_ICE, 3)
            .addEntry(Items.PACKED_ICE, 1);

    /**
     * Registers a new item under the mod's item registry
     * @param name The name of the item in-game
     * @param localName The local name of the item
     * @param itemSupplier The item supplier
     * @return A {@link RegistryObject} representing the newly created {@link Item}
     */
    private static RegistryObject<Item> registerItem(String name, String localName, Supplier<Item> itemSupplier)
    {
        RegistryObject<Item> registryItem = ITEMS.register(localName, itemSupplier);
        LanguageRegistry.instance(Language.EN_US).getItemNameRegistry().addEntry(name, registryItem);
        return registryItem;
    }

    /**
     * Registers the head item for the head block under the mod's item registry
     * @param localName The local name of the item
     * @param block The default head block of the item
     * @param wallBlock The wall head block of the item
     * @return A {@link RegistryObject} representing the newly created {@link StandingAndWallBlockItem}
     */
    private static RegistryObject<Item> registerHeadBlockItem(String localName, RegistryObject<Block> block, RegistryObject<Block> wallBlock)
    {
        return ITEMS.register(localName, () -> new StandingAndWallBlockItem(block.get(), wallBlock.get(), new Item.Properties(), Direction.DOWN));
    }

    /**
     * Registers all {@link Item}s under the {@link BMOBItems#ITEMS} registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerItems(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}

package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.*;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BMOBItems {

    /**
     * Item registry of this mod
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BurningMinesOfBelow.MODID);

    /**
     * Blaze Flames item
     */
    public static final RegistryObject<Item> BLAZE_FLAMES = registerItem("Blaze Flames", "blaze_flames", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Blazium Ingot item
     */
    public static final RegistryObject<Item> BLAZIUM_INGOT = registerItem("Blazium Ingot", "blazium_ingot",
            () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return Items.NETHERITE_INGOT;
                }

                @Override
                public List<Item> miscItems() {
                    return List.of(BLAZE_FLAMES.get());
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
    public static final RegistryObject<Item> INFERNAL_FLAME = registerItem("Infernal Flame", "infernal_flame", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Infernal Flame Ingot item
     */
    public static final RegistryObject<Item> INFERNAL_FLAME_INGOT = registerItem("Infernal Flame Ingot", "infernal_flame_ingot",
            () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return BLAZIUM_INGOT.get();
                }

                @Override
                public List<Item> miscItems() {
                    return List.of(INFERNAL_FLAME.get());
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
    public static final RegistryObject<Item> REFINED_DIAMOND = registerItem("Refined Diamond", "refined_diamond", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Chunk of Blue Ice item
     */
    public static final RegistryObject<Item> BLUE_ICE_CHUNK = registerItem("Chunk of Blue Ice", "blue_ice_chunk", () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Cyronite Shard item
     */
    public static final RegistryObject<Item> CYRONITE_SHARD = registerItem("Cyronite Shard", "cyronite_shard",
            () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of()) {
        @Override
        public Item mainItem() {
            return REFINED_DIAMOND.get();
        }

        @Override
        public List<Item> miscItems() {
            return List.of(BLUE_ICE_CHUNK.get());
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
     * Frigid Cyronite Crystal item
     */
    public static final RegistryObject<Item> FRIGID_CYRONITE_CRYSTAL = registerItem("Frigid Cyronite Crystal", "frigid_cyronite_crystal",
            () -> new ForgeCraftableMiscItem(new Item.Properties(), List.of()) {
                @Override
                public Item mainItem() {
                    return CYRONITE_SHARD.get();
                }

                @Override
                public List<Item> miscItems() {
                    return List.of(FROZEN_SOUL.get());
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
    public static final RegistryObject<Item> GARNET = registerItem("Garnet", "garnet",
            () -> new BMOBItem(new Item.Properties(), List.of()));


    /**
     * Peridot item
     */
    public static final RegistryObject<Item> PERIDOT = registerItem("Peridot", "peridot",
            () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Refined Garnet item
     */
    public static final RegistryObject<Item> REFINED_GARNET = registerItem("Refined Garnet", "refined_garnet",
            () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Refined Peridot item
     */
    public static final RegistryObject<Item> REFINED_PERIDOT = registerItem("Refined Peridot", "refined_peridot",
            () -> new BMOBItem(new Item.Properties(), List.of()));

    /**
     * Blazium Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Blazium armor piece
     */
    public static RegistryObject<Item> getBlaziumArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Blazium " + BMOBArmorItem.getArmorPieceName(type), "blazium_" + type.getName(),
                () -> new BlaziumArmorItem(type, new Item.Properties(), List.of()));
    }

    /**
     * Cyronite Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Cyronite armor piece
     */
    public static RegistryObject<Item> getCyroniteArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Cyronite " + BMOBArmorItem.getArmorPieceName(type), "cyronite_" + type.getName(),
                () -> new CyroniteArmorItem(type, new Item.Properties(), List.of()));
    }

    /**
     * Diamonite Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Diamonite armor piece
     */
    public static RegistryObject<Item> getDiamoniteArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Diamonite " + BMOBArmorItem.getArmorPieceName(type), "diamonite_" + type.getName(),
                () -> new DiamoniteArmorItem(type, new Item.Properties(), List.of()));
    }

    /**
     * Infernal Blazium Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Infernal Blazium armor piece
     */
    public static RegistryObject<Item> getInfernalBlaziumArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Infernal Blazium " + BMOBArmorItem.getArmorPieceName(type), "infernal_blazium_" + type.getName(),
                () -> new InfernalBlaziumArmorItem(type, new Item.Properties(), List.of()));
    }

    /**
     * Frigid Cyronite Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Frigid Cyronite armor piece
     */
    public static RegistryObject<Item> getFrigidCyroniteArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Frigid Cyronite " + BMOBArmorItem.getArmorPieceName(type), "frigid_cyronite_" + type.getName(),
                () -> new FrigidCyroniteArmorItem(type, new Item.Properties(), List.of()));
    }

    /**
     * Refined Diamonite Armor pieces
     * @param type The armor piece type
     * @return A {@link RegistryObject} that represents the specified Refined Diamonite armor piece
     */
    public static RegistryObject<Item> getRefinedDiamoniteArmorPiece(ArmorItem.Type type)
    {
        return registerItem("Refined Diamonite " + BMOBArmorItem.getArmorPieceName(type), "refined_diamonite_" + type.getName(),
                () -> new RefinedDiamoniteArmorItem(type, new Item.Properties(), List.of()));
    }


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
     * Registers all items under the item registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerItems(IEventBus eventBus)
    {
        for (ArmorItem.Type type : ArmorItem.Type.values())
        {
            getBlaziumArmorPiece(type);
            getCyroniteArmorPiece(type);
            getDiamoniteArmorPiece(type);
            getInfernalBlaziumArmorPiece(type);
            getFrigidCyroniteArmorPiece(type);
            getRefinedDiamoniteArmorPiece(type);
        }
        ITEMS.register(eventBus);
    }
}

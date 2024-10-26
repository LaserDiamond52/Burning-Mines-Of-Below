package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BMOBArmorItem;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BMOBArmorMaterials;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BlaziumArmorItem;
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


    public static final RegistryObject<Item> BLAZIUM_HELMET = registerItem("Blazium Helmet", "blazium_helmet",
            () -> new BlaziumArmorItem(ArmorItem.Type.HELMET, new Item.Properties(), List.of(BMOBTags.Items.getArmorPieceTag(ArmorItem.Type.HELMET), ItemTags.TRIMMABLE_ARMOR)));


    public static final RegistryObject<Item> BLAZIUM_CHESTPLATE = registerItem("Blazium Chestplate", "blazium_chestplate",
            () -> new BlaziumArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Properties(), List.of(Tags.Items.ARMORS_CHESTPLATES, ItemTags.TRIMMABLE_ARMOR)));


    public static final RegistryObject<Item> BLAZIUM_LEGGINGS = registerItem("Blazium Leggings", "blazium_leggings",
            () -> new BlaziumArmorItem(ArmorItem.Type.LEGGINGS, new Item.Properties(), List.of(Tags.Items.ARMORS_LEGGINGS, ItemTags.TRIMMABLE_ARMOR)));


    public static final RegistryObject<Item> BLAZIUM_BOOTS = registerItem("Blazium Boots", "blazium_boots",
            () -> new BlaziumArmorItem(ArmorItem.Type.BOOTS, new Item.Properties(), List.of(Tags.Items.ARMORS_BOOTS, ItemTags.TRIMMABLE_ARMOR)));

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
        ITEMS.register(eventBus);
    }
}

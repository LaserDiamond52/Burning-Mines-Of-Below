package net.laserdiamond.burningminesofbelow.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Helps manage and store the names and languages for different assets of this mod for translation with the {@link net.laserdiamond.burningminesofbelow.datagen.BMOBLanguageProvider}</li>
 * <li>This class cannot be instantiated outside of this class, as there should only be one instance of it per {@link Language} in order to avoid conflict</li>
 * @author Allen Malo
 */
public class LanguageRegistry {

    /**
     * The item name registry
     */
    private final NameRegistry<RegistryObject<Item>> itemNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The block name registry
     */
    private final NameRegistry<RegistryObject<Block>> blockNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The attribute name registry
     */
    private final NameRegistry<RegistryObject<Attribute>> attributeNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The creative mode tab name registry
     */
    private final NameRegistry<RegistryObject<CreativeModeTab>> creativeModeTabNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The key mapping name registry
     */
    private final NameRegistry<KeyMapping> keyMappingNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The mob effect name registry
     */
    private final NameRegistry<RegistryObject<MobEffect>> mobEffectNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * The entity name registry
     */
    private final NameRegistry<ResourceLocation> entityNameRegistry; // A Language Registry has-a NameRegistry (one-to-many)

    /**
     * {@link HashMap} of all the {@link Language}s and their {@link LanguageRegistry}s
     */
    private static final HashMap<Language, LanguageRegistry> LANGUAGE_REGISTRIES = new HashMap<>();

    /**
     * Creates a new {@link LanguageRegistry}
     */
    private LanguageRegistry()
    {
        this.itemNameRegistry = new NameRegistry<>();
        this.blockNameRegistry = new NameRegistry<>();
        this.attributeNameRegistry = new NameRegistry<>();
        this.creativeModeTabNameRegistry = new NameRegistry<>();
        this.keyMappingNameRegistry = new NameRegistry<>();
        this.mobEffectNameRegistry = new NameRegistry<>();
        this.entityNameRegistry = new NameRegistry<>();
    }

    /**
     * Gets the instance of the {@link LanguageRegistry}
     * @param lang The {@link Language} of the registry
     * @return The instance of the {@link LanguageRegistry} for the desired {@link Language}.
     * If no {@link LanguageRegistry} is present for the {@link Language}, a new one will be made
     */
    public static LanguageRegistry instance(Language lang)
    {
        if (LANGUAGE_REGISTRIES.get(lang) == null || !LANGUAGE_REGISTRIES.containsKey(lang)) // Is there a language registry for the language requested?
        {
            LANGUAGE_REGISTRIES.put(lang, new LanguageRegistry()); // Create new language registry if one does not already exist
        }
        return LANGUAGE_REGISTRIES.get(lang); // Return the language registry
    }

    /**
     * Gets the item name registry
     * @return The item's {@link NameRegistry}
     */
    public NameRegistry<RegistryObject<Item>> getItemNameRegistry() {
        return itemNameRegistry;
    }

    /**
     * Gets the block name registry
     * @return The block's {@link NameRegistry}
     */
    public NameRegistry<RegistryObject<Block>> getBlockNameRegistry() {
        return blockNameRegistry;
    }

    /**
     * Gets the attribute name registry
     * @return The attribute's {@link NameRegistry}
     */
    public NameRegistry<RegistryObject<Attribute>> getAttributeNameRegistry() {
        return attributeNameRegistry;
    }

    /**
     * Gets the creative mode tab name registry
     * @return The creative mode tab's {@link NameRegistry}
     */
    public NameRegistry<RegistryObject<CreativeModeTab>> getCreativeModeTabNameRegistry() {
        return creativeModeTabNameRegistry;
    }

    /**
     * Gets the key mapping name registry
     * @return The key mapping's {@link NameRegistry}
     */
    public NameRegistry<KeyMapping> getKeyMappingNameRegistry() {
        return keyMappingNameRegistry;
    }

    /**
     * Gets the mob effect name registry
     * @return The mob effect's {@link NameRegistry}
     */
    public NameRegistry<RegistryObject<MobEffect>> getMobEffectNameRegistry() {
        return mobEffectNameRegistry;
    }

    /**
     * Gets the entity name registry
     * @return The entity's {@link NameRegistry}
     */
    public NameRegistry<ResourceLocation> getEntityNameRegistry() {
        return entityNameRegistry;
    }
}

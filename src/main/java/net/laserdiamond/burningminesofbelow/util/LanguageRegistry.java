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
 * Class that is used to help manage the names and languages for different assets of this mod
 * <p>
 * This class cannot be instantiated outside of this class. There should only be one instance of it per {@link Language} in order to avoid conflict.
 * </p>
 */
public class LanguageRegistry {

    private final NameRegistry<RegistryObject<Item>> itemNameRegistry;
    private final NameRegistry<RegistryObject<Block>> blockNameRegistry;
    private final NameRegistry<RegistryObject<Attribute>> attributeNameRegistry;
    private final NameRegistry<RegistryObject<CreativeModeTab>> creativeModeTabNameRegistry;
    private final NameRegistry<KeyMapping> keyMappingNameRegistry;
    private final NameRegistry<RegistryObject<MobEffect>> mobEffectNameRegistry;
    private final NameRegistry<ResourceLocation> entityNameRegistry;

    private static final HashMap<Language, LanguageRegistry> LANGUAGE_REGISTRIES = new HashMap<>(); // HashMap of all the Languages and their registries

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

    public NameRegistry<RegistryObject<Item>> getItemNameRegistry() {
        return itemNameRegistry;
    }

    public NameRegistry<RegistryObject<Block>> getBlockNameRegistry() {
        return blockNameRegistry;
    }

    public NameRegistry<RegistryObject<Attribute>> getAttributeNameRegistry() {
        return attributeNameRegistry;
    }

    public NameRegistry<RegistryObject<CreativeModeTab>> getCreativeModeTabNameRegistry() {
        return creativeModeTabNameRegistry;
    }

    public NameRegistry<KeyMapping> getKeyMappingNameRegistry() {
        return keyMappingNameRegistry;
    }

    public NameRegistry<RegistryObject<MobEffect>> getMobEffectNameRegistry() {
        return mobEffectNameRegistry;
    }

    public NameRegistry<ResourceLocation> getEntityNameRegistry() {
        return entityNameRegistry;
    }
}

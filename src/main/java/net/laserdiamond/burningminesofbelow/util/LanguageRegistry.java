package net.laserdiamond.burningminesofbelow.util;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

/**
 * Class that is used to help manage the names and languages for different assets of this mod
 * <p>
 * This class cannot be instantiated outside of this class. There should only be one instance of it per {@link Language} in order to avoid conflict (Singleton design pattern)
 * </p>
 */
public class LanguageRegistry {

    private final Language lang;
    private final NameRegistry<RegistryObject<Item>> itemNameRegistry;
    private final NameRegistry<RegistryObject<Block>> blockNameRegistry;
    private final NameRegistry<RegistryObject<Attribute>> attributeNameRegistry;
    private final NameRegistry<RegistryObject<CreativeModeTab>> creativeModeTabNameRegistry;
    private final NameRegistry<KeyMapping> keyMappingNameRegistry;

    // TODO: Add other name registries here

    private static final HashMap<Language, LanguageRegistry> LANGUAGE_REGISTRIES = new HashMap<>(); // HashMap of all the Languages and their registries

    private LanguageRegistry(Language lang)
    {
        this.lang = lang;
        this.itemNameRegistry = new NameRegistry<>();
        this.blockNameRegistry = new NameRegistry<>();
        this.attributeNameRegistry = new NameRegistry<>();
        this.creativeModeTabNameRegistry = new NameRegistry<>();
        this.keyMappingNameRegistry = new NameRegistry<>();
    }

    /**
     * Gets the instance of the {@link LanguageRegistry}
     * @param lang The {@link Language} of the registry
     * @return The instance of the {@link LanguageRegistry} for the desired {@link Language}
     */
    public static LanguageRegistry instance(Language lang)
    {
        if (LANGUAGE_REGISTRIES.get(lang) == null || !LANGUAGE_REGISTRIES.containsKey(lang))
        {
            LANGUAGE_REGISTRIES.put(lang, new LanguageRegistry(lang)); // Create new language registry if one does not already exist
        }
        return LANGUAGE_REGISTRIES.get(lang); // Return the language registry
    }

    public Language getLang() {
        return lang;
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
}

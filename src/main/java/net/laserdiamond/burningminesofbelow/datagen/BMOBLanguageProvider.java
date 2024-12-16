package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.data.PackOutput;
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
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Provides the Translation Files for assets of this mod</li>
 * <li>Translates the local names of items to their assigned names in the {@link LanguageRegistry}</li>
 * <li>A {@link BMOBLanguageProvider} is-a {@link LanguageProvider}</li>
 * @author Allen Malo
 */
public class BMOBLanguageProvider extends LanguageProvider {

    /**
     * The {@link LanguageRegistry} the {@link BMOBLanguageProvider} will be translating to
     */
    private final LanguageRegistry languageRegistry; // BMOBLanguageProvider has-a LanguageRegistry

    /**
     * Creates a new {@link BMOBLanguageProvider}
     * @param output The {@link PackOutput}
     * @param lang The {@link Language} to translate to
     */
    public BMOBLanguageProvider(PackOutput output, Language lang) {
        super(output, BurningMinesOfBelow.MODID, lang.getName());
        this.languageRegistry = LanguageRegistry.instance(lang); // Get the language registry of the language passed through
    }

    /**
     * Creates all the translations
     */
    @Override
    protected void addTranslations()
    {
        this.addItemTranslations();
        this.addBlockTranslations();
        this.addMobEffectTranslations();
        this.addEntityTranslations();
        this.addAttributeTranslations();
        this.addCreativeTabTranslations();
        this.addKeyMappingTranslations();
    }

    /**
     * Adds all the item name translations
     */
    private void addItemTranslations()
    {
        NameRegistry<RegistryObject<Item>> itemNameRegistry = this.languageRegistry.getItemNameRegistry();
        for (RegistryObject<Item> item : itemNameRegistry.getRegistryMap().keySet())
        {
            this.add(item.get(), itemNameRegistry.getRegistryMap().get(item));
        }
    }

    /**
     * Adds all the block name translations
     */
    private void addBlockTranslations()
    {
        NameRegistry<RegistryObject<Block>> blockNameRegistry = this.languageRegistry.getBlockNameRegistry();
        for (RegistryObject<Block> block : blockNameRegistry.getRegistryMap().keySet())
        {
            this.add(block.get(), blockNameRegistry.getRegistryMap().get(block));
        }
    }

    /**
     * Adds al the mob effect name translations
     */
    private void addMobEffectTranslations()
    {
        NameRegistry<RegistryObject<MobEffect>> mobEffectNameRegistry = this.languageRegistry.getMobEffectNameRegistry();
        for (RegistryObject<MobEffect> mobEffect : mobEffectNameRegistry.getRegistryMap().keySet())
        {
            this.add(mobEffect.get(), mobEffectNameRegistry.getRegistryMap().get(mobEffect));
        }
    }

    /**
     * Adds all the entity name translations
     */
    private void addEntityTranslations()
    {
        NameRegistry<ResourceLocation> entityNameRegistry = this.languageRegistry.getEntityNameRegistry();
        for (ResourceLocation entity : entityNameRegistry.getRegistryMap().keySet())
        {
            this.add("entity." + entity.toString().replace(":", "."), entityNameRegistry.getRegistryMap().get(entity));
        }
    }

    /**
     * Adds all the attribute name translations
     */
    private void addAttributeTranslations()
    {
        NameRegistry<RegistryObject<Attribute>> attributeNameRegistry = this.languageRegistry.getAttributeNameRegistry();
        for (RegistryObject<Attribute> attribute : attributeNameRegistry.getRegistryMap().keySet())
        {
            this.add("attribute." + idToTranslation(attribute.getId()).replace(BurningMinesOfBelow.MODID, "name"), attributeNameRegistry.getRegistryMap().get(attribute));
        }
    }

    /**
     * Adds all the creative mode tab translations
     */
    private void addCreativeTabTranslations()
    {
        NameRegistry<RegistryObject<CreativeModeTab>> creativeModeTabNameRegistry = this.languageRegistry.getCreativeModeTabNameRegistry();
        for (RegistryObject<CreativeModeTab> creativeTab : creativeModeTabNameRegistry.getRegistryMap().keySet())
        {
            this.add("creative_tab." + idToTranslation(creativeTab.getId()), creativeModeTabNameRegistry.getRegistryMap().get(creativeTab));
        }
    }

    /**
     * Creates the {@link KeyMapping} translations
     */
    private void addKeyMappingTranslations()
    {
        this.add(BMOBKeyBindings.BURNING_MINES_OF_BELOW_CATEGORY, "Burning Mines Of Below Keybindings");
        NameRegistry<KeyMapping> keyMappingNameRegistry = this.languageRegistry.getKeyMappingNameRegistry();
        for (KeyMapping keyMapping : keyMappingNameRegistry.getRegistryMap().keySet())
        {
            this.add(keyMapping.getName(), keyMappingNameRegistry.getRegistryMap().get(keyMapping));
        }
    }

    /**
     * Replaces the ":" with a "." in the {@link ResourceLocation} and returns it as a {@link String}
     * @param id The {@link ResourceLocation}
     * @return The {@link ResourceLocation} as a {@link String}, with the ":" being replaced with a "."
     */
    private String idToTranslation(ResourceLocation id)
    {
        return id.toString().replace(":", ".");
    }

}

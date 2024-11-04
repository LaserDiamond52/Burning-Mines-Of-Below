package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class BMOBLanguageProvider extends LanguageProvider {

    private final LanguageRegistry languageRegistry;

    public BMOBLanguageProvider(PackOutput output, Language lang) {
        super(output, BurningMinesOfBelow.MODID, lang.getName());
        this.languageRegistry = LanguageRegistry.instance(lang); // Get the language registry of the language passed through
    }

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

    private void addKeyMappingTranslations()
    {
        this.add(BMOBKeyBindings.BURNING_MINES_OF_BELOW_CATEGORY, "Burning Mines Of Below Keybindings");
        NameRegistry<KeyMapping> keyMappingNameRegistry = this.languageRegistry.getKeyMappingNameRegistry();
        for (KeyMapping keyMapping : keyMappingNameRegistry.getRegistryMap().keySet())
        {
            this.add(keyMapping.getName(), keyMappingNameRegistry.getRegistryMap().get(keyMapping));
        }
    }

    private String idToTranslation(ResourceLocation id)
    {
        return id.toString().replace(":", ".");
    }

}

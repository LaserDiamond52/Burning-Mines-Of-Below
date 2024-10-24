package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.Attribute;
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
        this.addEntityTranslations();
        this.addAttributeTranslations();
    }

    /**
     * Adds all the item name translations
     */
    private void addItemTranslations()
    {
        NameRegistry<Item> itemNameRegistry = this.languageRegistry.getItemNameRegistry();
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
        NameRegistry<Block> blockNameRegistry = this.languageRegistry.getBlockNameRegistry();
        for (RegistryObject<Block> block : blockNameRegistry.getRegistryMap().keySet())
        {
            this.add(block.get(), blockNameRegistry.getRegistryMap().get(block));
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
        NameRegistry<Attribute> attributeNameRegistry = this.languageRegistry.getAttributeNameRegistry();
        for (RegistryObject<Attribute> attribute : attributeNameRegistry.getRegistryMap().keySet())
        {
            this.add(attribute.getId().toString(), attributeNameRegistry.getRegistryMap().get(attribute));
        }
    }

}

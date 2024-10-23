package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class BMOBLanguageProvider extends LanguageProvider {

    public BMOBLanguageProvider(PackOutput output, String locale) {
        super(output, BurningMinesOfBelow.MODID, locale);
    }

    @Override
    protected void addTranslations()
    {
        this.addItemTranslations();
        this.addBlockTranslations();
        this.addEntityTranslations();
    }

    private void addItemTranslations()
    {
        for (String name : BMOBItems.ITEM_NAME_REGISTRY.getRegistryMap().keySet())
        {
            this.add(BMOBItems.ITEM_NAME_REGISTRY.getRegistryMap().get(name).get(), name);
        }
    }

    private void addBlockTranslations()
    {
        for (String name : BMOBBlocks.BLOCK_NAME_REGISTRY.getRegistryMap().keySet())
        {
            this.add(BMOBBlocks.BLOCK_NAME_REGISTRY.getRegistryMap().get(name).get(), name);
        }
    }

    private void addEntityTranslations()
    {

    }


}

package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BMOBItemModelProvider extends ItemModelProvider {
    public BMOBItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BurningMinesOfBelow.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}

package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BMOBBlocksStateProvider extends BlockStateProvider {

    public BMOBBlocksStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BurningMinesOfBelow.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}

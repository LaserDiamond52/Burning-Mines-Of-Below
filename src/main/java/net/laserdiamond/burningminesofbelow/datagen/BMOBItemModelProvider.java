package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BMOBItemModelProvider extends ItemModelProvider {

    private final ItemModelGenerators itemModelGenerators;
    public BMOBItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, BurningMinesOfBelow.MODID, existingFileHelper);
        this.itemModelGenerators = new ItemModelGenerators(((resourceLocation, jsonElementSupplier) -> {resourceLocation = new ResourceLocation(BurningMinesOfBelow.MODID);
        }));
    }

    @Override
    protected void registerModels() {


    }
}

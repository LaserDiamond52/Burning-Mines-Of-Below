package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.CocytusTundraRegion;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.CocytusWastelandRegion;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.MagmaFieldsRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class BMOBTerrablender {

    public static void registerBiomes()
    {
        // TODO: Overworld region (if needed)

        Regions.register(new CocytusTundraRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "cocytus_tundra"), 6));
        Regions.register(new CocytusWastelandRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "cocytus_wasteland"), 5));
        Regions.register(new MagmaFieldsRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "magma_fields"), 7));
    }
}

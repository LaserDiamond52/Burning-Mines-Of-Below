package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class BMOBTerrablender {

    public static void registerBiomes()
    {
        // TODO: Overworld region (if needed)
        Regions.register(new BMOBNetherRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "nether"), 5));
    }
}

package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.nether.CocytusTundraRegion;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.nether.CocytusWastelandRegion;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.nether.MagniteFieldsRegion;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.overworld.MagniteCavesRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers the Biome Regions for Terrablender to generate the biomes around the world(s)</li>
 * @author Allen Malo
 */
public class BMOBTerrablender {

    /**
     * Registers all regions to Terrablender's Regions registry
     */
    public static void registerBiomeRegions()
    {
        Regions.register(new MagniteCavesRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "magnite_caves"), 4));

        Regions.register(new CocytusTundraRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "cocytus_tundra"), 9));
        Regions.register(new CocytusWastelandRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "cocytus_wasteland"), 10));
        Regions.register(new MagniteFieldsRegion(new ResourceLocation(BurningMinesOfBelow.MODID, "magnite_fields"), 12));
    }
}

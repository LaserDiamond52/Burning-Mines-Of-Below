package net.laserdiamond.burningminesofbelow.worldgen.feature;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BMOBFeature {

    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BurningMinesOfBelow.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> COCYTUS_ICE_SPIKE = FEATURES.register("cocytus_ice_spikes", CocytusIceSpikeFeature::new);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> COCYTUS_CEILING_ICE = FEATURES.register("cocytus_ceiling_ice", CocytusCeilingIceFeature::new);

    public static void registerFeatures(IEventBus eventBus)
    {
        FEATURES.register(eventBus);
    }
}

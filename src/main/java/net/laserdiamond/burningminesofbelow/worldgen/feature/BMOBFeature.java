package net.laserdiamond.burningminesofbelow.worldgen.feature;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and register new {@link Feature}s to the {@link BMOBFeature#FEATURES} registry</li>
 * @author Allen Malo
 */
public class BMOBFeature {

    /**
     * {@link Feature} registry of this mod
     */
    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BurningMinesOfBelow.MODID);

    /**
     * Cocytus Ice Spikes Feature
     */
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> COCYTUS_ICE_SPIKE = FEATURES.register("cocytus_ice_spikes", CocytusIceSpikeFeature::new);

    /**
     * Cocytus Ceiling Ice Feature
     */
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> COCYTUS_CEILING_ICE = FEATURES.register("cocytus_ceiling_ice", CocytusCeilingIceFeature::new);

    /**
     * Registers all {@link Feature}s under the {@link BMOBFeature#FEATURES} registry
     * @param eventBus The {@link IEventBus} of this mod
     */
    public static void registerFeatures(IEventBus eventBus)
    {
        FEATURES.register(eventBus);
    }
}

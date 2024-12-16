package net.laserdiamond.burningminesofbelow.worldgen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains the {@link ResourceKey}s of the {@link BiomeModifier}s for this mod</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBBiomeModifiers {

    /**
     * Biome Modifier for Garnet Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_GARNET_ORE = registerKey("overworld_garnet_ore_bm");

    /**
     * Biome Modifier for Deepslate Garnet Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEPSLATE_GARNET_ORE = registerKey("overworld_deepslate_garnet_ore_bm");

    /**
     * Biome Modifier for Deep Garnet Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_GARNET_ORE = registerKey("overworld_deep_garnet_ore_bm");

    /**
     * Biome Modifier for Peridot Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_PERIDOT_ORE = registerKey("overworld_peridot_ore_bm");

    /**
     * Biome Modifier for Deepslate Peridot Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEPSLATE_PERIDOT_ORE = registerKey("overworld_deepslate_peridot_ore_bm");

    /**
     * Biome Modifier for Deep Peridot Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_PERIDOT_ORE = registerKey("overworld_deep_peridot_ore_bm");

    /**
     * Biome Modifier for Deep Diamond Ore placement in the Overworld
     */
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_DIAMOND_ORE = registerKey("overworld_deep_diamond_ore_bm");

    /**
     * Biome Modifier for Deep Garnet Ore placement in the Nether
     */
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_GARNET_ORE = registerKey("nether_deep_garnet_ore_bm");

    /**
     * Biome Modifier for Deep Peridot Ore placement in the Nether
     */
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_PERIDOT_ORE = registerKey("nether_deep_peridot_ore_bm");

    /**
     * Biome Modifier for Deep diamond Ore placement in the Nether
     */
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_DIAMOND_ORE = registerKey("nether_deep_diamond_ore_bm");

    /**
     * Biome Modifier for Cryonite Ore placement in the Nether
     */
    public static final ResourceKey<BiomeModifier> NETHER_CRYONITE_ORE = registerKey("nether_cryonite_ore_bm");

    /**
     * Biome Modifier for Cocytus Ice Spike placement in Cocytus-themed biomes
     */
    public static final ResourceKey<BiomeModifier> COCYTUS_ICE_SPIKE = registerKey("cocytus_ice_spike_bm");

    /**
     * Biome Modifier for Cocytus Ceiling Ice placement in Cocytus-themed biomes
     */
    public static final ResourceKey<BiomeModifier> COCYTUS_CEILING_ICE = registerKey("cocytus_ceiling_ice_bm");

    /**
     * Registers all the {@link BiomeModifier}s of this mod
     * @param context The {@link BootstapContext} for registering the {@link BiomeModifier}s
     */
    public static void bootstrap(BootstapContext<BiomeModifier> context)
    {
        createOreBiomeModifier(context, OVERWORLD_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_GARNET_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_DEEPSLATE_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEPSLATE_GARNET_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_DEEP_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_GARNET_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_PERIDOT_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_DEEPSLATE_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEPSLATE_PERIDOT_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_DEEP_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_PERIDOT_ORE_PLACED_KEY);
        createOreBiomeModifier(context, OVERWORLD_DEEP_DIAMOND_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_DIAMOND_ORE_PLACED_KEY);
        createOreBiomeModifier(context, NETHER_DEEP_GARNET_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_GARNET_ORE_PLACED_KEY);
        createOreBiomeModifier(context, NETHER_DEEP_PERIDOT_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_PERIDOT_ORE_PLACED_KEY);
        createOreBiomeModifier(context, NETHER_DEEP_DIAMOND_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_DIAMOND_ORE_PLACED_KEY);

        createBiomeModifier(context, NETHER_CRYONITE_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_CRYONITE_ORE_PLACED_KEY, GenerationStep.Decoration.UNDERGROUND_DECORATION);

        createBiomeModifier(context, COCYTUS_ICE_SPIKE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.COCYTUS_ICE_SPIKES_KEY, GenerationStep.Decoration.UNDERGROUND_DECORATION);
        createBiomeModifier(context, COCYTUS_CEILING_ICE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.COCYTUS_CEILING_ICE, GenerationStep.Decoration.UNDERGROUND_DECORATION);
    }

    /**
     * Registers the biome modifier to this mod
     * @param name The name of the biome modifier
     * @return A {@link ResourceKey} depicting the new {@link BiomeModifier}
     */
    private static ResourceKey<BiomeModifier> registerKey(String name)
    {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(BurningMinesOfBelow.MODID, name));
    }

    /**
     * Creates a new biome modifier
     * @param context The {@link BootstapContext}
     * @param biomeModifier The {@link ResourceKey} for the {@link BiomeModifier}
     * @param biomeTag The biome depicting where the placed feature can generate
     * @param placedFeature The {@link ResourceKey} of the placed feature
     * @param generationStep The generation step in world generation for the placed feature to generate
     */
    private static void createBiomeModifier(BootstapContext<BiomeModifier> context, ResourceKey<BiomeModifier> biomeModifier, TagKey<Biome> biomeTag, ResourceKey<PlacedFeature> placedFeature, GenerationStep.Decoration generationStep)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        context.register(biomeModifier, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(biomeTag),
                HolderSet.direct(placedFeatures.getOrThrow(placedFeature)),
                generationStep
        ));
    }

    /**
     * Creates a new ore biome modifier
     * @param context The {@link BootstapContext}
     * @param biomeModifier The {@link BiomeModifier} for the ores
     * @param biomeTag The tag depicting where the ores can spawn
     * @param oreKey The {@link PlacedFeature} ore key
     */
    private static void createOreBiomeModifier(BootstapContext<BiomeModifier> context, ResourceKey<BiomeModifier> biomeModifier, TagKey<Biome> biomeTag, ResourceKey<PlacedFeature> oreKey)
    {
        createBiomeModifier(context, biomeModifier, biomeTag, oreKey, GenerationStep.Decoration.UNDERGROUND_ORES);
    }
}

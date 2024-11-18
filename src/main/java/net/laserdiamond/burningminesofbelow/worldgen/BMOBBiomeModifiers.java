package net.laserdiamond.burningminesofbelow.worldgen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.HolderGetter;
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

public class BMOBBiomeModifiers {

    public static final ResourceKey<BiomeModifier> OVERWORLD_GARNET_ORE = registerKey("overworld_garnet_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEPSLATE_GARNET_ORE = registerKey("overworld_deepslate_garnet_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_GARNET_ORE = registerKey("overworld_deep_garnet_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_PERIDOT_ORE = registerKey("overworld_peridot_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEPSLATE_PERIDOT_ORE = registerKey("overworld_deepslate_peridot_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_PERIDOT_ORE = registerKey("overworld_deep_peridot_ore");
    public static final ResourceKey<BiomeModifier> OVERWORLD_DEEP_DIAMOND_ORE = registerKey("overworld_deep_diamond_ore");
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_GARNET_ORE = registerKey("nether_deep_garnet_ore");
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_PERIDOT_ORE = registerKey("nether_deep_peridot_ore");
    public static final ResourceKey<BiomeModifier> NETHER_DEEP_DIAMOND_ORE = registerKey("nether_deep_diamond_ore");
    public static final ResourceKey<BiomeModifier> NETHER_CYRONITE_ORE = registerKey("nether_cyronite_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        createOreFeature(context, OVERWORLD_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_GARNET_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_DEEPSLATE_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEPSLATE_GARNET_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_DEEP_GARNET_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_GARNET_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_PERIDOT_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_DEEPSLATE_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEPSLATE_PERIDOT_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_DEEP_PERIDOT_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_PERIDOT_ORE_PLACED_KEY);
        createOreFeature(context, OVERWORLD_DEEP_DIAMOND_ORE, BiomeTags.IS_OVERWORLD, BMOBPlacedFeatures.OVERWORLD_DEEP_DIAMOND_ORE_PLACED_KEY);
        createOreFeature(context, NETHER_DEEP_GARNET_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_GARNET_ORE_PLACED_KEY);
        createOreFeature(context, NETHER_DEEP_PERIDOT_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_PERIDOT_ORE_PLACED_KEY);
        createOreFeature(context, NETHER_DEEP_DIAMOND_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_DEEP_DIAMOND_ORE_PLACED_KEY);
        createOreFeature(context, NETHER_CYRONITE_ORE, BiomeTags.IS_NETHER, BMOBPlacedFeatures.NETHER_CYRONITE_ORE_PLACED_KEY);

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
     * Creates a new ore biome modifier
     * @param context The {@link BootstapContext}
     * @param biomeModifier The {@link BiomeModifier} for the ores
     * @param biomeTag The tag depicting where the ores can spawn
     * @param oreKey The {@link PlacedFeature} ore key
     */
    private static void createOreFeature(BootstapContext<BiomeModifier> context, ResourceKey<BiomeModifier> biomeModifier, TagKey<Biome> biomeTag, ResourceKey<PlacedFeature> oreKey)
    {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        context.register(biomeModifier, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(biomeTag),
                HolderSet.direct(placedFeatures.getOrThrow(oreKey)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }
}

package net.laserdiamond.burningminesofbelow.worldgen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class BMOBPlacedFeatures {

    public static final ResourceKey<PlacedFeature> OVERWORLD_GARNET_ORE_PLACED_KEY = registerKey("overworld_garnet_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_DEEPSLATE_GARNET_ORE_PLACED_KEY = registerKey("overworld_deepslate_garnet_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_DEEP_GARNET_ORE_PLACED_KEY = registerKey("overworld_deep_garnet_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_PERIDOT_ORE_PLACED_KEY = registerKey("overworld_peridot_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_DEEPSLATE_PERIDOT_ORE_PLACED_KEY = registerKey("overworld_deepslate_peridot_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_DEEP_PERIDOT_ORE_PLACED_KEY = registerKey("overworld_deep_peridot_ore_pf");

    public static final ResourceKey<PlacedFeature> OVERWORLD_DEEP_DIAMOND_ORE_PLACED_KEY = registerKey("overworld_deep_diamond_ore_pf");

    public static final ResourceKey<PlacedFeature> NETHER_DEEP_GARNET_ORE_PLACED_KEY = registerKey("nether_deep_garnet_ore_pf");

    public static final ResourceKey<PlacedFeature> NETHER_DEEP_PERIDOT_ORE_PLACED_KEY = registerKey("nether_deep_peridot_ore_pf");

    public static final ResourceKey<PlacedFeature> NETHER_DEEP_DIAMOND_ORE_PLACED_KEY = registerKey("nether_deep_diamond_ore_pf");

    public static final ResourceKey<PlacedFeature> NETHER_CYRONITE_ORE_PLACED_KEY = registerKey("nether_cyronite_ore_pf");

    public static final ResourceKey<PlacedFeature> FIRE_KEY = registerKey("fire_patches_pf");

    public static final ResourceKey<PlacedFeature> SOUL_FIRE_KEY = registerKey("soul_fire_patches_pf");

    public static final ResourceKey<PlacedFeature> COCYTUS_ICE_SPIKES_KEY = registerKey("cocytus_ice_spikes_pf");

    public static final ResourceKey<PlacedFeature> COCYTUS_CEILING_ICE = registerKey("cocytus_ceiling_ice");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        registerPlacedFeature(context, OVERWORLD_GARNET_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_GARNET_ORE_KEY),
                OrePlacements.rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(20))));
        registerPlacedFeature(context, OVERWORLD_DEEPSLATE_GARNET_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_DEEPSLATE_GARNET_ORE_KEY),
                OrePlacements.rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-10))));
        registerPlacedFeature(context, OVERWORLD_DEEP_GARNET_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_DEEP_GARNET_ORE_KEY),
                OrePlacements.rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-25))));

        registerPlacedFeature(context, OVERWORLD_PERIDOT_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_PERIDOT_ORE_KEY),
                OrePlacements.commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(45))));
        registerPlacedFeature(context, OVERWORLD_DEEPSLATE_PERIDOT_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_DEEPSLATE_PERIDOT_KEY),
                OrePlacements.commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(5))));
        registerPlacedFeature(context, OVERWORLD_DEEP_PERIDOT_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_DEEP_PERIDOT_KEY),
                OrePlacements.rareOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-25))));

        registerPlacedFeature(context, OVERWORLD_DEEP_DIAMOND_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.OVERWORLD_DEEP_DIAMOND_ORE_KEY),
                OrePlacements.rareOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(-25))));

        registerPlacedFeature(context, NETHER_DEEP_GARNET_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.NETHER_DEEP_GARNET_ORE_KEY),
                OrePlacements.rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(15))));
        registerPlacedFeature(context, NETHER_DEEP_PERIDOT_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.NETHER_DEEP_PERIDOT_ORE_KEY),
                OrePlacements.rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(15))));
        registerPlacedFeature(context, NETHER_DEEP_DIAMOND_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.NETHER_DEEP_DIAMOND_ORE_KEY),
                OrePlacements.rareOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(15))));

        registerPlacedFeature(context, NETHER_CYRONITE_ORE_PLACED_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.NETHER_CYRONITE_ORE_KEY),
                OrePlacements.commonOrePlacement(8, HeightRangePlacement.triangle(VerticalAnchor.absolute(8), VerticalAnchor.absolute(40))));

        List<PlacementModifier> firePlacementModifiers = List.of(CountPlacement.of(UniformInt.of(0, 5)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());
        registerPlacedFeature(context, FIRE_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.FIRE), firePlacementModifiers);
        registerPlacedFeature(context, SOUL_FIRE_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.SOUL_FIRE), firePlacementModifiers);

        registerPlacedFeature(context, COCYTUS_ICE_SPIKES_KEY, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.COCYTUS_ICE_SPIKE), firePlacementModifiers);
        registerPlacedFeature(context, COCYTUS_CEILING_ICE, configuredFeatureHolderGetter.getOrThrow(BMOBConfiguredFeatures.COCYTUS_CEILING_ICE), List.of(new PlacementModifier[]{CountPlacement.of(BiasedToBottomInt.of(0, 9)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()}));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BurningMinesOfBelow.MODID, name));
    }

    private static void registerPlacedFeature(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?,?>> config, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}

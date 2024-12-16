package net.laserdiamond.burningminesofbelow.worldgen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.worldgen.feature.BMOBFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains all the {@link ConfiguredFeature}s of this mod</li>
 * <li>Registers the {@link ConfiguredFeature}s of this mod</li>
 * @author Allen Malo
 */
public class BMOBConfiguredFeatures {

    /**
     * {@link ConfiguredFeature} for garnet ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GARNET_ORE_KEY = registerKey("overworld_garnet_ore_cf");

    /**
     * {@link ConfiguredFeature} for deepslate garnet ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_GARNET_ORE_KEY = registerKey("overworld_deepslate_garnet_ore_cf");

    /**
     * {@link ConfiguredFeature} for deep garnet ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_GARNET_ORE_KEY = registerKey("overworld_deep_garnet_ore_cf");

    /**
     * {@link ConfiguredFeature} for peridot ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PERIDOT_ORE_KEY = registerKey("overworld_peridot_ore_cf");

    /**
     * {@link ConfiguredFeature} for deepslate peridot ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_PERIDOT_KEY = registerKey("overworld_deepslate_peridot_cf");

    /**
     * {@link ConfiguredFeature} for deep peridot ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_PERIDOT_KEY = registerKey("overworld_deep_peridot_cf");

    /**
     * {@link ConfiguredFeature} for deep diamond ore generation in the overworld
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_DIAMOND_ORE_KEY = registerKey("overworld_deep_diamond_ore_cf");

    /**
     * {@link ConfiguredFeature} for deep garnet ore generation in the Nether
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_GARNET_ORE_KEY = registerKey("nether_deep_garnet_ore_cf");

    /**
     * {@link ConfiguredFeature} for deep peridot ore generation in the Nether
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_PERIDOT_ORE_KEY =registerKey("nether_deep_peridot_ore_cf");

    /**
     * {@link ConfiguredFeature} for deep diamond ore generation in the Nether
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_DIAMOND_ORE_KEY = registerKey("nether_deep_diamond_ore_cf");

    /**
     * {@link ConfiguredFeature} for cyronite ore generation in the Nether
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_CYRONITE_ORE_KEY = registerKey("nether_cyronite_ore_cf");

    /**
     * {@link ConfiguredFeature} for the fire biome modifier placement
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIRE = registerKey("fire_cf");

    /**
     * {@link ConfiguredFeature} for the soul fire biome modifier placement
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_FIRE = registerKey("soul_fire_cf");

    /**
     * {@link ConfiguredFeature} for the cocytus biome ice spike placement
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCYTUS_ICE_SPIKE = registerKey("cocytus_ice_spike_cf");

    /**
     * {@link ConfiguredFeature} for the cocytus biome ceiling ice placement
     */
    public static final ResourceKey<ConfiguredFeature<?, ?>> COCYTUS_CEILING_ICE = registerKey("cocytus_ceiling_ice_cf");

    /**
     * Registers the {@link ConfiguredFeature}s of this mod
     * @param context The {@link BootstapContext} for registering the {@link ConfiguredFeature}s
     */
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceTest = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest frozenNetherrackReplaceTest = new TagMatchTest(BMOBTags.Blocks.FROZEN_NETHERRACK_ORE_REPLACEABLE);

        registerOreFeature(context, OVERWORLD_GARNET_ORE_KEY, stoneReplaceTest, BMOBBlocks.GARNET_ORE.get(), 5);
        registerOreFeature(context, OVERWORLD_DEEPSLATE_GARNET_ORE_KEY, deepslateReplaceTest, BMOBBlocks.DEEPSLATE_GARNET_ORE.get(), 4);
        registerOreFeature(context, OVERWORLD_DEEP_GARNET_ORE_KEY, deepslateReplaceTest, BMOBBlocks.DEEP_GARNET_ORE.get(), 2);

        registerOreFeature(context, OVERWORLD_PERIDOT_ORE_KEY, stoneReplaceTest, BMOBBlocks.PERIDOT_ORE.get(), 7);
        registerOreFeature(context, OVERWORLD_DEEPSLATE_PERIDOT_KEY, deepslateReplaceTest, BMOBBlocks.DEEPSLATE_PERIDOT_ORE.get(), 6);
        registerOreFeature(context, OVERWORLD_DEEP_PERIDOT_KEY, deepslateReplaceTest, BMOBBlocks.DEEP_PERIDOT_ORE.get(), 4);

        registerOreFeature(context, OVERWORLD_DEEP_DIAMOND_ORE_KEY, deepslateReplaceTest, BMOBBlocks.DEEP_DIAMOND_ORE.get(), 3);

        registerOreFeature(context, NETHER_DEEP_GARNET_ORE_KEY, netherrackReplaceTest, BMOBBlocks.DEEP_GARNET_ORE.get(), 4);
        registerOreFeature(context, NETHER_DEEP_PERIDOT_ORE_KEY, netherrackReplaceTest, BMOBBlocks.DEEP_PERIDOT_ORE.get(), 5);
        registerOreFeature(context, NETHER_DEEP_DIAMOND_ORE_KEY, netherrackReplaceTest, BMOBBlocks.DEEP_DIAMOND_ORE.get(), 4);

        registerScatteredOreFeature(context, NETHER_CYRONITE_ORE_KEY, frozenNetherrackReplaceTest, BMOBBlocks.CYRONITE_ORE.get(), 4);

        registerFeature(context, FIRE, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FIRE)), List.of(BMOBBlocks.MAGNITE_STONE.get(), Blocks.NETHERRACK, Blocks.MAGMA_BLOCK)));
        registerFeature(context, SOUL_FIRE, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SOUL_FIRE)), List.of(BMOBBlocks.FROZEN_SOUL_SAND.get(), BMOBBlocks.FROZEN_SOUL_SOIL.get(), BMOBBlocks.FROZEN_NETHERRACK.get(), Blocks.SOUL_SOIL)));
        registerFeature(context, COCYTUS_ICE_SPIKE, BMOBFeature.COCYTUS_ICE_SPIKE.get());
        registerFeature(context, COCYTUS_CEILING_ICE, BMOBFeature.COCYTUS_CEILING_ICE.get());
    }

    /**
     * Registers a new {@link ConfiguredFeature}
     * @param name The name of the new Configured Feature
     * @return A {@link ResourceKey} representing the new Configured Feature
     */
    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BurningMinesOfBelow.MODID, name));
    }

    /**
     * Creates a new Configured Feature
     * @param context The {@link BootstapContext}
     * @param key The {@link ResourceKey} of the {@link ConfiguredFeature}
     * @param feature The {@link Feature} to register the key as
     * @param config The {@link FeatureConfiguration} for the {@link ConfiguredFeature}
     * @param <FC> The {@link FeatureConfiguration} type
     * @param <F> The {@link Feature} type
     */
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void registerFeature(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config)
    {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    /**
     * Creates a new Configured Feature with the Feature Configuration set to None
     * @param context The {@link BootstapContext}
     * @param key The {@link ResourceKey} of the {@link ConfiguredFeature}
     * @param feature The {@link Feature} to register the key as. This must be a {@link NoneFeatureConfiguration} feature.
     */
    private static void registerFeature(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature)
    {
        registerFeature(context, key, feature, FeatureConfiguration.NONE);
    }

    /**
     * Creates a new Ore Configured Feature
     * @param context The {@link BootstapContext}
     * @param key The {@link ResourceKey} for the configured feature of the ore
     * @param ruleTest The ore's {@link RuleTest}
     * @param oreBlock The ore block
     * @param veinSize The vein size
     */
    private static void registerOreFeature(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, RuleTest ruleTest, Block oreBlock, int veinSize)
    {
        registerFeature(context, key, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(ruleTest, oreBlock.defaultBlockState())), veinSize));
    }

    /**
     * Creates a new Scattered Ore Configured Feature
     * @param context The {@link BootstapContext}
     * @param key The {@link ResourceKey} for the configured feature of the ore
     * @param ruleTest The ore's {@link RuleTest}
     * @param oreBlock The ore block
     * @param veinSize The vein size
     */
    private static void registerScatteredOreFeature(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, RuleTest ruleTest, Block oreBlock, int veinSize)
    {
        registerFeature(context, key, Feature.SCATTERED_ORE, new OreConfiguration(List.of(OreConfiguration.target(ruleTest, oreBlock.defaultBlockState())), veinSize));
    }
}

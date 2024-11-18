package net.laserdiamond.burningminesofbelow.worldgen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlock;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;

public class BMOBConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GARNET_ORE_KEY = registerKey("overworld_garnet_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_GARNET_ORE_KEY = registerKey("overworld_deepslate_garnet_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_GARNET_ORE_KEY = registerKey("overworld_deep_garnet_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PERIDOT_ORE_KEY = registerKey("overworld_peridot_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_PERIDOT_KEY = registerKey("overworld_deepslate_peridot_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_PERIDOT_KEY = registerKey("overworld_deep_peridot_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEP_DIAMOND_ORE_KEY = registerKey("overworld_deep_diamond_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_GARNET_ORE_KEY = registerKey("nether_deep_garnet_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_PERIDOT_ORE_KEY =registerKey("nether_deep_peridot_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_DEEP_DIAMOND_ORE_KEY = registerKey("nether_deep_diamond_ore_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_CYRONITE_ORE_KEY = registerKey("nether_cyronite_ore_key");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest stoneReplaceTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceTest = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest frozenNetherrackReplaceTest = new BlockMatchTest(BMOBBlocks.FROZEN_NETHERRACK.get());

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

        registerOreFeature(context, NETHER_CYRONITE_ORE_KEY, frozenNetherrackReplaceTest, BMOBBlocks.CYRONITE_ORE.get(), 4);

    }

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
}

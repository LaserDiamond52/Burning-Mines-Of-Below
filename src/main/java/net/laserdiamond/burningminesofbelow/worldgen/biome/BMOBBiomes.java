package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.worldgen.BMOBPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BMOBBiomes {

    public static final ResourceKey<Biome> COCYTUS_TUNDRA = registerBiome("cocytus_tundra");

    public static final ResourceKey<Biome> COCYTUS_WASTELAND = registerBiome("cocytus_wasteland");

    public static final ResourceKey<Biome> MAGNITE_FIELDS = registerBiome("magnite_fields");

    public static final ResourceKey<Biome> MAGNITE_CAVES = registerBiome("magnite_caves");

    private static ResourceKey<Biome> registerBiome(String localName)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(BurningMinesOfBelow.MODID, localName));
    }

    public static void boostrap(BootstapContext<Biome> context)
    {
        context.register(COCYTUS_TUNDRA, cocytusTundraBiome(context));
        context.register(COCYTUS_WASTELAND, cocytusWastelandBiome(context));
        context.register(MAGNITE_FIELDS, magniteFieldsBiome(context));
        context.register(MAGNITE_CAVES, magniteCavesBiome(context));
    }

    private static Biome cocytusTundraBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();

        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 6, 3, 5));
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(BMOBEntities.FROZEN_SOUL.get(), 8, 5, 7));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Cant go first

        BiomeDefaultFeatures.addAncientDebris(builder);
        addCocytusBiomeFeatures(builder);
//        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_CEILING_ICE);
//        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_ICE_SPIKES_KEY);
//        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.NETHER_CYRONITE_ORE_PLACED_KEY);
//        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.SOUL_FIRE_KEY);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(19711)
                        .skyColor(19711)
                        .waterColor(40447)
                        .waterFogColor(40447)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static Biome cocytusWastelandBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();

        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 6, 3, 5));
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(BMOBEntities.FROZEN_SOUL.get(), 8, 4, 6));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addAncientDebris(builder);
        addCocytusBiomeFeatures(builder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(19711)
                        .skyColor(19711)
                        .waterColor(40447)
                        .waterFogColor(40447)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static Biome magniteFieldsBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 6, 3, 5));
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BLAZE, 2, 2, 3));
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(BMOBEntities.MAGNITE_BLAZE.get(), 2, 1, 2));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addNetherDefaultOres(builder);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.FIRE_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(14954241)
                        .skyColor(14954241)
                        .waterColor(15489033)
                        .waterFogColor(15489033)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static Biome magniteCavesBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 5,  3, 4));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.FIRE_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(14954241)
                        .skyColor(14954241)
                        .waterColor(15489033)
                        .waterFogColor(15489033)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static void addCocytusBiomeFeatures(BiomeGenerationSettings.Builder builder)
    {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_CEILING_ICE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_ICE_SPIKES_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.NETHER_CYRONITE_ORE_PLACED_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.SOUL_FIRE_KEY);
    }
}

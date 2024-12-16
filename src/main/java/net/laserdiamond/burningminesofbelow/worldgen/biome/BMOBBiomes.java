package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.worldgen.BMOBPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains the {@link ResourceKey}s of the {@link Biome}s of this mod</li>
 * <li>Creates and maps the {@link Biome}'s features to the {@link Biome} itself</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBBiomes {

    /**
     * The Cocytus Tundra
     */
    public static final ResourceKey<Biome> COCYTUS_TUNDRA = registerBiome("cocytus_tundra");

    /**
     * The Cocytus Wasteland
     */
    public static final ResourceKey<Biome> COCYTUS_WASTELAND = registerBiome("cocytus_wasteland");

    /**
     * The Magnite Fields
     */
    public static final ResourceKey<Biome> MAGNITE_FIELDS = registerBiome("magnite_fields");

    /**
     * The Magnite Caves
     */
    public static final ResourceKey<Biome> MAGNITE_CAVES = registerBiome("magnite_caves");

    /**
     * Registers a new {@link Biome}
     * @param localName The local name of the {@link Biome}
     * @return A {@link ResourceKey} that represents the new {@link Biome}
     */
    private static ResourceKey<Biome> registerBiome(String localName)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(BurningMinesOfBelow.MODID, localName));
    }

    /**
     * Registers the {@link Biome} 's features to the {@link ResourceKey} of the {@link Biome}
     * @param context The {@link BootstapContext} for creating the features
     */
    public static void boostrap(BootstapContext<Biome> context)
    {
        context.register(COCYTUS_TUNDRA, cocytusTundraBiome(context));
        context.register(COCYTUS_WASTELAND, cocytusWastelandBiome(context));
        context.register(MAGNITE_FIELDS, magniteFieldsBiome(context));
        context.register(MAGNITE_CAVES, magniteCavesBiome(context));
    }

    /**
     * Creates the {@link Biome} features and settings for the {@link BMOBBiomes#COCYTUS_TUNDRA}
     * @param context The {@link BootstapContext} for creating the features
     * @return A {@link Biome} with all the features of the {@link BMOBBiomes#COCYTUS_TUNDRA}
     */
    private static Biome cocytusTundraBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();

        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 6, 3, 5));
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(BMOBEntities.FROZEN_SOUL.get(), 8, 5, 7));

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

    /**
     * Creates the {@link Biome} features and settings for the {@link BMOBBiomes#COCYTUS_WASTELAND}
     * @param context The {@link BootstapContext} for creating the features
     * @return A {@link Biome} with all the features of the {@link BMOBBiomes#COCYTUS_WASTELAND}
     */
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

    /**
     * Creates the {@link Biome} features and settings for the {@link BMOBBiomes#MAGNITE_FIELDS}
     * @param context The {@link BootstapContext} for creating the features
     * @return A {@link Biome} with all the features of the {@link BMOBBiomes#MAGNITE_FIELDS}
     */
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

    /**
     * Creates the {@link Biome} features and settings for the {@link BMOBBiomes#MAGNITE_CAVES}
     * @param context The {@link BootstapContext} for creating the features
     * @return A {@link Biome} with all the features of the {@link BMOBBiomes#MAGNITE_CAVES}
     */
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

    /**
     * Adds placed features of Cocytus-themed biomes to the {@link BiomeGenerationSettings.Builder}
     * @param builder The {@link  BiomeGenerationSettings.Builder}
     */
    private static void addCocytusBiomeFeatures(BiomeGenerationSettings.Builder builder)
    {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_CEILING_ICE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.COCYTUS_ICE_SPIKES_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.NETHER_CRYONITE_ORE_PLACED_KEY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BMOBPlacedFeatures.SOUL_FIRE_KEY);
    }
}

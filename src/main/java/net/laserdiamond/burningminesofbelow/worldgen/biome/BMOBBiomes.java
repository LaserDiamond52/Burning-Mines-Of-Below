package net.laserdiamond.burningminesofbelow.worldgen.biome;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BMOBBiomes {

    public static final ResourceKey<Biome> COCYTUS_TUNDRA = registerBiome("cocytus_tundra");

    public static final ResourceKey<Biome> COCYTUS_WASTELAND = registerBiome("cocytus_wasteland");

    public static final ResourceKey<Biome> MAGMA_FIELDS = registerBiome("magma_fields");

    private static ResourceKey<Biome> registerBiome(String name)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(BurningMinesOfBelow.MODID, name));
    }

    public static void boostrap(BootstapContext<Biome> context)
    {
        context.register(COCYTUS_TUNDRA, cocytusTundraBiome(context));
        context.register(COCYTUS_WASTELAND, cocytusWastelandBiome(context));
        context.register(MAGMA_FIELDS, magmaFieldsBiome(context));
    }

    private static Biome cocytusTundraBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        // TODO: Add mobs to spawn
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 6, 3, 5));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // TODO: Find out order
//        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
//        BiomeDefaultFeatures.addIcebergs(builder);
//        BiomeDefaultFeatures.addBlueIce(builder);
//
//        BiomeDefaultFeatures.addNetherDefaultOres(builder);
//        BiomeDefaultFeatures.addAncientDebris(builder);
//
//        BiomeDefaultFeatures.addSurfaceFreezing(builder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(10518688)
                        .skyColor(0)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static Biome cocytusWastelandBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        // TODO: Add mobs to spawn
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 6, 3, 5));


        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // TODO: Find out order
//        BiomeDefaultFeatures.addIcebergs(builder);
//        BiomeDefaultFeatures.addBlueIce(builder);
//
//        BiomeDefaultFeatures.addNetherDefaultOres(builder);
//        BiomeDefaultFeatures.addAncientDebris(builder);
//
//        BiomeDefaultFeatures.addSurfaceFreezing(builder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(10518688)
                        .skyColor(0)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    private static Biome magmaFieldsBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        mobSpawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.MAGMA_CUBE, 6, 3, 5));
        // TODO: Add other mobs to spawn

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // TODO: Find out order
//        BiomeDefaultFeatures.addNetherDefaultOres(builder);
//        BiomeDefaultFeatures.addAncientDebris(builder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .fogColor(10518688)
                        .skyColor(0)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .mobSpawnSettings(mobSpawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }
}

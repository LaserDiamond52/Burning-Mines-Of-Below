package net.laserdiamond.burningminesofbelow.worldgen.biome.regions.overworld;

import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBBiomes;
import net.laserdiamond.burningminesofbelow.worldgen.biome.regions.AbstractOverworldOverwriteRegion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;
import terrablender.api.ParameterUtils;

public final class MagniteCavesRegion extends AbstractOverworldOverwriteRegion {

    public MagniteCavesRegion(ResourceLocation name, int weight) {
        super(name, weight);
    }

    @Override
    protected ResourceKey<Biome> targetBiome() {
        return Biomes.LUSH_CAVES;
    }

    @Override
    protected @NotNull ResourceKey<Biome> biome() {
        return BMOBBiomes.MAGNITE_CAVES;
    }

    @NotNull
    @Override
    protected Climate.Parameter temperature() {
        return ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.FROZEN);
    }

    @Override
    protected @NotNull Climate.Parameter humidity() {
        return ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY);
    }

    @NotNull
    @Override
    protected Climate.Parameter continentalness() {
        return ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.INLAND, ParameterUtils.Continentalness.INLAND);
    }

    @Override
    protected @NotNull Climate.Parameter erosion() {
        return ParameterUtils.Erosion.span(ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1);
    }

    @Override
    protected @NotNull Climate.Parameter depth() {
        return ParameterUtils.Depth.span(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR);
    }

    @Override
    protected @NotNull Climate.Parameter weirdness() {
        return ParameterUtils.Weirdness.span(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING);
    }

    @Override
    protected float offset() {
        return 0F;
    }
}

package net.laserdiamond.burningminesofbelow.worldgen.biome.regions;

import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;
import terrablender.api.ParameterUtils;
import terrablender.api.RegionType;

public final class CocytusWastelandRegion extends AbstractRegion {

    public CocytusWastelandRegion(ResourceLocation name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    protected @NotNull ResourceKey<Biome> biome() {
        return BMOBBiomes.COCYTUS_WASTELAND;
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
    protected ParameterUtils.Continentalness continentalness() {
        return ParameterUtils.Continentalness.INLAND;
    }

    @Override
    protected @NotNull ParameterUtils.Erosion[] erosion() {
        return new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1};
    }

    @Override
    protected @NotNull ParameterUtils.Depth[] depth() {
        return new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR};
    }

    @Override
    protected @NotNull ParameterUtils.Weirdness[] weirdness() {
        return new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING};
    }
}

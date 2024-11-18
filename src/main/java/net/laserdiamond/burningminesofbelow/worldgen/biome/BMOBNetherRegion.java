package net.laserdiamond.burningminesofbelow.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.List;
import java.util.function.Consumer;

public class BMOBNetherRegion extends Region {

    public BMOBNetherRegion(ResourceLocation name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        createBiome(builder, BMOBBiomes.COCYTUS_TUNDRA,
                ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.FROZEN),
                ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY),
                ParameterUtils.Continentalness.INLAND,
                new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1},
                new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR},
                new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING});

        createBiome(builder, BMOBBiomes.COCYTUS_WASTELAND,
                ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.FROZEN),
                ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY),
                ParameterUtils.Continentalness.INLAND,
                new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1},
                new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR},
                new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING});

        createBiome(builder, BMOBBiomes.MAGMA_FIELDS,
                ParameterUtils.Temperature.span(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.FROZEN),
                ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY),
                ParameterUtils.Continentalness.INLAND,
                new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1},
                new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR},
                new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING});

        builder.build().forEach(mapper);
    }

    private void createBiome(VanillaParameterOverlayBuilder builder, ResourceKey<Biome> biome, Climate.Parameter temperature, Climate.Parameter humidity, ParameterUtils.Continentalness continentalness, ParameterUtils.Erosion[] erosion, ParameterUtils.Depth[] depths, ParameterUtils.Weirdness[] weirdness)
    {
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(temperature)
                .humidity(humidity)
                .continentalness(continentalness)
                .erosion(erosion)
                .depth(depths)
                .weirdness(weirdness)
                .build().forEach(parameterPoint -> builder.add(parameterPoint, biome));
    }
}

package net.laserdiamond.burningminesofbelow.worldgen.biome.regions;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

/**
 * An abstract region, which is used to create a new biome
 */
public abstract class AbstractRegion extends Region {

    public AbstractRegion(ResourceLocation name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {

        final VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        this.createBiome(builder, biome(), temperature(), humidity(), continentalness(), erosion(), depth(), weirdness());

        builder.build().forEach(mapper);
    }

    /**
     * Creates the new biome
     * @param builder The {@link VanillaParameterOverlayBuilder}
     * @param biome The {@link ResourceKey} of the biome being added
     * @param temperature The temperature of the biome
     * @param humidity The humidity of the biome
     * @param continentalness The continentalness of the biome
     * @param erosion The erosion of the biome
     * @param depth The depth of the biome
     * @param weirdness The weirdness of the biome
     */
    protected void createBiome(VanillaParameterOverlayBuilder builder, ResourceKey<Biome> biome, Climate.Parameter temperature, Climate.Parameter humidity, ParameterUtils.Continentalness continentalness, ParameterUtils.Erosion[] erosion, ParameterUtils.Depth[] depth, ParameterUtils.Weirdness[] weirdness)
    {
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(temperature)
                .humidity(humidity)
                .continentalness(continentalness)
                .erosion(erosion)
                .depth(depth)
                .weirdness(weirdness)
                .build().forEach(parameterPoint -> builder.add(parameterPoint, biome));
    }

    /**
     * The biome being made
     * @return The {@link ResourceKey} of the biome
     */
    @NotNull
    protected abstract ResourceKey<Biome> biome();

    /**
     * The temperature of the biome
     * @return The {@link Climate.Parameter} of the biome's temperature
     */
    @NotNull
    protected abstract Climate.Parameter temperature();

    /**
     * The humidity of the biome
     * @return The {@link Climate.Parameter} of the biome's humidity
     */
    @NotNull
    protected abstract Climate.Parameter humidity();

    /**
     * The continentalness of the biome
     * @return The {@link terrablender.api.ParameterUtils.Continentalness} for the biome
     */
    @NotNull
    protected abstract ParameterUtils.Continentalness continentalness();

    /**
     * The erosion of the biome
     * @return The {@link terrablender.api.ParameterUtils.Erosion} for the biome
     */
    @NotNull
    protected abstract ParameterUtils.Erosion[] erosion();

    /**
     * The depth of the biome
     * @return The {@link terrablender.api.ParameterUtils.Depth} for the biome
     */
    @NotNull
    protected abstract ParameterUtils.Depth[] depth();

    /**
     * The weirdness of the biome
     * @return The {@link terrablender.api.ParameterUtils.Weirdness} for the biome
     */
    @NotNull
    protected abstract ParameterUtils.Weirdness[] weirdness();


}
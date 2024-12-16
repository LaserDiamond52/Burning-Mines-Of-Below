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
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to set and manage the biome placement in the world</li>
 * <li>Manages the terrain generation/landscape the biome</li>
 * <li>An {@link AbstractRegion} is-a {@link Region}</li>
 * @author Allen Malo
 */
public abstract class AbstractRegion extends Region {

    /**
     * Creates a new {@link AbstractRegion}
     * @param name The {@link ResourceLocation} for the region
     * @param type The {@link RegionType}. Determines if the region spawns in the {@link RegionType#OVERWORLD} or {@link RegionType#NETHER}
     * @param weight The weight of the region. Determines how often this region will spawn relative to other regions
     */
    public AbstractRegion(ResourceLocation name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature(), humidity(), continentalness(), erosion(), depth(), weirdness(), offset()), biome()));
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
    protected abstract Climate.Parameter continentalness();

    /**
     * The erosion of the biome
     * @return The {@link terrablender.api.ParameterUtils.Erosion} for the biome
     */
    @NotNull
    protected abstract Climate.Parameter erosion();

    /**
     * The depth of the biome
     * @return The {@link terrablender.api.ParameterUtils.Depth} for the biome
     */
    @NotNull
    protected abstract Climate.Parameter depth();

    /**
     * The weirdness of the biome
     * @return The {@link terrablender.api.ParameterUtils.Weirdness} for the biome
     */
    @NotNull
    protected abstract Climate.Parameter weirdness();

    /**
     * The offset parameter of the biome
     * @return The offset for the biome
     */
    protected abstract float offset();

}

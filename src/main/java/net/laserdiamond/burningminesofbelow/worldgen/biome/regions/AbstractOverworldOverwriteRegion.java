package net.laserdiamond.burningminesofbelow.worldgen.biome.regions;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Abstract class for creating a {@link Biome} in the Overworld that will replace some chunks of an existing {@link Biome}</li>
 * <li>An {@link AbstractOverworldOverwriteRegion} is-a {@link AbstractRegion}</li>
 * @author Allen Malo
 */
public abstract class AbstractOverworldOverwriteRegion extends AbstractRegion {

    /**
     * Creates a new {@link AbstractOverworldOverwriteRegion}
     * @param name The {@link ResourceLocation} for the region
     * @param weight The weight of the region. Determines how often this region will spawn relative to other regions
     */
    public AbstractOverworldOverwriteRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder ->
        {
            modifiedVanillaOverworldBuilder.replaceBiome(targetBiome(), biome());
            super.addBiomes(registry, mapper);
        });
    }

    /**
     * The target biome to replace some chunks of
     * @return The {@link ResourceKey} of the biome to replace chunks of
     */
    protected abstract ResourceKey<Biome> targetBiome();
}

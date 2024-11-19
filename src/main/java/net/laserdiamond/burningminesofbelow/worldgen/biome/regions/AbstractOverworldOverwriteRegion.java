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
 * An abstract class for creating a biome in the Overworld that will replace some chunks of an existing biome
 */
public abstract class AbstractOverworldOverwriteRegion extends AbstractRegion {

    public AbstractOverworldOverwriteRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
        // TODO: Create Magnite Caves using this as the superclass
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder ->
        {
            modifiedVanillaOverworldBuilder.replaceBiome(targetBiome(), biome());

            final VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

            createBiome(builder, biome(), temperature(), humidity(), continentalness(), erosion(), depth(), weirdness());
        });
    }

    /**
     * The target biome to replace some chunks of
     * @return The {@link ResourceKey} of the biome to replace chunks of
     */
    protected abstract ResourceKey<Biome> targetBiome();
}

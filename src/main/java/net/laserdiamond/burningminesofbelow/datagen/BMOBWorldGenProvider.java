package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.worldgen.BMOBBiomeModifiers;
import net.laserdiamond.burningminesofbelow.worldgen.BMOBConfiguredFeatures;
import net.laserdiamond.burningminesofbelow.worldgen.BMOBPlacedFeatures;
import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Outputs the world generation assets as json files</li>
 * <li>A {@link BMOBWorldGenProvider} is-a {@link DatapackBuiltinEntriesProvider}</li>
 * @author Allen Malo
 */
public class BMOBWorldGenProvider extends DatapackBuiltinEntriesProvider {

    /**
     * The {@link RegistrySetBuilder} that contains all the world generation assets to create
     */
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, BMOBConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, BMOBPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BMOBBiomeModifiers::bootstrap)
            .add(Registries.BIOME, BMOBBiomes::boostrap);

    /**
     * Creates a new {@link BMOBWorldGenProvider}
     * @param output The {@link PackOutput} of this mod
     * @param registries The {@link CompletableFuture} {@link HolderLookup.Provider} of this mod
     */
    public BMOBWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(BurningMinesOfBelow.MODID));
    }
}

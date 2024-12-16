package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Runs all the data generator providers of this mod under the Mod EventBus</li>
 * <li>This class automatically registers any methods with the {@link SubscribeEvent} annotation to the mod's EventBus</li>
 * <li>Event methods are annotated with the {@link SubscribeEvent} annotation so that they are registered and ready to listen for their designated event</li>
 * @author Allen Malo
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    /**
     * Gathers and runs the data generator providers
     * @param event The {@link GatherDataEvent} to listen for
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        final DataGenerator generator = event.getGenerator();
        final PackOutput packOutput = generator.getPackOutput();
        final ExistingFileHelper fileHelper = event.getExistingFileHelper();
        final CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), BMOBLootTableProvider.create(packOutput)); // Create loot tables
        generator.addProvider(event.includeServer(), new BMOBRecipeProvider(packOutput)); // Create recipes

        generator.addProvider(event.includeClient(), new BMOBItemModelProvider(packOutput, fileHelper)); // Create item models
        generator.addProvider(event.includeClient(), new BMOBBlocksStateProvider(packOutput, fileHelper)); // Create block states and models

        BMOBTagsProvider.Blocks blocksTagGenerator = generator.addProvider(event.includeServer(), new BMOBTagsProvider.Blocks(packOutput, lookUpProvider, fileHelper)); // Create block tags
        generator.addProvider(event.includeServer(), new BMOBTagsProvider.Items(packOutput, lookUpProvider, blocksTagGenerator.contentsGetter(), fileHelper)); // Create item tags
        generator.addProvider(event.includeServer(), new BMOBTagsProvider.Entities(packOutput, lookUpProvider, fileHelper)); // Create Entity Tags
//        generator.addProvider(event.includeServer(), new BMOBTagsProvider.Biomes(packOutput, lookUpProvider, fileHelper));

        generator.addProvider(event.includeServer(), new BMOBWorldGenProvider(packOutput, lookUpProvider)); // Create world generation assets

        generator.addProvider(event.includeClient(), new BMOBLanguageProvider(packOutput, Language.EN_US)); // Create translations for en_us

    }
}

package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        final DataGenerator generator = event.getGenerator();
        final PackOutput packOutput = generator.getPackOutput();
        final ExistingFileHelper fileHelper = event.getExistingFileHelper();
        final CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), BMOBLootTableProvider.create(packOutput));
        generator.addProvider(event.includeServer(), new BMOBRecipeProvider(packOutput));

        generator.addProvider(event.includeClient(), new BMOBItemModelProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new BMOBBlocksStateProvider(packOutput, fileHelper));

        BMOBTagsProvider.Blocks blocksTagGenerator = generator.addProvider(event.includeServer(), new BMOBTagsProvider.Blocks(packOutput, lookUpProvider, fileHelper));
        generator.addProvider(event.includeServer(), new BMOBTagsProvider.Items(packOutput, lookUpProvider, blocksTagGenerator.contentsGetter(), fileHelper));
        generator.addProvider(event.includeServer(), new BMOBTagsProvider.Entities(packOutput, lookUpProvider, fileHelper));
    }
}

package net.laserdiamond.burningminesofbelow;

import com.mojang.logging.LogUtils;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBCreativeTabs;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.recipe.BMOBRecipes;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BurningMinesOfBelow.MODID)
public class BurningMinesOfBelow {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "burningminesofbelow";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public BurningMinesOfBelow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        this.registerEvents(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation getModResource(String path)
    {
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

        BMOBPackets.registerPackets();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        for (RegistryObject<Item> item : BMOBItems.ITEMS.getEntries())
        {
            BMOBCreativeTabs.addItemToTab(item, BMOBCreativeTabs.MAIN_TAB, event);
        }
    }

    private void registerEvents(IEventBus eventBus)
    {
        BMOBAttributes.registerAttributes(eventBus);
        BMOBItems.registerItems(eventBus);
        BMOBBlocks.registerBlocks(eventBus);
        BMOBBlockEntities.registerBlockEntities(eventBus);
        BMOBRecipes.registerSerializers(eventBus);
        BMOBCreativeTabs.registerCreativeTabs(eventBus);
        BMOBMenuTypes.registerMenuTypes(eventBus);
        BMOBRecipes.registerSerializers(eventBus);
    }
}

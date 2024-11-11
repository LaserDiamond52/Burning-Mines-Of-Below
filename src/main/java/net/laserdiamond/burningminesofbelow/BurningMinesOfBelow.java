package net.laserdiamond.burningminesofbelow;

import com.mojang.logging.LogUtils;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.effects.BMOBEffects;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBCreativeTabs;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BMOBArmorMaterials;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.recipe.BMOBRecipes;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.laserdiamond.burningminesofbelow.util.file.ArmorConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BurningMinesOfBelow.MODID)
public class BurningMinesOfBelow {

    /**
     * Mod ID of this mod
     */
    public static final String MODID = "burningminesofbelow";

    /**
     * slf4j Minecraft logger
     */
    public static final Logger LOGGER = LogUtils.getLogger();

    public BurningMinesOfBelow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        this.createArmorConfigs();
        this.registerEvents(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
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

    /**
     * Adds items to the creative tabs
     * @param event The {@link BuildCreativeModeTabContentsEvent}
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        for (RegistryObject<Item> item : BMOBItems.ITEMS.getEntries())
        {
            BMOBCreativeTabs.addItemToTab(item, BMOBCreativeTabs.MAIN_TAB, event);
        }
    }

    /**
     * Registers all the events of this mod
     * @param eventBus The {@link IEventBus} of this mod
     */
    private void registerEvents(IEventBus eventBus)
    {
        BMOBAttributes.registerAttributes(eventBus);
        BMOBItems.registerItems(eventBus);
        BMOBBlocks.registerBlocks(eventBus);
        BMOBBlockEntities.registerBlockEntities(eventBus);
        BMOBEntities.registerEntities(eventBus);
        BMOBRecipes.registerSerializers(eventBus);
        BMOBCreativeTabs.registerCreativeTabs(eventBus);
        BMOBMenuTypes.registerMenuTypes(eventBus);
        BMOBRecipes.registerSerializers(eventBus);
        BMOBEffects.registerEffects(eventBus);
    }

    /**
     * Creates all the armor json config files of this mod
     */
    private void createArmorConfigs()
    {
        for (BMOBArmorMaterials materials : BMOBArmorMaterials.values())
        {
            new ArmorConfig(materials).createFile();
        }
    }
}

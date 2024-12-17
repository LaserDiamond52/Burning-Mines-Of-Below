package net.laserdiamond.burningminesofbelow;

import com.mojang.logging.LogUtils;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.effects.BMOBEffects;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBCreativeTabs;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.recipe.BMOBRecipes;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBSurfaceRules;
import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBTerrablender;
import net.laserdiamond.burningminesofbelow.worldgen.feature.BMOBFeature;
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
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers all assets and features of this mod under the different registries</li>
 * <li>Contains the Mod ID of this mod, which helps the registries know the namespace to register their objects under</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 * <p>Forge Documentation:<a href="https://docs.minecraftforge.net/en/1.20.x/">...</a></p>
 */
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

    /**
     * Main method of this mod.
     * Fires all the registries and allows our mod to run
     */
    public BurningMinesOfBelow() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        this.registerEvents(modEventBus); // Register events of this mod

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() ->
        {
            // Register biomes
            BMOBTerrablender.registerBiomeRegions();
            // Create surface rules for out biomes
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, BMOBSurfaceRules.createOverworldSurfaceRules());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MODID, BMOBSurfaceRules.createNetherSurfaceRules());

        });

    }

    /**
     * Adds items to the creative tabs
     * @param event The {@link BuildCreativeModeTabContentsEvent}
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Loop through all items in the items registry and add them to the creative tab
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
        BMOBAttributes.registerAttributes(eventBus); // Register mod attributes
        BMOBItems.registerItems(eventBus); // Register mod items
        BMOBBlocks.registerBlocks(eventBus); // Register mod blocks
        BMOBBlockEntities.registerBlockEntities(eventBus); // Register block entities
        BMOBEntities.registerEntities(eventBus); // Register entities
        BMOBRecipes.registerSerializers(eventBus); // Register recipe types
        BMOBCreativeTabs.registerCreativeTabs(eventBus); // Register creative tabs
        BMOBMenuTypes.registerMenuTypes(eventBus); // Register menu types
        BMOBEffects.registerEffects(eventBus); // Register mob effects
        BMOBFeature.registerFeatures(eventBus); // Register features
    }
}

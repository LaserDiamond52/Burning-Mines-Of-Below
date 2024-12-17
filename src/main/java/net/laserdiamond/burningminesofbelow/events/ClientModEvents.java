package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBSkullBlock;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.renderer.BMOBSkullBlockRenderer;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.client.HeatHUDOverlay;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.client.model.*;
import net.laserdiamond.burningminesofbelow.entity.client.renderer.*;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.laserdiamond.burningminesofbelow.screen.forge.ForgeScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers and listens for client events on the mod bus of this mod</li>
 * <li>Methods with the {@link SubscribeEvent} annotation are listening for events</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 * <p>Forge Documentation:<a href="https://docs.minecraftforge.net/en/1.20.x/">...</a></p>
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{

    /**
     * Called when the client is being set up by the mod loader
     * @param event The {@link FMLClientSetupEvent} to listen for
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(BMOBMenuTypes.FORGE_MENU.get(), ForgeScreen::new);

        EntityRenderers.register(BMOBEntities.CYROBOLT.get(), CryoboltRenderer::new);
        EntityRenderers.register(BMOBEntities.MAGNITE_BLAZE.get(), MagniteBlazeRenderer::new);
        EntityRenderers.register(BMOBEntities.KING_INFERNIUS.get(), KingInferniusRenderer::new);
        EntityRenderers.register(BMOBEntities.FROZEN_SOUL.get(), FrozenSoulRenderer::new);
        EntityRenderers.register(BMOBEntities.FREEZING_REAPER.get(), FreezingReaperRenderer::new);

    }

    /**
     * Registers all the key mappings for this mod
     * @param event The {@link RegisterKeyMappingsEvent} to listen for
     */
    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event)
    {
        event.register(BMOBKeyBindings.INSTANCE.abilityKey);
    }

    /**
     * Registers the block entity renderers of this mod
     * @param event {@link net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers}
     */
    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(BMOBBlockEntities.SKULL_BLOCK_ENTITY.get(), SkullBlockRenderer::new);
    }

    /**
     * Registers layer definitions for block entities that make use of Vanilla models
     * @param event {@link net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions}
     */
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        final LayerDefinition innerArmorLayerDef = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.INNER_ARMOR_DEFORMATION), 64, 32);
        final LayerDefinition outerArmorLayerDef = LayerDefinition.create(HumanoidArmorModel.createBodyLayer(LayerDefinitions.OUTER_ARMOR_DEFORMATION), 64, 32);

        event.registerLayerDefinition(BMOBModelLayers.FROZEN_WITHER_SKULL, SkullModel::createMobHeadLayer);
        event.registerLayerDefinition(BMOBModelLayers.BLAZE_SKULL, SkullModel::createMobHeadLayer);

        event.registerLayerDefinition(BMOBModelLayers.MAGNITE_BLAZE, MagniteBlazeModel::createBodyLayer);
        event.registerLayerDefinition(BMOBModelLayers.KING_INFERNIUS, KingInferniusModel::createBodyLayer);

        event.registerLayerDefinition(BMOBModelLayers.FROZEN_SOUL, AbstractHumanoidModel::createBodyLayer);
        event.registerLayerDefinition(BMOBModelLayers.FROZEN_SOUL_INNER_ARMOR, () -> innerArmorLayerDef);
        event.registerLayerDefinition(BMOBModelLayers.FROZEN_SOUL_OUTER_ARMOR, () -> outerArmorLayerDef);

        event.registerLayerDefinition(BMOBModelLayers.FREEZING_REAPER, FreezingReaperModel::createBodyLayer);


    }

    /**
     * Adds any extra render layers to the entity renderers
     * @param event The {@link EntityRenderersEvent.AddLayers} to listen for
     */
    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event)
    {
        BMOBSkullBlockRenderer.addSkullBlockLayer(BMOBSkullBlock.Types.BLAZE_SKULL, BMOBModelLayers.BLAZE_SKULL, new ResourceLocation(BurningMinesOfBelow.MODID,"textures/block/blaze_skull.png"));
        BMOBSkullBlockRenderer.addSkullBlockLayer(BMOBSkullBlock.Types.FROZEN_WITHER_SKULL, BMOBModelLayers.FROZEN_WITHER_SKULL, new ResourceLocation(BurningMinesOfBelow.MODID,"textures/block/frozen_wither_skull.png"));

    }



    /**
     * Registers all the GUI overlays for this mod
     * @param event {@link RegisterGuiOverlaysEvent}
     */
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event)
    {
        event.registerAboveAll("heat", HeatHUDOverlay.HUD_HEAT);
    }

    /**
     * Called when common set up is being done by the mod loader
     * @param event The {@link FMLCommonSetupEvent} event
     */
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        BMOBPackets.registerPackets();
    }

}

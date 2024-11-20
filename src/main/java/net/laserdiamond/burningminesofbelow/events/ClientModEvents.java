package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.entity.BMOBBlockEntities;
import net.laserdiamond.burningminesofbelow.block.entity.renderer.BMOBSkullBlockEntityRenderer;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.client.HeatHUDOverlay;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.client.renderer.CyroboltRenderer;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.laserdiamond.burningminesofbelow.screen.forge.ForgeScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(BMOBMenuTypes.FORGE_MENU.get(), ForgeScreen::new);
    }

    /**
     * Registers all the key mappings for this mod
     * @param event {@link RegisterKeyMappingsEvent}
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
        event.registerBlockEntityRenderer(BMOBBlockEntities.SKULL_BLOCK_ENTITY.get(), BMOBSkullBlockEntityRenderer::new);
    }

    /**
     * Registers layer definitions for block entities that make use of Vanilla models
     * @param event {@link net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions}
     */
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(BMOBModelLayers.FROZEN_WITHER_SKULL, SkullModel::createMobHeadLayer);
        event.registerLayerDefinition(BMOBModelLayers.BLAZE_SKULL, SkullModel::createMobHeadLayer);
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


    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        BMOBPackets.registerPackets();
        EntityRenderers.register(BMOBEntities.CYROBOLT.get(), CyroboltRenderer::new);
    }

}

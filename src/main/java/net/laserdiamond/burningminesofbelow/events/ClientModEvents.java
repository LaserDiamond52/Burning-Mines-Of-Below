package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.client.HeatHUDOverlay;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.laserdiamond.burningminesofbelow.screen.forge.ForgeScreen;
import net.minecraft.client.gui.screens.MenuScreens;
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

    @SubscribeEvent
    public static void registerBlockEntityRenderer(EntityRenderersEvent.RegisterRenderers event)
    {

    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {

    }

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event)
    {
        event.registerAboveAll("heat", HeatHUDOverlay.HUD_HEAT);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {

    }

}

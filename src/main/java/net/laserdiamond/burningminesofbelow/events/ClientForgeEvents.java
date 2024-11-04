package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.AbilityKeyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents
{

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event)
    {
        final Minecraft minecraft = Minecraft.getInstance();
        final LocalPlayer localPlayer = minecraft.player;

        if (BMOBKeyBindings.INSTANCE.abilityKey.consumeClick())
        {
            if (localPlayer != null)
            {
                if (localPlayer.getMainHandItem().getItem() instanceof AbilityItem abilityItem)
                {
                    abilityItem.onKeyPressClient(event);
                    BMOBPackets.sendToServer(new AbilityKeyPacket());
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerUIOverlays(RenderGuiEvent event)
    {
//        final GuiGraphics guiGraphics = event.getGuiGraphics();
//        float partialTick = event.getPartialTick();
//        final Window window = event.getWindow();
//
//        final Minecraft minecraft = Minecraft.getInstance();
//        final GameRenderer gameRenderer = minecraft.gameRenderer;
//        final LocalPlayer player = minecraft.player;
//
//        if (player == null)
//        {
//            return;
//        }
//        if (player.hasEffect(BMOBEffects.HEAT_EXHAUSTION.get()))
//        {
//            float scalar = Mth.lerp(partialTick, player.oSpinningEffectIntensity, player.spinningEffectIntensity);
//            float scalar2 = minecraft.options.screenEffectScale().get().floatValue();
//
//            if (scalar > 0.0F && scalar2 < 1.0F)
//            {
//                gameRenderer.renderConfusionOverlay(guiGraphics, scalar * (1.0F - scalar2));
//            }
//            gameRenderer.renderConfusionOverlay(guiGraphics, 0.5F);
//
//        }
//
//        if (player.hasEffect(BMOBEffects.HYPOTHERMIA.get())) // Player has Hypothermia
//        {
//            minecraft.gui.renderTextureOverlay(guiGraphics, Gui.POWDER_SNOW_OUTLINE_LOCATION, player.getPercentFrozen());
//        }
    }
}

package net.laserdiamond.burningminesofbelow.client;

import net.laserdiamond.burningminesofbelow.effects.BMOBEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@Deprecated
public class HeatFreezeEffectsOverlay {

    public static final IGuiOverlay OVERLAY = (forgeGui, guiGraphics, partialTick, width, height) ->
    {
        int x = width / 2;
        int y = height;

        final Player player = ClientHeatData.getPlayer();

        Minecraft minecraft = Minecraft.getInstance();
        GameRenderer gameRenderer = minecraft.gameRenderer;

        final LocalPlayer localPlayer = minecraft.player;
        if (localPlayer == null || player == null)
        {
            return; // Player is null. Do not continue
        }
        if (player.hasEffect(BMOBEffects.HEAT_EXHAUSTION.get())) // Player has Heat Exhaustion
        {
            float scalar = Mth.lerp(partialTick, localPlayer.oSpinningEffectIntensity, localPlayer.spinningEffectIntensity);
            float scalar2 = minecraft.options.screenEffectScale().get().floatValue();

            if (scalar > 0.0F && scalar2 < 1.0F)
            {
                gameRenderer.renderConfusionOverlay(guiGraphics, scalar * (1.0F - scalar2));
            }
        }

        if (player.hasEffect(BMOBEffects.HYPOTHERMIA.get())) // Player has Hypothermia
        {
            minecraft.gui.renderTextureOverlay(guiGraphics, Gui.POWDER_SNOW_OUTLINE_LOCATION, localPlayer.getPercentFrozen());
        }

    };
}

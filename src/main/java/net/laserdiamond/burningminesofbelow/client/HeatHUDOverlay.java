package net.laserdiamond.burningminesofbelow.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HeatHUDOverlay {

    private static final ResourceLocation HEAT_BAR = new ResourceLocation(BurningMinesOfBelow.MODID, "textures/heat/heat_bars.png");

    public static final IGuiOverlay HUD_HEAT = ((forgeGui, guiGraphics, partialTick, width, height) ->
    {
        int x = width / 2;
        int y = height;

        // TODO: Don't show HUD if player is in Creative/Spectator

        int clientHeat = ClientHeatData.getPlayerHeat();
        final int barHeight = 7;
        final int barWidth = 92;

        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        guiGraphics.blit(HEAT_BAR, x, y - 57, 0, 0, barWidth, barHeight);

        guiGraphics.blit(HEAT_BAR, x, y - 57, 0, getHeatColor(clientHeat), getHeatScale(clientHeat, barWidth), barHeight);

    });

    private static int getHeatScale(int heat, int barWidth)
    {
        return heat * barWidth / PlayerHeat.MAX_HEAT;
    }

    private static int getHeatColor(int heat)
    {
        if (PlayerHeat.isHypothermia(heat))
        {
            return 7; // Player has hypothermia
        } else if (PlayerHeat.isFrostBite(heat))
        {
            return 14; // Player has frostbite
        } else if (PlayerHeat.isSafe(heat))
        {
            return 21; // Player heat is safe
        } else if (PlayerHeat.isHot(heat))
        {
            return 28; // Player is hot
        } else if (PlayerHeat.isHeatExhaustion(heat))
        {
            return 35; // Player has heat exhaustion
        }
        return 0;
    }
}

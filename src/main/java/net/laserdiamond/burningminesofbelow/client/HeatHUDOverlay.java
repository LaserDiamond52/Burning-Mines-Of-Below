package net.laserdiamond.burningminesofbelow.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Draws the Heat Bar on the client's screen</li>
 * @author Allen Malo
 */
public class HeatHUDOverlay {

    /**
     * The {@link ResourceLocation} for the heat bars to be drawn on the client's screen
     */
    private static final ResourceLocation HEAT_BAR = new ResourceLocation(BurningMinesOfBelow.MODID, "textures/heat/heat_bars.png");

    /**
     * Draws out the Heat bar on the client's screen
     */
    public static final IGuiOverlay HUD_HEAT = ((forgeGui, guiGraphics, partialTick, width, height) ->
    {
        int x = width / 2;
        int y = height;

        int clientHeat = ClientHeatData.getPlayerHeat();
        final Player player = ClientHeatData.getPlayer();
        final int barHeight = 7;
        final int barWidth = 92;

        final int[] playerCoordinates = new int[]{(int) player.position().x, (int) player.position().y, (int) player.position().z};

        player.displayClientMessage(Component.literal("X: " + playerCoordinates[0] + " | Y: " + playerCoordinates[1] + " | Z: " + playerCoordinates[2]).withStyle(getHeatColorFormat(clientHeat)), true);

        if (player.isCreative() || player.isSpectator())
        {
            return; // Do not display UI if player is in creative/spectator
        }
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        guiGraphics.blit(HEAT_BAR, x, y - 57, 0, 0, barWidth, barHeight); // Draw out empty heat bar

        guiGraphics.blit(HEAT_BAR, x, y - 57, 0, getHeatColor(clientHeat), getHeatScale(clientHeat, barWidth), barHeight); // Draw out appropriate color and size of heat bar

    });

    /**
     * Gets how much of the heat bar to draw on the screen
     * @param heat The amount of heat the playuer has
     * @param barWidth The width of the bar
     * @return An integer used for determining how much of the heat bar to draw on the player's screen
     */
    private static int getHeatScale(int heat, int barWidth)
    {
        return heat * barWidth / PlayerHeat.MAX_HEAT;
    }

    /**
     * Gets the color that the heat bar should be
     * @param heat The amount of heat the player has
     * @return The y-position of where to get the heat bar color from the texture as an integer.
     * @throws IllegalStateException If the player's heat is beyond the minimum or maximum heat bound
     */
    private static int getHeatColor(int heat) throws IllegalStateException
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
        throw new IllegalStateException("Could not get heat color for heat: " + heat);
    }

    /**
     * Gets the color that the displayed player coordinates should be
     * @param heat The amount of heat the player has
     * @return A {@link ChatFormatting} that represents the color the displayed player coordinates should be
     * @throws IllegalStateException If the player's heat is beyond the minimum or maximum heat bound
     */
    private static ChatFormatting getHeatColorFormat(int heat) throws IllegalStateException
    {
        if (PlayerHeat.isHypothermia(heat))
        {
            return ChatFormatting.AQUA;
        } else if (PlayerHeat.isFrostBite(heat))
        {
            return ChatFormatting.BLUE;
        } else if (PlayerHeat.isSafe(heat))
        {
            return ChatFormatting.YELLOW;
        } else if (PlayerHeat.isHot(heat))
        {
            return ChatFormatting.GOLD;
        } else if (PlayerHeat.isHeatExhaustion(heat))
        {
            return ChatFormatting.RED;
        }
        throw new IllegalStateException("Could not get heat color for heat: " + heat);
    }
}

package net.laserdiamond.burningminesofbelow.screen.forge;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Draws the Forge Crafting GUI on the client's screen</li>
 * <li>A {@link ForgeScreen} is-a {@link AbstractContainerScreen}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class ForgeScreen extends AbstractContainerScreen<ForgeMenu> {

    /**
     * The {@link ResourceLocation} of the texture for the Forge GUI
     */
    private static final ResourceLocation TEXTURE = new ResourceLocation(BurningMinesOfBelow.MODID, "textures/gui/forge.png");

    /**
     * The height of the fuel bar length in pixels from the texture
     */
    public static final int FUEL_BAR_LENGTH = 53;

    /**
     * Creates a new {@link ForgeScreen}
     * @param pMenu The {@link ForgeMenu} the screen will be a part of
     * @param pPlayerInventory The {@link Inventory} this will be added to
     * @param pTitle The {@link Component} that contains the title of the {@link ForgeScreen}
     */
    public ForgeScreen(ForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    /**
     * Draws the Forge's GUI texture on the client's screen
     * @param guiGraphics The {@link GuiGraphics} of the screen
     * @param v The partial tick
     * @param x The x position of the cursor
     * @param y The y position of the cursor
     */
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int screenX = (this.width - this.imageWidth) / 2;
        int screenY = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, screenX, screenY, 0, 0, this.imageWidth, this.imageHeight);

        this.renderFuelLevels(guiGraphics, screenX, screenY);
        this.renderProgressArrow(guiGraphics, screenX, screenY);
    }

    /**
     * Draws the progress arrow, indicating progress towards completion of a recipe, on the screen
     * @param guiGraphics The {@link GuiGraphics} of the screen
     * @param x The x coordinate to start drawing from on the screen
     * @param y The y coordinate to start drawing from on the screen
     */
    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y)
    {
        if (this.menu.isForging())
        {
            guiGraphics.blit(TEXTURE, x + 101, y + 35, 208, 0, this.menu.getScaledProgress(), 24);
        }
    }

    /**
     * Draws the fuel levels of the Forge on the screen
     * @param guiGraphics The {@link GuiGraphics} of the screen
     * @param x The x coordinate to start drawing from on the screen
     * @param y The y coordinate to start drawing from on the screen
     */
    private void renderFuelLevels(GuiGraphics guiGraphics, int x, int y)
    {
        int heatFuelLevel = this.menu.getScaledFuelLevel(2,3);
        int freezeFuelLevel = this.menu.getScaledFuelLevel(4, 5);

        guiGraphics.blit(TEXTURE, x + 12, y + 17 + FUEL_BAR_LENGTH - heatFuelLevel, 176, FUEL_BAR_LENGTH - heatFuelLevel, 16, heatFuelLevel); // Heat Fuel
        guiGraphics.blit(TEXTURE, x + 34, y + 17 + FUEL_BAR_LENGTH - freezeFuelLevel, 192, FUEL_BAR_LENGTH - freezeFuelLevel, 16, freezeFuelLevel); // Freeze Fuel
    }

    /**
     * Renders the GUI to the screen
     * @param pGuiGraphics The {@link GuiGraphics} to render
     * @param pMouseX The x coordinate of the client's cursor
     * @param pMouseY The y coordinate of the client's cursor
     * @param pPartialTick The partial tick
     */
    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

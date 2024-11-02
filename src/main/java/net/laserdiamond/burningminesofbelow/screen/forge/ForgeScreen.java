package net.laserdiamond.burningminesofbelow.screen.forge;

import com.mojang.blaze3d.systems.RenderSystem;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ForgeScreen extends AbstractContainerScreen<ForgeMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(BurningMinesOfBelow.MODID, "textures/gui/forge.png");
    public static final int FUEL_BAR_LENGTH = 53;

    public ForgeScreen(ForgeMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

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

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y)
    {
        if (this.menu.isForging())
        {
            guiGraphics.blit(TEXTURE, x + 101, y + 35, 208, 0, this.menu.getScaledProgress(), 24);
        }
    }

    private void renderFuelLevels(GuiGraphics guiGraphics, int x, int y)
    {
        int heatFuelLevel = this.menu.getScaledFuelLevel(2,3);
        int freezeFuelLevel = this.menu.getScaledFuelLevel(4, 5);

        guiGraphics.blit(TEXTURE, x + 12, y + 17 + FUEL_BAR_LENGTH - heatFuelLevel, 176, FUEL_BAR_LENGTH - heatFuelLevel, 16, heatFuelLevel); // Heat Fuel
        guiGraphics.blit(TEXTURE, x + 34, y + 17 + FUEL_BAR_LENGTH - freezeFuelLevel, 192, FUEL_BAR_LENGTH - freezeFuelLevel, 16, freezeFuelLevel); // Freeze Fuel
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

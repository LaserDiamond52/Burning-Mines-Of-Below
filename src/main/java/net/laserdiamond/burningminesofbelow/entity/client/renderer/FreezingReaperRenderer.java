package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
import net.laserdiamond.burningminesofbelow.entity.client.model.FreezingReaperModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Rendering the {@link FreezingReaperModel} onto the {@link FreezingReaperEntity}</li>
 * <li>Specifies the texture to render on the {@link FreezingReaperModel}</li>
 * @author Allen Malo
 */
public class FreezingReaperRenderer extends MobRenderer<FreezingReaperEntity, FreezingReaperModel> {
    private static final float SCALE = 1.5F;

    public FreezingReaperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FreezingReaperModel(pContext.bakeLayer(BMOBModelLayers.FREEZING_REAPER)), 0.75F * SCALE);
        this.addLayer(new FreezeReaperItemInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(FreezingReaperEntity freezingReaperEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/freezing_reaper.png");
    }

    @Override
    protected void scale(FreezingReaperEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(SCALE, SCALE, SCALE);
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Rendering items in the {@link FreezingReaperEntity}'s hands</li>
     * <li>Declared as a private static inner class because this render layer is specific to the {@link FreezingReaperRenderer}</li>
     * @author Allen Malo
     */
    private static class FreezeReaperItemInHandLayer extends ItemInHandLayer<FreezingReaperEntity, FreezingReaperModel>
    {

        public FreezeReaperItemInHandLayer(RenderLayerParent<FreezingReaperEntity, FreezingReaperModel> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
            super(pRenderer, pItemInHandRenderer);
        }

        @Override
        protected void renderArmWithItem(LivingEntity pLivingEntity, ItemStack pItemStack, ItemDisplayContext pDisplayContext, HumanoidArm pArm, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
            if (pArm == HumanoidArm.RIGHT)
            {
                pPoseStack.translate(0.0F, 0.75F, 0.0F);
            }
            super.renderArmWithItem(pLivingEntity, pItemStack, pDisplayContext, pArm, pPoseStack, pBuffer, pPackedLight);
        }
    }
}

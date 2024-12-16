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
 * <li>A {@link FreezingReaperRenderer} is-a {@link MobRenderer}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class FreezingReaperRenderer extends MobRenderer<FreezingReaperEntity, FreezingReaperModel> {

    /**
     * The factor to scale the size of the model by
     */
    private static final float SCALE = 1.5F;

    /**
     * Creates a new {@link FreezingReaperRenderer}
     * @param pContext The {@link EntityRendererProvider.Context}
     */
    public FreezingReaperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FreezingReaperModel(pContext.bakeLayer(BMOBModelLayers.FREEZING_REAPER)), 0.75F * SCALE);
        this.addLayer(new FreezingReaperItemInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    /**
     * Gets the {@link ResourceLocation} for the texture to render on the model
     * @param freezingReaperEntity The {@link FreezingReaperEntity} being rendered
     * @return The {@link ResourceLocation} for the texture to render on the model
     */
    @Override
    public ResourceLocation getTextureLocation(FreezingReaperEntity freezingReaperEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/freezing_reaper.png");
    }

    /**
     * Scales the size of the model
     * @param pLivingEntity The {@link FreezingReaperEntity} to scale
     * @param pPoseStack The {@link PoseStack}
     * @param pPartialTickTime The partial tick time
     */
    @Override
    protected void scale(FreezingReaperEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(SCALE, SCALE, SCALE);
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Rendering items in the {@link FreezingReaperEntity}'s hands</li>
     * <li>Declared as a private static inner class because this render layer is specific to the {@link FreezingReaperRenderer}</li>
     * <li>A {@link FreezingReaperItemInHandLayer} is-a {@link ItemInHandLayer}</li>
     * @author Allen Malo
     */
    private static class FreezingReaperItemInHandLayer extends ItemInHandLayer<FreezingReaperEntity, FreezingReaperModel>
    {

        /**
         * Creates a new {@link FreezingReaperItemInHandLayer}
         * @param pRenderer The {@link FreezingReaperRenderer} this layer will be applied to
         * @param pItemInHandRenderer The {@link ItemInHandRenderer} of the {@link EntityRendererProvider.Context}
         */
        public FreezingReaperItemInHandLayer(RenderLayerParent<FreezingReaperEntity, FreezingReaperModel> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
            super(pRenderer, pItemInHandRenderer);
        }

        /**
         * Renders the item in the hand of the {@link FreezingReaperModel}
         * @param pLivingEntity The {@link LivingEntity} the layer is being rendered on
         * @param pItemStack The {@link ItemStack} currently being held
         * @param pDisplayContext The {@link ItemDisplayContext} of holding the item
         * @param pArm The {@link HumanoidArm} holding the item
         * @param pPoseStack The {@link PoseStack}
         * @param pBuffer The {@link MultiBufferSource}
         * @param pPackedLight The packed light
         */
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

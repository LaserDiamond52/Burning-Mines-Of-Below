package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.entity.client.model.KingInferniusModel;
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
 * <li>Rendering the {@link KingInferniusModel} onto the {@link KingInferniusEntity}</li>
 * <li>Specifies the texture to render on the {@link KingInferniusModel}</li>
 * @author Allen Malo
 */
public final class KingInferniusRenderer extends MobRenderer<KingInferniusEntity, KingInferniusModel> {

    private static final float SCALE = 1.5F;

    public KingInferniusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KingInferniusModel(pContext.bakeLayer(BMOBModelLayers.KING_INFERNIUS)), 0.75F * SCALE);
        this.addLayer(new KingInferniusItemInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(KingInferniusEntity kingInferniusEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/king_infernius.png");
    }

    @Override
    protected void scale(KingInferniusEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(SCALE, SCALE, SCALE);
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Rendering items in the {@link KingInferniusEntity}'s hands</li>
     * <li>Declared as a private static inner class because this render layer is specific to the {@link KingInferniusEntity}</li>
     * @author Allen Malo
     */
    private static class KingInferniusItemInHandLayer extends ItemInHandLayer<KingInferniusEntity, KingInferniusModel>
    {
        public KingInferniusItemInHandLayer(RenderLayerParent<KingInferniusEntity, KingInferniusModel> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
            super(pRenderer, pItemInHandRenderer);
        }

        @Override
        protected void renderArmWithItem(LivingEntity pLivingEntity, ItemStack pItemStack, ItemDisplayContext pDisplayContext, HumanoidArm pArm, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
            if (pArm == HumanoidArm.RIGHT)
            {
                pPoseStack.translate(0.0F, 0.5F, 0.0F); // Only need to translate the right side
            }
            super.renderArmWithItem(pLivingEntity, pItemStack, pDisplayContext, pArm, pPoseStack, pBuffer, pPackedLight);
        }
    }
}

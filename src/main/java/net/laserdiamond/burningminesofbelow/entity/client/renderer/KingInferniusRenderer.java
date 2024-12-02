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

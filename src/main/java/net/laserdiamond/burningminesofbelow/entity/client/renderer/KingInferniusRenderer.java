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
 * <li>A {@link KingInferniusRenderer} is-a {@link MobRenderer}</li>
 * @author Allen Malo
 */
public final class KingInferniusRenderer extends MobRenderer<KingInferniusEntity, KingInferniusModel> {

    /**
     * The factor to scale the size of the model by
     */
    private static final float SCALE = 1.5F;

    /**
     * Creates a new {@link KingInferniusRenderer}
     * @param pContext The {@link EntityRendererProvider.Context}
     */
    public KingInferniusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KingInferniusModel(pContext.bakeLayer(BMOBModelLayers.KING_INFERNIUS)), 0.75F * SCALE);
        this.addLayer(new KingInferniusItemInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    /**
     * Gets the {@link ResourceLocation} for the texture to render on the model
     * @param kingInferniusEntity The {@link KingInferniusEntity} being rendered
     * @return The {@link ResourceLocation} for the texture to render on the model
     */
    @Override
    public ResourceLocation getTextureLocation(KingInferniusEntity kingInferniusEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/king_infernius.png");
    }

    /**
     * Scales the size of the model
     * @param pLivingEntity The {@link KingInferniusEntity} to scale
     * @param pPoseStack The {@link PoseStack}
     * @param pPartialTickTime The partial tick time
     */
    @Override
    protected void scale(KingInferniusEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(SCALE, SCALE, SCALE);
    }

    /**
     * <p>Version/date: 12/9/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Rendering items in the {@link KingInferniusEntity}'s hands</li>
     * <li>Declared as a private static inner class because this render layer is specific to the {@link KingInferniusEntity}</li>
     * <li>A {@link KingInferniusItemInHandLayer} is-a {@link ItemInHandLayer}</li>
     * @author Allen Malo
     */
    private static class KingInferniusItemInHandLayer extends ItemInHandLayer<KingInferniusEntity, KingInferniusModel>
    {

        /**
         * Creates a new {@link KingInferniusItemInHandLayer}
         * @param pRenderer The {@link KingInferniusRenderer} this layer will be applied to
         * @param pItemInHandRenderer The {@link ItemInHandRenderer} of the {@link EntityRendererProvider.Context}
         */
        public KingInferniusItemInHandLayer(RenderLayerParent<KingInferniusEntity, KingInferniusModel> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
            super(pRenderer, pItemInHandRenderer);
        }

        /**
         * Renders the item in the hand of the {@link KingInferniusModel}
         * @param pLivingEntity The {@link LivingEntity} the layer is being rendered on
         * @param pItemStack The {@link ItemStack} currently being held
         * @param pDisplayContext the {@link ItemDisplayContext} of holding the item
         * @param pArm The {@link HumanoidArm} holding the item
         * @param pPoseStack The {@link PoseStack}
         * @param pBuffer The {@link MultiBufferSource}
         * @param pPackedLight The packed light
         */
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

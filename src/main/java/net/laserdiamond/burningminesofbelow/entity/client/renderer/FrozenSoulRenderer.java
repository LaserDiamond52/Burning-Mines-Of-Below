package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.laserdiamond.burningminesofbelow.entity.client.model.FrozenSoulModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;

public final class FrozenSoulRenderer extends AbstractHumanoidRenderer<FrozenSoulEntity, FrozenSoulModel> {

    public FrozenSoulRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FrozenSoulModel(pContext.bakeLayer(BMOBModelLayers.FROZEN_SOUL)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrozenSoulEntity frozenSoulEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/frozen_soul.png");
    }

    @Override
    protected ModelLayerLocation innerArmorModelLayer() {
        return BMOBModelLayers.FROZEN_SOUL_INNER_ARMOR;
    }

    @Override
    protected ModelLayerLocation outerArmorModelLayer() {
        return BMOBModelLayers.FROZEN_SOUL_OUTER_ARMOR;
    }
}

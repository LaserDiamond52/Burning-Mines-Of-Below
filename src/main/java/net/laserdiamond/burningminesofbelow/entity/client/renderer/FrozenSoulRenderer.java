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

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Rendering the {@link FrozenSoulModel} onto the {@link FrozenSoulEntity}</li>
 * <li>Specifies the texture to render on the {@link FrozenSoulModel}</li>
 * <li>A {@link FrozenSoulRenderer} is-a {@link AbstractHumanoidRenderer}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public final class FrozenSoulRenderer extends AbstractHumanoidRenderer<FrozenSoulEntity, FrozenSoulModel> {

    /**
     * Creates a new {@link FrozenSoulRenderer}
     * @param pContext The {@link EntityRendererProvider.Context}
     */
    public FrozenSoulRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FrozenSoulModel(pContext.bakeLayer(BMOBModelLayers.FROZEN_SOUL)), 0.5F);
    }

    /**
     * Gets the {@link ResourceLocation} for the texture to render on the model
     * @param frozenSoulEntity The {@link FrozenSoulEntity} being rendered
     * @return The {@link ResourceLocation} for the texture to render on the model
     */
    @Override
    public ResourceLocation getTextureLocation(FrozenSoulEntity frozenSoulEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/frozen_soul.png");
    }

    /**
     * The inner armor model layer
     * @return The {@link ModelLayerLocation} for inner armor
     */
    @Override
    protected ModelLayerLocation innerArmorModelLayer() {
        return BMOBModelLayers.FROZEN_SOUL_INNER_ARMOR;
    }

    /**
     * The outer armor model layer
     * @return The {@link ModelLayerLocation} for the outer armor
     */
    @Override
    protected ModelLayerLocation outerArmorModelLayer() {
        return BMOBModelLayers.FROZEN_SOUL_OUTER_ARMOR;
    }
}

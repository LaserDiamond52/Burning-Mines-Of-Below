package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.Mob;

/**
 * Renderer used for rendering Humanoid mobs of this mod. This includes a {@link HumanoidArmorLayer}, allowing armor to be rendered on the {@link Mob}
 * @param <M> The {@link Mob} class
 * @param <HM> The {@link HumanoidModel} of the {@link Mob}
 */
public abstract class AbstractHumanoidRenderer<M extends Mob, HM extends HumanoidModel<M>> extends HumanoidMobRenderer<M, HM> {

    public AbstractHumanoidRenderer(EntityRendererProvider.Context pContext, HM pModel, float pShadowRadius) {
        super(pContext, pModel, pShadowRadius);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidArmorModel<>(pContext.bakeLayer(this.innerArmorModelLayer())), new HumanoidArmorModel<>(pContext.bakeLayer(this.outerArmorModelLayer())), pContext.getModelManager()));
    }

    /**
     * The {@link ModelLayerLocation} for the inner armor model
     * @return The {@link ModelLayerLocation} for the inner armor model
     */
    protected abstract ModelLayerLocation innerArmorModelLayer();

    /**
     * The {@link ModelLayerLocation} for the outer armor model
     * @return The {@link ModelLayerLocation} for the outer armor model
     */
    protected abstract ModelLayerLocation outerArmorModelLayer();
}

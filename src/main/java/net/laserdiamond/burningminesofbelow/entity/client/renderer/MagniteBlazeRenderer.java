package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.client.model.MagniteBlazeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Render the {@link MagniteBlazeModel} onto the {@link MagniteBlazeEntity}</li>
 * <li>Specifies the texture to render on the {@link MagniteBlazeModel}</li>
 * <li>A {@link MagniteBlazeRenderer} is-a {@link MobRenderer}</li>
 * @author Allen Malo
 */
public final class MagniteBlazeRenderer extends MobRenderer<MagniteBlazeEntity, MagniteBlazeModel> {

    /**
     * Creates a new {@link MagniteBlazeRenderer}
     * @param pContext The {@link EntityRendererProvider.Context}
     */
    public MagniteBlazeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MagniteBlazeModel(pContext.bakeLayer(BMOBModelLayers.MAGNITE_BLAZE)), 0.75F);
    }

    /**
     * Gets the {@link ResourceLocation} for the texture to render on the model
     * @param magniteBlazeEntity The {@link MagniteBlazeEntity} being rendered
     * @return The {@link ResourceLocation} for the texture to render on the model
     */
    @Override
    public ResourceLocation getTextureLocation(MagniteBlazeEntity magniteBlazeEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/magnite_blaze.png");
    }

    // TODO: Renderer?
    // The amount of shields rendered should be dependent on how much health the Magnite Blaze has
    // 5/5 - 4/5 -> All shields (North)
    // 4/5 - 3/5 -> Three shields (West)
    // 3/5 - 2/5 -> Two shields (South)
    // 2/5 - 1/5 -> One shield (East)
    // 1/5 - 0/5 -> No shields

//    @Override
//    public void render(MagniteBlazeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
//        float maxHealth = pEntity.getMaxHealth();
//        float health = pEntity.getHealth();
//    }
}

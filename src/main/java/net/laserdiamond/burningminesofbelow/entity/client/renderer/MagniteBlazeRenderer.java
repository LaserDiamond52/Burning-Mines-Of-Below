package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.client.model.MagniteBlazeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class MagniteBlazeRenderer extends MobRenderer<MagniteBlazeEntity, MagniteBlazeModel> {

    public MagniteBlazeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MagniteBlazeModel(pContext.bakeLayer(BMOBModelLayers.MAGNITE_BLAZE)), 0.75F);
    }

    @Override
    public ResourceLocation getTextureLocation(MagniteBlazeEntity magniteBlazeEntity) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/magnite_blaze.png");
    }

    // TODO: Renderer
    // The amount of shields rendered should be dependent on how much health the Magnite Blaze has
    // 5/5 - 4/5 -> All shields
    // 4/5 - 3/5 -> Three shields
    // 3/5 - 2/5 -> Two shields
    // 2/5 - 1/5 -> One shield
    // 1/5 - 0/5 -> No shields
}

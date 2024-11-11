package net.laserdiamond.burningminesofbelow.entity.client;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cyrobolt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class CyroboltRenderer extends ArrowRenderer<Cyrobolt> {

    public CyroboltRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Cyrobolt cyrobolt) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/projectile/cyrobolt.png");
    }
}

package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cyrobolt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Rendering the {@link Cyrobolt} projectile</li>
 * <li>Specifies the texture to render on the {@link Cyrobolt} projectile</li>
 * @author Allen Malo
 */
public final class CyroboltRenderer extends ArrowRenderer<Cyrobolt> {

    public CyroboltRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Cyrobolt cyrobolt) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/projectile/cyrobolt.png");
    }
}

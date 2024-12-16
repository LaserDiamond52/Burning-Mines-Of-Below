package net.laserdiamond.burningminesofbelow.entity.client.renderer;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cryobolt;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Rendering the {@link Cryobolt} projectile</li>
 * <li>Specifies the texture to render on the {@link Cryobolt} projectile</li>
 * <li>A {@link CryoboltRenderer} is-a {@link ArrowRenderer}</li>
 * @author Allen Malo
 */
public final class CryoboltRenderer extends ArrowRenderer<Cryobolt> {

    /**
     * Creates a new {@link CryoboltRenderer}
     * @param pContext The {@link EntityRendererProvider.Context}
     */
    public CryoboltRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Cryobolt cryobolt) {
        return new ResourceLocation(BurningMinesOfBelow.MODID, "textures/entity/projectile/cryobolt.png");
    }
}

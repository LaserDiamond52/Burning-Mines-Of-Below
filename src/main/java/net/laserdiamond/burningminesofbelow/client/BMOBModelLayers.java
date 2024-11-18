package net.laserdiamond.burningminesofbelow.client;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BMOBModelLayers {

    /**
     * Model layer for Frozen Wither Skull head block
     */
    public static final ModelLayerLocation FROZEN_WITHER_SKULL = createBasicModelLayer("textures/block/frozen_wither_skull.png");

    /**
     * Model layer for Blaze Skull head block
     */
    public static final ModelLayerLocation BLAZE_SKULL = createBasicModelLayer("textures/block/blaze_skull.png");

    /**
     * Creates a basic main model layer
     * @param path The resource location for the texture
     * @return A {@link ModelLayerLocation} for use in this mod
     */
    public static ModelLayerLocation createBasicModelLayer(String path)
    {
        return new ModelLayerLocation(new ResourceLocation(BurningMinesOfBelow.MODID, path), "main");
    }

}

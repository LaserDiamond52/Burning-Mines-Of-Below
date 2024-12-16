package net.laserdiamond.burningminesofbelow.client;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains all the {@link ModelLayerLocation}s for entities and any other assets that require {@link ModelLayerLocation}s</li>
 * @author Allen Malo
 */
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
     * Model layer for Magnite Blaze entity
     */
    public static final ModelLayerLocation MAGNITE_BLAZE = createBasicModelLayer("magnite_blaze_layer");

    /**
     * Model layer for King Infernius entity
     */
    public static final ModelLayerLocation KING_INFERNIUS = createBasicModelLayer("king_infernius");

    /**
     * Model layer for Frozen Soul entity
     */
    public static final ModelLayerLocation FROZEN_SOUL = createBasicModelLayer("frozen_soul");

    /**
     * Model layer for Freezing Reaper of the Damned entity
     */
    public static final ModelLayerLocation FREEZING_REAPER = createBasicModelLayer("freezing_reaper");

    /**
     * Inner Armor Model layer for Frozen Soul entity
     */
    public static final ModelLayerLocation FROZEN_SOUL_INNER_ARMOR = createInnerArmorLayer("frozen_soul");

    /**
     * Outer Armor Model Layer for Frozen Soul entity
     */
    public static final ModelLayerLocation FROZEN_SOUL_OUTER_ARMOR = createOuterArmorLayer("frozen_soul");


    /**
     * Creates a basic main model layer
     * @param path The resource location for the texture. For mobs, their local name is used.
     * @return A {@link ModelLayerLocation} for use in this mod
     */
    private static ModelLayerLocation createBasicModelLayer(String path)
    {
        return new ModelLayerLocation(new ResourceLocation(BurningMinesOfBelow.MODID, path), "main");
    }

    /**
     * Creates a model layer for inner armor. This is used for mob renderers that have a {@link net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer}
     * @param path The resource location for the texture. Typically, the local name of the mob is used here.
     * @return A {@link ModelLayerLocation} for use with the {@link net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer}
     */
    private static ModelLayerLocation createInnerArmorLayer(String path)
    {
        return new ModelLayerLocation(new ResourceLocation(BurningMinesOfBelow.MODID, path), "inner_armor");
    }

    /**
     * Creates a model layer for outer armor. This is used for mob renderers that have a {@link net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer}
     * @param path The resource location for the texture. Typically, the local name of the mob is used here.
     * @return A {@link ModelLayerLocation} for used with the {@link net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer}
     */
    private static ModelLayerLocation createOuterArmorLayer(String path)
    {
        return new ModelLayerLocation(new ResourceLocation(BurningMinesOfBelow.MODID, path), "outer_armor");
    }

}

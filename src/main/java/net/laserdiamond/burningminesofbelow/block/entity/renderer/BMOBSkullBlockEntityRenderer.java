package net.laserdiamond.burningminesofbelow.block.entity.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBSkullBlock;
import net.laserdiamond.burningminesofbelow.client.BMOBModelLayers;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;

import java.util.Map;

/**
 * Renderer for Skull Block Entities of this mod
 */
public class BMOBSkullBlockEntityRenderer extends SkullBlockRenderer {

    /**
     * {@link Map} used to get the {@link ResourceLocation} for the texture of the {@link net.minecraft.world.level.block.SkullBlock.Type}
     */
    public static final Map<SkullBlock.Type, ResourceLocation> HEAD_BY_TYPE = Map.of(
            BMOBSkullBlock.Types.FROZEN_WITHER_SKULL, new ResourceLocation(BurningMinesOfBelow.MODID, "textures/block/frozen_wither_skull.png"),
            BMOBSkullBlock.Types.BLAZE_SKULL, new ResourceLocation(BurningMinesOfBelow.MODID, "textures/block/blaze_skull.png"));

    /**
     * Map that contains the {@link SkullModel}s for the head blocks of this mod
     * @param entityModelSet {@link EntityModelSet}
     * @return A {@link Map} links the {@link BMOBSkullBlock.Types} to a {@link SkullModel}
     */
    public static Map<SkullBlock.Type, SkullModelBase> createHeadRenderers(EntityModelSet entityModelSet)
    {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
        builder.put(BMOBSkullBlock.Types.FROZEN_WITHER_SKULL, new SkullModel(entityModelSet.bakeLayer(BMOBModelLayers.FROZEN_WITHER_SKULL)));
        builder.put(BMOBSkullBlock.Types.BLAZE_SKULL, new SkullModel(entityModelSet.bakeLayer(BMOBModelLayers.BLAZE_SKULL)));
        return builder.build();
    }

    public BMOBSkullBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
        this.modelByType = createHeadRenderers(pContext.getModelSet());
    }

    /**
     * Renders the {@link net.laserdiamond.burningminesofbelow.block.entity.BMOBSkullBlockEntity}
     * @param pBlockEntity
     * @param pPartialTick
     * @param pPoseStack
     * @param pBuffer
     * @param pPackedLight
     * @param pPackedOverlay
     */
    @Override
    public void render(SkullBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay)
    {
        // Method has to be overridden and completely rewritten from superclass because of a static method from the superclass. Access transformers won't necessarily help here
        BlockState blockState = pBlockEntity.getBlockState();
        boolean isWallSkullBlock = blockState.getBlock() instanceof WallSkullBlock;
        Direction direction = isWallSkullBlock ? blockState.getValue(WallSkullBlock.FACING) : null;
        int rotationSegment = isWallSkullBlock ? RotationSegment.convertToSegment(direction.getOpposite()) : blockState.getValue(SkullBlock.ROTATION);
        float toDegrees = RotationSegment.convertToDegrees(rotationSegment);

        SkullBlock.Type type = ((AbstractSkullBlock) blockState.getBlock()).getType();
        SkullModelBase skullModelBase = this.modelByType.get(type);
        RenderType renderType = renderType(type); // This variable is mainly why we have to remake this method
        SkullBlockRenderer.renderSkull(direction, toDegrees, pBlockEntity.getAnimation(pPartialTick), pPoseStack, pBuffer, pPackedLight, skullModelBase, renderType);
    }

    /**
     * Gets the render type of the {@link SkullBlock.Type}
     * @param skullBlockType The {@link SkullBlock.Type}
     * @return The {@link RenderType} of the skull block
     */
    public static RenderType renderType(SkullBlock.Type skullBlockType)
    {
        // Static method from superclass cannot be used, since a new Map mapping the new skull block types and their textures has to be made.
        // Superclass's map cannot be overridden, nor will adding to it affect functionality.
        return RenderType.entityCutoutNoCullZOffset(HEAD_BY_TYPE.get(skullBlockType));
    }
}

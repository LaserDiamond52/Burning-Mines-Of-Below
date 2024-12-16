package net.laserdiamond.burningminesofbelow.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Base class for Hierarchical models</li>
 * <li>Sets up the animations associated with the entity and model</li>
 * <li>Prepares the model for rendering</li>
 * <li>A {@link AbstractHierarchicalModel} is-a {@link HierarchicalModel}</li>
 * <li>A {@link AbstractHierarchicalModel} is-a {@link HeadRotator}</li>
 * @author Allen Malo
 * @param <E> The {@link Entity} type
 */
public abstract class AbstractHierarchicalModel<E extends Entity> extends HierarchicalModel<E> implements HeadRotator<E> {

    /**
     * Sets up the animations for the {@link E}
     * @param e The {@link E} to set up animations for
     * @param limbSwing The limbSwing
     * @param limbSwingAmount The limbSwingAmount
     * @param ageInTicks The ageInTicks
     * @param netHeadYaw The head yaw
     * @param headPitch The head pitch
     */
    @Override
    public void setupAnim(E e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.headRotation(e, netHeadYaw, headPitch);
    }

    /**
     * Renders the root of this model to a buffer
     * @param pPoseStack The {@link PoseStack}
     * @param pBuffer The {@link VertexConsumer}
     * @param pPackedLight The packed light
     * @param pPackedOverlay The packed overlay
     * @param pRed The red value
     * @param pGreen The green value
     * @param pBlue The blue value
     * @param pAlpha the alpha value
     */
    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }
}

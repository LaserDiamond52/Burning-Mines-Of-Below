package net.laserdiamond.burningminesofbelow.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

/**
 * Abstract class for Hierarchical models with arms. Mainly used for allowing living entities to hold items in their hands as opposed to the {@link AbstractHierarchicalModel}.
 * @param <LE> The {@link LivingEntity} class
 */
public abstract class AbstractHierarchicalArmedModel<LE extends LivingEntity> extends HierarchicalModel<LE> implements HeadRotator<LE>, ArmedModel {

    @Override
    public void setupAnim(LE e, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.headRotation(e, netHeadYaw, headPitch);
    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
    }

    protected abstract ModelPart getArm(HumanoidArm side);

    @Override
    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack) {
        this.getArm(humanoidArm).translateAndRotate(poseStack);
    }
}

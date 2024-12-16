package net.laserdiamond.burningminesofbelow.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used for Hierarchical models with arms.</li>
 * <li>Allows for living entities to hold items in theirs hands</li>
 * <li>An {@link AbstractHierarchicalArmedModel} is-a {@link AbstractHierarchicalModel}</li>
 * <li>An {@link AbstractHierarchicalArmedModel} is-a {@link ArmedModel}</li>
 * @author Allen Malo
 * @param <LE> The {@link LivingEntity} class
 */
public abstract class AbstractHierarchicalArmedModel<LE extends LivingEntity> extends AbstractHierarchicalModel<LE> implements ArmedModel {

    /**
     * Gets the {@link ModelPart} that represents the arm
     * @param side The {@link HumanoidArm}. Can be left or right
     * @return The {@link ModelPart} for either arm specified
     */
    protected abstract ModelPart getArm(HumanoidArm side);

    /**
     * Translates the {@link PoseStack} to the arm {@link ModelPart}
     * @param humanoidArm The {@link HumanoidArm}. Can be left or right
     * @param poseStack The {@link PoseStack} to translate to the arm
     */
    @Override
    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack) {
        this.getArm(humanoidArm).translateAndRotate(poseStack);
    }
}

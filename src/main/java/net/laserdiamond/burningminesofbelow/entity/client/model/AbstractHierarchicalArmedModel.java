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
 * @author Allen Malo
 * @param <LE> The {@link LivingEntity} class
 */
public abstract class AbstractHierarchicalArmedModel<LE extends LivingEntity> extends AbstractHierarchicalModel<LE> implements ArmedModel {

    protected abstract ModelPart getArm(HumanoidArm side);

    @Override
    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack) {
        this.getArm(humanoidArm).translateAndRotate(poseStack);
    }
}

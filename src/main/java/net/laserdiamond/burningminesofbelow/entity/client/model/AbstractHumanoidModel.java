package net.laserdiamond.burningminesofbelow.entity.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractHumanoidModel<LE extends LivingEntity> extends HumanoidModel<LE> {

    protected final ModelPart leftSleeve;
    protected final ModelPart rightSleeve;
    protected final ModelPart leftPants;
    protected final ModelPart rightPants;
    protected final ModelPart jacket;

    public AbstractHumanoidModel(ModelPart pRoot) {
        super(pRoot);
        this.leftSleeve = pRoot.getChild("left_sleeve");
        this.rightSleeve = pRoot.getChild("right_sleeve");
        this.leftPants = pRoot.getChild("left_pants");
        this.rightPants = pRoot.getChild("right_pants");
        this.jacket = pRoot.getChild("jacket");
    }

    public static LayerDefinition createBodyLayer()
    {
        // TODO: Consider re-making model and parenting sleeves, pants, and jacket with their underlying parts
        MeshDefinition humanoidModel = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partDefinition = humanoidModel.getRoot();

        partDefinition.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE.extend(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE.extend(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE.extend(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, CubeDeformation.NONE.extend(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        partDefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, CubeDeformation.NONE.extend(0.25F)), PartPose.ZERO);


        return LayerDefinition.create(humanoidModel, 64, 64);
    }

    @Override
    public void setupAnim(LE pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);

    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return Iterables.concat(super.bodyParts(), ImmutableList.of(this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket));
    }
}

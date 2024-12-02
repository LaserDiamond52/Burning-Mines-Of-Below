package net.laserdiamond.burningminesofbelow.entity.client.model;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;

public final class FrozenSoulModel extends AbstractHumanoidModel<FrozenSoulEntity> {

    public FrozenSoulModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void setupAnim(FrozenSoulEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        AnimationUtils.bobArms(this.rightSleeve, this.leftSleeve, pAgeInTicks);
        AnimationUtils.bobArms(this.rightArm, this.leftArm, pAgeInTicks);

    }
}

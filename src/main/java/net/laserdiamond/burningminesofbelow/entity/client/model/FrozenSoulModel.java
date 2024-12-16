package net.laserdiamond.burningminesofbelow.entity.client.model;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Sets up the animations associated with the model for use with the {@link FrozenSoulEntity}</li>
 * <li>A {@link FrozenSoulModel} is-a {@link AbstractHumanoidModel}</li>
 * @author Allen Malo
 */
public final class FrozenSoulModel extends AbstractHumanoidModel<FrozenSoulEntity> {

    /**
     * Creates a new {@link FrozenSoulModel}
     * @param pRoot The {@link ModelPart} that will be the root of the model
     */
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

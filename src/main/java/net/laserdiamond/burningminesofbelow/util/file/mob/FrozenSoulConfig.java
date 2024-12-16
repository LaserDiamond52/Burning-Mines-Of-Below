package net.laserdiamond.burningminesofbelow.util.file.mob;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates, stores, and manages from a json file values that are relevant to the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity}</li>
 * <li>A {@link FrozenSoulConfig} is-a {@link AbstractMobConfig}</li>
 * @author Allen Malo
 */
public class FrozenSoulConfig extends AbstractMobConfig {

    /**
     * Creates a new {@link FrozenSoulConfig}
     */
    public FrozenSoulConfig() {
        super("frozen_soul");
    }

    @Override
    public double defaultHealth() {
        return 20D;
    }

    @Override
    public double defaultMovementSpeed() {
        return 0.15D;
    }

    @Override
    public double defaultAttackDamage() {
        return 3D;
    }

    @Override
    public double defaultAttackKnockback() {
        return 0.1D;
    }

    @Override
    public double defaultFollowRange() {
        return 50D;
    }

    @Override
    public double defaultArmor() {
        return 0D;
    }
}

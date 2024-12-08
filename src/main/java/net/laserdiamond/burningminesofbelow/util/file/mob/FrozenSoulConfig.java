package net.laserdiamond.burningminesofbelow.util.file.mob;

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

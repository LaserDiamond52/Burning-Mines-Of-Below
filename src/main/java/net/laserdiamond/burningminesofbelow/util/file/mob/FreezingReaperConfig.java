package net.laserdiamond.burningminesofbelow.util.file.mob;

public class FreezingReaperConfig extends AbstractMobConfig {


    /**
     * Creates a new {@link FreezingReaperConfig}
     */
    public FreezingReaperConfig() {
        super("freezing_reaper");

        int attackFreezeDuration = Math.max(0, this.defaultAttackFreezeDuration());
        float cyroBlastDamage = Math.max(0, this.defaultCyroBlastDamage());

        this.toJsonNotNull(this.jsonObject, "attack_freeze_duration", attackFreezeDuration);
        this.toJsonNotNull(this.jsonObject, "cyro_blast_damage", cyroBlastDamage);

        this.writeJsonToFile();
    }

    @Override
    public double defaultHealth() {
        return 500D;
    }

    @Override
    public double defaultMovementSpeed() {
        return 0.2D;
    }

    @Override
    public double defaultAttackDamage() {
        return 10D;
    }

    @Override
    public double defaultAttackKnockback() {
        return 0.1D;
    }

    @Override
    public double defaultFollowRange() {
        return 100D;
    }

    @Override
    public double defaultArmor() {
        return 5D;
    }

    /**
     *
     * @return Default duration time in ticks hit entities will freeze for when attacked by the Freezing Reaper
     */
    public int defaultAttackFreezeDuration()
    {
        return 600;
    }

    /**
     *
     * @return The time in ticks hit entities will freeze for when attack by the Freezing Reaper from the json file
     * Returns the default value if the value stored in the file is less than 0.
     */
    public int attackFreezeDuration()
    {
        int ret = this.jsonObject.getAsJsonObject("attack_freeze_duration").getAsInt();
        if (ret < 0)
        {
            return this.defaultAttackFreezeDuration();
        }
        return ret;
    }

    /**
     *
     * @return Default damage the Freezing Reaper's Cyro Blast attack will inflict
     */
    public float defaultCyroBlastDamage()
    {
        return 7F;
    }

    /**
     *
     * @return The damage the Freezing Reaper's Cyro Blast attack will inflict from the json file.
     * Returns the default amount if the value stored in the file is less than 0
     */
    public float cyroBlastDamage()
    {
        float ret = this.jsonObject.getAsJsonObject("cyro_blast_damage").getAsFloat();
        if (ret < 0)
        {
            return this.defaultCyroBlastDamage();
        }
        return ret;
    }
}

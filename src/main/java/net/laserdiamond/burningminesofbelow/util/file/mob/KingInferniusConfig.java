package net.laserdiamond.burningminesofbelow.util.file.mob;


public class KingInferniusConfig extends AbstractMobConfig {

    /**
     * Creates a new {@link KingInferniusConfig}
     */
    public KingInferniusConfig()
    {
        super("king_infernius");
        int attackFireDuration = Math.max(0, this.defaultAttackFireDuration());
        float fireBreathDamage = Math.max(0, this.defaultFireBreathDamage());
        float heatWaveDamage = Math.max(0, this.defaultHeatWaveDamage());

        this.toJsonNotNull(this.jsonObject, "attack_fire_duration", attackFireDuration);
        this.toJsonNotNull(this.jsonObject, "fire_breath_damage", fireBreathDamage);
        this.toJsonNotNull(this.jsonObject, "heat_wave_damage", heatWaveDamage);

        this.writeJsonToFile();
    }

    @Override
    public double defaultHealth() {
        return 650D;
    }

    @Override
    public double defaultMovementSpeed() {
        return 0.2D;
    }

    @Override
    public double defaultAttackDamage() {
        return 7D;
    }

    @Override
    public double defaultAttackKnockback() {
        return 0.15D;
    }

    @Override
    public double defaultFollowRange() {
        return 100D;
    }

    @Override
    public double defaultArmor() {
        return 10D;
    }

    /**
     * @return Default duration time in ticks hit entities will be set on fire when attacked by King Infernius
     */
    public int defaultAttackFireDuration()
    {
        return 600;
    }

    /**
     * @return The duration in ticks an attacked entity will be set on fire for when attacked by King Infernius from the json file
     * Returns the default value if the value stored in the file is less than 0.
     */
    public int attackFireDuration()
    {
        int ret = this.jsonObject.get("attack_fire_duration").getAsInt();
        if (ret < 0)
        {
            return this.defaultAttackFireDuration();
        }
        return ret;
    }

    /**
     * @return The default amount of damage King Infernius's fire breath attack deals per tick
     */
    public float defaultFireBreathDamage()
    {
        return 2.5F;
    }

    /**
     * @return The damage King Infernius's fire breath attack deals per tick from the json file
     * Returns the default value if the value stored in the file is equal to or less than 0.
     */
    public float fireBreathDamage()
    {
        float ret = this.jsonObject.get("fire_breath_damage").getAsFloat();
        if (ret <= 0)
        {
            return this.defaultFireBreathDamage();
        }
        return ret;
    }

    /**
     * @return The initial damage of King Infernius's heat wave attack
     */
    public float defaultHeatWaveDamage()
    {
        return 40F;
    }

    /**
     * @return The initial damage of King Infernius's heat wave attack from the json file.
     * Returns the default value if the value stored in the file is equal to or less than 0.
     */
    public float heatWaveDamage()
    {
        float ret = this.jsonObject.get("heat_wave_damage").getAsFloat();
        if (ret <= 0)
        {
            return this.defaultHeatWaveDamage();
        }
        return ret;
    }
}

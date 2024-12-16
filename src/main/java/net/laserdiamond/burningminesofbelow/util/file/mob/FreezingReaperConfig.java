package net.laserdiamond.burningminesofbelow.util.file.mob;

import com.google.gson.JsonObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates, stores, and reads from a json file files that are relevant to the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity}</li>
 * <li>A {@link FreezingReaperConfig} is-a {@link AbstractMobConfig}</li>
 * @author Allen Malo
 */
public class FreezingReaperConfig extends AbstractMobConfig {

    /**
     * Creates a new {@link FreezingReaperConfig}
     */
    public FreezingReaperConfig() {
        super("freezing_reaper");

        int attackFreezeDuration = Math.max(0, this.defaultAttackFreezeDuration());
        float cyroBlastDamage = Math.max(0, this.defaultCyroBlastDamage());
        float suddenBlizzardDamage = Math.max(0, this.defaultSuddenBlizzardDamage());
        float suddenBlizzardBlastRange = Math.max(0, this.defaultSuddenBlizzardBlastRange());
        float suddenBlizzardExplosionPower = Math.max(0, this.defaultSuddenBlizzardExplosionPower());

        this.toJsonNotNull(this.jsonObject, "attack_freeze_duration", attackFreezeDuration);

        JsonObject cyroBlastObj = this.createJsonNotNull(this.jsonObject.getAsJsonObject("cyrobolt_blast"));
        this.toJsonNotNull(cyroBlastObj, "damage", cyroBlastDamage);

        JsonObject suddenBlizzardObj = this.createJsonNotNull(this.jsonObject.getAsJsonObject("sudden_blizzard"));
        this.toJsonNotNull(suddenBlizzardObj, "damage", suddenBlizzardDamage);
        this.toJsonNotNull(suddenBlizzardObj, "blast_range", suddenBlizzardBlastRange);
        this.toJsonNotNull(suddenBlizzardObj, "explosion_power", suddenBlizzardExplosionPower);

        this.toJsonNotNull(this.jsonObject, "cyrobolt_blast", cyroBlastObj);
        this.toJsonNotNull(this.jsonObject, "sudden_blizzard", suddenBlizzardObj);

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
        int ret = this.jsonObject.get("attack_freeze_duration").getAsInt();
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
        float ret = this.jsonObject.getAsJsonObject("cyrobolt_blast").get("damage").getAsFloat();
        if (ret < 0)
        {
            return this.defaultCyroBlastDamage();
        }
        return ret;
    }

    /**
     *
     * @return The default damage the Freezing Reaper's Sudden Blizzard attack will inflict
     */
    public float defaultSuddenBlizzardDamage()
    {
        return 30;
    }

    /**
     *
     * @return The damage the Freezing Reaper's Sudden Blizzard attack will inflict from the json file.
     * Returns the default amount if the value stored in the file is less than 0
     */
    public float suddenBlizzardDamage()
    {
        float ret = this.jsonObject.getAsJsonObject("sudden_blizzard").get("damage").getAsInt();
        if (ret < 0)
        {
            return this.defaultSuddenBlizzardDamage();
        }
        return ret;
    }

    /**
     *
     * @return The default blast range of the Freezing Reaper's Sudden Blizzard attack
     */
    public int defaultSuddenBlizzardBlastRange()
    {
        return 15;
    }

    /**
     *
     * @return The blast range of the Freezing Reaper's Sudden Blizzard attack from the json file.
     * Returns the default amount if the value stored in the file is equal to or less than 0.
     */
    public int suddenBlizzardBlastRange()
    {
        int ret = this.jsonObject.getAsJsonObject("sudden_blizzard").get("blast_range").getAsInt();
        if (ret <= 0)
        {
            return this.defaultSuddenBlizzardBlastRange();
        }
        return ret;
    }

    /**
     *
     * @return The default explosion power of the Freezing Reaper's Sudden Blizzard attack.
     */
    public float defaultSuddenBlizzardExplosionPower()
    {
        return 2.5F;
    }

    /**
     *
     * @return The explosion power of the Freezing Reaper's Sudden Blizzard attack from the json file.
     * Returns the default amount if the value stored in the file is equal to or less than 0.
     */
    public float suddenBlizzardExplosionPower()
    {
        float ret = this.jsonObject.getAsJsonObject("sudden_blizzard").get("explosion_power").getAsFloat();
        if (ret <= 0)
        {
            return this.defaultSuddenBlizzardExplosionPower();
        }
        return ret;
    }
}

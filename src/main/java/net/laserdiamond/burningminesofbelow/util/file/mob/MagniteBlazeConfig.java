package net.laserdiamond.burningminesofbelow.util.file.mob;

import com.google.gson.JsonObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates, stores, and reads from a json file values that are relevant to the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity}</li>
 * <li>A {@link MagniteBlazeConfig} is-a {@link AbstractMobConfig}</li>
 * @author Allen Malo
 */
public class MagniteBlazeConfig extends AbstractMobConfig {

    /**
     * Creates a new {@link MagniteBlazeConfig}
     */
    public MagniteBlazeConfig()
    {
        super("magnite_blaze");

        int supportInterval = Math.max(0, this.defaultSupportInterval());
        int supportAmount = Math.max(0, this.defaultSupportAmount());
        int supportRange = Math.max(0, this.defaultSupportRange());
        int fireBallInterval = Math.max(0, this.defaultFireballLaunchInterval());
        float supportAbsorption = Math.max(0, this.defaultSupportAbsorption());

        JsonObject supportObj = this.createJsonNotNull(this.jsonObject.getAsJsonObject("support"));
        this.toJsonNotNull(supportObj, "interval", supportInterval);
        this.toJsonNotNull(supportObj, "amount", supportAmount);
        this.toJsonNotNull(supportObj, "range", supportRange);
        this.toJsonNotNull(supportObj, "absorption", supportAbsorption);

        this.toJsonNotNull(this.jsonObject, "support", supportObj);

        this.toJsonNotNull(this.jsonObject, "fireball_launch_interval", fireBallInterval);

        this.writeJsonToFile();
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

    /**
     *
     * @return The default time interval in ticks for the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity} to support/buff nearby {@link net.minecraft.world.entity.monster.Blaze}s
     */
    public int defaultSupportInterval()
    {
        return 300;
    }

    /**
     *
     * @return The time interval in ticks for the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity} to support/buff nearby {@link net.minecraft.world.entity.monster.Blaze}s from the json file
     */
    public int supportInterval()
    {
        int ret = this.jsonObject.getAsJsonObject("support").get("interval").getAsInt();
        if (ret <= 0)
        {
            return this.defaultSupportInterval();
        }
        return ret;
    }

    /**
     * @return The default amount of {@link net.minecraft.world.entity.monster.Blaze}s that can be supported by a buff
     */
    public int defaultSupportAmount()
    {
        return 7;
    }

    /**
     *
     * @return The amount of {@link net.minecraft.world.entity.monster.Blaze}s that can be supported by a buff from the json file
     */
    public int supportAmount()
    {
        int ret = this.jsonObject.getAsJsonObject("support").get("amount").getAsInt();
        if (ret < 0)
        {
            return this.defaultSupportAmount();
        }
        return ret;
    }

    /**
     *
     * @return The default range of the support attack goal
     */
    public int defaultSupportRange()
    {
        return 15;
    }

    /**
     *
     * @return The range of the support attack goal from the json file
     */
    public int supportRange()
    {
        int ret = this.jsonObject.getAsJsonObject("support").get("range").getAsInt();
        if (ret <= 0)
        {
            return this.defaultSupportRange();
        }
        return ret;
    }

    /**
     *
     * @return The default amount of absorption hearts for nearby {@link net.minecraft.world.entity.monster.Blaze}s to receive from the support attack
     */
    public float defaultSupportAbsorption()
    {
        return 10F;
    }

    /**
     *
     * @return The amount of absorption hearts for nearby {@link net.minecraft.world.entity.monster.Blaze}s to receive from the support attack from the json file
     */
    public float supportAbsorption()
    {
        float ret = this.jsonObject.getAsJsonObject("support").get("absorption").getAsFloat();
        if (ret < 0)
        {
            return this.defaultSupportAbsorption();
        }
        return ret;
    }

    /**
     *
     * @return The default time interval in ticks for the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity} to perform its fireball attack
     */
    public int defaultFireballLaunchInterval()
    {
        return 100;
    }

    /**
     *
     * @return The time interval in ticks for the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity} to perform its fireball attack
     */
    public int fireballLaunchInterval()
    {
        int ret = this.jsonObject.get("fireball_launch_interval").getAsInt();
        if (ret <= 0)
        {
            return this.defaultFireballLaunchInterval();
        }
        return ret;
    }
}

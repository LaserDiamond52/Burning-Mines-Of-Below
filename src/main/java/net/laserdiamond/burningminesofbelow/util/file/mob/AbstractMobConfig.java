package net.laserdiamond.burningminesofbelow.util.file.mob;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.util.file.JsonConfig;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Act as a base class for storing {@link Attribute} values for entities of this mod in a json file</li>
 * <li>A {@link AbstractMobConfig} is-a {@link JsonConfig}</li>
 * @author Allen Malo
 */
public abstract class AbstractMobConfig extends JsonConfig {

    /**
     * The health of the mob
     */
    protected double health;

    /**
     * The movement speed of the mob
     */
    protected double movementSpeed;

    /**
     * The attack damage of the mob
     */
    protected double attackDamage;

    /**
     * The attack knockback of the mob
     */
    protected double attackKnockback;

    /**
     * The follow range of the mob
     */
    protected double followRange;

    /**
     * The armor value of the mob
     */
    protected double armor;

    /**
     * Creates a new {@link AbstractMobConfig}
     * @param mobLocalName The local name of the mob
     */
    protected AbstractMobConfig(String mobLocalName)
    {
        super(mobLocalName);
        this.health = Math.max(0, this.defaultHealth());
        this.movementSpeed = Math.max(0, this.defaultMovementSpeed());
        this.attackDamage = Math.max(0, this.defaultAttackDamage());
        this.attackKnockback = Math.max(0, this.defaultAttackKnockback());
        this.followRange = Math.max(0, this.defaultFollowRange());
        this.armor = Math.max(0, this.defaultArmor());

        this.toJsonNotNull(this.jsonObject, "attributes", this.createAttributesJson());

        this.writeJsonToFile();
    }

    @Override
    public String folderName() {
        return "entity";
    }

    /**
     * Creates a {@link JsonObject} that contains all the Mob's attributes
     * @return A {@link JsonObject} that contains the values of the Mob's attributes
     */
    protected JsonObject createAttributesJson()
    {
        JsonObject attributeObj = this.createJsonNotNull(this.jsonObject.getAsJsonObject("attributes"));

        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.MAX_HEALTH), this.health);
        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.MOVEMENT_SPEED), this.movementSpeed);
        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.ATTACK_DAMAGE), this.attackDamage);
        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.ATTACK_KNOCKBACK), this.attackKnockback);
        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.FOLLOW_RANGE), this.followRange);
        this.toJsonNotNull(attributeObj, this.getAttributeKey(Attributes.ARMOR), this.armor);

        return attributeObj;
    }

    /**
     * The default health of the mob
     * @return The health amount to default to for the mob
     */
    public abstract double defaultHealth();

    /**
     * The default movement speed of the mob
     * @return The movement speed to default to for the mob
     */
    public abstract double defaultMovementSpeed();

    /**
     * The default attack damage of the mob
     * @return The attack damage to default to for the mob
     */
    public abstract double defaultAttackDamage();

    /**
     * The default attack knockback of the mob
     * @return The attack knockback to default to for the mob
     */
    public abstract double defaultAttackKnockback();

    /**
     * The default follow range of the mob
     * @return The follow range to default to for the mob
     */
    public abstract double defaultFollowRange();

    /**
     * The default armor value of the mob
     * @return The armor value to default to for the mob
     */
    public abstract double defaultArmor();

    /**
     * Gets the String value an {@link Attribute} value may be stored under in the {@link JsonObject}
     * @param attribute The {@link Attribute} to get the value of
     * @return The String key the {@link Attribute} value may be stored under in the {@link JsonObject}
     */
    protected final String getAttributeKey(Attribute attribute)
    {
        return attribute.getDescriptionId().replace("attribute.name.generic.", "");
    }

    /**
     * Gets the {@link Attribute} value stored in the Json file
     * @param attribute The {@link Attribute} to get the value of
     * @return The {@link Attribute} value as a double
     */
    public final double getAttributeValue(Attribute attribute)
    {
        return this.jsonObject.getAsJsonObject("attributes").get(getAttributeKey(attribute)).getAsDouble();
    }

}

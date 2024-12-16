package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BMOBArmorMaterial;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Config class for storing the stats of {@link BMOBArmorMaterial}s in a json file</li>
 * <li>A {@link ArmorConfig} is-a {@link JsonConfig}</li>
 * @author Allen Malo
 */
public final class ArmorConfig extends JsonConfig {

    /**
     * The {@link BMOBArmorMaterial} that will have a config file made for it
     */
    private final BMOBArmorMaterial armorMaterial; // An ArmorConfig has-a BMOBArmorMaterial

    /**
     * Creates a Json file to store the armor set values
     * @param armorMaterial The armor material of this mod
     */
    public ArmorConfig(BMOBArmorMaterial armorMaterial)
    {
        super(armorMaterial.getName().replace(BurningMinesOfBelow.MODID + ":", ""));
        this.armorMaterial = armorMaterial;

        this.toJsonNotNull(this.jsonObject, "toughness", this.armorMaterial.getToughness());
        this.toJsonNotNull(this.jsonObject, "knockback_resistance", this.armorMaterial.getKnockbackResistance());

        for (ArmorItem.Type type : ArmorItem.Type.values())
        {
            this.toJsonNotNull(this.jsonObject, type.getName(), this.armorObjectJson(type));
        }

        this.writeJsonToFile(); // Write the json object to the file
    }

    @Override
    public String folderName() {
        return "armor";
    }

    /**
     * Creates a {@link JsonObject} that contains all of an armor piece's stats
     * @param type The armor piece type
     * @return A {@link JsonObject} that contains information about an armor piece's stats
     */
    private JsonObject armorObjectJson(ArmorItem.Type type)
    {
        JsonObject armorObj = this.createJsonNotNull(this.jsonObject.getAsJsonObject(type.getName()));

        this.toJsonNotNull(armorObj, "armor", this.armorMaterial.getDefenseForType(type));
        this.toJsonNotNull(armorObj, "speed", this.attributeToJsonObj(this.armorMaterial.getSpeedForType(type)));
        this.toJsonNotNull(armorObj, "damage", this.attributeToJsonObj(this.armorMaterial.getDamageForType(type)));
        this.toJsonNotNull(armorObj, "heat_resistance", this.attributeToJsonObj(this.armorMaterial.getHeatResistanceAmountForType(type)));
        this.toJsonNotNull(armorObj, "freeze_resistance", this.attributeToJsonObj(this.armorMaterial.getFreezeResistanceAmountForType(type)));
        this.toJsonNotNull(armorObj, "refined_mineral_chance", this.attributeToJsonObj(this.armorMaterial.getRefinedMineralChanceAmountForType(type)));

        return armorObj;
    }

    /**
     * {@link JsonObject} that can contain the operation value and stat value of the {@link AttributeModifier} to be applied to the armor piece
     * @param operation The {@link AttributeModifier}'s operation
     * @param value The value of the {@link AttributeModifier}
     * @return A {@link JsonObject} containing the operation and value of the {@link AttributeModifier} for the armor piece stat
     */
    private JsonObject attributeToJsonObj(AttributeModifier.Operation operation, double value)
    {
        JsonObject attributeObj = new JsonObject();
        attributeObj.addProperty("operation", operation.toValue());
        attributeObj.addProperty("value", value);
        return attributeObj;
    }

    /**
     * Creates a {@link JsonObject} that can contain the operation value and stat value of the {@link AttributeModifier} to be applied to the armor piece
     * @param itemAttribute The {@link AttributeModifier} Attribute properties to apply to the Attribute on the armor piece
     * @return A {@link JsonObject} containing the operation and value of the {@link AttributeModifier} for the armor piece stat
     */
    private JsonObject attributeToJsonObj(ItemAttribute itemAttribute)
    {
        return attributeToJsonObj(itemAttribute.operation(), itemAttribute.value());
    }

    /**
     * Gets the armor point value of the armor piece
     * @param type The armor piece type
     * @return An integer depicting the armor points the armor piece gives when worn
     */
    public int getArmor(ArmorItem.Type type)
    {
        JsonObject armorObj = this.jsonObject.getAsJsonObject(type.getName());
        return armorObj.get("armor").getAsInt();
    }

    /**
     * Gets the toughness value of the armor set
     * @return The toughness value per piece of the armor set
     */
    public float getToughness()
    {
        return this.jsonObject.get("toughness").getAsFloat();
    }

    /**
     * Gets the knockback resistance of the armor set
     * @return The knockback resistance per piece of the armor set
     */
    public float getKnockbackResistance()
    {
        return this.jsonObject.get("knockback_resistance").getAsFloat();
    }

    /**
     * Gets the speed increase value of the armor piece
     * @param type The armor piece type
     * @return An {@link ItemAttribute} containing the {@link AttributeModifier} operation and value of the speed increase
     */
    public ItemAttribute getSpeedIncrease(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "speed");
    }

    /**
     * Gets the damage increase value of the armor piece
     * @param type The armor piece type
     * @return An {@link ItemAttribute} containing the {@link AttributeModifier} operation and value of the damage increase
     */
    public ItemAttribute getDamageIncrease(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "damage");
    }

    /**
     * Gets the heat resistance value of the armor piece
     * @param type The armor piece type
     * @return An {@link ItemAttribute} containing the {@link AttributeModifier} operation and value of the heat resistance
     */
    public ItemAttribute getHeatResistance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "heat_resistance");
    }

    /**
     * Gets the freeze resistance value of the armor piece
     * @param type The armor piece type
     * @return An {@link ItemAttribute} containing the {@link AttributeModifier} operation and value of the freeze resistance
     */
    public ItemAttribute getFreezeResistance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "freeze_resistance");
    }

    /**
     * Gets the refined mineral chance value of the armor piece
     * @param type The armor piece type
     * @return An {@link ItemAttribute} containing the {@link AttributeModifier} operation and value of the refined mineral chance
     */
    public ItemAttribute getRefinedMineralChance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "refined_mineral_chance");
    }

    /**
     * Helper method for getting the {@link ItemAttribute}s for the armor set
     * @param type The armor piece type
     * @param key The stat's key
     * @return An {@link ItemAttribute} containing the operation and value of the {@link AttributeModifier} to be applied to the armor piece
     */
    private ItemAttribute getItemAttribute(ArmorItem.Type type, String key)
    {
        JsonObject armorObj = this.jsonObject.getAsJsonObject(type.getName());
        JsonObject damageObj = armorObj.getAsJsonObject(key);
        AttributeModifier.Operation operation = AttributeModifier.Operation.fromValue(damageObj.get("operation").getAsInt());
        double value = damageObj.get("value").getAsDouble();
        return new ItemAttribute(operation, value);
    }
}

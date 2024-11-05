package net.laserdiamond.burningminesofbelow.util.file;

import com.google.gson.JsonObject;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.equipment.armor.BMOBArmorMaterials;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;

/**
 * Config class for storing the stats of armor sets
 */
public final class ArmorConfig extends JsonConfig {

    private final BMOBArmorMaterials armorMaterial;

    /**
     * Creates a Json file to store the armor set values
     * @param armorMaterial The armor material of this mod
     */
    public ArmorConfig(BMOBArmorMaterials armorMaterial) {
        super(armorMaterial.getName().replace(BurningMinesOfBelow.MODID + ":", ""));
        this.armorMaterial = armorMaterial;

        this.jsonObject.addProperty("toughness", this.armorMaterial.getToughness());
        this.jsonObject.addProperty("knockback_resistance", this.armorMaterial.getKnockbackResistance());

        for (ArmorItem.Type type : ArmorItem.Type.values())
        {
            this.jsonObject.add(type.getName(), armorObjectJson(type));
        }

        // TODO: Hard-coded armor stats should be written to the file
        // If the hard-coded stat and the stat in the file do not match, use the file stat
        // If the file stat is not a wanted value, use the hard-coded stat
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
        JsonObject armorObj = new JsonObject();

        // TODO: Other stat operations/values
        armorObj.addProperty("armor", this.armorMaterial.getDefenseForType(type));
        armorObj.add("damage", this.attributeToJsonObj(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("heat_resistance", this.attributeToJsonObj(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("freeze_resistance", this.attributeToJsonObj(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("refined_mineral_chance", this.attributeToJsonObj(AttributeModifier.Operation.MULTIPLY_BASE, 0));

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

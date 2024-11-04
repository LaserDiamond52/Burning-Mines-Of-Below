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
    public String filePath() {
        return "armor";
    }

    private JsonObject armorObjectJson(ArmorItem.Type type)
    {
        JsonObject armorObj = new JsonObject();

        armorObj.addProperty("armor", this.armorMaterial.getDefenseForType(type));
        armorObj.add("damage", this.bmobAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("heat_resistance", this.bmobAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("freeze_resistance", this.bmobAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0));
        armorObj.add("refined_mineral_chance", this.bmobAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0));

        return armorObj;
    }

    private JsonObject bmobAttribute(AttributeModifier.Operation operation, double value)
    {
        JsonObject attributeObj = new JsonObject();
        attributeObj.addProperty("operation", operation.toValue());
        attributeObj.addProperty("value", value);
        return attributeObj;
    }


    public int getArmor(ArmorItem.Type type)
    {
        JsonObject armorObj = this.jsonObject.getAsJsonObject(type.getName());
        return armorObj.get("armor").getAsInt();
    }

    public float getToughness()
    {
        return this.jsonObject.get("toughness").getAsFloat();
    }

    public float getKnockbackResistance()
    {
        return this.jsonObject.get("knockback_resistance").getAsFloat();
    }

    public ItemAttribute getDamageIncrease(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "damage");
    }

    public ItemAttribute getHeatResistance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "heat_resistance");
    }

    public ItemAttribute getFreezeResistance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "freeze_resistance");
    }

    public ItemAttribute getRefinedMineralChance(ArmorItem.Type type)
    {
        return this.getItemAttribute(type, "refined_mineral_chance");
    }

    private ItemAttribute getItemAttribute(ArmorItem.Type type, String key)
    {
        JsonObject armorObj = this.jsonObject.getAsJsonObject(type.getName());
        JsonObject damageObj = armorObj.getAsJsonObject(key);
        AttributeModifier.Operation operation = AttributeModifier.Operation.fromValue(damageObj.get("operation").getAsInt());
        double value = damageObj.get("value").getAsDouble();
        return new ItemAttribute(operation, value);
    }
}

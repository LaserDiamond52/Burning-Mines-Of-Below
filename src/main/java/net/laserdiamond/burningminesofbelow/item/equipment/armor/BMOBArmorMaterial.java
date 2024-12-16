package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains all the armor materials of this mod and their properties</li>
 * <li>A {@link BMOBArmorMaterial} is-a {@link ArmorMaterial}</li>
 * @author Allen Malo
 */
public enum BMOBArmorMaterial implements ArmorMaterial {

    /*
    Attribute Order:
    1. Speed
    2. Damage
    3. Heat Resistance
    4. Freeze Resistance
    5. Refined Mineral Chance
    */

    /**
     * Blazium armor material
     */
    BLAZIUM("blazium", 70, new int[]{3, 8, 6, 3},
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            15, SoundEvents.BLAZE_HURT, 2F, 0.05F, () -> Ingredient.of(BMOBItems.BLAZIUM_INGOT.get())),

    /**
     * Infernal Blazium armor material
     */
    INFERNAL_BLAZIUM("infernal_blazium", 81, new int[]{4, 9, 7, 4},
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            17, SoundEvents.BLAZE_HURT, 2F, 0.05F, () -> Ingredient.of(BMOBItems.BLAZIUM_INGOT.get(), BMOBItems.INFERNAL_FLAME_INGOT.get())),

    /**
     * Diamonite armor material
     */
    DIAMONITE("diamonite", 76, new int[]{5, 10, 8, 5},
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.ADDITION, 2.5),
            13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.25F, 0.0F, () -> Ingredient.of(BMOBItems.REFINED_DIAMOND.get())),

    /**
     * Refined Diamonite armor material
     */
    REFINED_DIAMONITE("refined_diamonite", 90, new int[]{7, 12, 10, 7},
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.ADDITION, 5.0),
            13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(BMOBItems.REFINED_DIAMOND.get())),

    /**
     * Cyronite armor material
     */
    CYRONITE("cyronite", 70, new int[]{3, 8, 6, 3},
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBArmorItem.createEmptyAttributes(),
            15, SoundEvents.AMETHYST_BLOCK_CHIME, 2.25F, 0.05F, () -> Ingredient.of(BMOBItems.CRYONITE_SHARD.get())),

    /**
     * Frigid Cyronite armor material
     */
    FRIGID_CYRONITE("frigid_cyronite", 81, new int[]{4, 9, 7, 4},
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBArmorItem.createEmptyAttributes(),
            BMOBArmorItem.createConsistentAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
            BMOBArmorItem.createEmptyAttributes(),
            17, SoundEvents.AMETHYST_BLOCK_CHIME, 2F, 0.05F, () -> Ingredient.of(BMOBItems.CRYONITE_SHARD.get(), BMOBItems.FRIGID_CRYONITE_CRYSTAL.get())),
    ;

    private final String name; // A BMOBArmorMaterial has-a String
    private final int durabilityMultiplier;
    private final int[] protectionAmount;
    private final ItemAttribute[] speedAmount; // A BMOBArmorMaterial has-a ItemAttribute (one-to-many)
    private final ItemAttribute[] damageAttributes; // A BMOBArmorMaterial has-a ItemAttribute (one-to-many)
    private final ItemAttribute[] heatResistanceAmount; // A BMOBArmorMaterial has-a ItemAttribute (one-to-many)
    private final ItemAttribute[] freezeResistanceAmount; // A BMOBArmorMaterial has-a ItemAttribute (one-to-many)
    private final ItemAttribute[] refinedMineralChanceAmount; // A BMOBArmorMaterial has-a ItemAttribute (one-to-many)
    private final int enchantValue;
    private final SoundEvent equipSound; // A BMOBArmorMaterial has-a SoundEvent
    private final float toughness;
    private final float kbRes;
    private final Supplier<Ingredient> repairIngredient; // A BMOBArmorMaterial has-a Supplier

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    /**
     * Creates a new {@link BMOBArmorMaterial}
     * @param name The name of the armor material
     * @param durabilityMultiplier The durability multiplier for the armor set. Applied across all pieces
     * @param protectionAmount The amount of protection each piece of armor should provide
     * @param speedAmount The {@link ItemAttribute}s for how much speed each piece should give
     * @param damageAttributes The {@link ItemAttribute}s for how much damage each piece should give
     * @param heatResistanceAmount The {@link ItemAttribute}s for how much heat resistance each piece should give
     * @param freezeResistanceAmount The {@link ItemAttribute}s for how much freeze resistance each piece should give
     * @param refinedMineralChanceAmount The {@link ItemAttribute}s for how much refined mineral chance each piece should give
     * @param enchantValue The enchantment value of the armor set. A higher value grants better odds at getting strong enchantments from an enchantment table
     * @param equipSound The {@link SoundEvent} when an armor piece is equipped
     * @param toughness The toughness of each armor piece of the set
     * @param kbRes The knockback resistance of each armor piece of the set
     * @param repairIngredient The {@link Ingredient}s that can be used to repair pieces of the armor set
     */
    BMOBArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmount, ItemAttribute[] speedAmount, ItemAttribute[] damageAttributes, ItemAttribute[] heatResistanceAmount, ItemAttribute[] freezeResistanceAmount, ItemAttribute[] refinedMineralChanceAmount, int enchantValue, SoundEvent equipSound, float toughness, float kbRes, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.speedAmount = speedAmount;
        this.damageAttributes = damageAttributes;
        this.heatResistanceAmount = heatResistanceAmount;
        this.freezeResistanceAmount = freezeResistanceAmount;
        this.refinedMineralChanceAmount = refinedMineralChanceAmount;
        this.enchantValue = enchantValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.kbRes = kbRes;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionAmount[type.ordinal()];
    }

    public ItemAttribute getSpeedForType(ArmorItem.Type type)
    {
        return this.speedAmount[type.ordinal()];
    }

    public ItemAttribute getDamageForType(ArmorItem.Type type) {
        return this.damageAttributes[type.ordinal()];
    }

    public ItemAttribute getHeatResistanceAmountForType(ArmorItem.Type type) {
        return this.heatResistanceAmount[type.ordinal()];
    }

    public ItemAttribute getFreezeResistanceAmountForType(ArmorItem.Type type) {
        return this.freezeResistanceAmount[type.ordinal()];
    }

    public ItemAttribute getRefinedMineralChanceAmountForType(ArmorItem.Type type) {
        return this.refinedMineralChanceAmount[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return BurningMinesOfBelow.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.kbRes;
    }


}

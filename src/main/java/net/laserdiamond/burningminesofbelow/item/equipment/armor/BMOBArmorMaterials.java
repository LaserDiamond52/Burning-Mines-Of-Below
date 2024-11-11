package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum BMOBArmorMaterials implements ArmorMaterial {

    BLAZIUM("blazium", 70, new int[]{3, 8, 6, 3},
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            15, SoundEvents.BLAZE_HURT, 2F, 0.05F, () -> Ingredient.of(BMOBItems.BLAZIUM_INGOT.get())),

    INFERNAL_BLAZIUM("infernal_blazium", 81, new int[]{4, 9, 7, 4},
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            17, SoundEvents.BLAZE_HURT, 2F, 0.05F, () -> Ingredient.of(BMOBItems.BLAZIUM_INGOT.get(), BMOBItems.INFERNAL_FLAME_INGOT.get())),

    DIAMONITE("diamonite", 76, new int[]{5, 10, 8, 5},
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.ADDITION, 2.5),
            13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.25F, 0.0F, () -> Ingredient.of(BMOBItems.REFINED_DIAMOND.get())),

    REFINED_DIAMONITE("refined_diamonite", 90, new int[]{7, 12, 10, 7},
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.ADDITION, 5.0),
            13, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, () -> Ingredient.of(BMOBItems.REFINED_DIAMOND.get())),

    CYRONITE("cyronite", 70, new int[]{3, 8, 6, 3},
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBItem.createEmptyArmorAttributes(),
            15, SoundEvents.AMETHYST_BLOCK_CHIME, 2.25F, 0.05F, () -> Ingredient.of(BMOBItems.CYRONITE_SHARD.get())),

    FRIGID_CYRONITE("frigid_cyronite", 81, new int[]{4, 9, 7, 4},
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
            BMOBItem.createEmptyArmorAttributes(),
            BMOBItem.createConsistentArmorAttributes(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
            BMOBItem.createEmptyArmorAttributes(),
            17, SoundEvents.AMETHYST_BLOCK_CHIME, 2F, 0.05F, () -> Ingredient.of(BMOBItems.CYRONITE_SHARD.get(), BMOBItems.FRIGID_CYRONITE_CRYSTAL.get())),
    ;

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmount;
    private final ItemAttribute[] speedAmount;
    private final ItemAttribute[] damageAttributes;
    private final ItemAttribute[] heatResistanceAmount;
    private final ItemAttribute[] freezeResistanceAmount;
    private final ItemAttribute[] refinedMineralChanceAmount;
    private final int enchantValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float kbRes;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    BMOBArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmount, ItemAttribute[] speedAmount, ItemAttribute[] damageAttributes, ItemAttribute[] heatResistanceAmount, ItemAttribute[] freezeResistanceAmount, ItemAttribute[] refinedMineralChanceAmount, int enchantValue, SoundEvent equipSound, float toughness, float kbRes, Supplier<Ingredient> repairIngredient) {
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

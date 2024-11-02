package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.List;

public final class InfernalBlaziumArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public InfernalBlaziumArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.INFERNAL_BLAZIUM, pType, pProperties, tags);
    }

    @Override
    protected ItemAttribute[] damageOutputAmounts() {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.05),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.05)
                };
    }

    @Override
    protected ItemAttribute[] heatIntervalAmount() {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.075),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.075)
                };
    }

    @Override
    protected ItemAttribute[] speedAmount() {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.025),
                        new ItemAttribute(AttributeModifier.Operation.MULTIPLY_BASE, 0.025)
                };
    }

    @Override
    public Item mainItem() {
        return switch (this.type)
        {
            case HELMET -> BMOBItems.BLAZIUM_HELMET.get();
            case CHESTPLATE -> BMOBItems.BLAZIUM_CHESTPLATE.get();
            case LEGGINGS -> BMOBItems.BLAZIUM_LEGGINGS.get();
            case BOOTS -> BMOBItems.BLAZIUM_BOOTS.get();
        };
    }

    @Override
    public List<Item> miscItems() {
        return List.of(BMOBItems.INFERNAL_FLAME_INGOT.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 3;
    }

    @Override
    public int heatFuelCost() {
        return 300;
    }

    @Override
    public int freezeFuelCost() {
        return 0;
    }
}

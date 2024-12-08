package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public final class BlaziumArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public BlaziumArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.BLAZIUM, pType, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return switch (this.type)
        {
            case HELMET -> Items.DIAMOND_HELMET;
            case CHESTPLATE -> Items.DIAMOND_CHESTPLATE;
            case LEGGINGS -> Items.DIAMOND_LEGGINGS;
            case BOOTS -> Items.DIAMOND_BOOTS;
        };
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.BLAZIUM_INGOT.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 1;
    }

    @Override
    public int heatFuelCost() {
        return 100;
    }

    @Override
    public int freezeFuelCost() {
        return 0;
    }
}

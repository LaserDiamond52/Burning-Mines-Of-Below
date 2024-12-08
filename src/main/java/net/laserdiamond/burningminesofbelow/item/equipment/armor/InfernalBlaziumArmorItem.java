package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public final class InfernalBlaziumArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public InfernalBlaziumArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.INFERNAL_BLAZIUM, pType, pProperties, tags);
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
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.INFERNAL_FLAME_INGOT.get());
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

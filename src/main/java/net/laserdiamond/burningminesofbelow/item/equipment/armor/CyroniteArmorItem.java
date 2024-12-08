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

public final class CyroniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public CyroniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.CYRONITE, pType, pProperties, tags);
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
        return Ingredient.of(BMOBItems.CYRONITE_SHARD.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 1;
    }

    @Override
    public int heatFuelCost() {
        return 0;
    }

    @Override
    public int freezeFuelCost() {
        return 100;
    }
}

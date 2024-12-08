package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public final class FrigidCyroniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public FrigidCyroniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.FRIGID_CYRONITE, pType, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return switch (this.type)
        {
            case HELMET -> BMOBItems.CYRONITE_HELMET.get();
            case CHESTPLATE -> BMOBItems.CYRONITE_CHESTPLATE.get();
            case LEGGINGS -> BMOBItems.CYRONITE_LEGGINGS.get();
            case BOOTS -> BMOBItems.CYRONITE_BOOTS.get();
        };
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.FRIGID_CYRONITE_CRYSTAL.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 3;
    }

    @Override
    public int heatFuelCost() {
        return 0;
    }

    @Override
    public int freezeFuelCost() {
        return 275;
    }
}

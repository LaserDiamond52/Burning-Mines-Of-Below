package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public final class RefinedDiamoniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public RefinedDiamoniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.REFINED_DIAMONITE, pType, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return switch (this.type)
        {
            case HELMET -> BMOBItems.DIAMONITE_HELMET.get();
            case CHESTPLATE -> BMOBItems.DIAMONITE_CHESTPLATE.get();
            case LEGGINGS -> BMOBItems.DIAMONITE_LEGGINGS.get();
            case BOOTS -> BMOBItems.DIAMONITE_BOOTS.get();
        };
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.REFINED_DIAMOND.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 3;
    }

    @Override
    public int heatFuelCost() {
        return 250;
    }

    @Override
    public int freezeFuelCost() {
        return 0;
    }
}

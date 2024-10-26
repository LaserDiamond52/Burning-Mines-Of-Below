package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public class RefinedDiamoniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public RefinedDiamoniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.REFINED_DIAMONITE, pType, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return BMOBItems.getDiamoniteArmorPiece(this.type).get();
    }

    @Override
    public List<Item> miscItems() {
        return List.of(BMOBItems.REFINED_DIAMOND.get());
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

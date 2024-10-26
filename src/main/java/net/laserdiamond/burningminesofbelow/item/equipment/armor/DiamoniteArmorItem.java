package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public final class DiamoniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    public DiamoniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterials.DIAMONITE, pType, pProperties, tags);
    }

    @Override
    protected ItemAttribute[] refinedMineralChanceAmount() {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 2.5),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 2.5),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 2.5),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 2.5)
                };
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
    public List<Item> miscItems() {
        return List.of(BMOBItems.REFINED_DIAMOND.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 1;
    }

    @Override
    public int heatFuelCost() {
        return 75;
    }

    @Override
    public int freezeFuelCost() {
        return 0;
    }
}

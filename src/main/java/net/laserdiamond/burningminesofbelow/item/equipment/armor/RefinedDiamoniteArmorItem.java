package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties of a {@link RefinedDiamoniteArmorItem}</li>
 * <li>Defines the recipe ingredients for a {@link RefinedDiamoniteArmorItem} from the Forge</li>
 * <li>A {@link RefinedDiamoniteArmorItem} is-a {@link BMOBArmorItem}</li>
 * <li>A {@link RefinedDiamoniteArmorItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public final class RefinedDiamoniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    /**
     * Creates a new {@link RefinedDiamoniteArmorItem}
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public RefinedDiamoniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterial.REFINED_DIAMONITE, pType, pProperties, tags);
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

package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties of a {@link BlaziumArmorItem}</li>
 * <li>Defines the recipe ingredients for a {@link BlaziumArmorItem} from the Forge</li>
 * <li>A {@link BlaziumArmorItem} is-a {@link BMOBArmorItem}</li>
 * <li>A {@link BlaziumArmorItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public final class BlaziumArmorItem extends BMOBArmorItem implements ForgeCraftable {

    /**
     * Creates a new {@link BlaziumArmorItem}
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public BlaziumArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterial.BLAZIUM, pType, pProperties, tags);
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

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
 * <li>Defines the properties of a {@link CyroniteArmorItem}</li>
 * <li>Defines the recipe ingredients for a {@link CyroniteArmorItem} from the Forge</li>
 * <li>A {@link CyroniteArmorItem} is-a {@link BMOBArmorItem}</li>
 * <li>A {@link CyroniteArmorItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public final class CyroniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    /**
     * Creates a new {@link CyroniteArmorItem}
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public CyroniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterial.CYRONITE, pType, pProperties, tags);
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

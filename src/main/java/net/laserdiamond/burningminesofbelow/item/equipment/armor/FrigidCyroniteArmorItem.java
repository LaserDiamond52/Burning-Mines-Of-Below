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
 * <li>Defines the properties of a {@link FrigidCyroniteArmorItem}</li>
 * <li>Defines the recipe ingredients for a {@link FrigidCyroniteArmorItem} from the Forge</li>
 * <li>A {@link FrigidCyroniteArmorItem} is-a {@link BMOBArmorItem}</li>
 * <li>A {@link FrigidCyroniteArmorItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public final class FrigidCyroniteArmorItem extends BMOBArmorItem implements ForgeCraftable {

    /**
     * Creates a new {@link FrigidCyroniteArmorItem}
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public FrigidCyroniteArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterial.FRIGID_CYRONITE, pType, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return switch (this.type)
        {
            case HELMET -> BMOBItems.CRYONITE_HELMET.get();
            case CHESTPLATE -> BMOBItems.CRYONITE_CHESTPLATE.get();
            case LEGGINGS -> BMOBItems.CRYONITE_LEGGINGS.get();
            case BOOTS -> BMOBItems.CYRONITE_BOOTS.get();
        };
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.FRIGID_CRYONITE_CRYSTAL.get());
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

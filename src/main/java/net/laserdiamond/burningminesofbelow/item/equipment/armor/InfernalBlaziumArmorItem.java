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
 * <li>Defines the properties of an {@link InfernalBlaziumArmorItem}</li>
 * <li>Defines the recipe ingredients for an {@link InfernalBlaziumArmorItem} from the Forge</li>
 * <li>A {@link InfernalBlaziumArmorItem} is-a {@link BMOBArmorItem}</li>
 * <li>A {@link InfernalBlaziumArmorItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public final class InfernalBlaziumArmorItem extends BMOBArmorItem implements ForgeCraftable {

    /**
     * Creates a new {@link InfernalBlaziumArmorItem}
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public InfernalBlaziumArmorItem(Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBArmorMaterial.INFERNAL_BLAZIUM, pType, pProperties, tags);
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

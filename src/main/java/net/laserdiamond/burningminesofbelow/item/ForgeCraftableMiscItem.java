package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines a miscellaneous item that can be crafted in the Forge</li>
 * <li>A {@link ForgeCraftableMiscItem} is-a {@link BMOBItem}</li>
 * <li>A {@link ForgeCraftableMiscItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 */
public abstract class ForgeCraftableMiscItem extends BMOBItem implements ForgeCraftable {

    /**
     * Creates a new {@link ForgeCraftableMiscItem}
     * @param pProperties The {@link Item.Properties} of the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public ForgeCraftableMiscItem(Properties pProperties, List<TagKey<Item>> tags) {
        super(pProperties, tags);
    }
}

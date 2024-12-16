package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Represents a simple item of this mod</li>
 * <li>A {@link BMOBItem} is-a {@link Item}</li>
 * <li>A {@link BMOBItem} is-a {@link Taggable}</li>
 * <li>A {@link BMOBItem} is-a {@link ForgeFuelItem}</li>
 * @author Allen Malo
 */
public class BMOBItem extends Item implements Taggable<Item>, ForgeFuelItem {

    /**
     * A {@link List} of {@link TagKey}s to apply to the item
     */
    private final List<TagKey<Item>> tags; // A BMOBItem has-a List

    /**
     * Creates a new {@link BMOBItem}
     * @param pProperties The {@link Item.Properties} of the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public BMOBItem(Properties pProperties, List<TagKey<Item>> tags) {
        super(pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

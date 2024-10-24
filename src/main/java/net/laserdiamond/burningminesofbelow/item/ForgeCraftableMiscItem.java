package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public abstract class ForgeCraftableMiscItem extends BMOBItem implements ForgeCraftable {

    public ForgeCraftableMiscItem(Properties pProperties, List<TagKey<Item>> tags) {
        super(pProperties, tags);
    }
}

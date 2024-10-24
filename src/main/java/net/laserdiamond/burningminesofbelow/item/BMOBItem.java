package net.laserdiamond.burningminesofbelow.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple item of this mod
 */
public class BMOBItem extends Item implements ItemTaggable {

    private final List<TagKey<Item>> tags;

    public BMOBItem(Properties pProperties, List<TagKey<Item>> tags) {
        super(pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

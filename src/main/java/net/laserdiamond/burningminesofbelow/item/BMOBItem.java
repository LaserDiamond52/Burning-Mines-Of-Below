package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class BMOBItem extends Item implements Taggable<Item> {

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

package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * Refined Ore Block of this mod. Drop chances for the refined drop are hard coded and tied to a player's Refined Mineral Chance attribute.
 */
public class RefinedOreBlock extends BMOBOreBlock {

    private final Item refinedDrop;

    public RefinedOreBlock(Properties pProperties, Item oreDrop, Item refinedDrop, IntProvider expRange, List<TagKey<Block>> tags) {
        super(pProperties, oreDrop, expRange, tags);
        this.refinedDrop = refinedDrop;
    }

    public Item getRefinedDrop() {
        return refinedDrop;
    }
}

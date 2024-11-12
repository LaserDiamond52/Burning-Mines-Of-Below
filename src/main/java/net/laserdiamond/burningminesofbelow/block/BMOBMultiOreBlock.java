package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class BMOBMultiOreBlock extends BMOBOreBlock {

    private final int minCount;
    private final int maxCount;

    public BMOBMultiOreBlock(Properties pProperties, Item oreDrop, int minCount, int maxCount, IntProvider expRange, List<TagKey<Block>> tags) {
        super(pProperties, oreDrop, expRange, tags);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }
}

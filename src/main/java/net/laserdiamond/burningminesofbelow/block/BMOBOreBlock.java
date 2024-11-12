package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.common.Tags;

import java.util.List;

public class BMOBOreBlock extends DropExperienceBlock implements Taggable<Block> {

    protected final List<TagKey<Block>> tags;
    protected final Item oreDrop;

    public BMOBOreBlock(Properties pProperties, Item oreDrop, IntProvider expRange, List<TagKey<Block>> tags) {
        super(pProperties, expRange);
        this.tags = tags;
        this.tags.add(Tags.Blocks.ORES);
        this.oreDrop = oreDrop;
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return tags;
    }

    public Item getOreDrop() {
        return oreDrop;
    }
}

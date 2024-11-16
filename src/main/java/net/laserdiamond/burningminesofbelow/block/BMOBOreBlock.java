package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class BMOBOreBlock extends DropExperienceBlock implements Taggable<Block> {

    protected final List<TagKey<Block>> tags;
    protected final RegistryObject<Item> oreDrop;

    public BMOBOreBlock(Properties pProperties, RegistryObject<Item> oreDrop, IntProvider expRange, List<TagKey<Block>> tags) {
        super(pProperties, expRange);
        this.tags = new ArrayList<>(tags);
        this.tags.add(Tags.Blocks.ORES);
        this.oreDrop = oreDrop;
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return tags;
    }

    public RegistryObject<Item> getOreDrop() {
        return this.oreDrop;
    }
}

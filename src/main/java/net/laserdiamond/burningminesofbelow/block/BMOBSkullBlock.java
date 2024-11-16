package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;

import java.util.List;

public class BMOBSkullBlock extends SkullBlock implements Taggable<Block> {

    protected final List<TagKey<Block>> tags;

    public BMOBSkullBlock(Type pType, Properties pProperties, List<TagKey<Block>> tags) {
        super(pType, pProperties);
        this.tags = tags;
    }



    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    public enum Types implements SkullBlock.Type
    {
        FROZEN_WITHER_SKULL,
        BLAZE_SKULL
    }
}

package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * A basic block of this mod
 */
public class BMOBBlock extends Block implements Taggable<Block> {

    private final List<TagKey<Block>> tags;

    public BMOBBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }
}

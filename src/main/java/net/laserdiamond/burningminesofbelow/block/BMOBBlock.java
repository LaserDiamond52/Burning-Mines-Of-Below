package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as a basic block of this mod</li>
 * <li>A {@link BMOBBlock} is-a {@link Block}</li>
 * <li>A {@link BMOBBlock} is-a {@link Taggable}</li>
 * @author Allen Malo
 */
public class BMOBBlock extends Block implements Taggable<Block> {

    /**
     * The {@link List} of tags to apply to the block
     */
    protected final List<TagKey<Block>> tags; // BMOBBlock has-a List

    /**
     * Creates a new {@link BMOBBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the {@link BMOBBlock}
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public BMOBBlock(Properties pProperties, List<TagKey<Block>> tags) {
        super(pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }
}

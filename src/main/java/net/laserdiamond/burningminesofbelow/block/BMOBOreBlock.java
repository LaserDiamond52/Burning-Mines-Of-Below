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

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates an ore block for use with this mod</li>
 * <li>A {@link BMOBOreBlock} is-a {@link DropExperienceBlock}</li>
 * <li>A {@link BMOBOreBlock} is-a {@link Taggable}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBOreBlock extends DropExperienceBlock implements Taggable<Block> {

    /**
     * The {@link List} of tags to apply to the block
     */
    protected final List<TagKey<Block>> tags; // BMOBOreBlock has-a List

    /**
     * The item {@link RegistryObject} to drop when successfully mined
     */
    protected final RegistryObject<Item> oreDrop; // BMOBOreBlock has-a RegistryObject

    /**
     * Creates a new {@link BMOBOreBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the block
     * @param oreDrop The item {@link RegistryObject} to drop when successfully mined
     * @param expRange The amount of experience orbs to drop when mined
     * @param tags A {@link List} of tags to apply to the block
     */
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

    /**
     *
     * @return The item {@link RegistryObject} to be dropped when successfully mined
     */
    public RegistryObject<Item> getOreDrop() {
        return this.oreDrop;
    }
}

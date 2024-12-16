package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create a {@link VanillaRefinedOreBlock} of this mod</li>
 * <li>Note: This differs from the {@link RefinedOreBlock}, in that this class is used for creating refined ore variants of Vanilla ores (pre-existing ores in the game)</li>
 * <li>A {@link VanillaRefinedOreBlock} is-a {@link DropExperienceBlock}</li>
 * <li>A {@link VanillaRefinedOreBlock} is-a {@link Taggable}</li>
 * @author Allen Malo
 * @see RefinedOreBlock
 */
public class VanillaRefinedOreBlock extends DropExperienceBlock implements Taggable<Block> {

    /**
     * The default ore drop when successfully mined
     */
    private final Item oreDrop; // VanillaRefinedOreBlock has-a Item

    /**
     * The refined ore drop
     */
    private final RegistryObject<Item> refinedDrop; // VanillaRefinedOreBlock has-a RegistryObject

    /**
     * {@link List} of tags that can be applied to the block
     */
    private final List<TagKey<Block>> tags; // VanillaRefinedOreBlock has-a List

    /**
     * Creates a new {@link VanillaRefinedOreBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour} properties
     * @param oreDrop The default ore drop when successfully mined
     * @param refinedDrop The refined ore drop to drop when a refined ore drop is rolled
     * @param pXpRange The amount of experience orbs to drop when successfully mined
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public VanillaRefinedOreBlock(Properties pProperties, Item oreDrop, RegistryObject<Item> refinedDrop, IntProvider pXpRange, List<TagKey<Block>> tags) {
        super(pProperties, pXpRange);
        this.oreDrop = oreDrop;
        this.refinedDrop = refinedDrop;
        this.tags = new ArrayList<>(tags);
        this.tags.add(BMOBTags.Blocks.REFINED_ORE);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    /**
     *
     * @return The default {@link Item} to be dropped when this block is mined
     */
    public Item getOreDrop() {
        return oreDrop;
    }

    /**
     *
     * @return The {@link Item} to be dropped when a refined drop is rolled
     */
    public RegistryObject<Item> getRefinedDrop() {
        return refinedDrop;
    }
}

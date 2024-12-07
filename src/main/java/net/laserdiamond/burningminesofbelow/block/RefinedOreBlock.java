package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * Refined Ore Block of this mod. Drop chances for the refined drop are hard coded and tied to a player's Refined Mineral Chance attribute.
 */
public class RefinedOreBlock extends BMOBOreBlock {

    /**
     * The refined ore drop
     */
    private final RegistryObject<Item> refinedDrop;

    /**
     * Creates a new {@link RefinedOreBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour} properties
     * @param oreDrop The default ore drop when successfully mined
     * @param refinedDrop The refined ore drop to drop when a refined ore drop is rolled
     * @param expRange The amount of experience orbs to drop when successfully mined
     * @param tags A {@link List} of {@link TagKey}s to apply to the block
     */
    public RefinedOreBlock(Properties pProperties, RegistryObject<Item> oreDrop, RegistryObject<Item> refinedDrop, IntProvider expRange, List<TagKey<Block>> tags)
    {
        super(pProperties, oreDrop, expRange, tags);
        this.refinedDrop = refinedDrop;
        this.tags.add(BMOBTags.Blocks.REFINED_ORE);
    }

    /**
     *
     * @return A {@link RegistryObject} of the {@link Item} to be dropped when a refined drop is rolled.
     */
    public RegistryObject<Item> getRefinedDrop() {
        return refinedDrop;
    }
}

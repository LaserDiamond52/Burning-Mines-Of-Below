package net.laserdiamond.burningminesofbelow.block;

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

    private final RegistryObject<Item> refinedDrop;

    public RefinedOreBlock(Properties pProperties, RegistryObject<Item> oreDrop, RegistryObject<Item> refinedDrop, IntProvider expRange, List<TagKey<Block>> tags)
    {
        super(pProperties, oreDrop, expRange, tags);
        this.refinedDrop = refinedDrop;
    }

    public RegistryObject<Item> getRefinedDrop() {
        return refinedDrop;
    }
}

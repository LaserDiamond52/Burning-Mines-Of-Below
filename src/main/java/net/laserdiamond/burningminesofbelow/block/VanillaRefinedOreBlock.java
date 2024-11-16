package net.laserdiamond.burningminesofbelow.block;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class VanillaRefinedOreBlock extends DropExperienceBlock implements Taggable<Block> {

    private final Item oreDrop;
    private final RegistryObject<Item> refinedDrop;
    private final List<TagKey<Block>> tags;

    public VanillaRefinedOreBlock(Properties pProperties, Item oreDrop, RegistryObject<Item> refinedDrop, IntProvider pXpRange, List<TagKey<Block>> tags) {
        super(pProperties, pXpRange);
        this.oreDrop = oreDrop;
        this.refinedDrop = refinedDrop;
        this.tags = tags;
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return this.tags;
    }

    public Item getOreDrop() {
        return oreDrop;
    }

    public RegistryObject<Item> getRefinedDrop() {
        return refinedDrop;
    }
}

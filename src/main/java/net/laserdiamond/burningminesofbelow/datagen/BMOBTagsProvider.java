package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BMOBTagsProvider {

    public static class Items extends ItemTagsProvider
    {

        public Items(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<net.minecraft.world.level.block.Block>> blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTagGenerator, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider)
        {
            for (RegistryObject<Item> item : BMOBItems.ITEMS.getEntries())
            {
                if (item.get() instanceof Taggable<?> taggable)
                {
                    for (TagKey<?> itemTag : taggable.getTags())
                    {
                        this.tag((TagKey<Item>) itemTag).add(item.get());
                    }
                }
            }
        }
    }

    public static class Blocks extends BlockTagsProvider
    {

        public Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider)
        {
            for (RegistryObject<Block> block : BMOBBlocks.BLOCKS.getEntries())
            {
                if (block.get() instanceof Taggable<?> taggable)
                {
                    for (TagKey<?> blockTag : taggable.getTags())
                    {
                        this.tag((TagKey<Block>) blockTag).add(block.get());
                    }
                }
            }
        }
    }

    public static class Entities extends EntityTypeTagsProvider
    {

        public Entities(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            super.addTags(pProvider);

            // TODO: add tags here
        }
    }


}

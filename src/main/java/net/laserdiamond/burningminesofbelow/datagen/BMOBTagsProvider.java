package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.laserdiamond.burningminesofbelow.item.ForgeFuelItem;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.laserdiamond.burningminesofbelow.worldgen.biome.BMOBBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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
            for (RegistryObject<Item> itemRegistryObject : BMOBItems.ITEMS.getEntries())
            {
                Item item = itemRegistryObject.get();
                if (item instanceof Taggable<?> taggable)
                {
                    for (TagKey<?> itemTag : taggable.getTags())
                    {
                        this.tag((TagKey<Item>) itemTag).add(itemRegistryObject.get());
                    }
                }
                if (item instanceof ForgeFuelItem forgeFuelItem)
                {
                    if (forgeFuelItem.heatFuel() > 0)
                    {
                        this.tag(BMOBTags.Items.FORGE_HEAT_FUEL).add(item);
                    }
                    if (forgeFuelItem.freezeFuel() > 0)
                    {
                        this.tag(BMOBTags.Items.FORGE_FREEZE_FUEL).add(item);
                    }
                }
                if (item instanceof ForgeCraftable forgeCraftable)
                {
                    if (!forgeCraftable.mainItem().getDefaultInstance().is(BMOBTags.Items.FORGE_MAIN_INGREDIENT))
                    {
                        this.tag(BMOBTags.Items.FORGE_MAIN_INGREDIENT).add(forgeCraftable.mainItem());
                    }
                    for (ItemStack ingredientStack : forgeCraftable.miscItems().getItems())
                    {
                        if (!ingredientStack.is(BMOBTags.Items.FORGE_MISC_INGREDIENT))
                        {
                            this.tag(BMOBTags.Items.FORGE_MISC_INGREDIENT).add(ingredientStack.getItem());
                        }
                    }
                }
            }

            // Add tags for fuel items
            try {
                for (Item heatFuelItem : BMOBItems.HEAT_FUEL_ITEMS.getFuelItemMap().keySet())
                {
                    this.tag(BMOBTags.Items.FORGE_HEAT_FUEL).add(heatFuelItem);
                }
                for (Item freezeFuelItem : BMOBItems.FREEZE_FUEL_ITEMS.getFuelItemMap().keySet())
                {
                    this.tag(BMOBTags.Items.FORGE_FREEZE_FUEL).add(freezeFuelItem);
                }
            } catch (NullPointerException e)
            {
                System.out.println("An item returned null, and the tag could not be applied!");
                e.printStackTrace();
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
        protected void addTags(HolderLookup.Provider pProvider)
        {
//            super.addTags(pProvider);

            // TODO: add tags here
        }
    }

    public static class Biomes extends BiomeTagsProvider
    {

        public Biomes(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper)
        {
            super(packOutput, provider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider)
        {
            this.tag(BMOBTags.Biomes.COCYTUS).add(BMOBBiomes.COCYTUS_TUNDRA);
            this.tag(BMOBTags.Biomes.COCYTUS).add(BMOBBiomes.COCYTUS_WASTELAND);
        }
    }


}

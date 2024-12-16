package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.laserdiamond.burningminesofbelow.item.ForgeFuelItem;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains the tag providers of this mod</li>
 * <li>Encompassing class of the tag provider classes of this mod</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBTagsProvider {

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Adds the {@link Item} {@link TagKey}s to the {@link Item}s</li>
     * <li>A {@link net.laserdiamond.burningminesofbelow.util.BMOBTags.Items} is-a {@link ItemTagsProvider}</li>
     * @author Allen Malo
     * @see net.minecraft.data.tags.VanillaItemTagsProvider
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     */
    public static class Items extends ItemTagsProvider
    {

        /**
         * Creates a new {@link net.laserdiamond.burningminesofbelow.util.BMOBTags.Items}
         * @param output The {@link PackOutput} of this mod
         * @param lookupProvider The {@link CompletableFuture} {@link HolderLookup.Provider} of this mod
         * @param blockTagGenerator The block tag generator of this mod
         * @param existingFileHelper The {@link ExistingFileHelper} of this mod
         */
        public Items(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<net.minecraft.world.level.block.Block>> blockTagGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, blockTagGenerator, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        /**
         * Adds all the {@link TagKey}s to the {@link Item}s
         * @param provider
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider provider)
        {
            for (RegistryObject<Item> itemRegistryObject : BMOBItems.ITEMS.getEntries()) // Loop through all items in the registry
            {
                Item item = itemRegistryObject.get(); // Get the item instance from the registry object
                if (item instanceof Taggable<?> taggable) // Check if the item is-a Taggable
                {
                    for (TagKey<?> itemTag : taggable.getTags())
                    {
                        this.tag((TagKey<Item>) itemTag).add(itemRegistryObject.get()); // Loop through all tags and add them
                    }
                }
                if (item instanceof ForgeFuelItem forgeFuelItem) // Check if the item is-a ForgeFuelItem
                {
                    if (forgeFuelItem.heatFuel() > 0)
                    {
                        this.tag(BMOBTags.Items.FORGE_HEAT_FUEL).add(item); // If the heat fuel the item can add is more than 0, then it is considered a heat fuel item for the Forge
                    }
                    if (forgeFuelItem.freezeFuel() > 0)
                    {
                        this.tag(BMOBTags.Items.FORGE_FREEZE_FUEL).add(item); // If the freeze fuel the item can add is more than 0, then it is considered a freeze fuel item for the Forge
                    }
                }
                if (item instanceof ForgeCraftable forgeCraftable) // Check if the item is-a ForgeCraftable
                {
                    if (!forgeCraftable.mainItem().getDefaultInstance().is(BMOBTags.Items.FORGE_MAIN_INGREDIENT))
                    {
                        this.tag(BMOBTags.Items.FORGE_MAIN_INGREDIENT).add(forgeCraftable.mainItem()); // If the item doesn't already, add the Main Ingredient tag to it
                    }
                    for (ItemStack ingredientStack : forgeCraftable.miscItems().getItems()) // Loop through all the applicable miscellaneous items
                    {
                        if (!ingredientStack.is(BMOBTags.Items.FORGE_MISC_INGREDIENT))
                        {
                            this.tag(BMOBTags.Items.FORGE_MISC_INGREDIENT).add(ingredientStack.getItem()); // If the item doesn't already, add the Misc Ingredient tag to it
                        }
                    }
                }
            }

            // Add tags for other fuel items
            // Use try-catch to catch a NullPointerException if the item to receive a tag is null
            try
            {
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

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Adds the {@link Block} {@link TagKey}s to the {@link Block}</li>
     * <li>A {@link BMOBTags.Blocks} is-a {@link BlockTagsProvider}</li>
     * @author Allen Malo
     * @see net.minecraft.data.tags.VanillaBlockTagsProvider
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     */
    public static class Blocks extends BlockTagsProvider
    {

        /**
         * Creates a new {@link BMOBTags.Blocks}
         * @param output The {@link PackOutput} of this mod
         * @param lookupProvider The {@link CompletableFuture} {@link HolderLookup.Provider} of this mod
         * @param existingFileHelper The {@link ExistingFileHelper} of this mod
         */
        public Blocks(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        /**
         * Applies all the {@link TagKey}s to the {@link Block}s
         * @param provider The {@link HolderLookup.Provider} of this mod
         */
        @SuppressWarnings("unchecked")
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

    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Adds the {@link net.minecraft.world.entity.EntityType} tags to the {@link net.minecraft.world.entity.EntityType}</li>
     * <li>A {@link BMOBTags.Entities} is-a {@link EntityTypeTagsProvider}</li>
     * @author Allen Malo
     * @see EntityTypeTagsProvider
     * @References:
     * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
     */
    public static class Entities extends EntityTypeTagsProvider
    {

        /**
         * Creates a new {@link BMOBTags.Entities}
         * @param output The {@link PackOutput} of this mod
         * @param lookupProvider The {@link CompletableFuture} {@link HolderLookup.Provider} of this mod
         * @param existingFileHelper The {@link ExistingFileHelper} of this mod
         */
        public Entities(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        /**
         * Applies the {@link TagKey}s to the {@link net.minecraft.world.entity.EntityType}s
         * @param pProvider The {@link HolderLookup.Provider} of this mod
         */
        @Override
        protected void addTags(HolderLookup.Provider pProvider)
        {
            this.tag(Tags.EntityTypes.BOSSES).add(BMOBEntities.KING_INFERNIUS.get());
            this.tag(Tags.EntityTypes.BOSSES).add(BMOBEntities.FREEZING_REAPER.get());

            this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(BMOBEntities.KING_INFERNIUS.get());
            this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(BMOBEntities.FREEZING_REAPER.get());
            this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE).add(BMOBEntities.MAGNITE_BLAZE.get());

            this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(BMOBEntities.KING_INFERNIUS.get());
            this.tag(EntityTypeTags.FREEZE_IMMUNE_ENTITY_TYPES).add(BMOBEntities.FREEZING_REAPER.get());

            this.tag(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES).add(BMOBEntities.MAGNITE_BLAZE.get());

            this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(BMOBEntities.CYROBOLT.get());
        }
    }

    @Deprecated
    public static class Biomes extends BiomeTagsProvider
    {

        public Biomes(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper)
        {
            super(packOutput, provider, BurningMinesOfBelow.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider)
        {
//            this.tag(BMOBTags.Biomes.COCYTUS).add(BMOBBiomes.COCYTUS_TUNDRA);
//            this.tag(BMOBTags.Biomes.COCYTUS).add(BMOBBiomes.COCYTUS_WASTELAND);
        }
    }


}

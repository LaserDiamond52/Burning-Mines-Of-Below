package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.block.*;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Generates all loot tables for the blocks of this mod</li>
 * <li>A {@link BMOBBlockLootTables} is-a {@link BlockLootSubProvider}</li>
 * @author Allen Malo
 * @see net.minecraft.data.loot.packs.VanillaBlockLoot
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 * <p>Forge Documentation:<a href="https://docs.minecraftforge.net/en/1.20.x/">...</a></p>
 */
public class BMOBBlockLootTables extends BlockLootSubProvider {

    /**
     * Creates the Block Loot Table generator
     */
    protected BMOBBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    /**
     * Generates all the loot tables for the blocks of this mod
     */
    @Override
    protected void generate() {

        for (RegistryObject<Block> blockRegistryObject : BMOBBlocks.BLOCKS.getEntries()) // Loop through all blocks in our Registry
        {
            Block block = blockRegistryObject.get();
            if (block instanceof AbstractForgeBlock<?> forgeBlock)
            {
                this.dropSelf(forgeBlock); // Forge blocks should drop themselves when mined
            } else if (block instanceof RefinedOreBlock refinedOreBlock)
            {
                // Refined Ore Blocks should have their default drops saved in a loot table
                // The refined drops are hard-coded to the block type.
                this.add(refinedOreBlock, oreBlock -> this.createOreDrop(refinedOreBlock, refinedOreBlock.getOreDrop().get()));
            } else if (block instanceof BMOBMultiOreBlock bmobMultiOreBlock)
            {
                // Multi Ore Blocks should drop the specified quantities of their drop
                this.add(bmobMultiOreBlock, multiOreBlock -> this.createMultiOreDrops(bmobMultiOreBlock, bmobMultiOreBlock.getOreDrop().get(), bmobMultiOreBlock.getMinCount(), bmobMultiOreBlock.getMaxCount()));
            } else if (block instanceof BMOBOreBlock bmobOreBlock)
            {
                // Ore blocks should drop their drop item
                this.add(bmobOreBlock, oreBlock -> this.createOreDrop(bmobOreBlock, bmobOreBlock.getOreDrop().get()));
            } else if (block instanceof VanillaRefinedOreBlock vanillaRefinedOreBlock)
            {
                // Similar case to the Refined Ore Blocks
                this.add(vanillaRefinedOreBlock, refinedOreBlock -> this.createOreDrop(vanillaRefinedOreBlock, vanillaRefinedOreBlock.getOreDrop()));
            } else if (block instanceof BMOBBlock bmobBlock)
            {
                // Standard block. Drop itself
                this.dropSelf(bmobBlock);
            } else if (block instanceof BMOBSkullBlock)
            {
                // Skull Blocks should drop themselves
                this.dropSelf(block);
            }
        }
    }

    /**
     * Creates a loot table that resembles the "multi-ore" loot table. This is similar to pre-existing ore loot tables for Redstone, Copper, Quartz, Lapis, etc.
     * @param pBlock The {@link Block} to be mined for the loot table
     * @param oreDrop The {@link Item} to drop
     * @param min The minimum count of the oreDrop to be dropped.
     * @param max The maximum count of the oreDrop to be dropped.
     * @return The {@link LootTable.Builder} to be assigned to the block.
     */
    protected LootTable.Builder createMultiOreDrops(Block pBlock, Item oreDrop, int min, int max) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(oreDrop).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    /**
     * Supply all registered blocks to the {@link BlockLootSubProvider} in order to make sure all blocks have a loot table associated with them
     * @return An {@link java.util.Iterator} that contains all the blocks of this mod
     */
    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        // Iterate through all blocks of this mod
        // Need to know what blocks need loot tables
        return BMOBBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

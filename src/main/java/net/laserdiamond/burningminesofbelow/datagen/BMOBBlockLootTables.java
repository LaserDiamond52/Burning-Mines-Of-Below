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

public class BMOBBlockLootTables extends BlockLootSubProvider {

    protected BMOBBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        for (RegistryObject<Block> blockRegistryObject : BMOBBlocks.BLOCKS.getEntries())
        {
            Block block = blockRegistryObject.get();
            if (block instanceof AbstractForgeBlock<?> forgeBlock)
            {
                this.dropSelf(forgeBlock);
            } else if (block instanceof RefinedOreBlock refinedOreBlock)
            {
                this.add(refinedOreBlock, oreBlock -> this.createOreDrop(refinedOreBlock, refinedOreBlock.getOreDrop().get()));
            } else if (block instanceof BMOBMultiOreBlock bmobMultiOreBlock)
            {
                this.add(bmobMultiOreBlock, multiOreBlock -> this.createMultiOreDrops(bmobMultiOreBlock, bmobMultiOreBlock.getOreDrop().get(), bmobMultiOreBlock.getMinCount(), bmobMultiOreBlock.getMaxCount()));
            } else if (block instanceof BMOBOreBlock bmobOreBlock)
            {
                this.add(bmobOreBlock, oreBlock -> this.createOreDrop(bmobOreBlock, bmobOreBlock.getOreDrop().get()));
            } else if (block instanceof VanillaRefinedOreBlock vanillaRefinedOreBlock)
            {
                this.add(vanillaRefinedOreBlock, refinedOreBlock -> this.createOreDrop(vanillaRefinedOreBlock, vanillaRefinedOreBlock.getOreDrop()));
            } else if (block instanceof BMOBBlock bmobBlock)
            {
                this.dropSelf(bmobBlock);
            } else if (block instanceof BMOBSkullBlock)
            {
                this.dropSelf(block);
            }
        }
    }

    protected LootTable.Builder createMultiOreDrops(Block pBlock, Item oreDrop, int min, int max) {
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(oreDrop).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


    /**
     * Supply all registered blocks to the {@link BlockLootSubProvider} in order to make sure all blocks have a loot table associated with them
     * @return An {@link java.util.Iterator} that contains all the blocks of this mod
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BMOBBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

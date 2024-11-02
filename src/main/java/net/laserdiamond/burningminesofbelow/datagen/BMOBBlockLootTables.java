package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.ForgeBlock;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
            if (block instanceof ForgeBlock forgeBlock)
            {
                this.dropSelf(forgeBlock);
            }
        }
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

package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates the states and models of the blocks of this mod</li>
 * <li>A {@link BMOBBlocksStateProvider} is-a {@link BlockStateProvider}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBBlocksStateProvider extends BlockStateProvider {

    /**
     * Creates a new {@link BMOBBlocksStateProvider}
     * @param output The {@link PackOutput} of this mod
     * @param exFileHelper The {@link ExistingFileHelper} of this mod
     */
    public BMOBBlocksStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BurningMinesOfBelow.MODID, exFileHelper);
    }

    /**
     * Registers and creates the states and models of the blocks of this mod
     */
    @Override
    protected void registerStatesAndModels()
    {
        for (RegistryObject<Block> blockRegistryObject : BMOBBlocks.BLOCKS.getEntries()) // Loop through all blocks in the registry
        {
            Block block = blockRegistryObject.get(); // Get the registry object as a block
            if (block instanceof AbstractForgeBlock<?> forgeBlock)
            {
                this.simpleBlockWithItem(forgeBlock, new ModelFile.UncheckedModelFile(this.modLoc("block/forge"))); // If the block is an AbstractForgeBlock, create a model for it using the Forge block model
            } else if (block instanceof SkullBlock || block instanceof WallSkullBlock)
            {
                this.headBlock(blockRegistryObject); // If the block is a SkullBlock or a WallSkullBlock, create a model for it using the skull block model
            } else
            {
                this.blockWithItem(blockRegistryObject); // Any other block is just normal
            }

        }
    }

    /**
     * Creates a simple block model with its item model
     * @param blockRegistryObject The block to create models for
     */
    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        this.simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    /**
     * Creates a skull block model for the block
     * @param blockRegistryObject The block to create the model for
     */
    private void headBlock(RegistryObject<Block> blockRegistryObject)
    {
        this.simpleBlock(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(this.mcLoc("block/skull")));

    }

}

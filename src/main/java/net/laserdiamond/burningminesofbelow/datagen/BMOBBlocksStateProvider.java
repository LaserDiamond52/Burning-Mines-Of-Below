package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BMOBBlocksStateProvider extends BlockStateProvider {

    public BMOBBlocksStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BurningMinesOfBelow.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegistryObject<Block> blockRegistryObject : BMOBBlocks.BLOCKS.getEntries())
        {
            Block block = blockRegistryObject.get();
            if (block instanceof AbstractForgeBlock forgeBlock)
            {
                this.simpleBlockWithItem(forgeBlock, new ModelFile.UncheckedModelFile(this.modLoc("block/forge")));
            } else if (block instanceof BMOBOreBlock || block instanceof BMOBMultiOreBlock || block instanceof RefinedOreBlock || block instanceof VanillaRefinedOreBlock || block instanceof BMOBBlock)
            {
                this.blockWithItem(blockRegistryObject);
            }
//            else if (block instanceof BMOBSkullBlock bmobSkullBlock)
//            {
//                this.simpleBlock(bmobSkullBlock, new ModelFile.UncheckedModelFile(this.mcLoc("block/skull")));
//            }
//            else if (block instanceof BMOBSkullBlockWall bmobSkullBlockWall)
//            {
//                this.simpleBlock(bmobSkullBlockWall, new ModelFile.UncheckedModelFile(this.mcLoc("block/skull")));
//            }
        }
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        this.simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

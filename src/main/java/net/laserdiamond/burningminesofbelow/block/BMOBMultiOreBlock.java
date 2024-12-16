package net.laserdiamond.burningminesofbelow.block;

import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates an ore block that can drop a higher quantity of the same ore drop</li>
 * <li>A {@link BMOBMultiOreBlock} is-a {@link BMOBOreBlock}</li>
 * @author Allen Malo
 */
public class BMOBMultiOreBlock extends BMOBOreBlock {

    private final int minCount;
    private final int maxCount;

    /**
     * Creates a new {@link BMOBMultiOreBlock}
     * @param pProperties The {@link net.minecraft.world.level.block.state.BlockBehaviour.Properties} of the {@link BMOBMultiOreBlock}
     * @param oreDrop The item {@link RegistryObject} to drop when successfully mined
     * @param minCount The minimum count of the item to drop when mined
     * @param maxCount The maximum count of the item to drop when mined
     * @param expRange The amount of experience orbs to drop when mined
     * @param tags A {@link List} of tags to apply to the block
     */
    public BMOBMultiOreBlock(Properties pProperties, RegistryObject<Item> oreDrop, int minCount, int maxCount, IntProvider expRange, List<TagKey<Block>> tags) {
        super(pProperties, oreDrop, expRange, tags);
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    /**
     *
     * @return The minimum count for the {@link BMOBOreBlock#getOreDrop()} to drop
     */
    public int getMinCount() {
        return minCount;
    }

    /**
     *
     * @return The maximum count for the {@link BMOBOreBlock#getOreDrop()} to drop
     */
    public int getMaxCount() {
        return maxCount;
    }
}

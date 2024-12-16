package net.laserdiamond.burningminesofbelow.util;

import net.minecraft.tags.TagKey;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help add {@link TagKey}s to {@link net.minecraft.world.item.Item}s, {@link net.minecraft.world.level.block.Block}s, and other assets of this mod</li>
 * @author Allen Malo
 * @param <T> The {@link TagKey} type
 */
public interface Taggable<T> {

    /**
     * Gets a {@link List} of all the {@link TagKey}s to be applied to the item upon data generation
     * @return A {@link List} of all the {@link TagKey}s given to the object
     */
    List<TagKey<T>> getTags();
}

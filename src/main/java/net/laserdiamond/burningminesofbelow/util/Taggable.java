package net.laserdiamond.burningminesofbelow.util;

import net.minecraft.tags.TagKey;

import java.util.List;

/**
 * Interface used to help add tags to item, blocks, and other features of this mod
 * @param <T> The {@link TagKey} type
 */
public interface Taggable<T> {

    /**
     * Gets a {@link List} of all the {@link TagKey}s to be applied to the item upon data generation
     * @return A {@link List} of all the {@link TagKey}s given to the object
     */
    List<TagKey<T>> getTags();
}

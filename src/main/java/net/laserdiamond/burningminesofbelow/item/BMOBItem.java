package net.laserdiamond.burningminesofbelow.item;

import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;

import java.util.List;

/**
 * A simple item of this mod
 */
public class BMOBItem extends Item implements Taggable<Item>, ForgeFuelItem {

    private final List<TagKey<Item>> tags;

    /**
     * Creates an array of the Attribute Modifier operations and values to add to an armor set across all armor pieces.
     * The Attribute Modifier values here will be applied across all pieces of the armor set.
     * @param operation The operation of the Attribute Modifier
     * @param value The value of the Attribute Modifier
     * @return An {@link ItemAttribute} array containing the Attribute Modifier values to apply to Attributes on an armor set.
     */
    public static ItemAttribute[] createConsistentArmorAttributes(AttributeModifier.Operation operation, double value)
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(operation, value),
                        new ItemAttribute(operation, value),
                        new ItemAttribute(operation, value),
                        new ItemAttribute(operation, value)
                };
    }

    /**
     * Creates an array of Attribute Modifier operations and values to add to an armor set across all armor pieces.
     * The Attribute Modifier values here are 0.
     * @return An {@link ItemAttribute} array containing the Attribute Modifier values to apply to Attributes on an armor set.
     */
    public static ItemAttribute[] createEmptyArmorAttributes()
    {
        return createConsistentArmorAttributes(AttributeModifier.Operation.ADDITION, 0);
    }

    public BMOBItem(Properties pProperties, List<TagKey<Item>> tags) {
        super(pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

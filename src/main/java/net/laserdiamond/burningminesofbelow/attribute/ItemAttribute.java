package net.laserdiamond.burningminesofbelow.attribute;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * Record class used to help add attribute modifiers to items
 * @param operation The {@link net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation} of the attribute modifier
 * @param value The value of the attribute modifier
 */
public record ItemAttribute(AttributeModifier.Operation operation, double value) {}

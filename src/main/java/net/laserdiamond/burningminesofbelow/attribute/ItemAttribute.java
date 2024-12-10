package net.laserdiamond.burningminesofbelow.attribute;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help add attributes modifiers to items by specifying the operation and value</li>
 * @param operation The {@link net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation} of the attribute modifier
 * @param value The value of the attribute modifier
 * @author Allen Malo
 */
public record ItemAttribute(AttributeModifier.Operation operation, double value) {}

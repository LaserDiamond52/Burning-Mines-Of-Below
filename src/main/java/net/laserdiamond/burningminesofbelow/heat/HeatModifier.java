package net.laserdiamond.burningminesofbelow.heat;

/**
 * Responsibilities:
 * <li>Helps determine the amount of heat to add/remove from the {@link net.minecraft.world.entity.player.Player}</li>
 * <li>Determines if the amount of heat to add/remove should bypass the safety threshold</li>
 * @param value The amount of heat to add/remove
 * @param canBypass True if the heat to add/remove should go outside the safe zone.
 */
public record HeatModifier(int value, boolean canBypass) {}

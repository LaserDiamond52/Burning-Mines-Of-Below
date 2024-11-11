package net.laserdiamond.burningminesofbelow.util.ability;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Class used to create and manage cooldowns for item abilities
 */
public final class AbilityCooldown {

    private final HashMap<UUID, Double> cooldown;

    AbilityCooldown()
    {
        this.cooldown = new HashMap<>();
    }

    /**
     * Sets the cooldown for the ability
     * @param player The player to set the cooldown for
     * @param ticks The amount of time in ticks for the cooldown duration
     */
    public void setCooldown(Player player, double ticks)
    {
        double cd = System.currentTimeMillis() + (ticks * 50);
        this.cooldown.put(player.getUUID(), cd);
    }

    /**
     * Checks if the player's cooldown is over
     * @param player the player to check the cooldown for
     * @return True if the player's cooldown is up, false otherwise
     */
    public boolean checkCooldown(Player player)
    {
        if (!this.cooldown.containsKey(player.getUUID()) || this.cooldown.get(player.getUUID()) <= System.currentTimeMillis())
        {
            return true;
        }
        return false;
    }

    /**
     * Gets the amount of time in seconds remaining on the player's cooldown
     * @param player The player to get the remaining cooldown duration of
     * @return The remaining duration of the cooldown in seconds
     */
    public double getCooldown(Player player)
    {
        double lastCooldown = this.cooldown.get(player.getUUID());
        return (lastCooldown - System.currentTimeMillis()) / 50;
    }
}

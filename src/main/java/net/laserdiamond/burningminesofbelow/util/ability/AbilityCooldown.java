package net.laserdiamond.burningminesofbelow.util.ability;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Represents a cooldown for an item ability</li>
 * <li>Maps the player's UUID to the time an ability was last used</li>
 * @author Allen Malo
 */
public final class AbilityCooldown {

    /**
     * The {@link AbilityCooldown} {@link HashMap}
     */
    private final HashMap<UUID, Double> cooldown; // An AbilityCooldown has-a HashMap

    /**
     * Creates a new {@link AbilityCooldown}
     */
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

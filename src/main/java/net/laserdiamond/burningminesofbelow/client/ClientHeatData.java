package net.laserdiamond.burningminesofbelow.client;

import net.minecraft.world.entity.player.Player;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Manage a player's heat on the client</li>
 * <li>This class is mostly static because it is client-side. As such, there is only ever one player in this scenario</li>
 * @author Allen Malo
 */
public class ClientHeatData {

    /**
     * The client's heat
     */
    private static int playerHeat;

    /**
     * The client's {@link Player}
     */
    private static Player player;

    /**
     * Sets the heat of the client
     * @param heat The amount of heat to assign the player
     */
    public static void setPlayerHeat(int heat)
    {
        ClientHeatData.playerHeat = heat;
    }

    /**
     * Sets the {@link Player} of the client
     * @param player the {@link Player} of the client
     */
    public static void setPlayer(Player player)
    {
        ClientHeatData.player = player;
    }

    /**
     * Gets the heat
     * @return The heat of the client
     */
    public static int getPlayerHeat()
    {
        return playerHeat;
    }

    /**
     * Gets the {@link Player} of the client
     * @return The {@link Player} of th client
     */
    public static Player getPlayer()
    {
        return player;
    }
}

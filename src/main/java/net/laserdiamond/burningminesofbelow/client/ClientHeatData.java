package net.laserdiamond.burningminesofbelow.client;

import net.minecraft.world.entity.player.Player;

public class ClientHeatData {

    private static int playerHeat;
    private static Player player;

    public static void setPlayerHeat(int heat)
    {
        ClientHeatData.playerHeat = heat;
    }

    public static void setPlayer(Player player)
    {
        ClientHeatData.player = player;
    }

    public static int getPlayerHeat()
    {
        return playerHeat;
    }

    public static Player getPlayer()
    {
        return player;
    }
}

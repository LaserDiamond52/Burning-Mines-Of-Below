package net.laserdiamond.burningminesofbelow.client;

public class ClientHeatData {

    private static int playerHeat;

    public static void setPlayerHeat(int heat)
    {
        ClientHeatData.playerHeat = heat;
    }

    public static int getPlayerHeat()
    {
        return playerHeat;
    }
}

package net.laserdiamond.burningminesofbelow.network.packet.heat;

import net.laserdiamond.burningminesofbelow.client.ClientHeatData;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.UUID;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>A packet sent from the server to the client for setting the player's heat</li>
 * <li>A {@link HeatS2CPacket} is-a {@link BMOBPacket}</li>
 * @author Allen Malo
 */
public class HeatS2CPacket extends BMOBPacket {

    /**
     * The new amount of heat to set to the {@link Player}
     */
    private final int heat;

    /**
     * The {@link Player} to set the heat to
     */
    private final Player player; // A HeatS2CPacket has-a Player

    /**
     * Creates a new {@link HeatS2CPacket}
     * @param heat The amount of heat to set to the {@link Player}
     * @param player The {@link Player} to set the heat to
     */
    public HeatS2CPacket(int heat, Player player) {
        this.heat = heat;
        this.player = player;
    }

    /**
     * Creates a new {@link HeatS2CPacket}
     * @param buf The {@link FriendlyByteBuf} to read from
     */
    public HeatS2CPacket(FriendlyByteBuf buf)
    {
        this.heat = buf.readInt();
        UUID playerUUID = buf.readUUID();
        this.player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerUUID);
    }

    /**
     * Writes the heat and player UUID to a {@link FriendlyByteBuf}
     * @param buf The {@link FriendlyByteBuf} to write to
     */
    @Override
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.heat);
        buf.writeUUID(this.player.getUUID());
    }

    /**
     * Sets the player's heat on the client
     * @param context {@link NetworkEvent.Context}
     */
    @Override
    public void packetWork(NetworkEvent.Context context)
    {
        ClientHeatData.setPlayerHeat(this.heat);
        ClientHeatData.setPlayer(this.player);
    }


}

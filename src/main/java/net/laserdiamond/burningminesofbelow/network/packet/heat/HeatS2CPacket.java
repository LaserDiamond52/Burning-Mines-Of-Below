package net.laserdiamond.burningminesofbelow.network.packet.heat;

import net.laserdiamond.burningminesofbelow.client.ClientHeatData;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.UUID;

public class HeatS2CPacket extends BMOBPacket {

    private final int heat;
    private final Player player;

    public HeatS2CPacket(int heat, Player player) {
        this.heat = heat;
        this.player = player;
    }

    public HeatS2CPacket(FriendlyByteBuf buf)
    {
        this.heat = buf.readInt();
        UUID playerUUID = buf.readUUID();
        this.player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerUUID);
    }

    @Override
    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.heat);
        buf.writeUUID(this.player.getUUID());
    }

    @Override
    public void packetWork(NetworkEvent.Context context)
    {
        ClientHeatData.setPlayerHeat(this.heat);
        ClientHeatData.setPlayer(this.player);
    }


}

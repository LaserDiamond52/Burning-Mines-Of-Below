package net.laserdiamond.burningminesofbelow.network.packet.heat;

import net.laserdiamond.burningminesofbelow.client.ClientHeatData;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class HeatS2CPacket extends BMOBPacket {

    private final int heat;

    public HeatS2CPacket(int heat) {
        this.heat = heat;
    }

    public HeatS2CPacket(FriendlyByteBuf buf)
    {
        this.heat = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(this.heat);
    }

    @Override
    public void packetWork(NetworkEvent.Context context)
    {
        ClientHeatData.setPlayerHeat(this.heat);
    }


}

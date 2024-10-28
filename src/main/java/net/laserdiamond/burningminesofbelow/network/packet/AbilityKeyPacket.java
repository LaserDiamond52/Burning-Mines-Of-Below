package net.laserdiamond.burningminesofbelow.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AbilityKeyPacket extends BMOBPacket {

    public AbilityKeyPacket(){}

    public AbilityKeyPacket(FriendlyByteBuf buf)
    {}


    @Override
    public void packetWork(NetworkEvent.Context context) {

    }
}

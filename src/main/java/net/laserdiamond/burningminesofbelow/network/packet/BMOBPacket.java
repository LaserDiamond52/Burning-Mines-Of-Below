package net.laserdiamond.burningminesofbelow.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 *
 */
public abstract class BMOBPacket {

    public BMOBPacket()
    {}

    public void toBytes(FriendlyByteBuf buf)
    {}

    /**
     * Packet logic. This is run on the packet's receiving end
     * @param context {@link NetworkEvent.Context}
     */
    public abstract void packetWork(NetworkEvent.Context context);

    public final boolean handle(Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> this.packetWork(context));
        return true;
    }
}

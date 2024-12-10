package net.laserdiamond.burningminesofbelow.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Represents a packet of this mod. Packets can contain information that can be sent from one end of the network to another</li>
 * @author Allen Malo
 */
public abstract class BMOBPacket {

    public BMOBPacket()
    {}

    public void toBytes(FriendlyByteBuf buf)
    {}

    /**
     * Packet logic. This is run on the packet's receiving end
     * <li>If the packet is traveling from client to server, this method runs on the server</li>
     * <li>If the packet is traveling from server to client, this method runs on the client</li>
     * @param context {@link NetworkEvent.Context}
     */
    public abstract void packetWork(NetworkEvent.Context context);

    /**
     * Handles the packet's logic
     * @param contextSupplier The {@link NetworkEvent.Context} supplier
     */
    public final void handle(Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> this.packetWork(context));
    }
}

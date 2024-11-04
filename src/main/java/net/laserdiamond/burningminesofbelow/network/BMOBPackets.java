package net.laserdiamond.burningminesofbelow.network;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.network.packet.AbilityKeyPacket;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.laserdiamond.burningminesofbelow.network.packet.heat.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public class BMOBPackets {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    /**
     * ID of the packet. Packets must have a unique ID. As such, whenever this method is called, the packet ID is incremented by 1 from the previous packet ID
     * @return The ID of the packet (incremented by 1 from the previous call of this method)
     */
    private static int id()
    {
        return packetId++;
    }

    /**
     * Registers all the packets of this mod
     */
    public static void registerPackets()
    {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(
                new ResourceLocation(BurningMinesOfBelow.MODID, "main"))
                .serverAcceptedVersions(s -> true)
                .clientAcceptedVersions(s -> true)
                .networkProtocolVersion(() -> "1.0")
                .simpleChannel();

        // Packet to send to server when player presses ability key and has an ability item in their hand
        registerPacket(AbilityKeyPacket.class, AbilityKeyPacket::new, NetworkDirection.PLAY_TO_SERVER);

        // Since a player heating up and freezing can happen at different intervals, we'll need two different packets for them
        registerPacket(HeatC2SPacket.class, HeatC2SPacket::new, NetworkDirection.PLAY_TO_SERVER); // This packet can add heat
        registerPacket(FreezeC2SPacket.class, FreezeC2SPacket::new, NetworkDirection.PLAY_TO_SERVER); // This packet can remove heat (freeze)

        // Packet for syncing the client heat with the server heat
        // Only one is needed, since we are just setting a value
        registerPacket(HeatS2CPacket.class, HeatS2CPacket::new, NetworkDirection.PLAY_TO_CLIENT);
    }

    /**
     * Registers a packet
     * @param packetClazz The packet class
     * @param decoder The packet's decoder. A {@link Function} that has a {@link FriendlyByteBuf} input, and returns the packet object
     * @param direction The direction that the packet will be traveling from
     * @param <P> The {@link BMOBPacket} type
     */
    public static <P extends BMOBPacket> void registerPacket(Class<P> packetClazz, Function<FriendlyByteBuf, P> decoder, NetworkDirection direction)
    {
        INSTANCE.messageBuilder(packetClazz, id(), direction)
                .decoder(decoder)
                .encoder(P::toBytes)
                .consumerMainThread(P::handle)
                .add();
    }

    /**
     * Sends an object to the server
     * @param message The object to send to the server
     * @param <MSG> The object type
     */
    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    /**
     * Sends an object to the player
     * @param message The object to send to the player
     * @param player The player receiving the object
     * @param <MSG> The object type
     */
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    /**
     * Sends an object to all clients
     * @param message The object to send to the clients
     * @param <MSG> The object type
     */
    public static <MSG> void sendToAllClients(MSG message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

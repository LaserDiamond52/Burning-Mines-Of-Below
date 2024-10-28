package net.laserdiamond.burningminesofbelow.network;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.network.packet.AbilityKeyPacket;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.laserdiamond.burningminesofbelow.network.packet.heat.FreezeC2SPacket;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatC2SPacket;
import net.laserdiamond.burningminesofbelow.network.packet.heat.HeatS2CPacket;
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

    public static void registerPackets()
    {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(
                new ResourceLocation(BurningMinesOfBelow.MODID, "main"))
                .serverAcceptedVersions(s -> true)
                .clientAcceptedVersions(s -> true)
                .networkProtocolVersion(() -> "1.0")
                .simpleChannel();

        registerPacket(AbilityKeyPacket.class, AbilityKeyPacket::new, NetworkDirection.PLAY_TO_SERVER);

        // Since a player heating up and freezing can happen at different intervals, we'll need two different packets for them
        registerPacket(HeatC2SPacket.class, HeatC2SPacket::new, NetworkDirection.PLAY_TO_SERVER); // This packet can add heat
        registerPacket(FreezeC2SPacket.class, FreezeC2SPacket::new, NetworkDirection.PLAY_TO_SERVER); // This packet can remove heat (freeze)

        // Packet for syncing the client heat with the server heat
        // Only one is needed, since we are just setting a value
        registerPacket(HeatS2CPacket.class, HeatS2CPacket::new, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static <P extends BMOBPacket> void registerPacket(Class<P> packetClazz, Function<FriendlyByteBuf, P> decoder, NetworkDirection direction)
    {
        INSTANCE.messageBuilder(packetClazz, id(), direction)
                .decoder(decoder)
                .encoder(P::toBytes)
                .consumerMainThread(P::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player)
    {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToAllClients(MSG message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}

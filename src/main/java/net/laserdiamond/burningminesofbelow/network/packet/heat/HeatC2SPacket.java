package net.laserdiamond.burningminesofbelow.network.packet.heat;

import net.laserdiamond.burningminesofbelow.heat.PlayerHeat;
import net.laserdiamond.burningminesofbelow.heat.PlayerHeatProvider;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.BMOBPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.network.NetworkEvent;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>A packet sent from the client to the server for adding to the player</li>
 * <li>A {@link HeatC2SPacket} is-a {@link BMOBPacket}</li>
 * @author Allen Malo
 */
public class HeatC2SPacket extends BMOBPacket {

    /**
     * The amount of heat to add
     */
    private int heat;

    /**
     * Creates a new {@link HeatC2SPacket}
     * @param heat The amount of heat to add
     */
    public HeatC2SPacket(int heat)
    {
        this.heat = heat;
    }

    /**
     * Creates a new {@link HeatC2SPacket}
     * @param buf The {@link FriendlyByteBuf} to read from
     */
    public HeatC2SPacket(FriendlyByteBuf buf)
    {}

    @Override
    public void packetWork(NetworkEvent.Context context) {
        // ON THE SERVER
        final ServerPlayer player = context.getSender();
        if (player == null)
        {
            return; // Player is null. Do not continue
        }

        PlayerHeat.addHeat(player, this.heat);
    }
}

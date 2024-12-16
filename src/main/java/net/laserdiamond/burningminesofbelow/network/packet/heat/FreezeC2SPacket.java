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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Function;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>A packet sent from the client to the server for removing heat from the player</li>
 * <li>A {@link FreezeC2SPacket} is-a {@link BMOBPacket}</li>
 * @author Allen Malo
 */
public class FreezeC2SPacket extends BMOBPacket {

    /**
     * The amount of heat to remove
     */
    private int heat;

    /**
     * Creates a new {@link FreezeC2SPacket}
     * @param heat The amount of heat to remove
     */
    public FreezeC2SPacket(int heat)
    {
        this.heat = heat;
    }

    /**
     * Creates a new {@link FreezeC2SPacket}
     * @param buf The {@link FriendlyByteBuf} to read from
     */
    public FreezeC2SPacket(FriendlyByteBuf buf)
    {}

    @Override
    public void packetWork(NetworkEvent.Context context) {

        final ServerPlayer player = context.getSender();
        if (player == null)
        {
            return; // Player is null. Do not continue
        }

        PlayerHeat.removeHeat(player, this.heat);
    }
}

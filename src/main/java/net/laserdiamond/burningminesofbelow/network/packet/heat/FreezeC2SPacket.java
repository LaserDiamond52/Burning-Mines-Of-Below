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

public class FreezeC2SPacket extends BMOBPacket {

    private int heat;

    public FreezeC2SPacket(int heat)
    {
        this.heat = heat;
    }

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

//        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat -> {
//
//
//            playerHeat.removeHeat(this.heat);
//            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
//        });
    }
}

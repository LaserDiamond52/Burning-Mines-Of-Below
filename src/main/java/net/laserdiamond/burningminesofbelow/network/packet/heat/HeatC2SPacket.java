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

public class HeatC2SPacket extends BMOBPacket {

    private int heat;

    public HeatC2SPacket(int heat)
    {
        this.heat = heat;
    }

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

//        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat -> {
//
//            playerHeat.addHeat(this.heat);
//            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
//        });
    }
}

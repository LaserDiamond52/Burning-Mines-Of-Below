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

public class FreezeC2SPacket extends BMOBPacket {

    public FreezeC2SPacket()
    {}

    public FreezeC2SPacket(FriendlyByteBuf buf)
    {}

    @Override
    public void packetWork(NetworkEvent.Context context) {

        final ServerPlayer player = context.getSender();
        if (player == null)
        {
            return; // Player is null. Do not continue
        }

        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat -> {

            if (player.isCreative() || player.isSpectator())
            {
                BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
                return; // Player should not be affected by freezing if they are in creative/spectator mode
            }

            int coolPoints = 0;
            boolean canFreeze = false;

            final BlockPos playerBlockPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
            final ServerLevel level = player.serverLevel();

            if (player.position().y >= PlayerHeat.Y_LEVEL_HEAT)
            {
                if (playerHeat.getHeat() > PlayerHeat.SAFE_HEAT)
                {
                    coolPoints++;
                }
                if (player.position().y >= PlayerHeat.Y_LEVEL_FREEZE)
                {
                    coolPoints++;
                    canFreeze = true;
                }
            }

            if (player.isInWater())
            {
                coolPoints++;
            }

            if (player.isInPowderSnow)
            {
                coolPoints++;
                canFreeze = true;
            }

            for (TagKey<Biome> biomeTagKey : PlayerHeat.coldBiomes())
            {
                if (level.getBiome(playerBlockPos).containsTag(biomeTagKey))
                {
                    coolPoints++;
                    canFreeze = true;
                    break;
                }
            }

            // TODO: Player should lose heat if they are in the cocytus tundra

            playerHeat.removeHeat(coolPoints, canFreeze);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
        });
    }
}

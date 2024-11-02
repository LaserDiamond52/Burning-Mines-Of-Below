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

    public HeatC2SPacket()
    {

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

        player.getCapability(PlayerHeatProvider.PLAYER_HEAT).ifPresent(playerHeat -> {

            if (player.isCreative() || player.isSpectator())
            {
                BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
                return; // Player should not be affected by heat/freezing if they are in creative/spectator mode
            }

            int heatPoints = 0;
            boolean canOverheat = false;

            final BlockPos playerBlockPos = new BlockPos((int) player.position().x, (int) player.position().y, (int) player.position().z);
            final ServerLevel level = player.serverLevel();

            if (player.position().y <= PlayerHeat.Y_LEVEL_FREEZE) // Check if player is within a valid y-level to gain heat
            {
                if (playerHeat.getHeat() < PlayerHeat.SAFE_HEAT) // Accumulate heat if the player is too cold
                {
                    heatPoints++;
                }
                if (player.position().y <= PlayerHeat.Y_LEVEL_HEAT) // Accumulate heat if the player is under y-level
                {
                    heatPoints++;
                    canOverheat = true; // Player can overheat from this
                }
            }

            if (player.isOnFire() || player.isInLava())
            {
                if (!player.fireImmune())
                {
                    heatPoints++;
                    canOverheat = true;
                }
            }

            for (TagKey<Biome> biomeTagKey : PlayerHeat.hotBiomes())
            {
                if (level.getBiome(playerBlockPos).containsTag(biomeTagKey))
                {
                    heatPoints++;
                    break;
                }
            }

            if (level.dimension() == Level.NETHER)
            {
                // TODO: Player shouldn't gain heat if in the Cocytus Tundra
                heatPoints++;
                canOverheat = true;
            }

            playerHeat.addHeat(heatPoints, canOverheat);
            BMOBPackets.sendToPlayer(new HeatS2CPacket(playerHeat.getHeat(), player), player);
        });
    }
}

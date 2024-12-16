package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings;
import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.network.BMOBPackets;
import net.laserdiamond.burningminesofbelow.network.packet.AbilityKeyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers and listens for client events on the Forge bus of this mod</li>
 * <li>Methods with the {@link SubscribeEvent} annotation are listening for events</li>
 * @author Allen Malo
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents
{

    /**
     * Called when a key input is detected on the client
     * @param event The {@link InputEvent.Key} to listen for
     */
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event)
    {
        final Minecraft minecraft = Minecraft.getInstance();
        final LocalPlayer localPlayer = minecraft.player;

        if (BMOBKeyBindings.INSTANCE.abilityKey.consumeClick())
        {
            if (localPlayer != null)
            {
                if (localPlayer.getMainHandItem().getItem() instanceof AbilityItem abilityItem)
                {
                    abilityItem.onKeyPressClient(event);
                    BMOBPackets.sendToServer(new AbilityKeyPacket());
                }
            }
        }
    }
}

package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEvents
{

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event)
    {

    }
}

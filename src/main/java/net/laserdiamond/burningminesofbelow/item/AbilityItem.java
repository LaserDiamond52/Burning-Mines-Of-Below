package net.laserdiamond.burningminesofbelow.item;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

/**
 * Interface used to give items abilities that are activated by the ability key
 */
public interface AbilityItem {

    /**
     * Runs on the server when the ability key is pressed while holding this item
     * @param event The {@link NetworkEvent.Context}
     */
    void onKeyPressServer(NetworkEvent.Context event);

    /**
     * Runs on the client when the ability key is pressed while holding this item
     * @param event The {@link NetworkEvent.Context}
     */
    void onKeyPressClient(InputEvent.Key event);
}

package net.laserdiamond.burningminesofbelow.network.packet;

import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.util.ability.AbilityCooldown;
import net.laserdiamond.burningminesofbelow.util.ability.CooldownRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.text.DecimalFormat;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>A packet send from the client to the server when the {@link net.laserdiamond.burningminesofbelow.client.BMOBKeyBindings#abilityKey} is pressed</li>
 * <li>A {@link AbilityKeyPacket} is-a {@link BMOBPacket}</li>
 * @author Allen Malo
 */
public class AbilityKeyPacket extends BMOBPacket {

    /**
     * Creates a new {@link AbilityKeyPacket}
     */
    public AbilityKeyPacket(){}

    /**
     * Creates a new {@link AbilityKeyPacket}
     * @param buf The {@link FriendlyByteBuf} to read from
     */
    public AbilityKeyPacket(FriendlyByteBuf buf)
    {}


    @Override
    public void packetWork(NetworkEvent.Context context) {
        final ServerPlayer player = context.getSender();

        if (player != null)
        {
            if (player.getMainHandItem().getItem() instanceof AbilityItem abilityItem) // Check if player is holding ability item
            {
                final AbilityCooldown abilityItemCooldown = CooldownRegistry.getAbilityCooldown(abilityItem);

                if (abilityItemCooldown.checkCooldown(player)) // Check if cooldown is up
                {
                    // Cooldown is up, cast ability
                    player.sendSystemMessage(Component.literal("Used ").append(abilityItem.abilityName()));
                    abilityItem.onKeyPressServer(context);
                    abilityItemCooldown.setCooldown(player, abilityItem.cooldown());
                } else // Cooldown is not up. Notify player
                {
                    double seconds = abilityItemCooldown.getCooldown(player) / 20;
                    player.sendSystemMessage(Component.literal("Ability on cooldown for " + new DecimalFormat("0.00").format(seconds) + " seconds").withStyle(ChatFormatting.RED));
                }
            }
        }
    }
}

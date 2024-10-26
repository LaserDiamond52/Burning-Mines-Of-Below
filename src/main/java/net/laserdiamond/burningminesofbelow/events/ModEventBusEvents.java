package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
    }

    @SubscribeEvent
    public static void entityAttributeModification(EntityAttributeModificationEvent event)
    {
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), 30);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), 45);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get(), 0);
    }

//    @SubscribeEvent
//    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event)
//    {
//
//    }

//    @SubscribeEvent
//    public static void onPlayerCloned(PlayerEvent.Clone event)
//    {
//
//    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event)
    {

    }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
//    {
//
//    }

//    @SubscribeEvent
//    public static void onPlayerJoinWorld(EntityJoinLevelEvent event)
//    {
//
//    }
}

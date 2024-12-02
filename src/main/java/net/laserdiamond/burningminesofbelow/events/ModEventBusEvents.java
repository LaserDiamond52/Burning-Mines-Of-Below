package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        // TODO: Creation of Attributes for entities go here
        event.put(BMOBEntities.MAGNITE_BLAZE.get(), MagniteBlazeEntity.createAttributes().build());
        event.put(BMOBEntities.KING_INFERNIUS.get(), KingInferniusEntity.createAttributes().build());
        event.put(BMOBEntities.FROZEN_SOUL.get(), FrozenSoulEntity.createAttributes().build());
        event.put(BMOBEntities.FREEZING_REAPER.get(), FreezingReaperEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void entityAttributeModification(EntityAttributeModificationEvent event)
    {
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), 600);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), 900);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get(), 0);
    }
}

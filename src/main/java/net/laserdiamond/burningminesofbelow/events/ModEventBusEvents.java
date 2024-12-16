package net.laserdiamond.burningminesofbelow.events;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.entity.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.file.mob.AbstractMobConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Registers and listens for server events on the mod bus of this mod</li>
 * <li>Methods with the {@link SubscribeEvent} annotation are listening for events</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = BurningMinesOfBelow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{

    /**
     * Called when it is time to create attributes for entities
     * @param event The {@link EntityAttributeCreationEvent} to listen for
     */
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {
        registerEntityAttribute(event, BMOBEntities.MAGNITE_BLAZE);
        registerEntityAttribute(event, BMOBEntities.KING_INFERNIUS);
        registerEntityAttribute(event, BMOBEntities.FROZEN_SOUL);
        registerEntityAttribute(event, BMOBEntities.FREEZING_REAPER);
    }

    /**
     * Registers all {@link net.minecraft.world.entity.ai.attributes.Attribute}s to the entity, as specified by the {@link AbstractMobConfig} registered to the entity.
     * @param event The {@link EntityAttributeCreationEvent}
     * @param entityTypeRegistryObject The {@link EntityType} {@link RegistryObject}
     * @param <L> The {@link LivingEntity} type of the {@link EntityType}
     */
    private static <L extends LivingEntity> void registerEntityAttribute(EntityAttributeCreationEvent event, RegistryObject<EntityType<L>> entityTypeRegistryObject)
    {
        event.put(entityTypeRegistryObject.get(), createAttributes(entityTypeRegistryObject).build());
    }

    /**
     * Creates the set of {@link net.minecraft.world.entity.ai.attributes.Attribute}s to be applied to the {@link LivingEntity}
     * @param entityTypeRegistryObject The {@link EntityType} {@link RegistryObject} to create attributes for. An {@link AbstractMobConfig} must be registered to it
     * @return The {@link AttributeSupplier.Builder} for the entity's attributes
     * @param <L> The {@link LivingEntity} type of the {@link EntityType}
     */
    private static <L extends LivingEntity> AttributeSupplier.Builder createAttributes(RegistryObject<EntityType<L>> entityTypeRegistryObject)
    {
        AbstractMobConfig config = MobConfigRegistry.getRegistryMap().get(entityTypeRegistryObject.getId());
        return Mob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, config.getAttributeValue(Attributes.MAX_HEALTH))
                .add(Attributes.MOVEMENT_SPEED, config.getAttributeValue(Attributes.MOVEMENT_SPEED))
                .add(Attributes.ATTACK_DAMAGE, config.getAttributeValue(Attributes.ATTACK_DAMAGE))
                .add(Attributes.ATTACK_KNOCKBACK, config.getAttributeValue(Attributes.ATTACK_KNOCKBACK))
                .add(Attributes.FOLLOW_RANGE, config.getAttributeValue(Attributes.FOLLOW_RANGE))
                .add(Attributes.ARMOR, config.getAttributeValue(Attributes.ARMOR));
    }

    /**
     * Called when it is time to modify the attributes of an entity
     * @param event The {@link EntityAttributeModificationEvent} to listen for
     */
    @SubscribeEvent
    public static void entityAttributeModification(EntityAttributeModificationEvent event)
    {
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), 600);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), 900);
        event.add(EntityType.PLAYER, BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get(), 0);
    }
}

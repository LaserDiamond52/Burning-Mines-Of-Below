package net.laserdiamond.burningminesofbelow.entity;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cyrobolt;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class BMOBEntities {

    /**
     * Entity registry of this mod
     */
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BurningMinesOfBelow.MODID);

    /**
     * The Cyrobolt projectile
     */
    public static RegistryObject<EntityType<Cyrobolt>> CYROBOLT = registerEntity("Cyrobolt", "cyrobolt",
            () -> EntityType.Builder.<Cyrobolt>of(Cyrobolt::new, MobCategory.MISC).sized(0.5F, 0.5F).build("cyrobolt"));

    // TODO: Add other entities

    public static RegistryObject<EntityType<MagniteBlazeEntity>> MAGNITE_BLAZE = registerEntity("Magnite Blaze", "magnite_blaze",
            () -> EntityType.Builder.of(MagniteBlazeEntity::new, MobCategory.MONSTER).sized(2.0F, 2.0F).build("magnite_blaze"));

    /**
     * Registers a new entity under the mod's entity registry
     * @param name The name of the entity in-game
     * @param localName The local name of the entity
     * @param entityTypeSupplier The entity supplier
     * @return A {@link RegistryObject} representing the newly created {@link Entity}
     * @param <E> The {@link Entity} class
     */
    @SuppressWarnings("unchecked")
    public static <E extends Entity> RegistryObject<EntityType<E>> registerEntity(String name, String localName, Supplier<EntityType<E>> entityTypeSupplier)
    {
        RegistryObject<EntityType<E>> entityTypeRegistryObject = ENTITIES.register(localName, entityTypeSupplier);
        LanguageRegistry.instance(Language.EN_US).getEntityNameRegistry().addEntry(name, entityTypeRegistryObject);
        return entityTypeRegistryObject;
    }

    /**
     * Registers all entities under the entity registry of this mod
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerEntities(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}

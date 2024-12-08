package net.laserdiamond.burningminesofbelow.entity;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FrozenSoulEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cyrobolt;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.MobConfigRegistry;
import net.laserdiamond.burningminesofbelow.util.file.mob.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
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

    /**
     * Magnite Blaze mob
     */
    public static RegistryObject<EntityType<MagniteBlazeEntity>> MAGNITE_BLAZE = registerEntity("Magnite Blaze", "magnite_blaze",
            () -> EntityType.Builder.of(MagniteBlazeEntity::new, MobCategory.MONSTER).sized(0.6F, 1.8F).build("magnite_blaze"), new MagniteBlazeConfig());

    /**
     * King Infernius boss mob
     */
    public static RegistryObject<EntityType<KingInferniusEntity>> KING_INFERNIUS = registerEntity("King Infernius", "king_infernius",
            () -> EntityType.Builder.of(KingInferniusEntity::new, MobCategory.MONSTER).sized(1.25F, 3.25F).build("king_infernius"), new KingInferniusConfig());

    /**
     * Frozen Soul mob
     */
    public static RegistryObject<EntityType<FrozenSoulEntity>> FROZEN_SOUL = registerEntity("Frozen Soul", "frozen_soul",
            () -> EntityType.Builder.of(FrozenSoulEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build("frozen_soul"), new FrozenSoulConfig());

    /**
     * Freezing Reaper of the Damned boss mob
     */
    public static RegistryObject<EntityType<FreezingReaperEntity>> FREEZING_REAPER = BMOBEntities.registerEntity("Freezing Reaper of the Damned", "freezing_reaper",
            () -> EntityType.Builder.of(FreezingReaperEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build("freezing_reaper"), new FreezingReaperConfig());

    /**
     * Registers a new entity under the mod's entity registry
     * @param name The name of the entity in-game
     * @param localName The local name of the entity
     * @param entityTypeSupplier The entity supplier
     * @return A {@link RegistryObject} representing the newly created {@link Entity}
     * @param <E> The {@link Entity} class
     */
    public static <E extends Entity> RegistryObject<EntityType<E>> registerEntity(String name, String localName, Supplier<EntityType<E>> entityTypeSupplier)
    {
        RegistryObject<EntityType<E>> entityTypeRegistryObject = ENTITIES.register(localName, entityTypeSupplier);
        LanguageRegistry.instance(Language.EN_US).getEntityNameRegistry().addEntry(name, entityTypeRegistryObject.getId());
        return entityTypeRegistryObject;
    }

    /**
     * Registers a new entity under the mod's entity registry. This register method should be used for mobs that will have a Json config file
     * @param name The name of the mob in-game
     * @param localName The local name of the mob
     * @param entityTypeSupplier The entity supplier
     * @param mobConfig The config for the mob
     * @return A {@link RegistryObject} representing the newly created {@link net.minecraft.world.entity.Mob}
     * @param <M> The {@link Mob} class
     * @param <MC> The {@link AbstractMobConfig} class
     */
    public static <M extends Mob, MC extends AbstractMobConfig> RegistryObject<EntityType<M>> registerEntity(String name, String localName, Supplier<EntityType<M>> entityTypeSupplier, MC mobConfig)
    {
        RegistryObject<EntityType<M>> entityTypeRegistryObject = registerEntity(name, localName, entityTypeSupplier);
        MobConfigRegistry.register(entityTypeRegistryObject.getId(), mobConfig);
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

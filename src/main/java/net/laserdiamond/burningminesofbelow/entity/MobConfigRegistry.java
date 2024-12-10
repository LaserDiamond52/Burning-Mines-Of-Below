package net.laserdiamond.burningminesofbelow.entity;

import net.laserdiamond.burningminesofbelow.util.file.mob.AbstractMobConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

import java.util.HashMap;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Map the {@link ResourceLocation}s of a mob to a Mob Config that can help control their stats from a Json file</li>
 * <li>{@link ResourceLocation}s are used instead of the {@link EntityType} because attempting to access the {@link EntityType} before the mod entity registry registers the entities throws an exception.</li>
 * @author Allen Malo
 * @param <MC> The {@link AbstractMobConfig} type
 */
public class MobConfigRegistry<MC extends AbstractMobConfig> {

    /**
     * The {@link HashMap} that stores all the {@link EntityType}s and their configs
     */
    private static final HashMap<ResourceLocation, AbstractMobConfig> MAP = new HashMap<>();

    /**
     * Registers a new entry into the {@link HashMap}
     * @param mobResourceLoc The {@link ResourceLocation} of the mob
     * @param mobConfig The config being registered to the mob.
     * @throws IllegalArgumentException If the {@link ResourceLocation} of the entity already has a config mapped to it
     */
    private MobConfigRegistry(ResourceLocation mobResourceLoc, MC mobConfig) throws IllegalArgumentException
    {
        if (MAP.get(mobResourceLoc) != null || MAP.containsKey(mobResourceLoc))
        {
            throw new IllegalArgumentException("Config " + mobConfig.toString() + " has already been assigned to mob " + mobResourceLoc);
        }
        MAP.put(mobResourceLoc, mobConfig);
    }

    /**
     * Registers a new entry into the {@link MobConfigRegistry}
     * @param mobResourceLoc The {@link ResourceLocation} of the mob to register
     * @param mobConfig The {@link AbstractMobConfig} to register to the entity
     * @param <M> The {@link Mob} type
     * @param <MC> The {@link AbstractMobConfig} type
     * @throws IllegalArgumentException If the {@link ResourceLocation} of the entity already has a config mapped to it
     */
    @SuppressWarnings("all")
    public static <MC extends AbstractMobConfig> void register(ResourceLocation mobResourceLoc, MC mobConfig) throws IllegalArgumentException
    {
        new MobConfigRegistry<>(mobResourceLoc, mobConfig);
    }

    /**
     * Gets a copy of the registry map.
     * @return A copy of the {@link HashMap} that stores the entries
     */
    public static HashMap<ResourceLocation, AbstractMobConfig> getRegistryMap()
    {
        return new HashMap<>(MAP);
    }
}

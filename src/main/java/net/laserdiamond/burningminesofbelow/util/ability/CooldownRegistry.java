package net.laserdiamond.burningminesofbelow.util.ability;

import net.laserdiamond.burningminesofbelow.item.AbilityItem;

import java.util.HashMap;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Maps all {@link AbilityItem}s to an {@link AbilityCooldown}</li>
 * @author Allen Malo
 */
public final class CooldownRegistry {

    private static final HashMap<AbilityItem, AbilityCooldown> COOLDOWN_REGISTRY_MAP = new HashMap<>();

    /**
     * Creates a new {@link CooldownRegistry}.
     * Declared private because only static member of this class are used.
     */
    private CooldownRegistry() {}

    /**
     * Gets the cooldown instance for the {@link AbilityItem}. If no {@link AbilityCooldown} exists for the {@link AbilityItem}, then a new {@link AbilityCooldown} is made and assigned.
     * @param abilityItem The ability item to get the cooldown for
     * @return The {@link AbilityCooldown} for the item
     */
    public static AbilityCooldown getAbilityCooldown(AbilityItem abilityItem)
    {
        if (COOLDOWN_REGISTRY_MAP.get(abilityItem) == null || !COOLDOWN_REGISTRY_MAP.containsKey(abilityItem))
        {
            COOLDOWN_REGISTRY_MAP.put(abilityItem, new AbilityCooldown());
        }
        return COOLDOWN_REGISTRY_MAP.get(abilityItem);
    }
}

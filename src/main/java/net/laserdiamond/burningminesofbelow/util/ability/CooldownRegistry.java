package net.laserdiamond.burningminesofbelow.util.ability;

import net.laserdiamond.burningminesofbelow.item.AbilityItem;

import java.util.HashMap;

/**
 * Contains all the cooldowns for ability items
 */
public final class CooldownRegistry {

    private static final HashMap<AbilityItem, AbilityCooldown> COOLDOWN_REGISTRY_MAP = new HashMap<>();

    private CooldownRegistry() {}

    /**
     * Gets the cooldown instance for the {@link AbilityItem}
     * @param abilityItem The ability item to get the cooldown for
     * @return The {@link AbilityCooldown} for the item
     */
    public static AbilityCooldown instance(AbilityItem abilityItem)
    {
        if (COOLDOWN_REGISTRY_MAP.get(abilityItem) == null || !COOLDOWN_REGISTRY_MAP.containsKey(abilityItem))
        {
            COOLDOWN_REGISTRY_MAP.put(abilityItem, new AbilityCooldown());
        }
        return COOLDOWN_REGISTRY_MAP.get(abilityItem);
    }
}

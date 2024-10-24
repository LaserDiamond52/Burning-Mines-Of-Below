package net.laserdiamond.burningminesofbelow.attribute;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BMOBAttributes {

    /**
     * Attribute registry of this mod
     */
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, BurningMinesOfBelow.MODID);

    /**
     * The player's heat interval value. Controls how long it takes for the player to heat up
     */
    public static final RegistryObject<Attribute> PLAYER_HEAT_INTERVAL = registerAttribute("Heat Interval", "heat_interval", () -> new RangedAttribute(descriptionId("heat_interval"), 30, 0, 1024).setSyncable(true));

    /**
     * The player's freeze interval value. Controls how long it takes for the player to freeze
     */
    public static final RegistryObject<Attribute> PLAYER_FREEZE_INTERVAL = registerAttribute("Freeze Interval", "freeze_interval", () -> new RangedAttribute(descriptionId("freeze_interval"), 45, 0, 1024).setSyncable(true));

    /**
     * The player's refined mineral chance value. Determines the chance of the player dropping a refined mineral from mining refined ores
     */
    public static final RegistryObject<Attribute> PLAYER_REFINED_MINERAL_CHANCE = registerAttribute("Refined Mineral Chance", "refined_mineral_chance", () -> new RangedAttribute(descriptionId("refined_mineral_chance"), 0, 0, 1024).setSyncable(true));

    /**
     * Registers a new {@link Attribute} under this mod's Attribute registry
     * @param name The name of the attribute in-game
     * @param localName The local name of the attribute
     * @param attributeSupplier The {@link Supplier} for the attribute
     * @return A {@link RegistryObject} representing the newly created {@link Attribute}
     */
    public static RegistryObject<Attribute> registerAttribute(String name, String localName, Supplier<Attribute> attributeSupplier)
    {
        RegistryObject<Attribute> attributeRegistryObject = ATTRIBUTES.register("generic." + localName, attributeSupplier);
        LanguageRegistry.instance(Language.EN_US).getAttributeNameRegistry().addEntry(name, attributeRegistryObject);
        return attributeRegistryObject;
    }

    /**
     * Helper method for creating the description id for attributes
     * @param attributeName The local name of the attribute
     * @return The complete description id of the attribute
     */
    private static String descriptionId(String attributeName)
    {
        return "attribute.name.generic." + attributeName;
    }

    /**
     * Registers all the {@link Attribute}s under this mod's attribute registry
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerAttributes(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }
}

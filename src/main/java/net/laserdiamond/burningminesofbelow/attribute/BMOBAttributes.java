package net.laserdiamond.burningminesofbelow.attribute;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.NameRegistry;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BMOBAttributes {

    public static final NameRegistry<Attribute> ATTRIBUTE_NAME_REGISTRY = new NameRegistry<>();
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, BurningMinesOfBelow.MODID);

    /**
     * Registers a new {@link Attribute} under this mod's Attribute registry
     * @param name The name of the attribute in-game
     * @param pathName The path name of the attribute
     * @param attributeSupplier The {@link Supplier} for the attribute
     * @return A {@link RegistryObject} representing the newly created {@link Attribute}
     */
    public static RegistryObject<Attribute> registerAttribute(String name, String pathName, Supplier<Attribute> attributeSupplier)
    {
        RegistryObject<Attribute> attributeRegistryObject = ATTRIBUTES.register(pathName, attributeSupplier);
        ATTRIBUTE_NAME_REGISTRY.addEntry(name, attributeRegistryObject);
        return attributeRegistryObject;
    }

    public static void registerAttributes(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }
}

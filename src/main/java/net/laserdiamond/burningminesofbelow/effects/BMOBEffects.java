package net.laserdiamond.burningminesofbelow.effects;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BMOBEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, BurningMinesOfBelow.MODID);

    public static final RegistryObject<MobEffect> HEAT_EXHAUSTION = registerEffect("Heat Exhaustion", "heat_exhaustion",
            () -> new HeatExhaustionEffect(MobEffectCategory.HARMFUL, 16072975)
                    .addAttributeModifier(BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), "d9430dee-5ab7-42f4-81d3-40b8a5b00ac5", 100, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "67967c98-72da-4ea7-ba06-da9232e57575", -0.10000000149011612, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "d5173698-edec-49af-9101-3488dbcd5868", -0.15000000596046448, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> HYPOTHERMIA = registerEffect("Hypothermia", "hypothermia",
            () -> new HypothermiaEffect(MobEffectCategory.HARMFUL, 372727)
                    .addAttributeModifier(BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), "d42fb526-c57a-4978-b5f7-47c2c6171b59", 100, AttributeModifier.Operation.ADDITION)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, "67967c98-72da-4ea7-ba06-da9232e57575", -0.10000000149011612, AttributeModifier.Operation.MULTIPLY_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "d5173698-edec-49af-9101-3488dbcd5868", -0.15000000596046448, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static RegistryObject<MobEffect> registerEffect(String name, String localName, Supplier<MobEffect> mobEffectSupplier)
    {
        RegistryObject<MobEffect> effectRegistryObject = MOB_EFFECTS.register(localName, mobEffectSupplier);
        LanguageRegistry.instance(Language.EN_US).getMobEffectNameRegistry().addEntry(name, effectRegistryObject);
        return effectRegistryObject;
    }

    public static void registerEffects(IEventBus eventBus)
    {
        MOB_EFFECTS.register(eventBus);
    }
}

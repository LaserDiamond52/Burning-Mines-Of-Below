package net.laserdiamond.burningminesofbelow.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates the functionality of the {@link HeatExhaustionEffect}</li>
 * <li>A {@link HeatExhaustionEffect} is-a {@link MobEffect}</li>
 * @author Allen Malo
 */
public class HeatExhaustionEffect extends MobEffect {

    /**
     * Creates a new {@link HeatExhaustionEffect}
     * @param pCategory The {@link MobEffectCategory} of the {@link MobEffect}
     * @param pColor The color of the particles emitted from the effect as an integer
     */
    protected HeatExhaustionEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    /**
     * A {@link List} of items that can remove this effect from the affected {@link LivingEntity}
     * @return A {@link List} of items that can remove this effect from a {@link LivingEntity}
     */
    @Override
    public List<ItemStack> getCurativeItems() {
        // Milk is normally a curative item of effects
        // Milk doesn't cure heat exhaustion
        // As such, no item should be able to remove heat exhaustion from the affected entity
        return List.of();
    }
}

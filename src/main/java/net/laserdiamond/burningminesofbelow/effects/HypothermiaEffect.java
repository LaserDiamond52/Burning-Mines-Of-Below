package net.laserdiamond.burningminesofbelow.effects;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates the functionality of the {@link HypothermiaEffect}</li>
 * <li>A {@link HypothermiaEffect} is-a {@link AttackDamageMobEffect}</li>
 * @author Allen Malo
 */
public class HypothermiaEffect extends AttackDamageMobEffect {

    /**
     * Creates a new {@link HypothermiaEffect}
     * @param pCategory The {@link MobEffectCategory} of the {@link net.minecraft.world.effect.MobEffect}
     * @param pColor The color of the particles emitted from the effect as an integer
     */
    protected HypothermiaEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor, 4.5);
    }

    /**
     * A {@link List} of items that can remove this effect from the affected {@link LivingEntity}
     * @return A {@link List} of items that can remove this effect from a {@link LivingEntity}
     */
    @Override
    public List<ItemStack> getCurativeItems() {
        // Milk is normally a curative item of effects
        // Milk doesn't cure hypothermia
        // As such, no item should be able to remove hypothermia from the affected entity
        return List.of();
    }

    /**
     * Any additional effects that should be applied to the affected {@link LivingEntity}
     * @param pLivingEntity The {@link LivingEntity} with the {@link MobEffect}
     * @param pAmplifier the amplifier of the effect. This is how strong the effect is
     */
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setTicksFrozen(200);
    }
}

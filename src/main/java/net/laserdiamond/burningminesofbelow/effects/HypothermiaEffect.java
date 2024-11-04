package net.laserdiamond.burningminesofbelow.effects;

import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class HypothermiaEffect extends AttackDamageMobEffect {


    protected HypothermiaEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor, 4.5);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setTicksFrozen(200);
    }
}

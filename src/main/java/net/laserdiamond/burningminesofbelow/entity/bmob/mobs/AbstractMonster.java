package net.laserdiamond.burningminesofbelow.entity.bmob.mobs;

import net.laserdiamond.burningminesofbelow.entity.client.animation.IdleAnimation;
import net.laserdiamond.burningminesofbelow.entity.client.animation.IdleEntityAnimator;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class AbstractMonster extends Monster {

    protected final AnimationState idleAnimationState;
    protected int idleTimeout;

    public AbstractMonster(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.idleAnimationState = new AnimationState();
        this.idleTimeout = 0;
    }


}

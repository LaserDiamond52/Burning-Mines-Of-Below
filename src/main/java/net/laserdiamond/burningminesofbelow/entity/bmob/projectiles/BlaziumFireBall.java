package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlaziumFireBall extends BMOBSmallFireBall {


    public BlaziumFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos) {
        super(pLevel, pShooter, pos);
    }

    @Override
    protected float impactDamage(LivingEntity hitEntity) {
        return 5F;
    }
}

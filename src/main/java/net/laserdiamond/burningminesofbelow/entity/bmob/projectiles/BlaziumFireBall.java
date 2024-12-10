package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the behavior of a {@link BlaziumFireBall} in-game</li>
 * <li>This fireball is only summoned by the Blazium Sword</li>
 * @author Allen Malo
 */
public class BlaziumFireBall extends BMOBSmallFireBall {

    public BlaziumFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos) {
        super(pLevel, pShooter, pos);
    }

    @Override
    protected float impactDamage(LivingEntity hitEntity) {
        return 5F;
    }
}

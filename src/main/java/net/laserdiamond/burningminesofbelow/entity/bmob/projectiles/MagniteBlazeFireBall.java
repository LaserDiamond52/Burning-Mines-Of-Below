package net.laserdiamond.burningminesofbelow.entity.bmob.projectiles;

import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class MagniteBlazeFireBall extends BMOBSmallFireBall {

    public MagniteBlazeFireBall(Level pLevel, LivingEntity pShooter, Vec3 pos) {
        super(pLevel, pShooter, pos);
    }

    @Override
    protected float impactDamage(LivingEntity hitEntity) {
        if (this.getOwner() instanceof MagniteBlazeEntity magniteBlazeEntity)
        {
            if (hitEntity instanceof Blaze || hitEntity instanceof KingInferniusEntity)
            {
                return 0F; // Cannot damage Blazes or King Infernius with fireball
            }
            final double damageAttribute = Objects.requireNonNull(magniteBlazeEntity.getAttribute(Attributes.ATTACK_DAMAGE)).getValue();
            return (float) damageAttribute;
        }
        return 0F;
    }
}

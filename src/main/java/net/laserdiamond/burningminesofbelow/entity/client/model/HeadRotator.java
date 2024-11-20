package net.laserdiamond.burningminesofbelow.entity.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Interface used to help create head rotation for the entity
 * @param <E> The {@link Entity} type
 */
public interface HeadRotator<E extends Entity> {

    /**
     * Helper method for the rotation of the head for the {@link Entity}
     * @param entity The entity
     * @param headYaw The yaw of the entity's head
     * @param headPitch The pitch of the entity's head
     */
    default void headRotation(E entity, float headYaw, float headPitch)
    {
        headYaw = Mth.clamp(headYaw, -30F, 30F);
        headPitch = Mth.clamp(headPitch, -25F, -25F);

        head().xRot = (float) (headYaw * Math.PI / 180F);
        head().yRot = (float) (headPitch * Math.PI / 180F);
    }

    /**
     * The head of the {@link Entity}
     * @return The {@link ModelPart} that represents the head of the {@link Entity}
     */
    @NotNull
    ModelPart head();
}

package net.laserdiamond.burningminesofbelow.entity.client.model;

import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help create head rotation for the entity</li>
 * <li>A {@link HeadRotator} is-a {@link HeadedModel}</li>
 * @author Allen Malo
 * @param <E> The {@link Entity} type
 */
public interface HeadRotator<E extends Entity> extends HeadedModel {

    /**
     * Helper method for the rotation of the head for the {@link Entity}
     * @param entity The entity
     * @param headYaw The yaw of the entity's head
     * @param headPitch The pitch of the entity's head
     */
    default void headRotation(E entity, float headYaw, float headPitch)
    {
        getHead().xRot = headYaw * ((float) Math.PI / 180F);
        getHead().yRot = headPitch * ((float) Math.PI / 180F);
    }
}

package net.laserdiamond.burningminesofbelow.entity.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the shape and {@link LayerDefinition} of the {@link MagniteBlazeEntity}</li>
 * <li>Sets up the animations associated with the entity's model, assigning them to the {@link net.minecraft.world.entity.AnimationState}s associated with them</li>
 * @author Allen Malo
 */
public final class MagniteBlazeModel extends AbstractHierarchicalModel<MagniteBlazeEntity> {

	private final ModelPart magnite_blaze;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart shields;
	public final ModelPart north;
	public final ModelPart south;
	public final ModelPart east;
	public final ModelPart west;

	public MagniteBlazeModel(ModelPart root) {
		this.magnite_blaze = root.getChild("magnite_blaze");
		this.body = this.magnite_blaze.getChild("body");
		this.head = this.body.getChild("head");
		this.torso = this.body.getChild("torso");
		this.shields = this.body.getChild("shields");
		this.north = this.shields.getChild("north");
		this.south = this.shields.getChild("south");
		this.east = this.shields.getChild("east");
		this.west = this.shields.getChild("west");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition magnite_blaze = partdefinition.addOrReplaceChild("magnite_blaze", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = magnite_blaze.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shields = body.addOrReplaceChild("shields", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition north = shields.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shield_r1 = north.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition south = shields.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition shield_r2 = south.addOrReplaceChild("shield_r2", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition east = shields.addOrReplaceChild("east", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition shield_r3 = east.addOrReplaceChild("shield_r3", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition west = shields.addOrReplaceChild("west", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition shield_r4 = west.addOrReplaceChild("shield_r4", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.5F, -8.5F, -3.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -1.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -20.0F, -2.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(MagniteBlazeEntity magniteBlazeEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(magniteBlazeEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.animate(magniteBlazeEntity.getIdleAnimationState(), Animations.MAGNITE_BLAZE_IDLE, ageInTicks, 1F);
	}


	@Override
	public ModelPart root() {
		return this.magnite_blaze;
	}

	@Override
	public @NotNull ModelPart getHead() {
		return this.head;
	}

	/**
	 * <p>Version/date: 12/9/24</p>
	 * <p>Responsibilities of class:</p>
	 * <li>Contains all the animations for the {@link MagniteBlazeEntity}</li>
	 * <li>Declared as a private static inner class because the declared {@link AnimationDefinition}s only work function properly with the {@link MagniteBlazeModel}. The animations are not needed outside the enclosing class</li>
	 * @author Allen Malo
	 */
	public static class Animations
	{
		/**
		 * Idle animation for Magnite Blaze
		 */
		public static final AnimationDefinition MAGNITE_BLAZE_IDLE = AnimationDefinition.Builder.withLength(4.0F).looping()
				.addAnimation("shields", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 90.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 135.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 180.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 225.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 270.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(3.5F, KeyframeAnimations.degreeVec(0.0F, 315.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 360.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();
	}
}
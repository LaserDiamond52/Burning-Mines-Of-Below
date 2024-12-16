package net.laserdiamond.burningminesofbelow.entity.client.model;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the shape and {@link LayerDefinition} of the {@link KingInferniusEntity}</li>
 * <li>Sets up the animations associated with the entity's model, assigning them to the {@link net.minecraft.world.entity.AnimationState}s associated with them</li>
 * <li>A {@link KingInferniusModel} is-a {@link AbstractHierarchicalArmedModel}</li>
 * @author Allen Malo
 */
public final class KingInferniusModel extends AbstractHierarchicalArmedModel<KingInferniusEntity> {

	// A KingInferniusModel has-a ModelPart (one-to-many)

	private final ModelPart king_infernius;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart torso;
	private final ModelPart right_leg;
	private final ModelPart right_knee;
	private final ModelPart right_foot;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart left_knee;
	private final ModelPart left_foot;
	private final ModelPart left_arm;
	private final ModelPart blaze_rod_crown;
	private final ModelPart set_1;
	private final ModelPart set_2;

	/**
	 * Creates a new {@link KingInferniusModel}
	 * @param root The {@link ModelPart} that will be the root of the model
	 */
	public KingInferniusModel(ModelPart root) {
		this.king_infernius = root.getChild("king_infernius");
		this.body = this.king_infernius.getChild("body");
		this.torso = this.body.getChild("torso");
		this.right_arm = this.torso.getChild("right_arm");
		this.left_arm = this.torso.getChild("left_arm");
		this.head = this.torso.getChild("head");
		this.blaze_rod_crown = this.head.getChild("blaze_rod_crown");
		this.set_1 = this.blaze_rod_crown.getChild("set_1");
		this.set_2 = this.blaze_rod_crown.getChild("set_2");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.left_leg = this.body.getChild("left_leg");
		this.right_knee = this.left_leg.getChild("right_knee");
		this.right_foot = this.right_knee.getChild("right_foot");
		this.right_leg = this.body.getChild("right_leg");
		this.left_knee = this.right_leg.getChild("left_knee");
		this.left_foot = this.left_knee.getChild("left_foot");
	}

	/**
	 * Creates a new {@link LayerDefinition} for the model
	 * @return The {@link LayerDefinition} for the model
	 */
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition king_infernius = partdefinition.addOrReplaceChild("king_infernius", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = king_infernius.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(32, 18).addBox(-4.0F, -11.75F, -1.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.75F, 0.0F));

		PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 34).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.75F, 1.0F));

		PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 34).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.75F, 1.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.5F, -3.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.75F, 0.0F));

		PartDefinition blaze_rod_crown = head.addOrReplaceChild("blaze_rod_crown", CubeListBuilder.create(), PartPose.offset(0.0F, -10.5F, 0.0F));

		PartDefinition set_1 = blaze_rod_crown.addOrReplaceChild("set_1", CubeListBuilder.create().texOffs(8, 50).addBox(-1.0F, -2.0F, -10.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 50).addBox(-10.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 50).addBox(-1.0F, -2.0F, 8.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(8, 50).addBox(8.0F, -2.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition set_2 = blaze_rod_crown.addOrReplaceChild("set_2", CubeListBuilder.create().texOffs(52, 0).addBox(-1.0F, -1.0F, -7.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 0).addBox(-7.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 0).addBox(-1.0F, -1.0F, 5.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(52, 0).addBox(5.0F, -1.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 1.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(56, 18).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.0F, 0.0F, -0.2618F, 0.0F, 0.5236F));

		PartDefinition tip_r1 = right_horn.addOrReplaceChild("tip_r1", CubeListBuilder.create().texOffs(56, 26).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.3333F, 1.6667F, 0.9163F, 0.0F, 0.0F));

		PartDefinition horn_r1 = right_horn.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(56, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.25F, 1.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition horn_r2 = right_horn.addOrReplaceChild("horn_r2", CubeListBuilder.create().texOffs(56, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.75F, 0.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(56, 18).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -7.0F, 0.0F, -0.2618F, 0.0F, -0.5236F));

		PartDefinition tip_r2 = left_horn.addOrReplaceChild("tip_r2", CubeListBuilder.create().texOffs(56, 26).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.3333F, 1.6667F, 0.9163F, 0.0F, 0.0F));

		PartDefinition horn_r3 = left_horn.addOrReplaceChild("horn_r3", CubeListBuilder.create().texOffs(56, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.25F, 1.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition horn_r4 = left_horn.addOrReplaceChild("horn_r4", CubeListBuilder.create().texOffs(56, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.75F, 0.25F, -0.2618F, 0.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.25F, -16.0F, 1.0F));

		PartDefinition thigh_r1 = left_leg.addOrReplaceChild("thigh_r1", CubeListBuilder.create().texOffs(32, 34).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.25F, -1.25F, -0.2608F, -0.0226F, -0.0843F));

		PartDefinition right_knee = left_leg.addOrReplaceChild("right_knee", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, 2.0F, 1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 7.0F, -3.0F));

		PartDefinition knee_r1 = right_knee.addOrReplaceChild("knee_r1", CubeListBuilder.create().texOffs(48, 34).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, 1.5F, 0.4363F, 0.0F, 0.0F));

		PartDefinition right_foot = right_knee.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(36, 11).addBox(0.75F, -1.0F, -4.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 9.0F, 2.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.25F, -16.0F, 1.0F));

		PartDefinition thigh_r2 = right_leg.addOrReplaceChild("thigh_r2", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -3.0F, -1.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.25F, -1.25F, -0.2608F, 0.0226F, 0.0843F));

		PartDefinition left_knee = right_leg.addOrReplaceChild("left_knee", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, 2.0F, 1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 7.0F, -3.0F));

		PartDefinition knee_r2 = left_knee.addOrReplaceChild("knee_r2", CubeListBuilder.create().texOffs(48, 41).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 2.0F, 1.5F, 0.4363F, 0.0F, 0.0F));

		PartDefinition left_foot = left_knee.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(32, 45).addBox(0.75F, -1.0F, -4.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 9.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(KingInferniusEntity kingInferniusEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(kingInferniusEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.animateWalk(Animations.KING_INFERNIUS_WALK, limbSwing, limbSwingAmount, 5F, 10F); // Walk animation

		this.animate(kingInferniusEntity.getIdleAnimationState(), Animations.KING_INFERNIUS_CROWN, ageInTicks, 1.0F); // Blaze Rod crown animation
		AnimationUtils.bobArms(this.right_arm, this.left_arm, ageInTicks); // idle/bob arms animation

		this.animate(KingInferniusEntity.Attack.MELEE.getAnimationState(), Animations.KING_INFERNIUS_MELEE_ATTACK, ageInTicks, 1.0F); // Melee attack animation
		this.animate(KingInferniusEntity.Attack.FIRE_BREATH.getAnimationState(), Animations.KING_INFERNIUS_FIRE_BREATH, ageInTicks, 1.0F); // Fire Breath animation
		this.animate(KingInferniusEntity.Attack.HEAT_WAVE.getAnimationState(), Animations.KING_INFERNIUS_HEAT_WAVE, ageInTicks, 1.0F); // Heat Wave animation
		this.animate(KingInferniusEntity.Attack.SOLAR_FLARE.getAnimationState(), Animations.KING_INFERNIUS_SOLAR_FLARE, ageInTicks, 1.0F); // Solar Flare animation
	}

	@Override
	public @NotNull ModelPart getHead() {
		return this.head;
	}

	@Override
	public ModelPart root() {
		return this.king_infernius;
	}

	@Override
	public ModelPart getArm(HumanoidArm humanoidArm)
	{
		return humanoidArm == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}

	/**
	 * <p>Version/date: 12/9/24</p>
	 * <p>Responsibilities of class:</p>
	 * <li>Contains all the animations for the {@link KingInferniusEntity}</li>
	 * <li>Declared as a private static inner class because the declared {@link AnimationDefinition}s only function properly with the {@link KingInferniusModel}. The animations are not needed outside the encompassing class</li>
	 * @author Allen Malo
	 */
	private static class Animations
	{
		/**
		 * Crown Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_CROWN = AnimationDefinition.Builder.withLength(4f).looping()
				.addAnimation("set_1",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 45f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 90f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 135f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 180f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 225f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3f, KeyframeAnimations.degreeVec(0f, 270f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, 315f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("set_2",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, -45f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -90f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, -135f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2f, KeyframeAnimations.degreeVec(0f, -180f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, -225f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3f, KeyframeAnimations.degreeVec(0f, -270f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3.5f, KeyframeAnimations.degreeVec(0f, -315f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(4f, KeyframeAnimations.degreeVec(0f, -360f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();

		/**
		 * Walking Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_WALK = AnimationDefinition.Builder.withLength(1.5f).looping()
				.addAnimation("left_leg",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_knee",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_leg",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-45f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-27.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_knee",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(15f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(20f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-30f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();

		/**
		 * Melee Attack Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_MELEE_ATTACK = AnimationDefinition.Builder.withLength(1.5f)
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(12.09f, 3.21f, -14.66f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-123.69f, -25.66f, 16.1f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(-62f, -3.84f, 28.82f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-130.52f, 28.02f, -21.88f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-43.84f, 0.7f, -25.1f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(10f, 0f, 15f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();

		/**
		 * Fire Breath Attack Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_FIRE_BREATH = AnimationDefinition.Builder.withLength(2.5f)
				.addAnimation("head",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(-25.77f, 13.57f, -6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(-25.77f, -13.57f, 6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.75f, KeyframeAnimations.degreeVec(-25.77f, 13.57f, -6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2f, KeyframeAnimations.degreeVec(-25.77f, -13.57f, 6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.25f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-15f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.25f, KeyframeAnimations.degreeVec(30f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-115.77f, 13.57f, -6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-195.64f, -63.97f, 107.3f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.25f, KeyframeAnimations.degreeVec(-195.64f, -63.97f, 107.3f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-115.77f, -13.57f, 6.46f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-195.64f, 63.97f, -107.3f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.25f, KeyframeAnimations.degreeVec(-195.64f, 63.97f, -107.3f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();

		/**
		 * Heat Wave Attack Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_HEAT_WAVE = AnimationDefinition.Builder.withLength(1f)
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-54.06f, 12.24f, 8.74f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(-49.48f, -28.02f, -21.88f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-60f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-54.06f, -12.24f, -8.74f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(-49.48f, 28.02f, 21.88f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();
		/**
		 * Solar Flare Attack Animation for King Infernius
		 */
		public static final AnimationDefinition KING_INFERNIUS_SOLAR_FLARE = AnimationDefinition.Builder.withLength(4f)
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-145f, 0f, 15f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3.5f, KeyframeAnimations.degreeVec(-145f, 0f, 15f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-145f, 0f, -15f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(3.5f, KeyframeAnimations.degreeVec(-145f, 0f, -15f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(4f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();
	}
}
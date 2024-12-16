package net.laserdiamond.burningminesofbelow.entity.client.model;
// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.HumanoidArm;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the shape and {@link LayerDefinition} of the {@link FreezingReaperEntity}</li>
 * <li>Sets up the animations associated with the model, assigning them to the {@link net.minecraft.world.entity.AnimationState}s associated with them</li>
 * <li>A {@link FreezingReaperModel} is-a {@link AbstractHierarchicalArmedModel}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 * <p>BlockBench</p>
 */
public final class FreezingReaperModel extends AbstractHierarchicalArmedModel<FreezingReaperEntity> {

	// FreezingReaperModel has-a ModelPart (one-to-many)

	private final ModelPart freezing_reaper;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart spikes;
	private final ModelPart spike_1_head;
	private final ModelPart spike_2_head;
	private final ModelPart spike_3_head;
	private final ModelPart spike_4_head;
	private final ModelPart spike_5_head;
	private final ModelPart spike_6_head;
	private final ModelPart right_arm;
	private final ModelPart right_arm_spikes;
	private final ModelPart spike_1_right;
	private final ModelPart spike_2_right;
	private final ModelPart spike_3_right;
	private final ModelPart left_arm;
	private final ModelPart left_arm_spikes;
	private final ModelPart spike_1_left;
	private final ModelPart spike_2_left;
	private final ModelPart spike_3_left;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	/**
	 * Creates a new {@link FreezingReaperModel}
	 * @param root The {@link ModelPart} that will be the root of the model
	 */
	public FreezingReaperModel(ModelPart root) {
		this.freezing_reaper = root.getChild("freezing_reaper");
		this.body = this.freezing_reaper.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.spikes = this.head.getChild("spikes");
		this.spike_1_head = this.spikes.getChild("spike_1_head");
		this.spike_2_head = this.spikes.getChild("spike_2_head");
		this.spike_3_head = this.spikes.getChild("spike_3_head");
		this.spike_4_head = this.spikes.getChild("spike_4_head");
		this.spike_5_head = this.spikes.getChild("spike_5_head");
		this.spike_6_head = this.spikes.getChild("spike_6_head");
		this.right_arm = this.torso.getChild("right_arm");
		this.right_arm_spikes = this.right_arm.getChild("right_arm_spikes");
		this.spike_1_right = this.right_arm_spikes.getChild("spike_1_right");
		this.spike_2_right = this.right_arm_spikes.getChild("spike_2_right");
		this.spike_3_right = this.right_arm_spikes.getChild("spike_3_right");
		this.left_arm = this.torso.getChild("left_arm");
		this.left_arm_spikes = this.left_arm.getChild("left_arm_spikes");
		this.spike_1_left = this.left_arm_spikes.getChild("spike_1_left");
		this.spike_2_left = this.left_arm_spikes.getChild("spike_2_left");
		this.spike_3_left = this.left_arm_spikes.getChild("spike_3_left");
		this.right_leg = this.body.getChild("right_leg");
		this.left_leg = this.body.getChild("left_leg");
	}

	/**
	 * Creates the {@link LayerDefinition} of the model
	 * @return The {@link LayerDefinition} of the model
	 */
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition freezing_reaper = partdefinition.addOrReplaceChild("freezing_reaper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = freezing_reaper.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition spikes = head.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition spike_1_head = spikes.addOrReplaceChild("spike_1_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -30.0F, 2.0F, -0.6545F, 0.0F, -0.5236F));

		PartDefinition spike_2_head = spikes.addOrReplaceChild("spike_2_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -30.0F, 2.0F, -0.6545F, 0.0F, 0.5236F));

		PartDefinition spike_3_head = spikes.addOrReplaceChild("spike_3_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -31.0F, 2.0F, -0.48F, 0.0F, -0.48F));

		PartDefinition spike_4_head = spikes.addOrReplaceChild("spike_4_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -31.0F, 2.0F, -0.48F, 0.0F, 0.48F));

		PartDefinition spike_5_head = spikes.addOrReplaceChild("spike_5_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -30.0F, 2.0F, -0.9599F, 0.0F, -0.5236F));

		PartDefinition spike_6_head = spikes.addOrReplaceChild("spike_6_head", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -30.0F, 2.0F, -0.9599F, 0.0F, 0.5236F));

		PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -10.0F, 0.0F));

		PartDefinition right_arm_spikes = right_arm.addOrReplaceChild("right_arm_spikes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition spike_1_right = right_arm_spikes.addOrReplaceChild("spike_1_right", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition spike_2_right = right_arm_spikes.addOrReplaceChild("spike_2_right", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, 0.0F, -0.5236F, 0.0F, -0.5672F));

		PartDefinition spike_3_right = right_arm_spikes.addOrReplaceChild("spike_3_right", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -1.0F, 0.0F, -0.9163F, 0.0F, -0.3491F));

		PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -10.0F, 0.0F));

		PartDefinition left_arm_spikes = left_arm.addOrReplaceChild("left_arm_spikes", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition spike_1_left = left_arm_spikes.addOrReplaceChild("spike_1_left", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 0.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition spike_2_left = left_arm_spikes.addOrReplaceChild("spike_2_left", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.75F, -1.0F, 0.0F, -0.5236F, 0.0F, 0.5672F));

		PartDefinition spike_3_left = left_arm_spikes.addOrReplaceChild("spike_3_left", CubeListBuilder.create().texOffs(48, 4).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(48, 0).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 0.0F, -0.9163F, 0.0F, 0.3491F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(FreezingReaperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.animate(entity.getIdleAnimationState(), Animations.FREEZING_REAPER_IDLE, ageInTicks, 1.0F);
		AnimationUtils.bobArms(this.right_arm, this.left_arm, ageInTicks);

		this.animate(FreezingReaperEntity.Attack.MELEE.getAnimationState(), Animations.FREEZING_REAPER_MELEE_ATTACK, ageInTicks, 1.0F);
		this.animate(FreezingReaperEntity.Attack.CRYOBOLT_BLAST.getAnimationState(), Animations.FREEZING_REAPER_CYROBOLT_BLAST, ageInTicks);
		this.animate(FreezingReaperEntity.Attack.SUDDEN_BLIZZARD.getAnimationState(), Animations.FREEZING_REAPER_SUDDEN_BLIZZARD, ageInTicks);
	}

	@Override
	protected ModelPart getArm(HumanoidArm side) {
		return side == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}

	@Override
	public ModelPart root() {
		return this.freezing_reaper;
	}

	/**
	 * <p>Version/date: 12/9/24</p>
	 * <p>Responsibilities of class:</p>
	 * <li>Contains all animations for the {@link FreezingReaperEntity}</li>
	 * <li>Declared as a private static inner class because the declared {@link AnimationDefinition}s only function properly with the {@link FreezingReaperModel}. The animations are not needed outside the encompassing class</li>
	 * @author Allen Malo
	 * @References:
	 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
	 * <p>BlockBench</p>
	 */
	private static class Animations
	{
		/**
		 * Idle animation for Freezing Reaper
		 */
		public static final AnimationDefinition FREEZING_REAPER_IDLE = AnimationDefinition.Builder.withLength(2.0F).looping()
				.addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.0F, KeyframeAnimations.degreeVec(25.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.5F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(2.0F, KeyframeAnimations.degreeVec(22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		/**
		 * Melee Attack animation for Freezing Reaper
		 */
		public static final AnimationDefinition FREEZING_REAPER_MELEE_ATTACK = AnimationDefinition.Builder.withLength(1.0F).looping()
				.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
						new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.25F, KeyframeAnimations.degreeVec(-125.0F, 35.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.5F, KeyframeAnimations.degreeVec(-47.5F, -25.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(0.75F, KeyframeAnimations.degreeVec(-107.5F, -37.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
						new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
				))
				.build();

		/**
		 * Cyroblast Attack animation for Freezing Reaper
		 */
		public static final AnimationDefinition FREEZING_REAPER_CYROBOLT_BLAST = AnimationDefinition.Builder.withLength(2f).looping()
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 12.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 12.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 12.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.75f, KeyframeAnimations.degreeVec(0f, -7.5f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.25f, KeyframeAnimations.degreeVec(-75f, -40f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.5f, KeyframeAnimations.degreeVec(-75f, 50f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(-75f, -40f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(-75f, 50f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.25f, KeyframeAnimations.degreeVec(-75f, -40f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.5f, KeyframeAnimations.degreeVec(-75f, 50f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1.75f, KeyframeAnimations.degreeVec(-75f, -40f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(2f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();

		/**
		 * Sudden Blizzard attack animation for Freezing Reaper
		 */
		public static final AnimationDefinition FREEZING_REAPER_SUDDEN_BLIZZARD = AnimationDefinition.Builder.withLength(1f).looping()
				.addAnimation("torso",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.375f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("head",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.375f, KeyframeAnimations.degreeVec(-32.5f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("right_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.375f, KeyframeAnimations.degreeVec(-137.5f, 0f, 45f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(-75f, -50f, 45f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR)))
				.addAnimation("left_arm",
						new AnimationChannel(AnimationChannel.Targets.ROTATION,
								new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.375f, KeyframeAnimations.degreeVec(-137.5f, 0f, -45f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(0.75f, KeyframeAnimations.degreeVec(-75f, 50f, -45f),
										AnimationChannel.Interpolations.LINEAR),
								new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
										AnimationChannel.Interpolations.LINEAR))).build();
	}


}
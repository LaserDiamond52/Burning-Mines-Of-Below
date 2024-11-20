package net.laserdiamond.burningminesofbelow.entity.client.model;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laserdiamond.burningminesofbelow.entity.bmob.mobs.MagniteBlazeEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class MagniteBlazeModel extends AbstractHierarchicalModel<MagniteBlazeEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "magniteblaze"), "main");
	private final ModelPart magnite_blaze;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart torso;
	private final ModelPart shields;
	private final ModelPart north;
	private final ModelPart south;
	private final ModelPart east;
	private final ModelPart west;

	public MagniteBlazeModel(ModelPart root) {
		this.magnite_blaze = root.getChild("magnite_blaze");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.torso = root.getChild("torso");
		this.shields = root.getChild("shields");
		this.north = root.getChild("north");
		this.south = root.getChild("south");
		this.east = root.getChild("east");
		this.west = root.getChild("west");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition magnite_blaze = partdefinition.addOrReplaceChild("magnite_blaze", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = magnite_blaze.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -32.0F, -3.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-4.5F, -32.5F, -3.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -20.0F, -2.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition shields = body.addOrReplaceChild("shields", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition north = shields.addOrReplaceChild("north", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shield_r1 = north.addOrReplaceChild("shield_r1", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition south = shields.addOrReplaceChild("south", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition shield_r2 = south.addOrReplaceChild("shield_r2", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition east = shields.addOrReplaceChild("east", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition shield_r3 = east.addOrReplaceChild("shield_r3", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition west = shields.addOrReplaceChild("west", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition shield_r4 = west.addOrReplaceChild("shield_r4", CubeListBuilder.create().texOffs(32, 22).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -9.0F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(MagniteBlazeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		magnite_blaze.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return null;
	}

	@Override
	public @NotNull ModelPart head() {
		return this.head;
	}
}
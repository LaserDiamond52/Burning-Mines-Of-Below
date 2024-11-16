package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

/**
 * The Tool tiers of this mod
 */
public class BMOBToolTiers {

    /**
     * Diamonite Tool Tier
     */
    public static final Tier DIAMONITE = TierSortingRegistry.registerTier(
            new ForgeTier(4, 1800, 10.0F, 4.0F, 12,
                    BMOBTags.Blocks.NEEDS_DIAMONITE_TOOL, () -> Ingredient.of(Items.DIAMOND, BMOBItems.REFINED_DIAMOND.get())),
            new ResourceLocation(BurningMinesOfBelow.MODID, "diamonite"), List.of(Tiers.DIAMOND), List.of());

    /**
     * Refined Diamonite Tool Tier
     */
    public static final Tier REFINED_DIAMONITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2300, 12.0F, 5.0F, 17,
                    BMOBTags.Blocks.NEEDS_REFINED_DIAMONITE_TOOL, () -> Ingredient.of(BMOBItems.REFINED_DIAMOND.get())),
            new ResourceLocation(BurningMinesOfBelow.MODID, "refined_diamonite"), List.of(Tiers.NETHERITE), List.of());

    /**
     * Blazium Tool Tier
     */
    public static final Tier BLAZIUM = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2740, 9.0F, 7.0F, 15,
                    BMOBTags.Blocks.NEEDS_BLAZIUM_TOOL, () -> Ingredient.of(BMOBItems.BLAZIUM_INGOT.get(), BMOBItems.BLAZE_FLAMES.get())),
            new ResourceLocation(BurningMinesOfBelow.MODID, "blazium"), List.of(Tiers.NETHERITE), List.of());

    /**
     * Cyronite Tool Tier
     */
    public static final Tier CYRONITE = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2930, 9.0F, 7.0F, 13,
                    BMOBTags.Blocks.NEEDS_CYRONITE_TOOL, () -> Ingredient.of(BMOBItems.CYRONITE_SHARD.get(), BMOBItems.FRIGID_CYRONITE_CRYSTAL.get())),
            new ResourceLocation(BurningMinesOfBelow.MODID, "cyronite"), List.of(Tiers.NETHERITE), List.of());
}

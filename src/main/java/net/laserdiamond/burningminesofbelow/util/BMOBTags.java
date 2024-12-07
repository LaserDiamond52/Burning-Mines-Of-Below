package net.laserdiamond.burningminesofbelow.util;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import java.util.List;

/**
 * Responsibilities:
 * <li>Contains inner classes for tags that can be applied to different assets of this mod</li>
 */
public class BMOBTags {

    /**
     * Responsibilities:
     * <li>Contains tags that can be applied to {@link Block}s</li>
     */
    public static class Blocks
    {
        /**
         * Indicates that a block is at least a {@link net.laserdiamond.burningminesofbelow.block.AbstractForgeBlock}
         */
        public static final TagKey<Block> FORGE_BLOCK = tag("forge_block");

        /**
         * Indicates that a block needs at least a Diamonite tool for it to drop its loot table when mined
         */
        public static final TagKey<Block> NEEDS_DIAMONITE_TOOL = tag("needs_diamonite_tool");

        /**
         * Indicates that a block needs at least a Refined Diamonite tool for it to drop its loot table when mined
         */
        public static final TagKey<Block> NEEDS_REFINED_DIAMONITE_TOOL = tag("needs_refined_diamonite_tool");

        /**
         * Indicates that a block needs at least a Blazium tool for it to drop its loot table when mined
         */
        public static final TagKey<Block> NEEDS_BLAZIUM_TOOL = tag("needs_blazium_tool");

        /**
         * Indicates that a block needs at least a Cyronite tool for it to drop its loot table when mined
         */
        public static final TagKey<Block> NEEDS_CYRONITE_TOOL = tag("needs_cyronite_tool");

        /**
         * Indicates that a block is a Refined Ore
         */
        public static final TagKey<Block> REFINED_ORE = tag("refined_ore");

        /**
         * Indicates that a block can be used as a summoning block for summoning the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity}.
         * Placing a block with this tag on the base block pattern will summon the entity.
         */
        public static final TagKey<Block> FREEZING_REAPER_SUMMON_BLOCK = tag("freezing_reaper_summoning_block");

        /**
         * Indicates that a block can be used as a base block for summoning the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity}.
         */
        public static final TagKey<Block> FREEZING_REAPER_BASE_BLOCK = tag("freezing_reaper_base_block");

        /**
         * Indicates that a block can be used as a summoning block for summoning the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity}.
         * Placing a block with this tag on the base block pattern will summon the entity.
         */
        public static final TagKey<Block> KING_INFERNIUS_SUMMON_BLOCK = tag("king_infernius_summoning_block");

        /**
         * Indicates that a block can be used as a base block for summoning the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity}.
         */
        public static final TagKey<Block> KING_INFERNIUS_BASE_BLOCK = tag("king_infernius_base_block");

        /**
         * Indicates that the {@link net.laserdiamond.burningminesofbelow.worldgen.feature.CocytusIceSpikeFeature} and {@link net.laserdiamond.burningminesofbelow.worldgen.feature.CocytusCeilingIceFeature} can start generation on this block
         */
        public static final TagKey<Block> COCYTUS_ICE_SPIKE_BASE_BLOCK = tag("ice_spike_base_block");

        /**
         * Indicates the target block for frozen netherrack ores.
         */
        public static final TagKey<Block> FROZEN_NETHERRACK_ORE_REPLACEABLE = tag("frozen_netherrack_ore_replaceable");

        /**
         * Creates a {@link Block} tag under this mod's directory
         * @param name The name of the tag
         * @return A {@link TagKey} representing the tag
         */
        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }

    /**
     * Responsibilities:
     * <li>Contains tags that can be applied to {@link Item}s</li>
     */
    public static class Items
    {
        /**
         * Indicates that an item can be used as heat fuel for the Forge.
         */
        public static final TagKey<Item> FORGE_HEAT_FUEL = tag("forge_heat_fuel");

        /**
         * Indicates that an item can be used as freeze fuel for the Forge.
         */
        public static final TagKey<Item> FORGE_FREEZE_FUEL = tag("forge_freeze_fuel");

        /**
         * Indicates that an item can be placed in the main ingredient slot for the Forge.
         */
        public static final TagKey<Item> FORGE_MAIN_INGREDIENT = tag("forge_main_ingredient");

        /**
         * Indicates that an item can be placed in the miscellaneous ingredient slot for the Forge.
         */
        public static final TagKey<Item> FORGE_MISC_INGREDIENT = tag("forge_misc_ingredient");

        /**
         * Indicates that an item can be used to summon the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.KingInferniusEntity}.
         * This should be applied to block items that also have the tag for summoning King Infernius.
         */
        public static final TagKey<Item> KING_INFERNIUS_SUMMON_ITEM = tag("king_infernius_summon_item");

        /**
         * Indicates that an item can be used to summon the {@link net.laserdiamond.burningminesofbelow.entity.bmob.mobs.FreezingReaperEntity}.
         * This should be applied  to block item that also have the tag for summoning the Freezing Reaper of the Damned.
         */
        public static final TagKey<Item> FREEZING_REAPER_SUMMON_ITEM = tag("freezing_reaper_summon_item");

        /**
         * Creates an {@link Item} tag under this mod's directory
         * @param name The name of the tag
         * @return A {@link TagKey} representing the tag
         */
        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }

        /**
         * A list of tags to apply to an armor piece
         * @param type The armor piece type
         * @return A list of tags to apply to the armor piece. This includes the tag that indicates what armor piece it is, and also tags the armor piece as trimmable.
         */
        public static List<TagKey<Item>> armorTags(ArmorItem.Type type)
        {
            TagKey<Item> armorTag = switch (type)
            {
                case HELMET -> Tags.Items.ARMORS_HELMETS;
                case CHESTPLATE -> Tags.Items.ARMORS_CHESTPLATES;
                case LEGGINGS -> Tags.Items.ARMORS_LEGGINGS;
                case BOOTS -> Tags.Items.ARMORS_BOOTS;
            };
            return List.of(armorTag, ItemTags.TRIMMABLE_ARMOR);
        }
    }

    public static class Biomes
    {

        public static final TagKey<Biome> COCYTUS = tag("cocytus");

        public static final TagKey<Biome> MAGMA = tag("magma");

        /**
         * Creates a {@link Biome} tag under this mod's directory
         * @param name The name of the tag
         * @return A {@link TagKey} representing the tag
         */
        private static TagKey<Biome> tag(String name)
        {
            return TagKey.create(Registries.BIOME, new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }

    public static class Entities
    {

        /**
         * Creates an {@link net.minecraft.world.entity.Entity} tag under this mod's directory
         * @param name The name of the tag
         * @return A {@link TagKey} representing the tag
         */
        private static TagKey<EntityType<?>> tag(String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }
}

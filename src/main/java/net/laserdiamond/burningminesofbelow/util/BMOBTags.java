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

public class BMOBTags {

    public static class Blocks
    {

        public static final TagKey<Block> FORGE_BLOCK = tag("forge_block");
        public static final TagKey<Block> NEEDS_DIAMONITE_TOOL = tag("needs_diamonite_tool");
        public static final TagKey<Block> NEEDS_REFINED_DIAMONITE_TOOL = tag("needs_refined_diamonite_tool");
        public static final TagKey<Block> NEEDS_BLAZIUM_TOOL = tag("needs_blazium_tool");
        public static final TagKey<Block> NEEDS_CYRONITE_TOOL = tag("needs_cyronite_tool");
        public static final TagKey<Block> REFINED_ORE = tag("refined_ore");
        public static final TagKey<Block> FREEZING_REAPER_SUMMON_BLOCK = tag("freezing_reaper_summoning_block");
        public static final TagKey<Block> FREEZING_REAPER_BASE_BLOCK = tag("freezing_reaper_base_block");
        public static final TagKey<Block> KING_INFERNIUS_SUMMON_BLOCK = tag("king_infernius_summoning_block");
        public static final TagKey<Block> KING_INFERNIUS_BASE_BLOCK = tag("king_infernius_base_block");

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

    public static class Items
    {
        public static final TagKey<Item> FORGE_HEAT_FUEL = tag("forge_heat_fuel");
        public static final TagKey<Item> FORGE_FREEZE_FUEL = tag("forge_freeze_fuel");
        public static final TagKey<Item> FORGE_MAIN_INGREDIENT = tag("forge_main_ingredient");
        public static final TagKey<Item> FORGE_MISC_INGREDIENT = tag("forge_misc_ingredient");

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

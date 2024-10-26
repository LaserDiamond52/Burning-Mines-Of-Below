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

        private static TagKey<Block> tag(String name)
        {
            return BlockTags.create(new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }

    public static class Items
    {

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

        private static TagKey<Biome> tag(String name)
        {
            return TagKey.create(Registries.BIOME, new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }

    public static class Entities
    {

        private static TagKey<EntityType<?>> tag(String name)
        {
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(BurningMinesOfBelow.MODID, name));
        }
    }
}

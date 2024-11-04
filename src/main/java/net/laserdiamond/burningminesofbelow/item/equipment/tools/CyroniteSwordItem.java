package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public final class CyroniteSwordItem extends BMOBSwordItem {

    public CyroniteSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBToolTiers.CYRONITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return Items.DIAMOND_SWORD;
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.CYRONITE_SHARD.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 2;
    }

    @Override
    public int heatFuelCost() {
        return 0;
    }

    @Override
    public int freezeFuelCost() {
        return 150;
    }
}

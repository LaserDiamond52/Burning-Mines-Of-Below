package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

public abstract class BMOBAxeItem extends AxeItem implements Taggable<Item>, HandheldItem, ForgeCraftable {

    protected final List<TagKey<Item>> tags;

    public BMOBAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.tags = new ArrayList<>(tags);
        this.tags.add(ItemTags.AXES);
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

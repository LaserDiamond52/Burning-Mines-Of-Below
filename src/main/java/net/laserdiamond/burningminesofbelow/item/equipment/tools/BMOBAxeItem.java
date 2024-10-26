package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

import java.util.List;

public class BMOBAxeItem extends AxeItem implements Taggable<Item> {

    protected final List<TagKey<Item>> tags;

    public BMOBAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.tags = tags;
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

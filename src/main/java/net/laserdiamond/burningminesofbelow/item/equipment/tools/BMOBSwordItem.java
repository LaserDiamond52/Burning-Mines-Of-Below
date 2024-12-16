package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.item.ForgeCraftable;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties of a {@link BMOBSwordItem}</li>
 * <li>Allows the subclass to define a Forge recipe for this item</li>
 * <li>A {@link BMOBSwordItem} is-a {@link SwordItem}</li>
 * <li>A {@link BMOBSwordItem} is-a {@link Taggable}</li>
 * <li>A {@link BMOBSwordItem} is-a {@link HandheldItem}</li>
 * <li>A {@link BMOBSwordItem} is-a {@link ForgeCraftable}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public abstract class BMOBSwordItem extends SwordItem implements Taggable<Item>, HandheldItem, ForgeCraftable {

    /**
     * A {@link List} of {@link TagKey}s to apply to the item
     */
    protected final List<TagKey<Item>> tags; // A BMOBSword has-a List

    /**
     * Creates a new {@link BMOBSwordItem}
     * @param pTier The {@link Tier} material of the item
     * @param pAttackDamageModifier Any additional attack damage to add to the sword
     * @param pAttackSpeedModifier The attack speed of the sword
     * @param pProperties The {@link Item.Properties} of the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the sword
     */
    public BMOBSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.tags = new ArrayList<>(tags);
        this.tags.add(ItemTags.SWORDS);
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

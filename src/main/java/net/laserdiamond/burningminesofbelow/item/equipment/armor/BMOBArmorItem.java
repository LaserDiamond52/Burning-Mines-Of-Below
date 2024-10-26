package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.item.ItemTaggable;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BMOBArmorItem extends ArmorItem implements ItemTaggable
{
    private final List<TagKey<Item>> tags;
    protected List<MobEffectInstance> effectInstances;

    public BMOBArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(pMaterial, pType, pProperties);
        this.tags = tags;
        this.effectInstances = new ArrayList<>();

        int slot = pType.ordinal();
        UUID uuid = BMOBArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(pType);
        double damageValue = this.damageOutputAmounts()[slot].value();
        double speedValue = this.speedAmount()[slot].value();
        double heatIntervalValue = this.heatIntervalAmount()[slot].value();
        double freezeIntervalValue = this.freezeIntervalAmount()[slot].value();
        double refinedMineralChanceValue = this.refinedMineralChanceAmount()[slot].value();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = ImmutableMultimap.builder();

        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor armor", this.getMaterial().getDefenseForType(pType), AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.getMaterial().getToughness(), AttributeModifier.Operation.ADDITION));

        if (this.getMaterial().getKnockbackResistance() != 0.0)
        {
            modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.getMaterial().getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
        }

        if (damageValue != 0.0)
        {
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Armor attack damage", damageValue, this.damageOutputAmounts()[slot].operation()));
        }
        if (speedValue != 0.0)
        {
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor movement speed", speedValue, this.speedAmount()[slot].operation()));
        }
        if (heatIntervalValue != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), new AttributeModifier(uuid, "Armor heat interval", heatIntervalValue, this.heatIntervalAmount()[slot].operation()));
        }
        if (freezeIntervalValue != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), new AttributeModifier(uuid, "Armor freeze interval", freezeIntervalValue, this.freezeIntervalAmount()[slot].operation()));
        }
        if (refinedMineralChanceValue != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get(), new AttributeModifier(uuid, "Armor refined mineral chance", refinedMineralChanceValue, this.freezeIntervalAmount()[slot].operation()));
        }

        this.defaultModifiers = modifiers.build();
    }

    /**
     * The attack damage modifiers for the armor piece
     * @return An array of {@link ItemAttribute}s that contain the operation and value of the damage modifiers for the armor set
     */
    protected ItemAttribute[] damageOutputAmounts()
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0)
                };
    }

    /**
     * The movement speed modifiers for the armor piece
     * @return An array of {@link ItemAttribute}s that contain the operation and value of the movement speed modifiers for the armor set
     */
    protected ItemAttribute[] speedAmount()
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0)
                };
    }

    /**
     * The heat interval modifiers for the armor piece
     * @return An array of {@link ItemAttribute}s that contain the operation and value of the heat interval modifiers for the armor set
     */
    protected ItemAttribute[] heatIntervalAmount()
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0)
                };
    }

    /**
     * The freeze interval modifiers for the armor piece
     * @return An array of {@link ItemAttribute}s that contain the operation and value of the freeze interval modifiers for the armor set
     */
    protected ItemAttribute[] freezeIntervalAmount()
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0)
                };
    }

    /**
     * The refined mineral chance modifiers for the armor piece
     * @return An array of {@link ItemAttribute}s that contain the operation and value of the refined mineral chance modifiers for the armor set
     */
    protected ItemAttribute[] refinedMineralChanceAmount()
    {
        return new ItemAttribute[]
                {
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0),
                        new ItemAttribute(AttributeModifier.Operation.ADDITION, 0.0)
                };
    }

    /**
     * The status effects that the player gains when wearing the full armor set
     * @return A {@link List} of {@link MobEffectInstance}s that are the status effects the player gains while wearing the full armor set
     */
    public List<MobEffectInstance> armorEffects()
    {
        return this.effectInstances;
    }

    /**
     * Checks if the player's armor slots are all occupied
     * @param player The player to check
     * @return True if all the player's armor slots are occupied, false otherwise
     */
    protected boolean hasFullArmorOn(Player player)
    {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }

    /**
     * Checks if the player is wearing the full armor set of an armor material
     * @param player The {@link Player} wearing the armor
     * @param armorMaterial The armor material to check if the {@link Player} is wearing
     * @return True if the player is wearing the full set of the specified armor material
     */
    protected boolean hasFullSetOn(Player player, ArmorMaterial armorMaterial)
    {
        for (ItemStack armorStack : player.getInventory().armor)
        {
            if (!((armorStack.getItem()) instanceof ArmorItem))
            {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == armorMaterial && leggings.getMaterial() == armorMaterial && chestplate.getMaterial() == armorMaterial && helmet.getMaterial() == armorMaterial;

    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if (!pLevel.isClientSide)
        {
            if (pEntity instanceof Player player)
            {
                if (!hasFullArmorOn(player))
                {
                    return;
                }

                if (hasFullSetOn(player, this.getMaterial()))
                {
                    for (MobEffectInstance effectInstance : this.armorEffects())
                    {
                        boolean hasPlayerEffect = player.hasEffect(effectInstance.getEffect());

                        if (!hasPlayerEffect)
                        {
                            player.addEffect(effectInstance);
                        }
                    }
                }
            }
        }
    }


    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}

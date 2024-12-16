package net.laserdiamond.burningminesofbelow.item.equipment.armor;

import com.google.common.collect.ImmutableMultimap;
import net.laserdiamond.burningminesofbelow.attribute.BMOBAttributes;
import net.laserdiamond.burningminesofbelow.attribute.ItemAttribute;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.laserdiamond.burningminesofbelow.util.Taggable;
import net.laserdiamond.burningminesofbelow.util.file.ArmorConfig;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates a {@link BMOBArmorItem} and applies all the necessary attributes for the item</li>
 * <li>Defines any basic behaviors for an armor piece of this mod</li>
 * <li>A {@link BMOBArmorItem} is-a {@link ArmorItem}</li>
 * <li>A {@link BMOBArmorItem} is-a {@link Taggable}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public abstract class BMOBArmorItem extends ArmorItem implements Taggable<Item>
{

    /**
     * Creates an array of the Attribute Modifier operations and values to add to an armor set across all armor pieces.
     * The Attribute Modifier values here will be applied across all pieces of the armor set.
     * @param operation The operation of the Attribute Modifier
     * @param value The value of the Attribute Modifier
     * @return An {@link ItemAttribute} array containing the Attribute Modifier values to apply to Attributes on an armor set.
     */
    public static ItemAttribute[] createConsistentAttributes(AttributeModifier.Operation operation, double value)
    {
        return new ItemAttribute[]{new ItemAttribute(operation, value), new ItemAttribute(operation, value), new ItemAttribute(operation, value), new ItemAttribute(operation, value)};
    }

    /**
     * Creates an array of Attribute Modifier operations and values to add to an armor set across all armor pieces.
     * The Attribute Modifier values here are 0.
     * @return An {@link ItemAttribute} array containing the Attribute Modifier values to apply to Attributes on an armor set.
     */
    public static ItemAttribute[] createEmptyAttributes()
    {
        return createConsistentAttributes(AttributeModifier.Operation.ADDITION, 0);
    }

    /**
     * A {@link List} of {@link TagKey}s to apply to the item
     */
    protected final List<TagKey<Item>> tags; // A BMOBArmorItem has-a List (one-to-many)

    /**
     * A {@link List} of {@link MobEffectInstance}s that the player can gain when wearing the full set of a specific {@link BMOBArmorMaterial}
     */
    protected List<MobEffectInstance> effectInstances; // A BMOBArmorItem has-a List (one-to-many)

    /**
     * Creates a new {@link BMOBArmorItem}
     * @param pMaterial The {@link BMOBArmorMaterial} of the armor piece
     * @param pType The armor piece type to create
     * @param pProperties The {@link Item.Properties} to give the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public BMOBArmorItem(BMOBArmorMaterial pMaterial, Type pType, Properties pProperties, List<TagKey<Item>> tags) {
        super(pMaterial, pType, pProperties);
        this.tags = new ArrayList<>(tags);
        this.effectInstances = new ArrayList<>();

        this.tags.addAll(BMOBTags.Items.armorTags(pType));

        ArmorConfig armorConfig = new ArmorConfig(pMaterial);

        UUID uuid = BMOBArmorItem.ARMOR_MODIFIER_UUID_PER_TYPE.get(pType);
        final int armor = armorConfig.getArmor(pType);
        final float toughness = armorConfig.getToughness();
        final float knockbackResistance = armorConfig.getKnockbackResistance();

        final ItemAttribute damageAttribute = armorConfig.getDamageIncrease(pType);
        final ItemAttribute speedAttribute = armorConfig.getSpeedIncrease(pType);
        final ItemAttribute heatIntervalAttribute = armorConfig.getHeatResistance(pType);
        final ItemAttribute freezeIntervalAttribute = armorConfig.getFreezeResistance(pType);
        final ItemAttribute refinedMineralAttribute = armorConfig.getRefinedMineralChance(pType);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> modifiers = ImmutableMultimap.builder();

        modifiers.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor armor", armor, AttributeModifier.Operation.ADDITION));
        modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));

        if (this.getMaterial().getKnockbackResistance() != 0.0)
        {
            modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", knockbackResistance, AttributeModifier.Operation.ADDITION));
        }

        if (damageAttribute.value() != 0.0)
        {
            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Armor attack damage", damageAttribute.value(), damageAttribute.operation()));
        }
        if (speedAttribute.value() != 0.0)
        {
            modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Armor movement speed", speedAttribute.value(), speedAttribute.operation()));
        }
        if (heatIntervalAttribute.value() != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_HEAT_INTERVAL.get(), new AttributeModifier(uuid, "Armor heat interval", heatIntervalAttribute.value(), heatIntervalAttribute.operation()));
        }
        if (freezeIntervalAttribute.value() != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_FREEZE_INTERVAL.get(), new AttributeModifier(uuid, "Armor freeze interval", freezeIntervalAttribute.value(), freezeIntervalAttribute.operation()));
        }
        if (refinedMineralAttribute.value() != 0.0)
        {
            modifiers.put(BMOBAttributes.PLAYER_REFINED_MINERAL_CHANCE.get(), new AttributeModifier(uuid, "Armor refined mineral chance", refinedMineralAttribute.value(), refinedMineralAttribute.operation()));
        }

        this.defaultModifiers = modifiers.build();
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

    /**
     * Tick method called for a {@link Player}'s inventory
     * @param pStack The {@link ItemStack} of the item
     * @param pLevel The {@link Level} the {@link Player} is on
     * @param pEntity The {@link Entity} of the {@link Player}
     * @param pSlotId The id of the slot this item is in
     * @param pIsSelected If this item is selected
     */
    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {

        if (!pLevel.isClientSide) // Do not run on client
        {
            if (pEntity instanceof Player player)
            {
                if (!hasFullArmorOn(player)) // Check if the player has all armor slots occupied
                {
                    return; // An armor slot is empty. End method
                }

                if (hasFullSetOn(player, this.getMaterial())) // Does the player have a full set on of the same material?
                {
                    for (MobEffectInstance effectInstance : this.armorEffects()) // Loop through all effects to be applied
                    {
                        boolean hasPlayerEffect = player.hasEffect(effectInstance.getEffect()); // Does the player already have the effect?

                        if (!hasPlayerEffect) // Does the player have the effect?
                        {
                            player.addEffect(effectInstance); // Doesn't have it. Add effect
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

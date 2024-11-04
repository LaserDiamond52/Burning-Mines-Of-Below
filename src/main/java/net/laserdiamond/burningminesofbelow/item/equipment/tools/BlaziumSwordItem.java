package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Map;

public final class BlaziumSwordItem extends BMOBSwordItem implements AbilityItem {

    public BlaziumSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBToolTiers.BLAZIUM, pAttackDamageModifier, pAttackSpeedModifier, pProperties, tags);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {

        final Map<Enchantment, Integer> enchantments = stack.getAllEnchantments();
        if (enchantments.containsKey(Enchantments.FIRE_ASPECT))
        {
            int level = stack.getEnchantmentLevel(Enchantments.FIRE_ASPECT);
            entity.setSecondsOnFire(160 * level);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public Item mainItem() {
        return Items.DIAMOND_SWORD;
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.BLAZIUM_INGOT.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 2;
    }

    @Override
    public int heatFuelCost() {
        return 150;
    }

    @Override
    public int freezeFuelCost() {
        return 0;
    }

    @Override
    public void onKeyPressServer(NetworkEvent.Context event) {
        final ServerPlayer player = event.getSender();

        // TODO: Fire three fireballs
    }

    @Override
    public void onKeyPressClient(InputEvent.Key event) {

    }
}

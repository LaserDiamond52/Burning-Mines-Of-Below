package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cyrobolt;
import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;

public final class CyroniteScytheItem extends BMOBSwordItem implements AbilityItem {

    public CyroniteScytheItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBToolTiers.CYRONITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return BMOBItems.REFINED_DIAMONITE_HOE.get();
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.FRIGID_CYRONITE_CRYSTAL.get());
    }

    @Override
    public int forgeCraftingLevel() {
        return 3;
    }

    @Override
    public int heatFuelCost() {
        return 0;
    }

    @Override
    public int freezeFuelCost() {
        return 300;
    }

    @Override
    public void onKeyPressServer(NetworkEvent.Context event)
    {
        final ServerPlayer player = event.getSender();

        if (player == null)
        {
            return;
        }

        final Level level = player.level();
        level.playSound(null, player.getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 100, 1F);

        float launchYaw = -10;
        for (int i = 0; i < 3; i++)
        {
            Cyrobolt cyrobolt = new Cyrobolt(player, level, 3, 250);
            cyrobolt.shootFromRotation(player, player.getXRot(), player.getYRot() + launchYaw, 0.0F, 5.0F, 0.5F);
            level.addFreshEntity(cyrobolt);
            launchYaw += 10;
        }

    }

    @Override
    public void onKeyPressClient(InputEvent.Key event)
    {

    }

    @Override
    public double cooldown() {
        return 5;
    }

    @Override
    public Component abilityName() {
        return Component.literal("Cyro-blast").withStyle(ChatFormatting.AQUA);
    }
}

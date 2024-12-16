package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.entity.bmob.projectiles.Cryobolt;
import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties and abilities of a {@link CryoniteSwordItem}</li>
 * <li>Defines the recipe ingredients for a {@link CryoniteSwordItem} from the Forge</li>
 * <li>A {@link CryoniteSwordItem} is-a {@link BMOBSwordItem}</li>
 * <li>A {@link CryoniteSwordItem} is-a {@link AbilityItem}</li>
 * @author Allen Malo
 */
public final class CryoniteSwordItem extends BMOBSwordItem implements AbilityItem, HandheldItem {

    /**
     * Creates a new {@link CryoniteSwordItem}
     * @param pAttackDamageModifier Any additional attack damage to add to the sword
     * @param pAttackSpeedModifier The attack speed of the sword
     * @param pProperties The {@link Item.Properties} of the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the item
     */
    public CryoniteSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBToolTiers.CYRONITE, pAttackDamageModifier, pAttackSpeedModifier, pProperties, tags);
    }

    @Override
    public Item mainItem() {
        return Items.DIAMOND_SWORD;
    }

    @Override
    public Ingredient miscItems() {
        return Ingredient.of(BMOBItems.CRYONITE_SHARD.get());
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

    @Override
    public void onKeyPressServer(NetworkEvent.Context event) {
        final ServerPlayer player = event.getSender();

        if (player == null)
        {
            return;
        }

        final Level level = player.level();

        level.playSound(null, player.getOnPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 100, 1);

        Cryobolt cryobolt = new Cryobolt(player, level, 5, 200);
        cryobolt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5.0F, 1.0F);

        level.addFreshEntity(cryobolt);
    }

    @Override
    public void onKeyPressClient(InputEvent.Key event) {

    }

    @Override
    public double cooldown() {
        return 15;
    }

    @Override
    public Component abilityName() {
        return Component.literal("Cryobolt").withStyle(ChatFormatting.AQUA);
    }
}

package net.laserdiamond.burningminesofbelow.item.equipment.tools;

import net.laserdiamond.burningminesofbelow.item.AbilityItem;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

import java.util.*;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Defines the properties of a {@link BlaziumSwordItem}</li>
 * <li>Defines the recipe ingredients for a {@link BlaziumSwordItem} from the Forge</li>
 * <li>A {@link BlaziumSwordItem} is-a {@link BMOBSwordItem}</li>
 * <li>A {@link BlaziumSwordItem} is-a {@link AbilityItem}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public final class BlaziumSwordItem extends BMOBSwordItem implements AbilityItem {

    /**
     * Creates a new {@link BlaziumSwordItem}
     * @param pAttackDamageModifier Any additional attack damage to add to the sword
     * @param pAttackSpeedModifier The attack speed of the sword
     * @param pProperties The {@link Item.Properties} of the item
     * @param tags A {@link List} of {@link TagKey}s to apply to the sword
     */
    public BlaziumSwordItem(int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, List<TagKey<Item>> tags) {
        super(BMOBToolTiers.BLAZIUM, pAttackDamageModifier, pAttackSpeedModifier, pProperties, tags);
    }

    /**
     * Called when a {@link Player} left clicks an {@link Entity} with this item
     * @param stack The {@link ItemStack} of this item
     * @param player The {@link Player} left-clicking
     * @param entity The {@link Entity} that was left-clicked
     * @return False
     */
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
    public void onKeyPressServer(NetworkEvent.Context event)
    {
        final ServerPlayer player = event.getSender();

        if (player == null)
        {
            return;
        }

        BlaziumFireBallAbility.INSTANCE.setAbilityActive(player, true); // Ability is now active
    }

    @Override
    public void onKeyPressClient(InputEvent.Key event) {}

    @Override
    public double cooldown() {
        return 50 + BlaziumFireBallAbility.ABILITY_DURATION;
    }

    @Override
    public Component abilityName() {
        return Component.literal("Blaze Barrage").withStyle(ChatFormatting.GOLD);
    }


    /**
     * <p>Version/date: 12/16/24</p>
     * <p>Responsibilities of class:</p>
     * <li>Manage the Blazium Sword's fireball ability</li>
     * <li>Help determine when the ability is active</li>
     * <li>Keep count of the amount of fireballs launched</li>
     * @author Allen Malo
     */
    public static class BlaziumFireBallAbility
    {
        private final HashMap<UUID, Boolean> abilityActiveMap; // A BlaziumFireBallAbility has-a HashMap (one-to-many)
        private final HashMap<UUID, Integer> abilityFireBallLaunchedMap; // A BlaziumFireBallAbility has-a HashMap (one-to-many)
        private final HashMap<UUID, Integer> abilityIntervalMap; // A BlaziumFireBallAbility has-a HashMap (one-to-many)

        /**
         * The maximum amount of fireballs to launch
         */
        public static final int MAX_FIRE_BALLS = 3;

        /**
         * The time gap in ticks between when each fireball is to be launched
         */
        public static final int FIRE_BALL_LAUNCH_INTERVAL = 5;

        /**
         * The total amount of time in ticks it takes to launch all fireballs from the ability
         */
        public static final int ABILITY_DURATION = (MAX_FIRE_BALLS - 1) * FIRE_BALL_LAUNCH_INTERVAL;

        /**
         * The instance of the {@link BlaziumFireBallAbility} class.
         * There should only be one instance of this class, since we want
         * to access the same fields/methods each time we access an object
         * instance of this class.
         */
        public static final BlaziumFireBallAbility INSTANCE = new BlaziumFireBallAbility();

        /**
         * Creates a new {@link BlaziumFireBallAbility}
         */
        private BlaziumFireBallAbility()
        {
            this.abilityActiveMap = new HashMap<>();
            this.abilityFireBallLaunchedMap = new HashMap<>();
            this.abilityIntervalMap = new HashMap<>();
        }

        /**
         * Sets the ability to be active for the player
         * @param player The player to set the ability active for
         * @param abilityActive Whether to set the ability to be active or not
         */
        public void setAbilityActive(Player player, boolean abilityActive)
        {
            this.abilityActiveMap.put(player.getUUID(), abilityActive);
        }

        /**
         * Checks if the ability is active for the player
         * @param player The player to check
         * @return False if the player's UUID is not stored or if false is stored in the HashMap. Only returns true if true is stored in the HashMap
         */
        public boolean isAbilityActive(Player player)
        {
            if (this.abilityActiveMap.get(player.getUUID()) == null || !this.abilityActiveMap.containsKey(player.getUUID()) || !this.abilityActiveMap.get(player.getUUID()))
            {
                return false;
            }
            return this.abilityActiveMap.get(player.getUUID());
        }

        /**
         * Sets the amount of fireballs that have been launched by the ability
         * @param player The player to set the amount for
         * @param fireballsLaunched The amount of fireballs launched
         */
        public void setFireBallsLaunched(Player player, int fireballsLaunched)
        {
            this.abilityFireBallLaunchedMap.put(player.getUUID(), Math.max(Math.min(MAX_FIRE_BALLS, fireballsLaunched), 0));
        }

        /**
         * Checks if the player has launched a fireball
         * @param player The player to check
         * @return False if the player's UUID is not stored or if 0 is stored in the HashMap for the player's UUID. Only returns true if the value stored under the player's UUID in the HashMap is greater than 0
         */
        public boolean hasLaunchedFireball(Player player)
        {
            if (this.abilityFireBallLaunchedMap.get(player.getUUID()) == null || !this.abilityFireBallLaunchedMap.containsKey(player.getUUID()) || this.abilityFireBallLaunchedMap.get(player.getUUID()) == 0)
            {
                return false;
            }
            return true;
        }

        /**
         * Checks if the player has launched the maximum amount of fireballs from the ability
         * @param player The player to check
         * @return Only returns true if the player has launched the maximum amount of fireballs. False otherwise
         */
        public boolean hasLaunchedMaxFireBalls(Player player)
        {
            if (this.abilityFireBallLaunchedMap.get(player.getUUID()) != null && this.abilityFireBallLaunchedMap.containsKey(player.getUUID()) && this.abilityFireBallLaunchedMap.get(player.getUUID()) == MAX_FIRE_BALLS)
            {
                return true;
            }
            return false;
        }

        /**
         * Gets the amount of fireballs launched by the player
         * @param player The player to check
         * @return The amount of fireballs launched by the player. Returns 0 if the player's UUID does not exist in the HashMap
         */
        public int getFireBallsLaunched(Player player)
        {
            if (this.abilityFireBallLaunchedMap.get(player.getUUID()) != null && this.abilityFireBallLaunchedMap.containsKey(player.getUUID()))
            {
                return this.abilityFireBallLaunchedMap.get(player.getUUID());
            }
            return 0;
        }

        /**
         * Sets the time in ticks to launch the next fireball
         * @param player The player to set the time for
         * @param ticksToNextLaunch The time in ticks for the next fireball to launch
         */
        public void setTickTimeToNextLaunch(Player player, int ticksToNextLaunch)
        {
            this.abilityIntervalMap.put(player.getUUID(), ticksToNextLaunch);
        }

        /**
         * Gets the time in ticks for when the next fireball will be launched
         * @param player The player to set the time for
         * @return The time in ticks for when the next fireball will be launched. Returns 0 if the player's UUID is not stored in the HashMap
         */
        public int getTickTimeToNextLaunch(Player player)
        {
            if (this.abilityIntervalMap.get(player.getUUID()) != null && this.abilityIntervalMap.containsKey(player.getUUID()))
            {
                return this.abilityIntervalMap.get(player.getUUID());
            }
            return 0;
        }
    }
}

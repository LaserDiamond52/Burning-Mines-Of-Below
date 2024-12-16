package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.entity.AbstractForgeBlockEntity;
import net.laserdiamond.burningminesofbelow.screen.BMOBMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates and runs the functionality of the Forge's inventory, allowing {@link Player}s to place and remove items from it</li>
 * <li>A {@link ForgeMenu} is-a {@link AbstractContainerMenu}</li>
 * @author Allen Malo
 */
public class ForgeMenu extends AbstractContainerMenu {

    /**
     * The {@link AbstractForgeBlockEntity} of the {@link ForgeMenu}
     */
    private final AbstractForgeBlockEntity be; // A ForgeMenu has-a AbstractForgeBlockEntity

    /**
     * The {@link Level} the {@link ForgeMenu} is on
     */
    private final Level level; // A ForgeMenu has-a Level

    /**
     * The {@link ContainerData} of the {@link ForgeMenu}
     */
    private final ContainerData containerData; // A ForgeMenu has-a Level

    /**
     * The {@link SlotItemHandler}s of the {@link ForgeMenu}
     */
    private SlotItemHandler mainInput, miscInput, fuelInput, output; // A ForgeMenu has-a SlotItemHandler (one-to-many)

    /**
     * Creates a new {@link ForgeMenu}
     * @param containerId The container ID
     * @param inventory The {@link Inventory} to create the {@link ForgeMenu} with
     * @param friendlyByteBuf The {@link FriendlyByteBuf} to read the {@link net.minecraft.core.BlockPos} from
     */
    public ForgeMenu(int containerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, inventory, inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()), new SimpleContainerData(6));

    }

    /**
     * Creates a new {@link ForgeMenu}
     * @param containerId The container ID
     * @param inventory The {@link Inventory} to create the {@link ForgeMenu} with
     * @param blockEntity The {@link BlockEntity} of the {@link ForgeMenu}
     * @param containerData The {@link ContainerData} for the {@link ForgeMenu}
     */
    public ForgeMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData containerData) {
        super(BMOBMenuTypes.FORGE_MENU.get(), containerId);

        checkContainerSize(inventory, 4); // Set the container size to 4, since we are going to have 4 slots
        this.be = ((AbstractForgeBlockEntity) blockEntity);
        this.level = inventory.player.level();
        this.containerData = containerData;

        this.addPlayerInventory(inventory); // Add the player's inventory
        this.addPlayerHotbar(inventory); // Add the player's hotbar

        // Create the item slots for the menu
        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler ->
        {
            this.mainInput = new ForgeMainItemSlot(iItemHandler, 0, 58, 17);
            this.addSlot(this.mainInput);
            this.miscInput = new ForgeMiscItemSlot(iItemHandler, 1, 83, 17);
            this.addSlot(this.miscInput);
            this.fuelInput = new ForgeFuelItemSlot(iItemHandler, 2, 71, 56);
            this.addSlot(this.fuelInput);
            this.output = new ResultSlot(iItemHandler, 3, 131, 35);
            this.addSlot(this.output);
        });

        this.addDataSlots(containerData);
    }

    /**
     * Determines if the Forge is currently working on a recipe
     * @return True if the progress towards a new recipe is greater than 0
     */
    public boolean isForging()
    {
        return this.containerData.get(0) > 0;
    }

    /**
     * Gets the current progress towards completion of the current recipe scaled for use with drawing the arrow in the GUI
     * @return The progress scaled for use with drawing the arrow indicating recipe progress
     */
    public int getScaledProgress()
    {
        int progress = this.containerData.get(0);
        int maxProgress = this.containerData.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    /**
     * Gets the amount of fuel in the Forge scaled for use with drawing the amount of fuel remaining in the GUI
     * @param fuelDataInt The index of the current amount of fuel from the {@link ContainerData}
     * @param maxFuelDataInt The index of the maximum amount of fuel from the {@link ContainerData}
     * @return The amount of fuel in the Forge scaled fpr use with drawing the amount of fuel remaining
     */
    public int getScaledFuelLevel(int fuelDataInt, int maxFuelDataInt)
    {
        int fuelLevel = this.containerData.get(fuelDataInt);
        int maxFuelLevel = this.containerData.get(maxFuelDataInt);
        int fuelLevelSize = ForgeScreen.FUEL_BAR_LENGTH;

        return (maxFuelLevel != 0 && fuelLevel != 0) ? (fuelLevel * fuelLevelSize / maxFuelLevel) : 0;
    }

    /**
     * The amount of hotbar slots the player has
     */
    private static final int HOTBAR_SLOT_COUNT = 9;

    /**
     * The amount of rows in a player's inventory
     */
    private static final int PLAYER_INV_ROW_COUNT = 3;

    /**
     * The amount of columns in a player's inventory
     */
    private static final int PLAYER_INV_COLUMN_COUNT = 9;

    /**
     * Gets the total slot count in the player's inventory
     */
    private static final int PLAYER_INV_SLOT_COUNT = PLAYER_INV_COLUMN_COUNT * PLAYER_INV_ROW_COUNT;

    /**
     * Gets the total amount of slots the player has in inventory, including the hotbar
     */
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;

    /**
     * The first slot index of the player's inventory
     */
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;

    /**
     * The first slot index of the Forge relative to the player's inventory
     */
    private static final int FORGE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    /**
     * The amount of slots in the Forge's inventory
     */
    private static final int FORGE_INVENTORY_SLOT_COUNT = 4;

    /**
     * Creates the quick move functionality within the inventory for the {@link Player}.
     * This is what controls a {@link Player}'s ability to shift-click items in the inventory.
     * @param player The {@link Player} in the inventory
     * @param i The index of the slot that was clicked
     * @return The {@link ItemStack} that was in the slot clicked
     */
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        Slot sourceSlot = slots.get(i);
        if (!sourceSlot.hasItem())
        {
            return ItemStack.EMPTY;
        }
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copySourceStack = sourceStack.copy();

        // Check if player clicked on a Vanilla container slot
        if (i < FORGE_INVENTORY_FIRST_SLOT_INDEX)
        {
            // Player clicked in a vanilla container slot, so merge the item stack into the Forge inventory
            if (!this.moveItemStackTo(sourceStack, FORGE_INVENTORY_FIRST_SLOT_INDEX, FORGE_INVENTORY_FIRST_SLOT_INDEX + FORGE_INVENTORY_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;
            }
        } else if (i < FORGE_INVENTORY_FIRST_SLOT_INDEX + FORGE_INVENTORY_SLOT_COUNT)
        {
            // Player clicked in a Forge inventory slot, so merge the item stack into the player's inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;
            }
        } else
        {
            // Invalid slot index was clicked
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount() == 0)
        {
            sourceSlot.set(ItemStack.EMPTY);
        } else
        {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(player, sourceStack);
        return copySourceStack;
    }

    /**
     * Determines if the {@link Player} can still access this menu
     * @param player The {@link Player} accessing the menu
     * @return True if the block is still valid
     */
    @Override
    public boolean stillValid(Player player) {
        return stillValid(BMOBBlocks.FORGE_LEVEL_1.get(), player) || stillValid(BMOBBlocks.FORGE_LEVEL_2.get(), player) || stillValid(BMOBBlocks.FORGE_LEVEL_3.get(), player);
    }

    /**
     * Determines if the {@link Player} still has valid access to this menu
     * @param block The {@link Block} of the Forge block
     * @param player The {@link Player} attempted to get access
     * @return True if the {@link Player} should have access
     */
    private boolean stillValid(Block block, Player player)
    {
        return stillValid(ContainerLevelAccess.create(this.level, this.be.getBlockPos()), player, block);
    }

    /**
     * Adds the player's inventory to the {@link ForgeMenu} view
     * @param inventory The {@link Inventory} to add to
     */
    private void addPlayerInventory(Inventory inventory)
    {
        for (int i = 0; i < PLAYER_INV_ROW_COUNT; i++)
        {
            for (int j = 0; j < PLAYER_INV_COLUMN_COUNT; j++)
            {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    /**
     * Adds the player's hotbar to the {@link ForgeMenu} view
     * @param inventory The {@link Inventory} to add to
     */
    private void addPlayerHotbar(Inventory inventory)
    {
        for (int i = 0; i < HOTBAR_SLOT_COUNT; i++)
        {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
}

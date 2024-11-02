package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.block.BMOBBlock;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.laserdiamond.burningminesofbelow.block.ForgeBlock;
import net.laserdiamond.burningminesofbelow.block.entity.ForgeBlockEntity;
import net.laserdiamond.burningminesofbelow.item.BMOBItem;
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

public class ForgeMenu extends AbstractContainerMenu {

    private final ForgeBlockEntity be;
    private final Level level;
    private final ContainerData containerData;
    private SlotItemHandler mainInput, miscInput, fuelInput, output;

    public ForgeMenu(int containerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf)
    {
        this(containerId, inventory, inventory.player.level().getBlockEntity(friendlyByteBuf.readBlockPos()), new SimpleContainerData(6));

    }

    public ForgeMenu(int containerId, Inventory inventory, BlockEntity blockEntity, ContainerData containerData) {
        super(BMOBMenuTypes.FORGE_MENU.get(), containerId);

        checkContainerSize(inventory, 4);
        this.be = ((ForgeBlockEntity) blockEntity);
        this.level = inventory.player.level();
        this.containerData = containerData;

        this.addPlayerInventory(inventory);
        this.addPlayerHotbar(inventory);

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

    public boolean isForging()
    {
        return this.containerData.get(0) > 0;
    }

    public int getScaledProgress()
    {
        int progress = this.containerData.get(0);
        int maxProgress = this.containerData.get(1);
        int progressArrowSize = 24;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuelLevel(int fuelDataInt, int maxFuelDataInt)
    {
        int fuelLevel = this.containerData.get(fuelDataInt);
        int maxFuelLevel = this.containerData.get(maxFuelDataInt);
        int fuelLevelSize = ForgeScreen.FUEL_BAR_LENGTH;

        return (maxFuelLevel != 0 && fuelLevel != 0) ? (fuelLevel * fuelLevelSize / maxFuelLevel) : 0;
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INV_ROW_COUNT = 3;
    private static final int PLAYER_INV_COLUMN_COUNT = 9;
    private static final int PLAYER_INV_SLOT_COUNT = PLAYER_INV_COLUMN_COUNT * PLAYER_INV_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INV_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int FORGE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int FORGE_INVENTORY_SLOT_COUNT = 4;

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

    @Override
    public boolean stillValid(Player player) {
        return stillValid(BMOBBlocks.FORGE_LEVEL_1.get(), player) || stillValid(BMOBBlocks.FORGE_LEVEL_2.get(), player) || stillValid(BMOBBlocks.FORGE_LEVEL_3.get(), player);
    }

    private boolean stillValid(Block block, Player player)
    {
        return stillValid(ContainerLevelAccess.create(this.level, this.be.getBlockPos()), player,
                block);
    }

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

    private void addPlayerHotbar(Inventory inventory)
    {
        for (int i = 0; i < HOTBAR_SLOT_COUNT; i++)
        {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }
}

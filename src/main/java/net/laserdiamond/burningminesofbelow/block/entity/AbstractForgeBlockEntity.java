package net.laserdiamond.burningminesofbelow.block.entity;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.laserdiamond.burningminesofbelow.recipe.ForgeRecipe;
import net.laserdiamond.burningminesofbelow.screen.forge.ForgeMenu;
import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractForgeBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(4);

    public static final int MAIN_ITEM_INPUT_SLOT = 0;
    public static final int MISC_ITEM_INPUT_SLOT = 1;
    public static final int FUEL_ITEM_INPUT_SLOT = 2;
    public static final int OUTPUT_SLOT = 3;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final ContainerData containerData;
    private final int forgeLevel;
    private final int[] progress = new int[]{0, 100};
    private final int[] heatFuelLevel = new int[]{0, 100};
    private final int[] freezeFuelLevel = new int[]{0, 100};

    public AbstractForgeBlockEntity(BlockEntityType<? extends AbstractForgeBlockEntity> forgeBe, BlockPos blockPos, BlockState blockState, int forgeLevel)
    {
        super(forgeBe, blockPos, blockState);
        this.forgeLevel = forgeLevel;
        this.heatFuelLevel[1] = ForgeRecipe.FORGE_LEVEL_MAX_FUELS.get(forgeLevel)[0];
        this.freezeFuelLevel[1] = ForgeRecipe.FORGE_LEVEL_MAX_FUELS.get(forgeLevel)[1];
        this.containerData = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i)
                {
                    case 0 -> AbstractForgeBlockEntity.this.progress[0];
                    case 1 -> AbstractForgeBlockEntity.this.progress[1];
                    case 2 -> AbstractForgeBlockEntity.this.heatFuelLevel[0];
                    case 3 -> AbstractForgeBlockEntity.this.heatFuelLevel[1];
                    case 4 -> AbstractForgeBlockEntity.this.freezeFuelLevel[0];
                    case 5 -> AbstractForgeBlockEntity.this.freezeFuelLevel[1];
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i)
                {
                    case 0 -> AbstractForgeBlockEntity.this.progress[0] = i1;
                    case 1 -> AbstractForgeBlockEntity.this.progress[1] = i1;
                    case 2 -> AbstractForgeBlockEntity.this.heatFuelLevel[0] = i1;
                    case 3 -> AbstractForgeBlockEntity.this.heatFuelLevel[1] = i1;
                    case 4 -> AbstractForgeBlockEntity.this.freezeFuelLevel[0] = i1;
                    case 5 -> AbstractForgeBlockEntity.this.freezeFuelLevel[1] = i1;
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemStackHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
    }

    /**
     * Determines the items to drop from the Forge when the block is broken. When the Forge is mined/destroyed, all the items inside its container will be dropped.
     */
    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < this.itemStackHandler.getSlots(); i++)
        {
            inv.setItem(i, this.itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block." + BurningMinesOfBelow.MODID + ".forge_level_" + this.forgeLevel);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ForgeMenu(i, inventory, this, this.containerData);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", this.itemStackHandler.serializeNBT());
        pTag.putInt("forge.progress", this.progress[0]);
        pTag.putInt("forge.heat_fuel_level", this.heatFuelLevel[0]);
        pTag.putInt("forge.freeze_fuel_level", this.freezeFuelLevel[0]);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));

        this.progress[0] = pTag.getInt("forge.progress");
        this.heatFuelLevel[0] = pTag.getInt("forge.heat_fuel_level");
        this.freezeFuelLevel[0] = pTag.getInt("forge.freeze_fuel_level");
    }

    /**
     * The tick method that runs every in-game tick for the Forge. This method is responsible for the functionality of the block
     * @param level The world/level the block is in
     * @param blockPos The position of the block
     * @param blockState The state of the block
     */
    public void tick(Level level, BlockPos blockPos, BlockState blockState)
    {
        ItemStack fuelItemStack = this.itemStackHandler.getStackInSlot(FUEL_ITEM_INPUT_SLOT);

        boolean heatFuelConsumed = false;
        boolean freezeFuelConsumed = false;
        Item returnItem = Items.AIR;
        int itemCount = 0;
        if (this.heatFuelLevel[0] < this.heatFuelLevel[1])
        {
            if (this.itemStackHandler.getStackInSlot(FUEL_ITEM_INPUT_SLOT).is(BMOBTags.Items.FORGE_HEAT_FUEL))
            {
                int fuelAmount = BMOBItems.HEAT_FUEL_ITEMS.getFuelAmount(fuelItemStack.getItem());
                this.heatFuelLevel[0] = Math.min(this.heatFuelLevel[1], this.heatFuelLevel[0] + fuelAmount); // Add fuel
                returnItem = fuelItemStack.getItem(); // The item to return is assumed to be the fuel item
                itemCount = fuelItemStack.getCount() - 1; // The new amount we want in the fuel slot
                heatFuelConsumed = true;
            }
        }
        if (this.freezeFuelLevel[0] < this.freezeFuelLevel[1])
        {
            if (this.itemStackHandler.getStackInSlot(FUEL_ITEM_INPUT_SLOT).is(BMOBTags.Items.FORGE_FREEZE_FUEL))
            {
                int fuelAmount = BMOBItems.FREEZE_FUEL_ITEMS.getFuelAmount(fuelItemStack.getItem());
                this.freezeFuelLevel[0] = Math.min(this.freezeFuelLevel[1], this.freezeFuelLevel[0] + fuelAmount); // Add fuel
                returnItem = fuelItemStack.getItem(); // The item to return is assumed to be the fuel item
                itemCount = fuelItemStack.getCount() - 1; // The new amount we want in the fuel slot
                freezeFuelConsumed = true;
            }
        }

        if (heatFuelConsumed || freezeFuelConsumed) // Consume items only if the fuel has been consumed
        {
            if (fuelItemStack.getItem() instanceof BucketItem) // If a fuel item that is a bucket is placed...
            {
                this.itemStackHandler.setStackInSlot(FUEL_ITEM_INPUT_SLOT, new ItemStack(Items.BUCKET)); // Replace the item in the slot with an empty bucket
            } else
            {
                if (itemCount <= 0)
                {
                    returnItem = Items.AIR; // Set the item to air if the consumed amount is 0 or less
                }
                ItemStack returnStack = new ItemStack(returnItem);
                returnStack.setCount(itemCount);
                this.itemStackHandler.setStackInSlot(FUEL_ITEM_INPUT_SLOT, returnStack);
            }
        }

        if (this.hasRecipeJson())
        {
            Optional<ForgeRecipe> recipe = this.getCurrentRecipe();
            if (recipe.isEmpty())
            {
                return; // Recipe is empty. End method
            }
            this.increaseForgingProgress(recipe.get());
            setChanged(level, blockPos, blockState);

            if (this.hasProgressFinished())
            {
                this.forgeItem(recipe.get());
                this.resetForgeProgress();
            }
        } else
        {
            this.resetForgeProgress();
        }
    }

    /**
     * Resets the crafting progress of the Forge
     */
    private void resetForgeProgress()
    {
        this.progress[0] = 0;
    }

    /**
     * Consumes the appropriate amount of main/misc items to output the result item of the recipe
     * @param recipe The {@link ForgeRecipe} being made
     */
    private void forgeItem(ForgeRecipe recipe)
    {
        ItemStack result = recipe.getResultItem(null);

        this.itemStackHandler.extractItem(MAIN_ITEM_INPUT_SLOT, 1, false);
        this.itemStackHandler.extractItem(MISC_ITEM_INPUT_SLOT, 1, false);

        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    /**
     * Checks if the progress for the current Forge recipe is completed
     * @return True if the current progress is equal to or greater than the maximum progress, false otherwise
     */
    private boolean hasProgressFinished()
    {
        return progress[0] >= progress[1];
    }

    /**
     * Increments the Forge crafting progress. Once the progress is complete, the heat/freeze fuel levels are deducted depending on the fuel cost of the recipe that was just completed
     * @param recipe The recipe being made
     */
    private void increaseForgingProgress(ForgeRecipe recipe)
    {
        progress[0]++;
        if (hasProgressFinished() && this.heatFuelLevel[0] >= recipe.getHeatFuelCost())
        {
            this.heatFuelLevel[0] -= recipe.getHeatFuelCost();
        }
        if (hasProgressFinished() && this.freezeFuelLevel[0] >= recipe.getFreezeFuelCost())
        {
            this.freezeFuelLevel[0] -= recipe.getFreezeFuelCost();
        }
    }

    /**
     * Determines if a valid recipe has been created in the Forge based on the recipe Json files, the fuel levels in the Forge, and the Forge Level of this Forge
     * @return True if a valid recipe was found, false otherwise
     */
    private boolean hasRecipeJson()
    {
        Optional<ForgeRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty())
        {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(null);
        boolean isReqLevel = this.forgeLevel >= recipe.get().getForgeLevel(); // Check that the Forge outputting the recipe is at the required level to do so
        boolean hasEnoughHeatFuel = this.heatFuelLevel[0] >= recipe.get().getHeatFuelCost(); // Check that there is enough heat fuel
        boolean hasEnoughFreezeFuel = this.freezeFuelLevel[0] >= recipe.get().getFreezeFuelCost(); // Check that there is enough freeze fuel

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem()) && isReqLevel && hasEnoughHeatFuel && hasEnoughFreezeFuel;
    }

    /**
     * Gets the current recipe being made based on the items in the ingredient slots
     * @return An {@link Optional} that may or may not contain a {@link ForgeRecipe}
     */
    private Optional<ForgeRecipe> getCurrentRecipe()
    {
        SimpleContainer inv = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < this.itemStackHandler.getSlots(); i++)
        {
            inv.setItem(i, this.itemStackHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(ForgeRecipe.Type.INSTANCE, inv, this.level);
    }

    /**
     * Determines if the Forge is capable of outputting an item into the output slot.
     * @param item The item present in the output slot
     * @return True if the output slot is either empty, or if the item in the output slot is stackable with the crafting result of the previously completed {@link ForgeRecipe}
     */
    private boolean canInsertItemIntoOutputSlot(Item item)
    {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    /**
     * Determines whether there is enough room in the output slot for an additional result item. The amount of items in the output slot cannot exceed the maximum stack size of the item currently in the output slot
     * @param count The amount of items to add to the result slot
     * @return True if the Forge can output items to the output slot, false otherwise
     */
    private boolean canInsertAmountIntoOutputSlot(int count)
    {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}

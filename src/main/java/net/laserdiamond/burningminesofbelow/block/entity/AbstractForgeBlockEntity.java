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

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and run major functionality of the Forge Block</li>
 * <li>Acts as a base class for creating Forge Blocks</li>
 * <li>An {@link AbstractForgeBlockEntity} is-a {@link BlockEntity}</li>
 * <li>An {@link AbstractForgeBlockEntity} is-a {@link MenuProvider}</li>
 * <li>All Aggregation relationships carry over to the subclasses of this class</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public abstract class AbstractForgeBlockEntity extends BlockEntity implements MenuProvider {

    /**
     * The {@link ItemStackHandler} of the block entity. Helps handle items that can be stored in the block entity
     */
    protected final ItemStackHandler itemStackHandler = new ItemStackHandler(4); // AbstractForgeBlockEntity has-a ItemStackHandler

    /**
     * The inventory index for the main item input slot
     */
    public static final int MAIN_ITEM_INPUT_SLOT = 0;

    /**
     * The inventory index for the miscellaneous item input slot
     */
    public static final int MISC_ITEM_INPUT_SLOT = 1;

    /**
     * The inventory index for the fuel item input slot
     */
    public static final int FUEL_ITEM_INPUT_SLOT = 2;

    /**
     * the inventory index for the recipe output slot.
     */
    public static final int OUTPUT_SLOT = 3;

    /**
     * The {@link LazyOptional} {@link IItemHandler}.
     */
    protected LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty(); // AbstractForgeBlockEntity has-a LazyOptional

    /**
     * The {@link ContainerData} for the block entity. Responsible for storing information about recipe progress and fuel
     */
    protected final ContainerData containerData; // AbstractForgeBlockEntity ha-a ContainerData

    /**
     * The current level of the Forge
     */
    protected final int forgeLevel;

    /**
     * Contains the current progress towards completion of a recipe, and the amount of progress needed for recipe completion.
     */
    protected final int[] progress = new int[]{0, 100};

    /**
     * Contains the current heat fuel level and the maximum heat fuel that can be stored.
     */
    protected final int[] heatFuelLevel = new int[]{0, 100};

    /**
     * Contains the current freeze fuel level and the maximum freeze fuel that can be stored.
     */
    protected final int[] freezeFuelLevel = new int[]{0, 100};

    /**
     * Creates a new {@link AbstractForgeBlockEntity}
     * @param forgeBe The {@link BlockEntityType} of the {@link AbstractForgeBlockEntity}
     * @param blockPos The {@link BlockPos} of the block
     * @param blockState The {@link BlockState} of the block
     * @param forgeLevel The level of the Forge
     */
    public AbstractForgeBlockEntity(BlockEntityType<? extends AbstractForgeBlockEntity> forgeBe, BlockPos blockPos, BlockState blockState, int forgeLevel)
    {
        super(forgeBe, blockPos, blockState); // Call parent constructor
        this.forgeLevel = forgeLevel; // Indicate the forge level
        this.heatFuelLevel[1] = ForgeRecipe.FORGE_LEVEL_MAX_FUELS.get(forgeLevel)[0]; // Set the max heat fuel capacity
        this.freezeFuelLevel[1] = ForgeRecipe.FORGE_LEVEL_MAX_FUELS.get(forgeLevel)[1]; // Set the max freeze fuel capacity
        this.containerData = new ContainerData() // Create new ContainerData
        {
            /**
             * Gets a value stored in the container data. This could be value indicating recipe progress, fuel amounts, etc.
             * @param index The index to get a value from
             * @return A value about the block entity's container data
             */
            @Override
            public int get(int index)
            {
                return switch (index)
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

            /**
             * Sets value stored in the container data.
             * @param index The index to set a value to
             * @param value The value to assign in the container data
             */
            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> AbstractForgeBlockEntity.this.progress[0] = value;
                    case 1 -> AbstractForgeBlockEntity.this.progress[1] = value;
                    case 2 -> AbstractForgeBlockEntity.this.heatFuelLevel[0] = value;
                    case 3 -> AbstractForgeBlockEntity.this.heatFuelLevel[1] = value;
                    case 4 -> AbstractForgeBlockEntity.this.freezeFuelLevel[0] = value;
                    case 5 -> AbstractForgeBlockEntity.this.freezeFuelLevel[1] = value;
                }
            }

            /**
             *
             * @return The amount of variables being stored in the container data
             */
            @Override
            public int getCount()
            {
                return 6; // We are storing 6 variables in the block entity, so return 6
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

    /**
     * The display name of the block entity. This is shown in the Forge's GUI
     * @return A {@link Component} that defines the translation key for the display name to display in the inventory.
     */
    @Override
    public Component getDisplayName() {
        return Component.translatable("block." + BurningMinesOfBelow.MODID + ".forge_level_" + this.forgeLevel);
    }

    /**
     * Create the {@link ForgeMenu} for the {@link Player} to use
     * @param i The id of the inventory
     * @param inventory The current inventory
     * @param player The {@link Player} attempting to open the {@link ForgeMenu}
     * @return Am {@link AbstractContainerMenu} of the {@link ForgeMenu}
     */
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ForgeMenu(i, inventory, this, this.containerData);
    }

    /**
     * Saves data about the block entity.
     * @param pTag The {@link CompoundTag} to save data to
     */
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", this.itemStackHandler.serializeNBT()); // Save the items to the CompoundTag
        // Save the recipe progress and fuel levels to the CompoundTag
        pTag.putInt("forge.progress", this.progress[0]);
        pTag.putInt("forge.heat_fuel_level", this.heatFuelLevel[0]);
        pTag.putInt("forge.freeze_fuel_level", this.freezeFuelLevel[0]);
        super.saveAdditional(pTag); // Call superclass method to save all other necessary data
    }

    /**
     * Loads data about the block entity.
     * @param pTag The {@link CompoundTag} to save data to
     */
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag); // Call superclass method to load all other necessary data
        // Set the items in the inventory
        this.itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));

        // Set the current progress to recipe completion and fuel levels to the value saved on the CompoundTag
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
        if (this.heatFuelLevel[0] < this.heatFuelLevel[1]) // Is our current heat fuel less than the max fuel?
        {
            // Not at full fuel
            if (this.itemStackHandler.getStackInSlot(FUEL_ITEM_INPUT_SLOT).is(BMOBTags.Items.FORGE_HEAT_FUEL)) // Is there a heat fuel item in the fuel slot?
            {
                int fuelAmount = BMOBItems.HEAT_FUEL_ITEMS.getFuelAmount(fuelItemStack.getItem()); // The fuel amount to add
                this.heatFuelLevel[0] = Math.min(this.heatFuelLevel[1], this.heatFuelLevel[0] + fuelAmount); // Add fuel
                returnItem = fuelItemStack.getItem(); // The item to return is assumed to be the fuel item
                itemCount = fuelItemStack.getCount() - 1; // The new amount we want in the fuel slot
                heatFuelConsumed = true; // Fuel is consumed, return true
            }
        }
        if (this.freezeFuelLevel[0] < this.freezeFuelLevel[1]) // Is our current freeze fuel less than the max fuel
        {
            // Not at full fuel
            if (this.itemStackHandler.getStackInSlot(FUEL_ITEM_INPUT_SLOT).is(BMOBTags.Items.FORGE_FREEZE_FUEL)) // Is there a freeze fuel item in the fuel slot?
            {
                int fuelAmount = BMOBItems.FREEZE_FUEL_ITEMS.getFuelAmount(fuelItemStack.getItem()); // The fuel amount to add
                this.freezeFuelLevel[0] = Math.min(this.freezeFuelLevel[1], this.freezeFuelLevel[0] + fuelAmount); // Add fuel
                returnItem = fuelItemStack.getItem(); // The item to return is assumed to be the fuel item
                itemCount = fuelItemStack.getCount() - 1; // The new amount we want in the fuel slot
                freezeFuelConsumed = true; // Fuel is consumed, return true
            }
        }

        if (heatFuelConsumed || freezeFuelConsumed) // Consume items only if the fuel has been consumed
        {
            if (fuelItemStack.getItem() instanceof BucketItem) // If a fuel item that is a bucket is placed...
            {
                this.itemStackHandler.setStackInSlot(FUEL_ITEM_INPUT_SLOT, new ItemStack(Items.BUCKET)); // Replace the item in the slot with an empty bucket
            } else // A bucket item was not placed
            {
                // Are we out of the input item type?
                // If we are, then air should be in the fuel slot now. Otherwise, the item type should still be the fuel item
                if (itemCount <= 0)
                {
                    // Out of items
                    returnItem = Items.AIR; // Set the item to air if the consumed amount is 0 or less
                }
                ItemStack returnStack = new ItemStack(returnItem); // Create the item we want to return.
                returnStack.setCount(itemCount); // Set the new count of the item
                this.itemStackHandler.setStackInSlot(FUEL_ITEM_INPUT_SLOT, returnStack); // Set the item stack in the fuel slot to the new item stack
            }
        }

        if (this.hasRecipeJson()) // Is a recipe present in the inventory?
        {
            Optional<ForgeRecipe> recipe = this.getCurrentRecipe();
            if (recipe.isEmpty()) // Make sure the recipe is not empty/null
            {
                return; // No recipe present. End method
            }
            this.increaseForgingProgress(recipe.get());
            setChanged(level, blockPos, blockState);

            if (this.hasProgressFinished()) // Is the recipe done?
            {
                this.forgeItem(recipe.get()); // Output the item
                this.resetForgeProgress(); // Reset progress
            }
        } else // No recipe present
        {
            this.resetForgeProgress(); // Reset progress
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
        // Get the result item
        ItemStack result = recipe.getResultItem(null);

        // Remove items from the main slot and misc slot
        this.itemStackHandler.extractItem(MAIN_ITEM_INPUT_SLOT, 1, false);
        this.itemStackHandler.extractItem(MISC_ITEM_INPUT_SLOT, 1, false);

        // Set the result item slot to have the result item
        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(), this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
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
        progress[0]++; // Increment progress
        if (hasProgressFinished() && this.heatFuelLevel[0] >= recipe.getHeatFuelCost())
        {
            this.heatFuelLevel[0] -= recipe.getHeatFuelCost(); // Deduct fuel amount based on the fuel cost
        }
        if (hasProgressFinished() && this.freezeFuelLevel[0] >= recipe.getFreezeFuelCost())
        {
            this.freezeFuelLevel[0] -= recipe.getFreezeFuelCost(); // Deduct fuel amount base on the fuel cost
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
            return false; // No recipe. Return false
        }
        ItemStack result = recipe.get().getResultItem(null); // Get the result item
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
     * @return True if the output slot is either empty, or if the item in the output slot is stackable with the crafting result of the previously completed {@link ForgeRecipe}, and doesn't exceed said item's maximum stack size.
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

package net.laserdiamond.burningminesofbelow.screen.forge;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as a result item slot for any inventories that require an output slot for their recipes</li>
 * <li>Does not allow player to put items in this slot. Players can only remove items from this slot</li>
 * <li>A {@link ResultSlot} is-a {@link SlotItemHandler}</li>
 * @author Allen Malo
 */
public class ResultSlot extends SlotItemHandler {

    /**
     * Creates a new {@link ResultSlot}
     * @param itemHandler The {@link IItemHandler} for the slot
     * @param index The index of the slot in the inventory it will be a part of
     * @param xPosition The x position of the slot in the UI
     * @param yPosition The y position of the slot in the UI
     */
    public ResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    /**
     * Determines if the {@link ItemStack} can be placed in this slot
     * @param stack The {@link ItemStack} being placed in this slot
     * @return False. Players should not be able to put items in a {@link ResultSlot}
     */
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}

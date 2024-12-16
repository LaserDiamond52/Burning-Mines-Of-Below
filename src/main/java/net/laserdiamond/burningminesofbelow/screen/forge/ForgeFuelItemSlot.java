package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Acts as the fuel ingredient slot for the Forge crafting menu</li>
 * <li>Only allows items with the {@link BMOBTags.Items#FORGE_HEAT_FUEL} or {@link BMOBTags.Items#FORGE_FREEZE_FUEL} to be inserted in this slot</li>
 * <li>A {@link ForgeFuelItemSlot} is-a {@link SlotItemHandler}</li>
 * @author Allen Malo
 */
public class ForgeFuelItemSlot extends SlotItemHandler {

    /**
     * Creates a new {@link ForgeFuelItemSlot}
     * @param itemHandler The {@link IItemHandler} for the slot
     * @param index The index of the slot in the inventory it will be a part of
     * @param xPosition The x position of the slot in the UI
     * @param yPosition The y position of the slot in the UI
     */
    public ForgeFuelItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    /**
     * Determines if the {@link ItemStack} can be placed in this slot
     * @param stack The {@link ItemStack} being placed in this slot
     * @return True if the {@link ItemStack} has the {@link BMOBTags.Items#FORGE_HEAT_FUEL} or {@link BMOBTags.Items#FORGE_FREEZE_FUEL} tags
     */
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(BMOBTags.Items.FORGE_HEAT_FUEL) || stack.is(BMOBTags.Items.FORGE_FREEZE_FUEL);
    }
}

package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * The fuel ingredient slot for the Forge crafting menu. This item slot only allows items with the Forge Heat Fuel or Forge Freeze Fuel tags to be placed in it
 */
public class ForgeFuelItemSlot extends SlotItemHandler {

    public ForgeFuelItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(BMOBTags.Items.FORGE_HEAT_FUEL) || stack.is(BMOBTags.Items.FORGE_FREEZE_FUEL);
    }
}

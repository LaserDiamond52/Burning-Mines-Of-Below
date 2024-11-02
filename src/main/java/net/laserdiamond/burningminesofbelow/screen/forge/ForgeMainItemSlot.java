package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * The main ingredient input slot for the Forge crafting menu. This item slot only allows items with the Forge Main Ingredient tag to be placed in it
 */
public class ForgeMainItemSlot extends SlotItemHandler {

    public ForgeMainItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(BMOBTags.Items.FORGE_MAIN_INGREDIENT);
    }
}

package net.laserdiamond.burningminesofbelow.screen.forge;

import net.laserdiamond.burningminesofbelow.util.BMOBTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

/**
 * The miscellaneous ingredient input slot for the Forge crafting menu. This item slot only allows items with the Forge Misc Ingredient tag to be placed in it
 */
public class ForgeMiscItemSlot extends SlotItemHandler {

    public ForgeMiscItemSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(BMOBTags.Items.FORGE_MISC_INGREDIENT);
    }
}

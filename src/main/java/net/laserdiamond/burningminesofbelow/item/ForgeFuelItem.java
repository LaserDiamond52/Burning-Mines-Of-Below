package net.laserdiamond.burningminesofbelow.item;

/**
 * Interface used to create fuel items for the Forge for items of this mod
 */
public interface ForgeFuelItem {

    /**
     * The amount of heat fuel this item can add to the Forge
     * @return The amount of heat fuel to add to the Forge
     */
    default int heatFuel()
    {
        return 0;
    }

    /**
     * The amount of freeze fuel this item can add to the Forge
     * @return The amount of heat fuel to add to the Forge
     */
    default int freezeFuel()
    {
        return 0;
    }
}

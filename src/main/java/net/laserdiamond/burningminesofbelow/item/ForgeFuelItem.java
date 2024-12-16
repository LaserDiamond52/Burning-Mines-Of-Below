package net.laserdiamond.burningminesofbelow.item;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Used to help create items that can add fuel to the Forge</li>
 * @author Allen Malo
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

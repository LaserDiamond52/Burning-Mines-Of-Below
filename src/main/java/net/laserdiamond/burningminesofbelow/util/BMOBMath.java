package net.laserdiamond.burningminesofbelow.util;

import java.util.Random;

public class BMOBMath {

    /**
     * Gets a random number between 0 and the max value (inclusive)
     * @param max The largest possible number to get
     * @return A random number within the bounds of 0 and the max number
     */
    public static Integer getRandomInteger(Integer max)
    {
        return new Random().nextInt(max + 1);
    }

    /**
     * Gets the guaranteed amount of times an event from happening.
     * Values less than 0 will only return 0.
     * Values less than 100 will also return 0, since values less than 100 will not guarantee that something happens.
     * Every 100 adds +1 to the return value.
     * This is typically used for stats that have a chance of an event happening, such as dropping a refined ore drop from mining a refined ore block.
     * @param chance The chance of something happening
     * @return An integer representing the amount of times an event is guaranteed to happen.
     */
    public static int getGuaranteedFromChance(double chance)
    {
        return (int) Math.floor(Math.max(chance, 0) / 100);
    }

    /**
     * Gets the chance of an event happening from the last two digits of the chance value.
     * Values less than 0 will only return 0.
     * Values less than 100, yet greater than 0, will return themselves.
     * Values that have 0 as the last two digits will only return 0.
     * This is typically used for stats that have a chance of an event happening, such as dropping a refined ore drop from mining a refined ore block.
     * @param chance The chance of something happening
     * @return The last two digits of the double passed through
     */
    public static int getLastTwoDigitsFromChance(double chance)
    {
        return (int) (Math.max(0, chance) % 100);
    }
}

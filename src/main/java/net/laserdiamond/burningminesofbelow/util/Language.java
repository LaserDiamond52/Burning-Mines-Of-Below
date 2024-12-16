package net.laserdiamond.burningminesofbelow.util;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains the different languages that the local names for assets of this mod can be translated to</li>
 * <li>Currently, the only supported language is English</li>
 * @author Allen Malo
 */
public enum Language {

    EN_US;

    // TODO: Add any other languages here

    /**
     * Gets the name of the language
     * @return The name of the enum entry in lowercase
     */
    public String getName()
    {
        return this.name().toLowerCase();
    }
}

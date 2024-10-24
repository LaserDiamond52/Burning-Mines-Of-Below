package net.laserdiamond.burningminesofbelow.util;

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

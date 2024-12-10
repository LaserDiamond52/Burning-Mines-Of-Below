package net.laserdiamond.burningminesofbelow.util.file.heat;

import net.laserdiamond.burningminesofbelow.util.file.JsonConfig;

public final class HeatConfig extends JsonConfig {

    /**
     * Creates a new {@link HeatConfig} file
     */
    public HeatConfig() {
        super("heat");
    }

    @Override
    public String folderName() {
        return "heat";
    }


}

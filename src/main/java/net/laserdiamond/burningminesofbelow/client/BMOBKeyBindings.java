package net.laserdiamond.burningminesofbelow.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Contains all the key mappings of this mod</li>
 * <li>Registers all the key mappings of this mod</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Custom Thirst System in Forge Minecraft 1.19.1 Modding<a href="https://www.youtube.com/watch?v=NN-k74NMKRc&list=PLKGarocXCE1EquNdk4WsX-VZTmbeOZ7MS">...</a></p>
 */
public final class BMOBKeyBindings {

    /**
     * Instance of {@link BMOBKeyBindings}
     */
    public static final BMOBKeyBindings INSTANCE = new BMOBKeyBindings();

    /**
     * The category string for keys of this mod
     */
    public static final String BURNING_MINES_OF_BELOW_CATEGORY = "key.categories." + BurningMinesOfBelow.MODID;


    /**
     * The ability key {@link KeyMapping}
     */
    public final KeyMapping abilityKey; // BMOBKeyBindings has-a KeyMapping

    /**
     * Creates a new {@link BMOBKeyBindings} object. There should only be one instance of this class across the whole workspace
     */
    private BMOBKeyBindings()
    {
        abilityKey = registerKeyMapping("Cast Ability", "cast_ability", KeyConflictContext.IN_GAME, InputConstants.KEY_R);
    }

    /**
     * Registers a new {@link KeyMapping}
     * @param name The name of the key mapping
     * @param description The local name of the key mapping
     * @param keyConflictContext The {@link KeyConflictContext}
     * @param keyInputConstant The key to press
     * @return The newly created {@link KeyMapping}
     */
    private KeyMapping registerKeyMapping(String name, String description, KeyConflictContext keyConflictContext, int keyInputConstant)
    {
        KeyMapping keyMapping = new KeyMapping("key." + BurningMinesOfBelow.MODID + "." + description,
                keyConflictContext,
                InputConstants.getKey(keyInputConstant, -1),
                BURNING_MINES_OF_BELOW_CATEGORY);
        LanguageRegistry.instance(Language.EN_US).getKeyMappingNameRegistry().addEntry(name, keyMapping);
        return keyMapping;
    }
}

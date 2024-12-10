package net.laserdiamond.burningminesofbelow.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.util.Language;
import net.laserdiamond.burningminesofbelow.util.LanguageRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class BMOBKeyBindings {

    public static final BMOBKeyBindings INSTANCE = new BMOBKeyBindings();

    private BMOBKeyBindings(){}

    public static final String BURNING_MINES_OF_BELOW_CATEGORY = "key.categories." + BurningMinesOfBelow.MODID;

    public final KeyMapping abilityKey = registerKeyMapping("Cast Ability", "cast_ability", KeyConflictContext.IN_GAME, InputConstants.KEY_R);


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

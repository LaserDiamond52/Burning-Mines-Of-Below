package net.laserdiamond.burningminesofbelow.biomes;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BMOBBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, BurningMinesOfBelow.MODID);


    public static void registerBiomes(IEventBus eventBus)
    {
        BIOMES.register(eventBus);
    }
}

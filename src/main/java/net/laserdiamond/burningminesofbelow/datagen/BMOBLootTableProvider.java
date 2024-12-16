package net.laserdiamond.burningminesofbelow.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Creates the loot tables for assets of this mod</li>
 * @author Allen Malo
 */
public class BMOBLootTableProvider {

    /**
     * Creates the loot tables of this mod
     * @param output The {@link PackOutput} of this mod
     * @return The {@link LootTableProvider} with all the loot tables to create
     */
    public static LootTableProvider create(PackOutput output)
    {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(BMOBBlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(BMOBEntityLootTables::new, LootContextParamSets.ENTITY)
        ));
    }
}

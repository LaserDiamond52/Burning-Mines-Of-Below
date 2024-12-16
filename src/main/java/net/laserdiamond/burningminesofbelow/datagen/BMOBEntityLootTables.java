package net.laserdiamond.burningminesofbelow.datagen;

import net.laserdiamond.burningminesofbelow.entity.BMOBEntities;
import net.laserdiamond.burningminesofbelow.item.BMOBItems;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

/**
 * <p>Version/date: 12/16/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Generates all loot tables for the entities of this mod</li>
 * <li>A {@link BMOBEntityLootTables} is-a {@link EntityLootSubProvider}</li>
 * @author Allen Malo
 * @see net.minecraft.data.loot.packs.VanillaEntityLoot
 * @References:
 * <p>KaupenJoe; Forge Modding Tutorials 1.20.X<a href="https://www.youtube.com/watch?v=55qUIf3GMss&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-">...</a></p>
 */
public class BMOBEntityLootTables extends EntityLootSubProvider {

    /**
     * Creates a new {@link BMOBEntityLootTables}
     */
    protected BMOBEntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    /**
     * Creates all the {@link net.minecraft.world.entity.Entity} loot tables
     */
    @Override
    public void generate()
    {
        this.add(BMOBEntities.KING_INFERNIUS.get(), LootTable.lootTable().withPool(this.createConstantLootPoolDrop(BMOBItems.INFERNAL_FLAME.get(), 1.0F)).withPool(this.createLootPoolDrop(BMOBItems.BLAZE_FLAMES.get(), 1.0F, 2.0F, 4.0F, 1.0F, 2.0F)));
        this.add(BMOBEntities.FREEZING_REAPER.get(), LootTable.lootTable().withPool(this.createConstantLootPoolDrop(BMOBItems.FRIGID_CRYONITE_CRYSTAL.get(), 1.0F)).withPool(this.createLootPoolDrop(BMOBItems.CRYONITE_SHARD.get(), 1.0F, 3.0F, 5.0F, 1.0F, 2.0F)));

        this.add(BMOBEntities.MAGNITE_BLAZE.get(), LootTable.lootTable().withPool(this.createLootPoolDrop(Items.BLAZE_ROD, 1.0F, 1.0F, 3.0F, 1.0F, 2.0F)).withPool(this.createLootPoolDrop(BMOBItems.BLAZE_FLAMES.get(), 1.0F, 0.0F, 2.0F, 0.0F, 1.0F)));
        this.add(BMOBEntities.FROZEN_SOUL.get(), LootTable.lootTable().withPool(this.createLootPoolDrop(BMOBItems.FROZEN_SOUL.get(), 1.0F, 1.0F, 5.0F, 0.0F, 1.0F)).withPool(this.createLootPoolDrop(BMOBItems.CRYONITE_SHARD.get(), 1.0F, 0.0F, 1.0F, 0.0F, 0.0F)));
    }

    /**
     * Gets all known {@link EntityType}s in order to make sure all entities have a loot table associated with them
     * @return A {@link Stream} that contains all the {@link EntityType}s of this mod.
     */
    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        // Return a stream of all entities in this mod
        // Need to know what entities need loot tables
        return BMOBEntities.ENTITIES.getEntries().stream().map(RegistryObject::get);
    }

    /**
     * Creates a loot pool drop
     * @param item The {@link Item} to drop
     * @param rolls The amount of rolls for the drop
     * @param minCount The minimum drop count from one roll
     * @param maxCount The maximum drop count from one roll
     * @param lootingMin The minimum looting bonus
     * @param lootingMax The maximum looting bonus
     * @return The {@link LootPool.Builder}
     */
    private LootPool.Builder createLootPoolDrop(Item item, float rolls, float minCount, float maxCount, float lootingMin, float lootingMax)
    {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(rolls)).add(LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(lootingMin, lootingMax)))).when(LootItemKilledByPlayerCondition.killedByPlayer());
    }

    /**
     * Creates a constant loot pool drop
     * @param item the {@link Item} to drop
     * @param amount The amount of the {@link Item} to always drop
     * @return The {@link LootPool.Builder}
     */
    private LootPool.Builder createConstantLootPoolDrop(Item item, float amount)
    {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(ConstantValue.exactly(amount))));
    }
}

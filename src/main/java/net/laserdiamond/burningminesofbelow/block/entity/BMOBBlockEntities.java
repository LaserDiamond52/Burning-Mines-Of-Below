package net.laserdiamond.burningminesofbelow.block.entity;

import net.laserdiamond.burningminesofbelow.BurningMinesOfBelow;
import net.laserdiamond.burningminesofbelow.block.BMOBBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and register new {@link BlockEntityType}s to the mod's block entity registry</li>
 * @author Allen Malo
 */
public class BMOBBlockEntities {

    /**
     * Block Entity registry of this mod
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BurningMinesOfBelow.MODID);

    /**
     * Level 1 Forge block entity
     */
    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel1>> FORGE_LEVEL_1 = registerBlockEntity("forge_1_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel1::new, BMOBBlocks.FORGE_LEVEL_1.get()).build(null));

    /**
     * Level 2 Forge block entity
     */
    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel2>> FORGE_LEVEL_2 = registerBlockEntity("forge_2_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel2::new, BMOBBlocks.FORGE_LEVEL_2.get()).build(null));

    /**
     * Level 3 Forge block entity
     */
    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel3>> FORGE_LEVEL_3 = registerBlockEntity("forge_3_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel3::new, BMOBBlocks.FORGE_LEVEL_3.get()).build(null));

    /**
     * Skull block entity
     */
    public static final RegistryObject<BlockEntityType<BMOBSkullBlockEntity>> SKULL_BLOCK_ENTITY = registerBlockEntity("bmob_skull_block",
            () -> BlockEntityType.Builder.of(BMOBSkullBlockEntity::new, BMOBBlocks.FROZEN_WITHER_SKULL.get(), BMOBBlocks.BLAZE_SKULL.get(), BMOBBlocks.FROZEN_WITHER_SKULL_WALL.get(), BMOBBlocks.BLAZE_SKULL_WALL.get()).build(null));


    /**
     * Registers a new block entity
     * @param localName The local name of the block entity
     * @param blockEntityTypeSupplier the {@link Supplier} for the block entity
     * @return A {@link RegistryObject} representing the newly create block entity
     * @param <BE> The {@link BlockEntity} type
     */
    public static <BE extends BlockEntity> RegistryObject<BlockEntityType<BE>> registerBlockEntity(String localName, Supplier<BlockEntityType<BE>> blockEntityTypeSupplier)
    {
        return BLOCK_ENTITIES.register(localName, blockEntityTypeSupplier);
    }

    /**
     * Registers all block entities under the mod's block entity registry
     * @param eventBus The mod's {@link IEventBus}
     */
    public static void registerBlockEntities(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}

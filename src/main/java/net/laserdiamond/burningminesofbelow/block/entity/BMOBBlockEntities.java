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

public class BMOBBlockEntities {


    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BurningMinesOfBelow.MODID);

    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel1>> FORGE_LEVEL_1 = registerBlockEntity("forge_1_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel1::new, BMOBBlocks.FORGE_LEVEL_1.get()).build(null));

    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel2>> FORGE_LEVEL_2 = registerBlockEntity("forge_2_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel2::new, BMOBBlocks.FORGE_LEVEL_2.get()).build(null));

    public static final RegistryObject<BlockEntityType<ForgeBlockEntityLevel3>> FORGE_LEVEL_3 = registerBlockEntity("forge_3_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntityLevel3::new, BMOBBlocks.FORGE_LEVEL_3.get()).build(null));

    public static final RegistryObject<BlockEntityType<BMOBSkullBlockEntity>> SKULL_BLOCK_ENTITY = registerBlockEntity("bmob_skull_block",
            () -> BlockEntityType.Builder.of(BMOBSkullBlockEntity::new, BMOBBlocks.FROZEN_WITHER_SKULL.get(), BMOBBlocks.BLAZE_SKULL.get(), BMOBBlocks.FROZEN_WITHER_SKULL_WALL.get(), BMOBBlocks.BLAZE_SKULL_WALL.get()).build(null));


    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String localName, Supplier<BlockEntityType<T>> blockEntityTypeSupplier)
    {
        return BLOCK_ENTITIES.register(localName, blockEntityTypeSupplier);
    }

    public static void registerBlockEntities(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}

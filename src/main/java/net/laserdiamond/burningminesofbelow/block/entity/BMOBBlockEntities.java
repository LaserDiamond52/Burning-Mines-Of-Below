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

    public static final RegistryObject<BlockEntityType<ForgeBlockEntity>> FORGE = registerBlockEntity("forge_be",
            () -> BlockEntityType.Builder.of(ForgeBlockEntity::new,
                            BMOBBlocks.FORGE_LEVEL_1.get(),
                            BMOBBlocks.FORGE_LEVEL_2.get(),
                            BMOBBlocks.FORGE_LEVEL_3.get()
                    )
                    .build(null));

    public static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String localName, Supplier<BlockEntityType<T>> blockEntityTypeSupplier)
    {
        return BLOCK_ENTITIES.register(localName, blockEntityTypeSupplier);
    }

    public static void registerBlockEntities(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}

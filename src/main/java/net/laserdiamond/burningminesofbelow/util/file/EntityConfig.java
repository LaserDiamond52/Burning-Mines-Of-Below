package net.laserdiamond.burningminesofbelow.util.file;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

public class EntityConfig<E extends Entity> extends JsonConfig {


    protected EntityConfig(RegistryObject<EntityType<E>> entityType) {
        super(entityType.get().getDescriptionId());


    }

    @Override
    public String folderName() {
        return "entity";
    }
}

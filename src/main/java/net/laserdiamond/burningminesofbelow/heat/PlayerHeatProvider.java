package net.laserdiamond.burningminesofbelow.heat;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <p>Version/date: 12/9/24</p>
 * <p>Responsibilities of class:</p>
 * <li>Create and manage the capability for a {@link net.minecraft.world.entity.player.Player} to have heat saved to them</li>
 * @author Allen Malo
 */
public class PlayerHeatProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerHeat> PLAYER_HEAT = CapabilityManager.get(new CapabilityToken<>() {});

    private PlayerHeat playerHeat = null;
    private final LazyOptional<PlayerHeat> optional = LazyOptional.of(this::createPlayerHeat);

    private PlayerHeat createPlayerHeat() {
        if (this.playerHeat == null)
        {
            this.playerHeat = new PlayerHeat();
        }
        return this.playerHeat;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == PLAYER_HEAT)
        {
            return this.optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.createPlayerHeat().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        this.createPlayerHeat().loadNBTData(compoundTag);
    }
}

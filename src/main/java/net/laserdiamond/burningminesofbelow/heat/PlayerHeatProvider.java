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
 * <li>A {@link PlayerHeatProvider} is-a {@link ICapabilityProvider}</li>
 * <li>A {@link PlayerHeatProvider} is-a {@link INBTSerializable}</li>
 * @author Allen Malo
 * @References:
 * <p>KaupenJoe; Custom Thirst System in Forge Minecraft 1.19.1 Modding<a href="https://www.youtube.com/watch?v=NN-k74NMKRc&list=PLKGarocXCE1EquNdk4WsX-VZTmbeOZ7MS">...</a></p>
 */
public class PlayerHeatProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    /**
     * The {@link PlayerHeat} {@link Capability}
     */
    public static Capability<PlayerHeat> PLAYER_HEAT = CapabilityManager.get(new CapabilityToken<>() {});

    /**
     * The {@link PlayerHeat} to create for the {@link net.minecraft.world.entity.player.Player}
     */
    private PlayerHeat playerHeat = null;

    /**
     * The {@link PlayerHeat} {@link LazyOptional}
     */
    private final LazyOptional<PlayerHeat> optional = LazyOptional.of(this::createPlayerHeat);

    /**
     * Creates the {@link PlayerHeat} for the {@link net.minecraft.world.entity.player.Player}, or returns an existing one if one already exists
     * @return A new {@link PlayerHeat} for the {@link net.minecraft.world.entity.player.Player} if they didn't previously have one.
     * Returns the existing one if one is already there.
     */
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

    /**
     * Saves the {@link PlayerHeat} to a {@link CompoundTag}
     * @return The {@link CompoundTag} containing data about the {@link PlayerHeat}
     */
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.createPlayerHeat().saveNBTData(nbt);
        return nbt;
    }

    /**
     * Loads the {@link PlayerHeat} from the {@link CompoundTag}
     * @param compoundTag The {@link CompoundTag} to deserialize the {@link PlayerHeat} from
     */
    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        this.createPlayerHeat().loadNBTData(compoundTag);
    }
}

package io.github.makaseloli.realobsidian;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import org.slf4j.Logger;

public final class RealObsidianMod implements ModInitializer {
    public static final String MOD_ID = "realobsidian";

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        patchObsidianProperties();
    }

    private void patchObsidianProperties() {
        float glassHardness = Blocks.GLASS.defaultBlockState().destroySpeed;
        SoundType glassSound = Blocks.GLASS.defaultBlockState().getSoundType();

        patchBlockToGlassProperties(Blocks.OBSIDIAN, glassHardness, glassSound);
        patchBlockToGlassProperties(Blocks.CRYING_OBSIDIAN, glassHardness, glassSound);
    }

    private void patchBlockToGlassProperties(Block block, float glassHardness, SoundType glassSound) {
        block.properties.destroyTime = glassHardness;
        block.properties.soundType = glassSound;
        block.soundType = glassSound;
        block.getStateDefinition().getPossibleStates().forEach(state -> state.destroySpeed = glassHardness);

        LOGGER.info(
                "Patched {} hardness and sounds to match glass: hardness={}, sound={}",
                BuiltInRegistries.BLOCK.getKey(block),
                glassHardness,
                glassSound
        );
    }
}

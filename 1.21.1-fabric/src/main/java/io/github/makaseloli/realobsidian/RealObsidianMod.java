package io.github.makaseloli.realobsidian;

import net.fabricmc.api.ModInitializer;

public final class RealObsidianMod implements ModInitializer {
    @Override
    public void onInitialize() {
        RealObsidianPatcher.patchObsidianProperties();
    }
}

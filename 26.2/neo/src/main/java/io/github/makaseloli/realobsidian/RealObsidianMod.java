package io.github.makaseloli.realobsidian;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod(Constants.MOD_ID)
public final class RealObsidianMod {
    public RealObsidianMod(IEventBus modEventBus) {
        modEventBus.addListener(this::onCommonSetup);
        NeoForge.EVENT_BUS.addListener(this::onBreakSpeed);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(RealObsidianPatcher::patchObsidianProperties);
    }

    private void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!isPatchedObsidian(event.getState())) {
            return;
        }

        float glassSpeed = event.getEntity().getDestroySpeed(
                Blocks.GLASS.defaultBlockState(),
                event.getPosition().orElse(null)
        );
        if (!event.getEntity().hasCorrectToolForDrops(event.getState())) {
            glassSpeed *= 100.0F / 30.0F;
        }
        event.setNewSpeed(glassSpeed);
    }

    private static boolean isPatchedObsidian(BlockState state) {
        return state.is(Blocks.OBSIDIAN) || state.is(Blocks.CRYING_OBSIDIAN);
    }
}

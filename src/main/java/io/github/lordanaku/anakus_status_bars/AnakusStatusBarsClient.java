package io.github.lordanaku.anakus_status_bars;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.ConfigFileHandler;
import io.github.lordanaku.anakus_status_bars.screen.hud.custom.*;
import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import io.github.lordanaku.anakus_status_bars.network.ClientSyncHandler;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.LogHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class AnakusStatusBarsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LogHelper.info("Loaded");
        ConfigFileHandler.readFromConfig();
        ClientSyncHandler.init();

        ModUtils.registerHudElements(new HealthHudElement(), new HungerHudElement(), new ArmorBarElement(), new MountBarElement(), new BreathBarElement());

        HudRenderCallback.EVENT.register(new RenderHudElements());

        ModUtils.setupHudElements();
    }
}

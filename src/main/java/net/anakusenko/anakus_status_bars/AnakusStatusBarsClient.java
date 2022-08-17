package net.anakusenko.anakus_status_bars;

import net.anakusenko.anakus_status_bars.network.ClientSyncHandler;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class AnakusStatusBarsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSyncHandler.init();

        HudRenderCallback.EVENT.register(new RenderHudElements());
    }
}

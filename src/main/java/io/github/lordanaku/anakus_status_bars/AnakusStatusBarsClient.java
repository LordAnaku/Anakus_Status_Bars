package io.github.lordanaku.anakus_status_bars;

import io.github.lordanaku.anakus_status_bars.api.ModRegisterFunctions;
import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.ConfigFileHandler;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.elements.*;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.network.ClientSyncHandler;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.LogHelper;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class AnakusStatusBarsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Settings.registerShouldRenderSettings();
        Settings.registerColorSettings();
        Settings.registerAlphaSettings();
        Settings.registerIconSettings();
        Settings.registerPositionSettings();
        RenderHudFunctions.setYModIncrement(TextureRecords.DEFAULT_BAR.height() + 1);
        ModRegisterFunctions.registerHudElementSide("Mana", true);
        ModRegisterFunctions.registerHudElementSide("Mana", true);
        ModRegisterFunctions.registerHudElementSide("Mana", true);



        LogHelper.info("Loaded");
        ConfigFileHandler.readFromConfig();
        ClientSyncHandler.init();

        ASBModUtils.registerHudElements(new HealthHudElement(), new HungerHudElement(), new ArmorBarElement(), new MountBarElement(), new BreathBarElement());

        HudRenderCallback.EVENT.register(new RenderHudElements());

        Settings.setupHudElements();
    }
}

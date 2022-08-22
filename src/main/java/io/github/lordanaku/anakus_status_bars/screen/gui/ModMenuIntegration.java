package io.github.lordanaku.anakus_status_bars.screen.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return Config::CreateConfigScreen;
    }
}


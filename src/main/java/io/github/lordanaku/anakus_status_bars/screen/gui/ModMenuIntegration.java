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

    /*
    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("title.anakus_status_bars.title"));

        builder.setSavingRunnable(() -> {
            // Serialise the config into the config file. This will be called last after all variables are updated.
        });

        ConfigCategory mainCategory = builder.getOrCreateCategory((Text) new TranslatableTextContent("category.anakus_status_bars.general"));
        ConfigCategory colorCategory = builder.getOrCreateCategory((Text) new TranslatableTextContent("category.anakus_status_bars.color"));
        ConfigValues.build(mainCategory, builder.entryBuilder());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        mainCategory.addEntry(entryBuilder.startBooleanToggle((Text) new TranslatableTextContent("config.anakus_status_bars.enable_saturation_bar"), ConfigValues.enableExhaustionBar)
                .setDefaultValue(true)
                .setTooltip((Text) new TranslatableTextContent("config.anakus_status_bars.enable_saturation_bar.tooltip"))
                .setSaveConsumer(newValue -> ConfigValues.enableExhaustionBar = newValue)
                .build());

        return builder.build();
    }
    */
}


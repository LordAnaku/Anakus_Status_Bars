package net.anakusenko.anakus_status_bars.screen.gui.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class Config {
    public static Screen CreateConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.anakus_status_bars.config"));

        builder.setSavingRunnable(ConfigFileHandler::writeToConfig);

        ConfigCategory mainCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.general"));
        ConfigCategory colorCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.color"));
        ConfigValues.buildMain(mainCategory, builder.entryBuilder());
        ConfigValues.buildColors(colorCategory, builder.entryBuilder());

        return builder.build();
    }
}

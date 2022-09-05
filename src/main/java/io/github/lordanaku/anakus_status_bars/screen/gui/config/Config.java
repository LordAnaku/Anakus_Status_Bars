package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.utils.LogHelper;
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
        ConfigCategory iconCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.icon"));
        ConfigCategory textCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.text"));
        ConfigCategory colorCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.color"));
        ConfigCategory textColorSettings = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.text_color"));
        ConfigCategory alphaCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.alpha"));
        ConfigCategory positionCategory = builder.getOrCreateCategory(Text.translatable("category.anakus_status_bars.position"));

        for (IHudElement hudElement : Settings.I_HUD_ELEMENTS_LIST) {
            hudElement.registerSettings(mainCategory, iconCategory, textCategory, colorCategory, textColorSettings, alphaCategory, builder.entryBuilder());
            LogHelper.info("Registered settings for " + hudElement.name());
        }

        ConfigValues.buildPosition(positionCategory, builder.entryBuilder());

        return builder.build();
    }
}

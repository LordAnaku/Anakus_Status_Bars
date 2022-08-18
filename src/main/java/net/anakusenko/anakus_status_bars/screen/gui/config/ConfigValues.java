package net.anakusenko.anakus_status_bars.screen.gui.config;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.text.Text;

public class ConfigValues {

    /** Main Category */
    public static BooleanListEntry enableSaturationBar;
    public static BooleanListEntry enableExhaustionBar;

    /** Color Category */
    public static ColorEntry healthBarColor;
    public static ColorEntry hungerBarColor;
    public static ColorEntry saturationBarColor;
    public static FloatListEntry exhaustionBarAlpha;
    public static ColorEntry armorBarColor;
    public static ColorEntry breathBarColor;
    public static ColorEntry mountBarColor;





    public static void buildMain(ConfigCategory category, ConfigEntryBuilder builder) {

        enableSaturationBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_saturation_bar"), Settings.enableSaturationBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableSaturationBar = newValue)
                .build();
        category.addEntry(enableSaturationBar);

        enableExhaustionBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_exhaustion_bar"), Settings.enableExhaustionBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableExhaustionBar = newValue)
                .build();
        category.addEntry(enableExhaustionBar);
    }

    public static void buildColors(ConfigCategory category, ConfigEntryBuilder builder) {
        healthBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_bar_color"), Settings.healthBarColor)
                .setDefaultValue(0xDC1432)
                .setSaveConsumer(newValue -> Settings.healthBarColor = newValue)
                .build();
        category.addEntry(healthBarColor);

        hungerBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_bar_color"), Settings.hungerBarColor)
                .setDefaultValue(0xD2691E)
                .setSaveConsumer(newValue -> Settings.hungerBarColor = newValue)
                .build();
        category.addEntry(hungerBarColor);

        saturationBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.saturation_bar_color"), Settings.saturationBarColor)
                .setDefaultValue(0xFFF600)
                .setSaveConsumer(newValue -> Settings.saturationBarColor = newValue)
                .build();
        category.addEntry(saturationBarColor);

        exhaustionBarAlpha = builder.startFloatField(Text.translatable("option.anakus_status_bars.exhaustion_bar_alpha"), Settings.exhaustionBarAlpha)
                .setDefaultValue(0.5f)
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.exhaustion_bar_alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.exhaustionBarAlpha = newValue)
                .build();
        category.addEntry(exhaustionBarAlpha);
    }
}

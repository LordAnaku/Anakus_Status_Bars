package net.anakusenko.anakus_status_bars.screen.gui.config;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.text.Text;

public class ConfigValues {

    /** Main Category */
    public static BooleanListEntry enableHealthBar;
    public static BooleanListEntry enableHungerBar;
    public static BooleanListEntry enableSaturationBar;
    public static BooleanListEntry enableExhaustionBar;
    public static BooleanListEntry enableArmorBar;
    public static BooleanListEntry enableBreathBar;
    public static BooleanListEntry enableMountBar;


    /** Color Category */
    public static ColorEntry healthBarColor;
    public static ColorEntry healthPoisonColor;
    public static ColorEntry healthWitherColor;
    public static ColorEntry hungerBarColor;
    public static ColorEntry hungerEffectColor;
    public static ColorEntry saturationBarColor;
    public static FloatListEntry exhaustionBarAlpha;
    public static ColorEntry armorBarColor;
    public static ColorEntry breathBarColor;
    public static ColorEntry mountBarColor;





    public static void buildMain(ConfigCategory category, ConfigEntryBuilder builder) {

        enableHealthBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_health_bar"), Settings.enableHealthBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableHealthBar = newValue)
                .build();
        category.addEntry(enableHealthBar);

        enableHungerBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_hunger_bar"), Settings.enableHungerBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableHungerBar = newValue)
                .build();
        category.addEntry(enableHungerBar);

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

        enableArmorBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_armor_bar"), Settings.enableArmorBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableArmorBar = newValue)
                .build();
        category.addEntry(enableArmorBar);

        enableBreathBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_breath_bar"), Settings.enableBreathBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableBreathBar = newValue)
                .build();
        category.addEntry(enableBreathBar);

        enableMountBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_mount_bar"), Settings.enableMountBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableMountBar = newValue)
                .build();
        category.addEntry(enableMountBar);
    }

    public static void buildColors(ConfigCategory category, ConfigEntryBuilder builder) {
        healthBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_bar_color"), Settings.healthBarColor)
                .setDefaultValue(0xDC1432)
                .setSaveConsumer(newValue -> Settings.healthBarColor = newValue)
                .build();
        category.addEntry(healthBarColor);

        healthPoisonColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_poison_color"), Settings.healthPoisonColor)
                .setDefaultValue(0x228B22)
                .setSaveConsumer(newValue -> Settings.healthPoisonColor = newValue)
                .build();
        category.addEntry(healthPoisonColor);

        healthWitherColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_wither_color"), Settings.healthWitherColor)
                .setDefaultValue(0x36454F)
                .setSaveConsumer(newValue -> Settings.healthWitherColor = newValue)
                .build();
        category.addEntry(healthWitherColor);

        hungerBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_bar_color"), Settings.hungerBarColor)
                .setDefaultValue(0xD2691E)
                .setSaveConsumer(newValue -> Settings.hungerBarColor = newValue)
                .build();
        category.addEntry(hungerBarColor);

        hungerEffectColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_effect_color"), Settings.hungerEffectColor)
                .setDefaultValue(0x550000)
                .setSaveConsumer(newValue -> Settings.hungerEffectColor = newValue)
                .build();
        category.addEntry(hungerEffectColor);

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

        armorBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.armor_bar_color"), Settings.armorBarColor)
                .setDefaultValue(0xB2BEB5)
                .setSaveConsumer(newValue -> Settings.armorBarColor = newValue)
                .build();
        category.addEntry(armorBarColor);

        breathBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.breath_bar_color"), Settings.breathBarColor)
                .setDefaultValue(0xA1CAF1)
                .setSaveConsumer(newValue -> Settings.breathBarColor = newValue)
                .build();
        category.addEntry(breathBarColor);

        mountBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.mount_bar_color"), Settings.mountBarColor)
                .setDefaultValue(0x54626F)
                .setSaveConsumer(newValue -> Settings.mountBarColor = newValue)
                .build();
        category.addEntry(mountBarColor);
    }
}

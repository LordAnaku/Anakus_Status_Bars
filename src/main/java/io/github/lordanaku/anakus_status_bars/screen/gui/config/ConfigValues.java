package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.text.Text;

import java.util.Arrays;

public class ConfigValues {

    /* * Main Category */
    public static BooleanListEntry enableHealthBar;
    public static BooleanListEntry enableHungerBar;
    public static BooleanListEntry enableSaturationBar;
    public static BooleanListEntry enableExhaustionBar;
    public static BooleanListEntry enableArmorBar;
    public static BooleanListEntry enableAbsorptionBar;
    public static BooleanListEntry enableBreathBar;
    public static BooleanListEntry enableMountBar;
    public static StringListListEntry leftOrder;
    public static StringListListEntry rightOrder;


    /* * Color Category */
    public static ColorEntry healthBarColor;
    public static ColorEntry healthPoisonColor;
    public static ColorEntry healthWitherColor;
    public static ColorEntry hurtBarColor;
    public static ColorEntry hungerBarColor;
    public static ColorEntry hungerEffectColor;
    public static ColorEntry saturationBarColor;
    public static FloatListEntry exhaustionBarAlpha;
    public static ColorEntry armorBarColor;
    public static ColorEntry absorptionBarColor;
    public static ColorEntry breathBarColor;
    public static ColorEntry mountBarColor;

    /* * Icon Category */
    public static BooleanListEntry enableHealthIcon;
    public static BooleanListEntry enableHungerIcon;
    public static BooleanListEntry enableArmorIcon;
    public static BooleanListEntry enableBreathIcon;
    public static BooleanListEntry enableMountIcon;



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

        enableAbsorptionBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_absorption_bar"), Settings.enableAbsorptionBar)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableAbsorptionBar = newValue)
                .build();
        category.addEntry(enableAbsorptionBar);

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

        leftOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.left_order"), ModUtils.getStringList(Settings.leftOrder))
                .setDefaultValue(Arrays.asList("Health", "Armor"))
                .setSaveConsumer(newValue -> Settings.leftOrder = ModUtils.getJsonArray(newValue))
                .build();
        category.addEntry(leftOrder);

        rightOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.right_order"), ModUtils.getStringList(Settings.rightOrder))
                .setDefaultValue(Arrays.asList("Hunger", "Breath", "MountHealth"))
                .setSaveConsumer(newValue -> Settings.rightOrder = ModUtils.getJsonArray(newValue))
                .build();
        category.addEntry(rightOrder);
    }

    public static void buildColors(ConfigCategory category, ConfigEntryBuilder builder) {
        healthBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_bar_color"), Settings.healthBarColor)
                .setDefaultValue(0xff1313)
                .setSaveConsumer(newValue -> Settings.healthBarColor = newValue)
                .build();
        category.addEntry(healthBarColor);

        healthPoisonColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_poison_color"), Settings.healthPoisonColor)
                .setDefaultValue(0x8b8712)
                .setSaveConsumer(newValue -> Settings.healthPoisonColor = newValue)
                .build();
        category.addEntry(healthPoisonColor);

        healthWitherColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_wither_color"), Settings.healthWitherColor)
                .setDefaultValue(0x2b2b2b)
                .setSaveConsumer(newValue -> Settings.healthWitherColor = newValue)
                .build();
        category.addEntry(healthWitherColor);

        hurtBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hurt_bar_color"), Settings.hurtBarColor)
                .setDefaultValue(0x550000)
                .setSaveConsumer(newValue -> Settings.hurtBarColor = newValue)
                .build();
        category.addEntry(hurtBarColor);

        hungerBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_bar_color"), Settings.hungerBarColor)
                .setDefaultValue(0x9d6d43)
                .setSaveConsumer(newValue -> Settings.hungerBarColor = newValue)
                .build();
        category.addEntry(hungerBarColor);

        hungerEffectColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_effect_color"), Settings.hungerEffectColor)
                .setDefaultValue(0x5f6d43)
                .setSaveConsumer(newValue -> Settings.hungerEffectColor = newValue)
                .build();
        category.addEntry(hungerEffectColor);

        saturationBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.saturation_bar_color"), Settings.saturationBarColor)
                .setDefaultValue(0xd42a2a)
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
                .setDefaultValue(0xb8b9c4)
                .setSaveConsumer(newValue -> Settings.armorBarColor = newValue)
                .build();
        category.addEntry(armorBarColor);

        absorptionBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.absorption_bar_color"), Settings.absorptionBarColor)
                .setDefaultValue(0xd4af37)
                .setSaveConsumer(newValue -> Settings.absorptionBarColor = newValue)
                .build();
        category.addEntry(absorptionBarColor);

        breathBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.breath_bar_color"), Settings.breathBarColor)
                .setDefaultValue(0x0094ff)
                .setSaveConsumer(newValue -> Settings.breathBarColor = newValue)
                .build();
        category.addEntry(breathBarColor);

        mountBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.mount_bar_color"), Settings.mountBarColor)
                .setDefaultValue(0xda662c)
                .setSaveConsumer(newValue -> Settings.mountBarColor = newValue)
                .build();
        category.addEntry(mountBarColor);
    }

    public static void buildIcons(ConfigCategory iconCategory, ConfigEntryBuilder entryBuilder) {
        enableHealthIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_health_icon"), Settings.enableHealthIcon)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableHealthIcon = newValue)
                .build();
        iconCategory.addEntry(enableHealthIcon);

        enableHungerIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_hunger_icon"), Settings.enableHungerIcon)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableHungerIcon = newValue)
                .build();
        iconCategory.addEntry(enableHungerIcon);

        enableArmorIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_armor_icon"), Settings.enableArmorIcon)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableArmorIcon = newValue)
                .build();
        iconCategory.addEntry(enableArmorIcon);

        enableBreathIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_breath_icon"), Settings.enableBreathIcon)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableBreathIcon = newValue)
                .build();
        iconCategory.addEntry(enableBreathIcon);

        enableMountIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_mount_icon"), Settings.enableMountIcon)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.enableMountIcon = newValue)
                .build();
        iconCategory.addEntry(enableMountIcon);
    }
}

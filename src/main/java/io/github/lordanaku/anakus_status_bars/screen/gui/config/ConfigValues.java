package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.text.Text;

import java.util.ArrayList;
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

        enableHealthBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_health_bar"), Settings.shouldRenderSettings.get("health"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("health", newValue))
                .build();
        category.addEntry(enableHealthBar);

        enableHungerBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_hunger_bar"), Settings.shouldRenderSettings.get("hunger"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("hunger", newValue))
                .build();
        category.addEntry(enableHungerBar);

        enableSaturationBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_saturation_bar"), Settings.shouldRenderSettings.get("saturation"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("saturation", newValue))
                .build();
        category.addEntry(enableSaturationBar);

        enableExhaustionBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_exhaustion_bar"), Settings.shouldRenderSettings.get("exhaustion"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("exhaustion", newValue))
                .build();
        category.addEntry(enableExhaustionBar);

        enableArmorBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_armor_bar"), Settings.shouldRenderSettings.get("armor"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("armor", newValue))
                .build();
        category.addEntry(enableArmorBar);

        enableAbsorptionBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_absorption_bar"), Settings.shouldRenderSettings.get("absorption"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("absorption", newValue))
                .build();
        category.addEntry(enableAbsorptionBar);

        enableBreathBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_breath_bar"), Settings.shouldRenderSettings.get("breath"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("breath", newValue))
                .build();
        category.addEntry(enableBreathBar);

        enableMountBar = builder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_mount_bar"), Settings.shouldRenderSettings.get("mount"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace("mount", newValue))
                .build();
        category.addEntry(enableMountBar);

        leftOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.left_order"), Settings.positionSettings.get("left"))
                .setDefaultValue(Arrays.asList("Health", "Armor"))
                .setSaveConsumer(newValue -> Settings.positionSettings.replace("left", new ArrayList<>(newValue)))
                .build();
        category.addEntry(leftOrder);

        rightOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.right_order"), Settings.positionSettings.get("right"))
                .setDefaultValue(Arrays.asList("Hunger", "Breath", "MountHealth"))
                .setSaveConsumer(newValue -> Settings.positionSettings.replace("right", new ArrayList<>(newValue)))
                .build();
        category.addEntry(rightOrder);
    }

    public static void buildColors(ConfigCategory category, ConfigEntryBuilder builder) {
        healthBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_bar_color"), Settings.colorSettings.get("color_health"))
                .setDefaultValue(0xff1313)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_health", newValue))
                .build();
        category.addEntry(healthBarColor);

        healthPoisonColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_poison_color"), Settings.colorSettings.get("color_health_poison"))
                .setDefaultValue(0x8b8712)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_health_poison", newValue))
                .build();
        category.addEntry(healthPoisonColor);

        healthWitherColor = builder.startColorField(Text.translatable("option.anakus_status_bars.health_wither_color"), Settings.colorSettings.get("color_health_wither"))
                .setDefaultValue(0x2b2b2b)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_health_wither", newValue))
                .build();
        category.addEntry(healthWitherColor);

        hurtBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hurt_bar_color"), Settings.colorSettings.get("color_hurt"))
                .setDefaultValue(0x550000)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_hurt", newValue))
                .build();
        category.addEntry(hurtBarColor);

        hungerBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_bar_color"), Settings.colorSettings.get("color_hunger"))
                .setDefaultValue(0x9d6d43)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_hunger", newValue))
                .build();
        category.addEntry(hungerBarColor);

        hungerEffectColor = builder.startColorField(Text.translatable("option.anakus_status_bars.hunger_effect_color"), Settings.colorSettings.get("color_hunger_effect"))
                .setDefaultValue(0x5f6d43)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_hunger_effect", newValue))
                .build();
        category.addEntry(hungerEffectColor);

        saturationBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.saturation_bar_color"), Settings.colorSettings.get("color_saturation"))
                .setDefaultValue(0xd42a2a)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_saturation", newValue))
                .build();
        category.addEntry(saturationBarColor);

        exhaustionBarAlpha = builder.startFloatField(Text.translatable("option.anakus_status_bars.exhaustion_bar_alpha"), Settings.alphaSettings.get("alpha_exhaustion"))
                .setDefaultValue(0.5f)
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.exhaustion_bar_alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace("alpha_exhaustion", newValue))
                .build();
        category.addEntry(exhaustionBarAlpha);

        armorBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.armor_bar_color"), Settings.colorSettings.get("color_armor"))
                .setDefaultValue(0xb8b9c4)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_armor", newValue))
                .build();
        category.addEntry(armorBarColor);

        absorptionBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.absorption_bar_color"), Settings.colorSettings.get("color_absorption"))
                .setDefaultValue(0xd4af37)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_absorption", newValue))
                .build();
        category.addEntry(absorptionBarColor);

        breathBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.breath_bar_color"), Settings.colorSettings.get("color_breath"))
                .setDefaultValue(0x0094ff)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_breath", newValue))
                .build();
        category.addEntry(breathBarColor);

        mountBarColor = builder.startColorField(Text.translatable("option.anakus_status_bars.mount_bar_color"), Settings.colorSettings.get("color_mount"))
                .setDefaultValue(0xda662c)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace("color_mount", newValue))
                .build();
        category.addEntry(mountBarColor);
    }

    public static void buildIcons(ConfigCategory iconCategory, ConfigEntryBuilder entryBuilder) {
        enableHealthIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_health_icon"), Settings.iconSettings.get("icon_health"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.iconSettings.replace("icon_health", newValue))
                .build();
        iconCategory.addEntry(enableHealthIcon);

        enableHungerIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_hunger_icon"), Settings.iconSettings.get("icon_hunger"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.iconSettings.replace("icon_hunger", newValue))
                .build();
        iconCategory.addEntry(enableHungerIcon);

        enableArmorIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_armor_icon"), Settings.iconSettings.get("icon_armor"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.iconSettings.replace("icon_armor", newValue))
                .build();
        iconCategory.addEntry(enableArmorIcon);

        enableBreathIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_breath_icon"), Settings.iconSettings.get("icon_breath"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.iconSettings.replace("icon_breath", newValue))
                .build();
        iconCategory.addEntry(enableBreathIcon);

        enableMountIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.enable_mount_icon"), Settings.iconSettings.get("icon_mount"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Settings.iconSettings.replace("icon_mount", newValue))
                .build();
        iconCategory.addEntry(enableMountIcon);
    }
}

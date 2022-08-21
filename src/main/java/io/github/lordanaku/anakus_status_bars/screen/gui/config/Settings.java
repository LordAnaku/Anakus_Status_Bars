package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import com.google.gson.JsonArray;
import io.github.lordanaku.anakus_status_bars.utils.ModUtils;

import java.util.Arrays;

public final class Settings {
    /* * Should Render Settings */
    public static boolean enableHealthBar = true;
    public static boolean enableHungerBar = true;
    public static boolean enableSaturationBar = true;
    public static boolean enableExhaustionBar = true;
    public static boolean enableArmorBar = true;
    public static boolean enableAbsorptionBar = true;
    public static boolean enableBreathBar = true;
    public static boolean enableMountBar = true;

    /* * Color Settings */
    public static int healthBarColor = 0xff1313;
    public static int healthPoisonColor = 0x8b8712;
    public static int healthWitherColor = 0x2b2b2b;
    public static int hurtBarColor = 0x550000;
    public static int hungerBarColor = 0x9d6d43;
    public static int hungerEffectColor = 0x5f6d43;
    public static int saturationBarColor = 0xd42a2a;
    public static float exhaustionBarAlpha = 0.5f;
    public static int armorBarColor = 0xb8b9c4;
    public static int absorptionBarColor = 0xd4af37;
    public static int breathBarColor = 0x0094ff;
    public static int mountBarColor = 0xda662c;

    /* * Icon Settings */
    public static boolean enableHealthIcon = true;
    public static boolean enableHungerIcon = true;
    public static boolean enableArmorIcon = true;
    public static boolean enableBreathIcon = true;
    public static boolean enableMountIcon = true;

    /* * Position Settings */
    public static JsonArray leftOrder = ModUtils.getJsonArray(Arrays.asList("Health", "Armor"));
    public static JsonArray rightOrder = ModUtils.getJsonArray(Arrays.asList("Hunger", "Breath", "MountHealth"));
}

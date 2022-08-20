package net.anakusenko.anakus_status_bars.screen.gui.config;

import com.google.gson.JsonArray;
import net.anakusenko.anakus_status_bars.utils.ModUtils;

import java.util.Arrays;
import java.util.List;

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
    public static int healthBarColor = 0xDC1432;
    public static int healthPoisonColor = 0x228B22;
    public static int healthWitherColor = 0x36454F;
    public static int hungerBarColor = 0xD2691E;
    public static int hungerEffectColor = 0x550000;
    public static int saturationBarColor = 0xFFF600;
    public static float exhaustionBarAlpha = 0.5f;
    public static int armorBarColor = 0xB2BEB5;
    public static int absorptionBarColor = 0x007FFF;
    public static int breathBarColor = 0xA1CAF1;
    public static int mountBarColor = 0x54626F;

    /* * Text Settings */

    /* * Icon Settings */

    /* * Position Settings */
    public static JsonArray leftOrder = ModUtils.getJsonArray(Arrays.asList("Health", "Armor"));
    public static JsonArray rightOrder = ModUtils.getJsonArray(Arrays.asList("Hunger", "Breath", "MountHealth"));
}

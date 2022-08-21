package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Settings {
    /* * Should Render Settings */
    public static Map<String, Boolean> shouldRenderSettings = new HashMap<>();

    public static void registerShouldRenderSettings() {
        shouldRenderSettings.put("health", true);
        shouldRenderSettings.put("hunger", true);
        shouldRenderSettings.put("saturation", true);
        shouldRenderSettings.put("exhaustion", true);
        shouldRenderSettings.put("armor", true);
        shouldRenderSettings.put("absorption", true);
        shouldRenderSettings.put("breath", true);
        shouldRenderSettings.put("mount", true);
    }

    /* * Color Settings */
    public static Map<String, Integer> colorSettings = new HashMap<>();
    public static Map<String, Float> alphaSettings = new HashMap<>();

    public static void registerColorSettings() {
        colorSettings.put("color_health", 0xff1313);
        colorSettings.put("color_health_poison", 0x8b8712);
        colorSettings.put("color_health_wither", 0x2b2b2b);
        colorSettings.put("color_hurt", 0x550000);
        colorSettings.put("color_hunger", 0x9d6d43);
        colorSettings.put("color_hunger_effect", 0x5f6d43);
        colorSettings.put("color_saturation", 0xd42a2a);
        colorSettings.put("color_armor", 0xb8b9c4);
        colorSettings.put("color_absorption", 0xd4af37);
        colorSettings.put("color_breath", 0x0094ff);
        colorSettings.put("color_mount", 0xda662c);
    }

    public static void registerAlphaSettings() {
        alphaSettings.put("alpha_exhaustion", 0.5f);
    }


    /* * Icon Settings */
    public static Map<String, Boolean> iconSettings = new HashMap<>();

    public static void registerIconSettings() {
        iconSettings.put("icon_health", true);
        iconSettings.put("icon_hunger", true);
        iconSettings.put("icon_armor", true);
        iconSettings.put("icon_breath", true);
        iconSettings.put("icon_mount", true);
    }

    /* * Position Settings */
    public static Map<String, ArrayList<String>> positionSettings = new HashMap<>();

    public static void registerPositionSettings() {
        positionSettings.put("left", new ArrayList<>(Arrays.asList("Health", "Armor")));
        positionSettings.put("right", new ArrayList<>(Arrays.asList("Hunger", "Breath", "MountHealth")));
    }


}

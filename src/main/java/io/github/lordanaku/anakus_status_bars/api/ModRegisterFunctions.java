package io.github.lordanaku.anakus_status_bars.api;

import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;

import java.util.List;

public class ModRegisterFunctions {

    /**
     *  Register Settings for rather or not mod hud elements should render.
     * @param key The key for the setting (probably best to stick to ID chosen for HudElements.)
     * @param value The default value for if hud element should render.
     */
    public static void registerShouldRenderModSettings(String key, Boolean value) {
        Settings.shouldRenderSettings.put(key, value);
    }

    /**
     *  Register Settings for color of hud elements
     * @param key The key for the setting (probably best to stick to ID chosen for HudElements.)
     * @param value The default value for hud element color.
     */
    public static void registerColorModSettings(String key, int value) {
        Settings.colorSettings.put(key, value);
    }

    /**
     *  Register Settings for rather or not mod hud elements should render.
     * @param key The key for the setting (probably best to stick to ID chosen for HudElements.)
     * @param value The default value for hud element alpha.
     */
    public static void registerAlphaModSettings(String key, float value) {
        Settings.alphaSettings.put(key, value);
    }

    /**
     *  Register Settings for rather or not mod hud elements icon should render.
     *  @param key The key for the setting (probably best to stick to ID chosen for HudElements.)
     *  @param value The default value for if hud element icon should render.
     */
    public static void registerIconModSettings(String key, Boolean value) {
        Settings.iconSettings.put(key, value);
    }

    /**
     *  Register Default side for your hud element to render on to left side. (only call one or the other)
     * @param HudElementName The ID of your HudElement. (This must be the same as the ID you gave it in HudElements.)
     */
    public static void addModHudElementToLeftSide(String HudElementName) {
        ASBModUtils.leftOrderDefault.add(HudElementName);
    }

    /**
     *  Register Default side for your hud element to render on to right side. (only call one or the other)
     * @param HudElementName The ID of your HudElement. (This must be the same as the ID you gave it in HudElements.)
     */
    public static void addModHudElementToRightSide(String HudElementName) {
        ASBModUtils.rightOrderDefault.add(HudElementName);
    }

    /**
     *  Register Mod Hud Element to array of Hud Elements to be rendered.
     * @param hudElements The Hud Element to be registered.
     */
    public static void registerModHudElements(HudElements hudElements) {
        ASBModUtils.registry.put(hudElements.name(), hudElements);
    }
}

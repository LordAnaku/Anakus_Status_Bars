package io.github.lordanaku.anakus_status_bars.api;

import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;

@SuppressWarnings("unused")
public class ModRegisterFunctions {

    /**
     *  Register Settings for rather or not mod hud elements should render.
     * @param key The key for the setting (probably best to stick to ID chosen for IHudElement.)
     * @param value The default value for if hud element should render.
     */
    public static void registerShouldRenderModSettings(String key, Boolean value) {
        if (!Settings.shouldRenderSettings.containsKey(key)) Settings.shouldRenderSettings.put(key, value);
    }

    /**
     *  Register Settings for color of hud element.
     * @param key The key for the setting (probably best to stick to Color_ID chosen for IHudElement.)
     * @param value The default value for hud element color.
     */
    public static void registerColorModSettings(String key, int value) {
        if (!Settings.colorSettings.containsKey(key)) Settings.colorSettings.put(key, value);
    }

    /**
     *  Register Settings for the alpha of hud element.
     * @param key The key for the setting (probably best to stick to Alpha_ID chosen for IHudElement.)
     * @param value The default value for hud element alpha.
     */
    public static void registerAlphaModSettings(String key, float value) {
        if (!Settings.alphaSettings.containsKey(key)) Settings.alphaSettings.put(key, value);
    }

    /**
     *  Register Settings for rather or not mod hud elements icon should render.
     *  @param key The key for the setting (probably best to stick to Icon_ID chosen for IHudElement.)
     *  @param value The default value for if hud element icon should render.
     */
    public static void registerIconModSettings(String key, Boolean value) {
        if (!Settings.iconSettings.containsKey(key)) Settings.iconSettings.put(key, value);
    }

    /**
     *  Register Default side for your hud element to render on to left side. (only call one or the other)
     * @param hudElementName The ID of your HudElement. (This must be the same as the ID you gave it in IHudElement.)
     * @param side The default side for your HudElement to render on. (left = true, right = false)
     */
    public static void registerHudElementSide(String hudElementName, boolean side) {
        if (side) {
            if(!Settings.leftOrderDefault.contains(hudElementName)) ASBModUtils.setOrderDefaults(hudElementName, true);
            if(!Settings.positionOrderSettings.get("left").contains(hudElementName)) Settings.positionOrderSettings.get("left").add(hudElementName);
        } else {
            if (!Settings.rightOrderDefault.contains(hudElementName)) ASBModUtils.setOrderDefaults(hudElementName, false);
            if (!Settings.positionOrderSettings.get("right").contains(hudElementName)) Settings.positionOrderSettings.get("right").add(hudElementName);
        }
    }

    /**
     *  Register Mod Hud Element to array of Hud Elements to be rendered.
     * @param IHudElements The Hud Element to be registered.
     */
    public static void registerModHudElements(IHudElement IHudElements) {
        if (!Settings.registry.containsKey(IHudElements.name())) Settings.registry.put(IHudElements.name(), IHudElements);
    }
}

package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElementType;
import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;

import java.util.*;

import static io.github.lordanaku.anakus_status_bars.utils.ASBModUtils.leftSide;
import static io.github.lordanaku.anakus_status_bars.utils.ASBModUtils.rightSide;

public final class Settings {
    /* * Hud Element Types * */
    public static final HudElementType HEALTH = new HudElementType("health", true, true, true, false, 0xff1313, 1);
    public static final HudElementType FOOD = new HudElementType("hunger", true, false, true, false, 0x9d6d43, 1);
    public static final HudElementType ARMOR = new HudElementType("armor", true, true, true, false, 0xb8b9c4, 1);
    public static final HudElementType AIR = new HudElementType("air", true, false, true, false, 0x0094ff, 1);
    public static final HudElementType MOUNT_HEALTH = new HudElementType("mount_health", true, false, true, false, 0xda662c, 1);

    /* * Default Settings * */
    public static ArrayList<String> leftOrderDefault = new ArrayList<>(Arrays.asList(HEALTH.name(), ARMOR.name()));
    public static ArrayList<String> rightOrderDefault = new ArrayList<>(Arrays.asList(FOOD.name(), AIR.name(), MOUNT_HEALTH.name()));
    public static final float ALPHA_DEFAULT = 0.5f;
    public static final int ABSORPTION_COLOR_DEFAULT = 0xd4af37;
    public static final int POISON_COLOR_DEFAULT = 0x8b8712;
    public static final int WITHER_COLOR_DEFAULT = 0x2b2b2b;
    public static final int FROSTBITE_COLOR_DEFAULT = 0x4bbad7;
    public static final int HURT_COLOR_DEFAULT = 0x550000;
    public static final int HUNGER_COLOR_DEFAULT = 0x5f6d43;
    public static final int SATURATION_COLOR_DEFAULT = 0xd42a2a;

    /* * Hud Element Registry * */
    public static final Map<String, IHudElement> registry = new HashMap<>();
    public static final List<IHudElement> I_HUD_ELEMENTS_LIST = new ArrayList<>();

    /* * Settings * */
    public static Map<String, Boolean> shouldRenderSettings = new HashMap<>();
    public static Map<String, Boolean> iconSettings = new HashMap<>();
    public static Map<String, Boolean> textSettings = new HashMap<>();
    public static Map<String, Integer> colorSettings = new HashMap<>();
    public static Map<String, Integer> textColorSettings = new HashMap<>();
    public static Map<String, Float> alphaSettings = new HashMap<>();
    public static Map<String, ArrayList<String >> positionOrderSettings = new HashMap<>();
    public static Map<String, Integer> positionOffsets = new HashMap<>();

    /* * Setup Elements * */

    public static void registerElementSettings() {
        /* * Render Toggles * */
        shouldRenderSettings.put(HEALTH.name(), HEALTH.shouldRender());
        shouldRenderSettings.put(HEALTH.name() + "_absorption", HEALTH.shouldRender());
        shouldRenderSettings.put(FOOD.name(), FOOD.shouldRender());
        shouldRenderSettings.put(FOOD.name() + "_saturation", FOOD.shouldRender());
        shouldRenderSettings.put(FOOD.name() + "_exhaustion", FOOD.shouldRender());
        shouldRenderSettings.put(ARMOR.name(), ARMOR.shouldRender());
        shouldRenderSettings.put(AIR.name(), AIR.shouldRender());
        shouldRenderSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.shouldRender());

        /* * Icon Toggles * */
        iconSettings.put(HEALTH.name(), HEALTH.shouldRenderIcon());
        iconSettings.put(FOOD.name(), FOOD.shouldRenderIcon());
        iconSettings.put(ARMOR.name(), ARMOR.shouldRenderIcon());
        iconSettings.put(AIR.name(), AIR.shouldRenderIcon());
        iconSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.shouldRenderIcon());

        /* * Text Toggles * */
        textSettings.put(HEALTH.name(), HEALTH.shouldRenderText());
        textSettings.put(FOOD.name(), FOOD.shouldRenderText());
        textSettings.put(ARMOR.name(), ARMOR.shouldRenderText());
        textSettings.put(AIR.name(), AIR.shouldRenderText());
        textSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.shouldRenderText());

        /* * Color Settings * */
        colorSettings.put(HEALTH.name(), HEALTH.color());
        colorSettings.put(HEALTH.name() + "_absorption", ABSORPTION_COLOR_DEFAULT);
        colorSettings.put(HEALTH.name() + "_poison", POISON_COLOR_DEFAULT);
        colorSettings.put(HEALTH.name() + "_wither", WITHER_COLOR_DEFAULT);
        colorSettings.put(HEALTH.name() + "_frostbite", FROSTBITE_COLOR_DEFAULT);
        colorSettings.put(HEALTH.name() + "_hurt", HURT_COLOR_DEFAULT);
        colorSettings.put(FOOD.name(), FOOD.color());
        colorSettings.put(FOOD.name() + "_hunger", HUNGER_COLOR_DEFAULT);
        colorSettings.put(FOOD.name() + "_saturation", SATURATION_COLOR_DEFAULT);
        colorSettings.put(ARMOR.name(), ARMOR.color());
        colorSettings.put(AIR.name(), AIR.color());
        colorSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.color());

        /* * Text Color Settings * */
        textColorSettings.put(HEALTH.name(), HEALTH.color());
        textColorSettings.put(HEALTH.name() + "_absorption", ABSORPTION_COLOR_DEFAULT);
        textColorSettings.put(FOOD.name(), FOOD.color());
        textColorSettings.put(FOOD.name() + "_saturation", SATURATION_COLOR_DEFAULT);
        textColorSettings.put(ARMOR.name(), ARMOR.color());
        textColorSettings.put(AIR.name(), AIR.color());
        textColorSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.color());

        /* * Alpha Settings * */
        alphaSettings.put(HEALTH.name(), HEALTH.alpha());
        alphaSettings.put(HEALTH.name() + "_absorption", HEALTH.alpha());
        alphaSettings.put(FOOD.name(), FOOD.alpha());
        alphaSettings.put(FOOD.name() + "_saturation", FOOD.alpha());
        alphaSettings.put(FOOD.name() + "_exhaustion", ALPHA_DEFAULT);
        alphaSettings.put(ARMOR.name(), ARMOR.alpha());
        alphaSettings.put(AIR.name(), AIR.alpha());
        alphaSettings.put(MOUNT_HEALTH.name(), MOUNT_HEALTH.alpha());

        /* * Position Settings * */
        positionOrderSettings.put("left", new ArrayList<>(leftOrderDefault));
        positionOrderSettings.put("right", new ArrayList<>(rightOrderDefault));
        positionOffsets.put("left_y_offset", 40);
        positionOffsets.put("right_y_offset", 40);
        positionOffsets.put("left_x_offset", 0);
        positionOffsets.put("right_x_offset", 0);
    }

    public static void setupHudElements() {
        I_HUD_ELEMENTS_LIST.clear();
        for (String hudElements : positionOrderSettings.get("left")) {
            if (registry.containsKey(hudElements)) {
                I_HUD_ELEMENTS_LIST.add(registry.get(hudElements).setSide(leftSide));
            }
        }
        for (String hudElements : positionOrderSettings.get("right")) {
            if (registry.containsKey(hudElements)) {
                I_HUD_ELEMENTS_LIST.add(registry.get(hudElements).setSide(rightSide));
            }
        }
    }
}

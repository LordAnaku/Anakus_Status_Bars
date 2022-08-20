package net.anakusenko.anakus_status_bars.utils;

public enum TextureUtils {
    DEFAULT_BAR(0, 0, 81, 9),
    PROGRESS_BAR(0, 9, 81, 9),
    STATUS_EFFECT_BAR(0, 18, 81, 9),
    EXHAUSTION_BAR(0, 27, 81, 9),
    EFFECT_BAR(0, 36, 81, 9),
    SHIELD_ICON(83, 0, 9, 9),
    HEART_OUTLINE_ICON(16, 0, 9, 9),
    HEART_ICON(52, 0, 9, 9),
    HEART_POISON_ICON(88, 0, 9, 9),
    HEART_WITHER_ICON(124, 0, 9, 9),
    HEART_GOLD_ICON(160, 0, 9, 9),
    HEART_ICE_ICON(178, 0, 9, 9),
    HEART_MOUNT_ICON(88, 9, 9, 9),
    ARMOR_ICON(43, 9, 9, 9),
    BUBBLE_ICON(16, 18, 9, 9),
    BUBBLE_BURST_ICON(25,18, 9, 9),
    HUNGER_OUTLINE_ICON(16, 27, 9, 9),
    HUNGER_ICON(52, 27, 9, 9),
    HUNGER_EFFECT_ICON(88, 27, 9, 9),
    ;

    private final int startX;
    private final int startY;
    private final int width;
    private final int height;

    TextureUtils(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

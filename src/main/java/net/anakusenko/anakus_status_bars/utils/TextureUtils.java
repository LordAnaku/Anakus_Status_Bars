package net.anakusenko.anakus_status_bars.utils;

public enum TextureUtils {
    DEFAULT_BAR(0, 0, 81, 9),
    PROGRESS_BAR(0, 9, 81, 9),
    STATUS_EFFECT_BAR(0, 18, 81, 9),
    EXHAUSTION_BAR(0, 27, 81, 9),
    EFFECT_BAR(0, 36, 81, 9),
    SHIELD_ICON(83, 0, 9, 9),
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

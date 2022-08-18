package net.anakusenko.anakus_status_bars.utils;

public class ColorUtils {
    private final int color;

    private ColorUtils(int c) {
        this.color = c;
    }

    public static ColorUtils fromHex(int color) {
        return new ColorUtils(0xFF000000 | color);
    }

    public int getColor() {
        return color;
    }

    public int getRed() { return (color >> 16 & 0xFF); }

    public int getGreen() { return (color >> 8 & 0xFF);}

    public int getBlue() { return (color & 0xFF); }

    public int getAlpha() { return (color >> 24 & 0xFF); }

    public float getRedF() { return (color >> 16 & 0xFF)/255f; }

    public float getGreenF() { return (color >> 8 & 0xFF)/255f;}

    public float getBlueF() { return (color & 0xFF)/255f; }

    public float getAlphaF() { return (color >> 24 & 0xFF)/255f; }
}

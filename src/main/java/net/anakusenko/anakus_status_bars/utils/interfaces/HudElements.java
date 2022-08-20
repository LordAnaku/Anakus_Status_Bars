package net.anakusenko.anakus_status_bars.utils.interfaces;

public interface HudElements {
    void renderBar();
    void renderIcon();
    boolean getSide();
    HudElements setSide(boolean side);
    boolean shouldRender();
    boolean shouldRenderIcon();
    String name();
}

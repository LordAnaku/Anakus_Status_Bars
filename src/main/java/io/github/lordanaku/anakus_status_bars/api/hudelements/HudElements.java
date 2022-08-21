package io.github.lordanaku.anakus_status_bars.api.hudelements;

public interface HudElements {
    void renderBar();
    void renderIcon();
    boolean getSide();
    HudElements setSide(boolean side);
    boolean shouldRender();
    boolean shouldRenderIcon();
    String name();
}

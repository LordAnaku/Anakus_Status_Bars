package net.anakusenko.anakus_status_bars.utils.interfaces;

import net.anakusenko.anakus_status_bars.utils.ModUtils;

public interface HudElements {
    void renderBar();
    void renderText();
    void renderIcon();
    boolean getSide();
    HudElements setSide(boolean side);
    boolean shouldRender();
    boolean shouldRenderText();
    boolean shouldRenderIcon();

    String name();


}

package io.github.lordanaku.anakus_status_bars.api.hudelements;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

public interface IHudElement {
    void renderBar();
    void renderIcon();
    void renderText();
    boolean getSide();
    IHudElement setSide(boolean side);
    boolean shouldRender();
    boolean shouldRenderIcon();
    boolean shouldRenderText();
    void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder);
    String name();
}

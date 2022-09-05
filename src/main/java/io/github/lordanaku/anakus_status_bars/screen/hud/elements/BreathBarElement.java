package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings.AIR;

public class BreathBarElement implements IHudElement {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getBreathProgress();
        if (progress <= 0) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get(Settings.HEALTH.name() + "_hurt"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get(AIR.name()), Settings.alphaSettings.get(AIR.name()));
    }

    @Override
    public void renderIcon() {
        if (progress <= 0) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.BUBBLE_BURST_ICON, 81);
        } else {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.BUBBLE_ICON, 81);
        }
    }

    @Override
    public void renderText() {
        RenderHudFunctions.drawText(Math.round(getBreathPercentage()*100) + "%", getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(AIR.name()), 81);
    }

    @Override
    public boolean getSide() {
        return renderSide;
    }

    @Override
    public IHudElement setSide(boolean side) {
        this.renderSide = side;
        return this;
    }

    @Override
    public boolean shouldRender() {
        assert ASBModUtils.getPlayer() != null;
        return Settings.shouldRenderSettings.get(AIR.name()) && ASBModUtils.getPlayer().getAir() < 300;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get(AIR.name());
    }

    @Override
    public boolean shouldRenderText() {
        return shouldRender() && Settings.textSettings.get(AIR.name());
    }

    @Override
    public void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder) {
        BooleanListEntry toggleAirBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_air_bar"), Settings.shouldRenderSettings.get(AIR.name()))
                .setDefaultValue(AIR.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(AIR.name(), newValue))
                .build();
        mainCategory.addEntry(toggleAirBar);

        BooleanListEntry toggleAirIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_air_icon"), Settings.iconSettings.get(AIR.name()))
                .setDefaultValue(AIR.shouldRenderIcon())
                .setSaveConsumer(newValue -> Settings.iconSettings.replace(AIR.name(), newValue))
                .build();
        iconCategory.addEntry(toggleAirIcon);

        BooleanListEntry toggleAirText = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_air_text"), Settings.textSettings.get(AIR.name()))
                .setDefaultValue(AIR.shouldRenderText())
                .setSaveConsumer(newValue -> Settings.textSettings.replace(AIR.name(), newValue))
                .build();
        textCategory.addEntry(toggleAirText);

        ColorEntry airColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.air_color"), Settings.colorSettings.get(AIR.name()))
                .setDefaultValue(AIR.color())
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(AIR.name(), newValue))
                .build();
        colorCategory.addEntry(airColor);

        ColorEntry airTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.air_text_color"), Settings.textColorSettings.get(AIR.name()))
                .setDefaultValue(AIR.color())
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(AIR.name(), newValue))
                .build();
        textColorSettings.addEntry(airTextColor);

        FloatListEntry airAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.air_alpha"), Settings.alphaSettings.get(AIR.name()))
                .setDefaultValue(AIR.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(AIR.name(), newValue))
                .build();
        alphaCategory.addEntry(airAlpha);
    }

    @Override
    public String name() {
        return AIR.name();
    }

    private void getBreathProgress() {
        float ratio = getBreathPercentage();
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 2);
    }

    private float getBreathPercentage() {
        assert ASBModUtils.getPlayer() != null;
        float breath = ASBModUtils.getPlayer().getAir();
        float breathMax = 300;
        return breath / breathMax;
    }
}

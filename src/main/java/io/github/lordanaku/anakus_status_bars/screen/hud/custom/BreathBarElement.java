package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import net.minecraft.util.math.MathHelper;

public class BreathBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getBreathPercent();
        if (progress <= 0) {
            RenderHudElements.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), Settings.colorSettings.get("color_hurt"));
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()));
        }
        RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), progress, Settings.colorSettings.get("color_breath"), 1);
    }

    @Override
    public void renderIcon() {
        if (progress <= 0) {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.BUBBLE_BURST_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.BUBBLE_ICON);
        }
    }

    @Override
    public boolean getSide() {
        return renderSide;
    }

    @Override
    public HudElements setSide(boolean side) {
        this.renderSide = side;
        return this;
    }

    @Override
    public boolean shouldRender() {
        assert ASBModUtils.getPlayer() != null;
        return Settings.shouldRenderSettings.get("breath") && ASBModUtils.getPlayer().getAir() < 300;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get("icon_breath");
    }

    @Override
    public String name() {
        return "Breath";
    }

    private void getBreathPercent() {
        assert ASBModUtils.getPlayer() != null;
        float breath = ASBModUtils.getPlayer().getAir();
        float breathMax = 300;
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(breath / breathMax * maxProgress) + 2);
    }
}

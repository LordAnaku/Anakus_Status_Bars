package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import net.minecraft.util.math.MathHelper;

public class BreathBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getBreathPercent();
        if (progress <= 0) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get("color_hurt"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get("color_breath"), 1);
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
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(breath / breathMax * maxProgress) + 2);
    }
}

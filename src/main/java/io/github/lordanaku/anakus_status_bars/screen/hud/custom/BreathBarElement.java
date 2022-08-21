package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class BreathBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getBreathPercent();
        if (progress <= 0) {
            RenderHudElements.drawStatusEffectBar(getSide(), ModUtils.getPosYMod(getSide()), Settings.hurtBarColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ModUtils.getPosYMod(getSide()));
        }
        RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), progress, Settings.breathBarColor, 1);
    }

    @Override
    public void renderIcon() {
        if (progress <= 0) {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.BUBBLE_BURST_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.BUBBLE_ICON);
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
        assert ModUtils.getPlayer() != null;
        return Settings.enableBreathBar && ModUtils.getPlayer().getAir() < 300;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.enableBreathIcon;
    }

    @Override
    public String name() {
        return "Breath";
    }

    private void getBreathPercent() {
        assert ModUtils.getPlayer() != null;
        float breath = ModUtils.getPlayer().getAir();
        float breathMax = 300;
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(breath / breathMax * maxProgress) + 2);
    }
}

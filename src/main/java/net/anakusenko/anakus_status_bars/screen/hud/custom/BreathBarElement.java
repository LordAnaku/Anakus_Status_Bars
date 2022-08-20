package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class BreathBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getBreathPercent();
        int posYMod;
        if (getSide()) {
            posYMod = ModUtils.leftSideIncrement;
        } else {
            posYMod = ModUtils.rightSideIncrement;
        }
        if (progress <= 0) {
            RenderHudElements.drawStatusEffectBar(getSide(), posYMod, Settings.hungerEffectColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), posYMod);
        }
        RenderHudElements.drawProgressBar(getSide(), posYMod, progress, Settings.breathBarColor, 1);
    }

    @Override
    public void renderText() {

    }

    @Override
    public void renderIcon() {

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
    public boolean shouldRenderText() {
        return false;
    }

    @Override
    public boolean shouldRenderIcon() {
        return false;
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

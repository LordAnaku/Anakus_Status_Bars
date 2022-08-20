package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

public class HealthHudElement implements HudElements {
    private boolean renderSide;
    private int progress;
    private int aProgress;

    @Override
    public void renderBar() {
        getHealthPercent();

        assert ModUtils.getPlayer() != null;
        int posYMod;
        if (getSide()) {
            posYMod = ModUtils.leftSideIncrement;
        } else {
            posYMod = ModUtils.rightSideIncrement;
        }
        if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudElements.drawStatusEffectBar(getSide(), posYMod, Settings.healthPoisonColor, 1);
        } else if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), posYMod, Settings.healthWitherColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), posYMod);
        }
        RenderHudElements.drawProgressBar(getSide(), posYMod, progress, Settings.healthBarColor, 1);

        if (Settings.enableAbsorptionBar) {
            getAbsorptionPercent();
            RenderHudElements.drawProgressBar(getSide(), posYMod, aProgress, Settings.absorptionBarColor, 1);
        }
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
        return Settings.enableHealthBar;
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
        return "Health";
    }

    private void getAbsorptionPercent() {
        assert ModUtils.getPlayer() != null;
        float absorption = ModUtils.getPlayer().getAbsorptionAmount();
        float absorptionMax = ModUtils.getPlayer().getMaxHealth();
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        aProgress = Math.min(maxProgress, MathHelper.ceil(absorption / absorptionMax * maxProgress) + 2);
    }

    private void getHealthPercent() {
        assert ModUtils.getPlayer() != null;
        float health = ModUtils.getPlayer().getHealth();
        float healthMax = ModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 2);
    }
}

package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.utils.interfaces.HudElements;
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
        if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ModUtils.getPosYMod(getSide()), Settings.healthPoisonColor, 1);
        } else if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ModUtils.getPosYMod(getSide()), Settings.healthWitherColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ModUtils.getPosYMod(getSide()));
        }
        RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), progress, Settings.healthBarColor, 1);

        if (Settings.enableAbsorptionBar) {
            getAbsorptionPercent();
            RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), aProgress, Settings.absorptionBarColor, 1);
        }
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_OUTLINE_ICON);

        assert ModUtils.getPlayer() != null;
        if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_POISON_ICON);
        } else if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_WITHER_ICON);
        } else if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_GOLD_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_ICON);
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
        return Settings.enableHealthBar;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.enableHealthIcon;
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

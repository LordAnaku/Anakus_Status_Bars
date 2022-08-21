package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

public class HealthHudElement implements HudElements {
    private boolean renderSide;
    private int progress;
    private int aProgress;

    @Override
    public void renderBar() {
        getHealthPercent();

        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), Settings.colorSettings.get("color_health_poison"));
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), Settings.colorSettings.get("color_health_wither"));
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()));
        }
        RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), progress, Settings.colorSettings.get("color_health"), 1);

        if (Settings.shouldRenderSettings.get("absorption")) {
            getAbsorptionPercent();
            RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), aProgress, Settings.colorSettings.get("color_absorption"), 1);
        }
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_OUTLINE_ICON);

        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_POISON_ICON);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_WITHER_ICON);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_GOLD_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_ICON);
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
        return Settings.shouldRenderSettings.get("health");
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get("icon_health");
    }

    @Override
    public String name() {
        return "Health";
    }

    private void getAbsorptionPercent() {
        assert ASBModUtils.getPlayer() != null;
        float absorption = ASBModUtils.getPlayer().getAbsorptionAmount();
        float absorptionMax = ASBModUtils.getPlayer().getMaxHealth();
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        aProgress = Math.min(maxProgress, MathHelper.ceil(absorption / absorptionMax * maxProgress) + 2);
    }

    private void getHealthPercent() {
        assert ASBModUtils.getPlayer() != null;
        float health = ASBModUtils.getPlayer().getHealth();
        float healthMax = ASBModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 2);
    }
}

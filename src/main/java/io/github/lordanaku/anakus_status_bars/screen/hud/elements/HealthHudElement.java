package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
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
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get("color_health_poison"));
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get("color_health_wither"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get("color_health"), 1);

        if (Settings.shouldRenderSettings.get("absorption")) {
            getAbsorptionPercent();
            RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, aProgress, Settings.colorSettings.get("color_absorption"), 1);
        }
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_OUTLINE_ICON, 81);

        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_POISON_ICON, 81);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_WITHER_ICON, 81);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_GOLD_ICON, 81);
        } else {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_ICON, 81);
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
        int maxProgress = 81;
        aProgress = Math.min(maxProgress, MathHelper.ceil(absorption / absorptionMax * maxProgress) + 2);
    }

    private void getHealthPercent() {
        assert ASBModUtils.getPlayer() != null;
        float health = ASBModUtils.getPlayer().getHealth();
        float healthMax = ASBModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 2);
    }
}

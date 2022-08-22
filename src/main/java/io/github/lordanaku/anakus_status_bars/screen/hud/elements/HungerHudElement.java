package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

public class HungerHudElement implements HudElements {
    private boolean renderSide;
    private int currentHunger;
    private int currentSaturation;
    private int currentExhaust;
    private final int maxProgress = 81;
    @Override
    public void renderBar() {
        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get("color_hunger_effect"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }

        getHunger();
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, currentHunger, Settings.colorSettings.get("color_hunger"), 1);

        if (Settings.shouldRenderSettings.get("saturation")) {
            getSaturation();
            RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, currentSaturation, Settings.colorSettings.get("color_saturation"), 1);
        }

        if (Settings.shouldRenderSettings.get("exhaustion")) {
            getExhaust();
            RenderHudFunctions.drawExhaustBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.EXHAUSTION_BAR, currentExhaust, Settings.alphaSettings.get("alpha_exhaustion"));
        }
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_OUTLINE_ICON, 81);

        assert ASBModUtils.getPlayer() != null;
        if(ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_EFFECT_ICON, 81);
        } else {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HUNGER_ICON, 81);
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
        return Settings.shouldRenderSettings.get("hunger");
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get("icon_hunger");
    }

    @Override
    public String name() {
        return "Hunger";
    }

    private void getHunger() {
        assert ASBModUtils.getPlayer() != null;
        int cFoodLevel = ASBModUtils.getPlayer().getHungerManager().getFoodLevel();
        float ratio = Math.min(1, Math.max(0, cFoodLevel / 20f));
        currentHunger = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getSaturation() {
        assert ASBModUtils.getPlayer() != null;
        float cSatLevel = ASBModUtils.getPlayer().getHungerManager().getSaturationLevel();
        float ratio = Math.min(1, Math.max(0, cSatLevel / 20f));
        currentSaturation = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getExhaust() {
        assert ASBModUtils.getPlayer() != null;
        float cExhLevel = ASBModUtils.getPlayer().getHungerManager().getExhaustion();
        float maxExhaust = 4f;
        float ratio = Math.min(1, Math.max(0, cExhLevel / maxExhaust));
        currentExhaust = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress));
    }
}

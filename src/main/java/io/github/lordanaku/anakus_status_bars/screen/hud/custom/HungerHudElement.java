package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

public class HungerHudElement implements HudElements {
    private boolean renderSide;
    private int currentHunger;
    private int currentSaturation;
    private int currentExhaust;
    private final int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
    @Override
    public void renderBar() {
        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), Settings.colorSettings.get("color_hunger_effect"));
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()));
        }

        getHunger();
        RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), currentHunger, Settings.colorSettings.get("color_hunger"), 1);

        if (Settings.shouldRenderSettings.get("saturation")) {
            getSaturation();
            RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), currentSaturation, Settings.colorSettings.get("color_saturation"), 1);
        }

        if (Settings.shouldRenderSettings.get("exhaustion")) {
            getExhaust();
            RenderHudElements.drawExhaustBar(getSide(), ASBModUtils.getPosYMod(getSide()), currentExhaust, Settings.alphaSettings.get("alpha_exhaustion"));
        }
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_OUTLINE_ICON);

        assert ASBModUtils.getPlayer() != null;
        if(ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_EFFECT_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_ICON);
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

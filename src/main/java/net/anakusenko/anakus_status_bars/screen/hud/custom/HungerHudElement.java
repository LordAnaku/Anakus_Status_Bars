package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
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
        assert ModUtils.getPlayer() != null;
        int posYMod;
        if (getSide()) {
            posYMod = ModUtils.leftSideIncrement;
        } else {
            posYMod = ModUtils.rightSideIncrement;
        }
        if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), posYMod, Settings.hungerEffectColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), posYMod);
        }

        getHunger();
        RenderHudElements.drawProgressBar(getSide(), posYMod, currentHunger, Settings.hungerBarColor, 1);

        if (Settings.enableSaturationBar) {
            getSaturation();
            RenderHudElements.drawProgressBar(getSide(), posYMod, currentSaturation, Settings.saturationBarColor, 1);
        }

        if (Settings.enableExhaustionBar) {
            getExhaust();
            RenderHudElements.drawExhaustBar(getSide(), posYMod, currentExhaust, Settings.exhaustionBarAlpha);
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
        return Settings.enableHungerBar;
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
        return "Hunger";
    }

    private void getHunger() {
        assert ModUtils.getPlayer() != null;
        int cFoodLevel = ModUtils.getPlayer().getHungerManager().getFoodLevel();
        float ratio = Math.min(1, Math.max(0, cFoodLevel / 20f));
        currentHunger = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getSaturation() {
        assert ModUtils.getPlayer() != null;
        float cSatLevel = ModUtils.getPlayer().getHungerManager().getSaturationLevel();
        float ratio = Math.min(1, Math.max(0, cSatLevel / 20f));
        currentSaturation = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private void getExhaust() {
        assert ModUtils.getPlayer() != null;
        float cExhLevel = ModUtils.getPlayer().getHungerManager().getExhaustion();
        float maxExhaust = 4f;
        float ratio = Math.min(1, Math.max(0, cExhLevel / maxExhaust));
        currentExhaust = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress));
    }
}

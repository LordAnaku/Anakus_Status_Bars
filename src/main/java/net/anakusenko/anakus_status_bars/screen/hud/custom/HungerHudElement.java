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
        if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudElements.drawStatusEffectBar(getSide(), ModUtils.getPosYMod(getSide()), Settings.hungerEffectColor, 1);
        } else {
            RenderHudElements.drawDefaultBar(getSide(), ModUtils.getPosYMod(getSide()));
        }

        getHunger();
        RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), currentHunger, Settings.hungerBarColor, 1);

        if (Settings.enableSaturationBar) {
            getSaturation();
            RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), currentSaturation, Settings.saturationBarColor, 1);
        }

        if (Settings.enableExhaustionBar) {
            getExhaust();
            RenderHudElements.drawExhaustBar(getSide(), ModUtils.getPosYMod(getSide()), currentExhaust, Settings.exhaustionBarAlpha);
        }
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_OUTLINE_ICON);

        assert ModUtils.getPlayer() != null;
        if(ModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_EFFECT_ICON);
        } else {
            RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HUNGER_ICON);
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
        return Settings.enableHungerBar;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.enableHungerIcon;
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

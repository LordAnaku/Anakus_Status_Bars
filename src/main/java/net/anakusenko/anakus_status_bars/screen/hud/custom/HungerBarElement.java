package net.anakusenko.anakus_status_bars.screen.hud.custom;


import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HungerBarElement {
    private static int currentHunger = 75;
    private static int currentSaturation = 50;
    private static int currentExhaust = 25;
    private static final int maxProgress = 81;


    public static void drawHungerBar() {
        if (Settings.enableHungerBar) {
            assert ModUtils.getPlayer() != null;
            if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.HUNGER)) {
                RenderHudElements.drawStatusEffectBar(false, -40, Settings.hungerEffectColor, .85f);
            } else {
                RenderHudElements.drawDefaultBar(false, -40);
            }

            getHunger();
            RenderHudElements.drawProgressBar(false, -40, currentHunger, Settings.hungerBarColor, 1);

            if (Settings.enableSaturationBar){
                getSaturation();
                RenderHudElements.drawProgressBar(false, -40, currentSaturation, Settings.saturationBarColor, 1);
            }

            if (Settings.enableExhaustionBar){
                getExhaust();
                RenderHudElements.drawExhaustBar(false, -40, currentExhaust, Settings.exhaustionBarAlpha);
            }
        }
    }

    private static void getHunger() {
        assert ModUtils.getPlayer() != null;
        int cFoodLevel = ModUtils.getPlayer().getHungerManager().getFoodLevel();
        float ratio = Math.min(1, Math.max(0, cFoodLevel / 20f));
        currentHunger = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private static void getSaturation() {
        assert ModUtils.getPlayer() != null;
        float cSatLevel = ModUtils.getPlayer().getHungerManager().getSaturationLevel();
        float ratio = Math.min(1, Math.max(0, cSatLevel / 20f));
        currentSaturation = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress) + 2);
    }

    private static void getExhaust() {
        assert ModUtils.getPlayer() != null;
        float cExhLevel = ModUtils.getPlayer().getHungerManager().getExhaustion();
        float maxExhaust = 4f;
        float ratio = Math.min(1, Math.max(0, cExhLevel / maxExhaust));
        currentExhaust = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress));
    }
}

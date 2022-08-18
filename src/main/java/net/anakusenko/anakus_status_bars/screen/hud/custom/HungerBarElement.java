package net.anakusenko.anakus_status_bars.screen.hud.custom;


import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HungerBarElement {
    private static int currentHunger = 75;
    private static int currentSaturation = 50;
    private static int currentExhaust = 25;
    private static int maxProgress = 81;
    private static float maxExhaust = 4f;


    public static void drawHungerBar() {
        getHunger();
        getSaturation();
        getExhaust();
        RenderHudElements.drawDefaultBar(false,-40);
        RenderHudElements.drawProgressBar(false, -40, currentHunger, Settings.hungerBarColor,1);
        RenderHudElements.drawProgressBar(false, -40, currentSaturation, Settings.saturationBarColor,1);
        RenderHudElements.drawExhaustBar(false, -40, currentExhaust, Settings.exhaustionBarAlpha);
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
        float ratio = Math.min(1, Math.max(0, cExhLevel / maxExhaust));
        currentExhaust = Math.min(maxProgress, MathHelper.ceil(ratio*maxProgress));
    }
}

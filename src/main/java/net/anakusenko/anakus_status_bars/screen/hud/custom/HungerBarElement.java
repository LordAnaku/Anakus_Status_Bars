package net.anakusenko.anakus_status_bars.screen.hud.custom;


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
        RenderHudElements.drawProgressBar(false, -40, currentHunger, 0.8f,0.5f,0f,1);
        RenderHudElements.drawProgressBar(false, -40, currentSaturation, 1f,1f,0f,1);
        RenderHudElements.drawExhaustBar(false, -40, currentExhaust);
    }

    private static void getHunger() {
        if(ModUtils.getPlayer() != null) {
            int cFoodLevel = ModUtils.getPlayer().getHungerManager().getFoodLevel();
            currentHunger = MathHelper.ceil((cFoodLevel/20f)*maxProgress);
        }
    }

    private static void getSaturation() {
        if(ModUtils.getPlayer() != null) {
            float cSatLevel = ModUtils.getPlayer().getHungerManager().getSaturationLevel();
            currentSaturation = MathHelper.ceil((cSatLevel/20f)*maxProgress);
        }
    }

    private static void getExhaust() {
        if(ModUtils.getPlayer() != null){
            float cExhLevel = ModUtils.getPlayer().getHungerManager().getExhaustion();
            currentExhaust = MathHelper.ceil((cExhLevel/maxExhaust)*maxProgress);
            ModUtils.sendMessage("Exhaust: " + currentExhaust);
        }
    }
}

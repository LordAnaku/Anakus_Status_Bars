package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HealthBarElement {
    private static int progress;
    private static int maxProgress = 81;

    public static void drawHealthBar() {
        getHealthPercent();
        RenderHudElements.drawDefaultBar(true,-40);
        RenderHudElements.drawProgressBar(true, -40, progress, 0.8f, 0.2f, 0.2f, .85f);
    }

    public static void getHealthPercent() {
        assert ModUtils.getPlayer() != null;
        float health = ModUtils.getPlayer().getHealth();
        float healthMax = ModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 4);
    }
}

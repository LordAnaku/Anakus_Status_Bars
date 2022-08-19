package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HealthBarElement {
    private static int progress;
    private static int maxProgress = 81;

    public static void drawHealthBar() {
        if (Settings.enableHealthBar) {
            getHealthPercent();
            assert ModUtils.getPlayer() != null;
            if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
                RenderHudElements.drawStatusEffectBar(true, -40, Settings.healthPoisonColor, .85f);
            } else if (ModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
                RenderHudElements.drawStatusEffectBar(true, -40, Settings.healthWitherColor, .85f);
            } else {
                RenderHudElements.drawDefaultBar(true, -40);
            }
            RenderHudElements.drawProgressBar(true, -40, progress, Settings.healthBarColor, .85f);
        }
    }

    public static void getHealthPercent() {
        assert ModUtils.getPlayer() != null;
        float health = ModUtils.getPlayer().getHealth();
        float healthMax = ModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 4);
    }
}

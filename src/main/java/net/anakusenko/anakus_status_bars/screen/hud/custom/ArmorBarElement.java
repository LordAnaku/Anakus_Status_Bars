package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class ArmorBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        assert ModUtils.getPlayer() != null;
        if (Settings.enableArmorBar && ModUtils.getPlayer().getArmor() > 0) {
            getArmorPercent();
            int posYMod;
            if (getSide()) {
                posYMod = ModUtils.leftSideIncrement;
            } else {
                posYMod = ModUtils.rightSideIncrement;
            }
            RenderHudElements.drawDefaultBar(getSide(), posYMod);
            RenderHudElements.drawProgressBar(getSide(), posYMod, progress, Settings.armorBarColor, 1);
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
        assert ModUtils.getPlayer() != null;
        return Settings.enableArmorBar && ModUtils.getPlayer().getArmor() > 0;
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
        return "Armor";
    }

    private void getArmorPercent() {
        assert ModUtils.getPlayer() != null;
        float armor = ModUtils.getPlayer().getArmor();
        float armorMax = 20;
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(armor / armorMax * maxProgress) + 2);
    }
}

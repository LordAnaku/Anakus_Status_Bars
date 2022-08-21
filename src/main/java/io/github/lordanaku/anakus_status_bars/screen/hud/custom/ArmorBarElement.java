package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class ArmorBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        assert ModUtils.getPlayer() != null;
        getArmorPercent();
        RenderHudElements.drawDefaultBar(getSide(), ModUtils.getPosYMod(getSide()));
        RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), progress, Settings.armorBarColor, 1);
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.ARMOR_ICON);
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
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.enableArmorIcon;
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

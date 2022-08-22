package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import net.minecraft.util.math.MathHelper;

public class ArmorBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        assert ASBModUtils.getPlayer() != null;
        getArmorPercent();
        RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get("color_armor"), 1);
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.ARMOR_ICON, 81);
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
        assert ASBModUtils.getPlayer() != null;
        return Settings.shouldRenderSettings.get("armor") && ASBModUtils.getPlayer().getArmor() > 0;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get("icon_armor");
    }

    @Override
    public String name() {
        return "Armor";
    }

    private void getArmorPercent() {
        assert ASBModUtils.getPlayer() != null;
        float armor = ASBModUtils.getPlayer().getArmor();
        float armorMax = 20;
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(armor / armorMax * maxProgress) + 2);
    }
}

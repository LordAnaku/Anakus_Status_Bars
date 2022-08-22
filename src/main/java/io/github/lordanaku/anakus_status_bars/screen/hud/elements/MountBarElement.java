package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import net.minecraft.util.math.MathHelper;

public class MountBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getMountPercent();
        RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get("color_mount"), 1);
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_OUTLINE_ICON, 81);
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_MOUNT_ICON, 81);
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
        return Settings.shouldRenderSettings.get("mount") && ASBModUtils.getRiddenEntity() != null;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get("icon_mount");
    }

    @Override
    public String name() {
        return "MountHealth";
    }

    private void getMountPercent() {
        assert ASBModUtils.getRiddenEntity() != null;
        float mount = ASBModUtils.getRiddenEntity().getHealth();
        float mountMax = ASBModUtils.getRiddenEntity().getMaxHealth();
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(mount / mountMax * maxProgress) + 2);
    }
}

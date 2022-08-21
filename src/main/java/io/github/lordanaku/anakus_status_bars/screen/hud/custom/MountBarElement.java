package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.HudElements;
import net.minecraft.util.math.MathHelper;

public class MountBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getMountPercent();
        RenderHudElements.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()));
        RenderHudElements.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), progress, Settings.colorSettings.get("color_mount"), 1);
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_OUTLINE_ICON);
        RenderHudElements.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureUtils.HEART_MOUNT_ICON);
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
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(mount / mountMax * maxProgress) + 2);
    }
}

package io.github.lordanaku.anakus_status_bars.screen.hud.custom;

import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.screen.hud.RenderHudElements;
import io.github.lordanaku.anakus_status_bars.utils.ModUtils;
import io.github.lordanaku.anakus_status_bars.utils.TextureUtils;
import io.github.lordanaku.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class MountBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getMountPercent();
        RenderHudElements.drawDefaultBar(getSide(), ModUtils.getPosYMod(getSide()));
        RenderHudElements.drawProgressBar(getSide(), ModUtils.getPosYMod(getSide()), progress, Settings.mountBarColor, 1);
    }

    @Override
    public void renderIcon() {
        RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_OUTLINE_ICON);
        RenderHudElements.drawIcon(getSide(), ModUtils.getPosYMod(getSide()), TextureUtils.HEART_MOUNT_ICON);
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
        return Settings.enableMountBar && ModUtils.getRiddenEntity() != null;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.enableMountIcon;
    }

    @Override
    public String name() {
        return "MountHealth";
    }

    private void getMountPercent() {
        assert ModUtils.getRiddenEntity() != null;
        float mount = ModUtils.getRiddenEntity().getHealth();
        float mountMax = ModUtils.getRiddenEntity().getMaxHealth();
        int maxProgress = TextureUtils.PROGRESS_BAR.getWidth();
        progress = Math.min(maxProgress, MathHelper.ceil(mount / mountMax * maxProgress) + 2);
    }
}

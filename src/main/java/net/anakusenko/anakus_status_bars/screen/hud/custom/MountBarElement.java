package net.anakusenko.anakus_status_bars.screen.hud.custom;

import net.anakusenko.anakus_status_bars.screen.gui.config.Settings;
import net.anakusenko.anakus_status_bars.screen.hud.RenderHudElements;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.anakusenko.anakus_status_bars.utils.TextureUtils;
import net.anakusenko.anakus_status_bars.utils.interfaces.HudElements;
import net.minecraft.util.math.MathHelper;

public class MountBarElement implements HudElements {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getMountPercent();
        int posYMod;
        if (getSide()) {
            posYMod = ModUtils.leftSideIncrement;
        } else {
            posYMod = ModUtils.rightSideIncrement;
        }
        RenderHudElements.drawDefaultBar(getSide(), posYMod);
        RenderHudElements.drawProgressBar(getSide(), posYMod, progress, Settings.mountBarColor, 1);
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
        return Settings.enableMountBar && ModUtils.getRiddenEntity() != null;
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

package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings.MOUNT_HEALTH;

public class MountBarElement implements IHudElement {
    private boolean renderSide;
    private int progress;
    @Override
    public void renderBar() {
        getMountPercent();
        RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get(MOUNT_HEALTH.name()), 1);
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_OUTLINE_ICON, 81);
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_MOUNT_ICON, 81);
    }

    @Override
    public void renderText() {
        LivingEntity mountEntity = ASBModUtils.getRiddenEntity(); assert mountEntity != null;
        RenderHudFunctions.drawText(String.valueOf(Math.round(mountEntity.getHealth())), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(MOUNT_HEALTH.name()), 81);
    }

    @Override
    public boolean getSide() {
        return renderSide;
    }

    @Override
    public IHudElement setSide(boolean side) {
        this.renderSide = side;
        return this;
    }

    @Override
    public boolean shouldRender() {
        return Settings.shouldRenderSettings.get(MOUNT_HEALTH.name()) && ASBModUtils.getRiddenEntity() != null;
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get(MOUNT_HEALTH.name());
    }

    @Override
    public boolean shouldRenderText() {
        return shouldRender() && Settings.textSettings.get(MOUNT_HEALTH.name());
    }

    @Override
    public void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder) {
        BooleanListEntry toggleMountBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_mount_bar"), Settings.shouldRenderSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(MOUNT_HEALTH.name(), newValue))
                .build();
        mainCategory.addEntry(toggleMountBar);

        BooleanListEntry toggleMountBarIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_mount_icon"), Settings.iconSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.shouldRenderIcon())
                .setSaveConsumer(newValue -> Settings.iconSettings.replace(MOUNT_HEALTH.name(), newValue))
                .build();
        iconCategory.addEntry(toggleMountBarIcon);

        BooleanListEntry toggleMountBarText = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_mount_text"), Settings.textSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.shouldRenderText())
                .setSaveConsumer(newValue -> Settings.textSettings.replace(MOUNT_HEALTH.name(), newValue))
                .build();
        textCategory.addEntry(toggleMountBarText);

        ColorEntry mountColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.mount_color"), Settings.colorSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.color())
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(MOUNT_HEALTH.name(), newValue))
                .build();
        colorCategory.addEntry(mountColor);

        ColorEntry mountTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.mount_text_color"), Settings.textColorSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.color())
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(MOUNT_HEALTH.name(), newValue))
                .build();
        textColorSettings.addEntry(mountTextColor);

        FloatListEntry mountAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.mount_alpha"), Settings.alphaSettings.get(MOUNT_HEALTH.name()))
                .setDefaultValue(MOUNT_HEALTH.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(MOUNT_HEALTH.name(), MathHelper.clamp(newValue, 0, 1)))
                .build();
        alphaCategory.addEntry(mountAlpha);
    }

    @Override
    public String name() {
        return MOUNT_HEALTH.name();
    }

    private void getMountPercent() {
        assert ASBModUtils.getRiddenEntity() != null;
        float mount = ASBModUtils.getRiddenEntity().getHealth();
        float mountMax = ASBModUtils.getRiddenEntity().getMaxHealth();
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(mount / mountMax * maxProgress) + 2);
    }
}

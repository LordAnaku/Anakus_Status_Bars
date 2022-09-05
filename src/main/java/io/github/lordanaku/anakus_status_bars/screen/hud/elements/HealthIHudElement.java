package io.github.lordanaku.anakus_status_bars.screen.hud.elements;

import io.github.lordanaku.anakus_status_bars.api.hudelements.IHudElement;
import io.github.lordanaku.anakus_status_bars.api.hudelements.RenderHudFunctions;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings;
import io.github.lordanaku.anakus_status_bars.utils.TextureRecords;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.ColorEntry;
import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

import static io.github.lordanaku.anakus_status_bars.screen.gui.config.Settings.HEALTH;

public class HealthIHudElement implements IHudElement {
    private boolean renderSide;
    private int progress;
    private int aProgress;

    @Override
    public void renderBar() {
        getHealthPercent();

        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get(HEALTH.name() + "_poison"));
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get(HEALTH.name() + "_wither"));
        } else if (ASBModUtils.getPlayer().isFrozen()) {
            RenderHudFunctions.drawStatusEffectBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR, Settings.colorSettings.get(HEALTH.name() + "_frostbite"));
        } else {
            RenderHudFunctions.drawDefaultBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.DEFAULT_BAR);
        }
        RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, progress, Settings.colorSettings.get(HEALTH.name()), Settings.alphaSettings.get(HEALTH.name()));

        if (Settings.shouldRenderSettings.get(HEALTH.name() + "_absorption")) {
            getAbsorptionPercent();
            RenderHudFunctions.drawProgressBar(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.PROGRESS_BAR, aProgress, Settings.colorSettings.get(HEALTH.name() + "_absorption"), Settings.alphaSettings.get(HEALTH.name() + "_absorption"));
        }
    }

    @Override
    public void renderIcon() {
        RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_OUTLINE_ICON, 81);

        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.POISON)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_POISON_ICON, 81);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.WITHER)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_WITHER_ICON, 81);
        } else if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_GOLD_ICON, 81);
        } else if (ASBModUtils.getPlayer().isFrozen()) {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_ICE_ICON, 81);
        } else {
            RenderHudFunctions.drawIcon(getSide(), ASBModUtils.getPosYMod(getSide()), TextureRecords.HEART_ICON, 81);
        }
    }

    @Override
    public void renderText() {
        assert ASBModUtils.getPlayer() != null;
        if (ASBModUtils.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
            RenderHudFunctions.drawText(String.valueOf(Math.round(ASBModUtils.getPlayer().getHealth() + ASBModUtils.getPlayer().getAbsorptionAmount())), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(HEALTH.name() + "_absorption"), 81);
        } else {
            RenderHudFunctions.drawText(String.valueOf(Math.round(ASBModUtils.getPlayer().getHealth())), getSide(), shouldRenderIcon(), ASBModUtils.getPosYMod(getSide()), Settings.textColorSettings.get(HEALTH.name()), 81);
        }
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
        return Settings.shouldRenderSettings.get(HEALTH.name());
    }

    @Override
    public boolean shouldRenderIcon() {
        return shouldRender() && Settings.iconSettings.get(HEALTH.name());
    }

    @Override
    public boolean shouldRenderText() {
        return shouldRender() && Settings.textSettings.get(HEALTH.name());
    }

    @Override
    public void registerSettings(ConfigCategory mainCategory, ConfigCategory iconCategory, ConfigCategory textCategory, ConfigCategory colorCategory, ConfigCategory textColorSettings, ConfigCategory alphaCategory, ConfigEntryBuilder entryBuilder) {
        /* * Health * */
        BooleanListEntry toggleHealthBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_health_bar"), Settings.shouldRenderSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(HEALTH.name(), newValue))
                .build();
        mainCategory.addEntry(toggleHealthBar);

        BooleanListEntry toggleHealthIcon = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_health_icon"), Settings.iconSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.shouldRenderIcon())
                .setSaveConsumer(newValue -> Settings.iconSettings.replace(HEALTH.name(), newValue))
                .build();
        iconCategory.addEntry(toggleHealthIcon);

        BooleanListEntry toggleHealthText = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_health_text"), Settings.textSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.shouldRenderText())
                .setSaveConsumer(newValue -> Settings.textSettings.replace(HEALTH.name(), newValue))
                .build();
        textCategory.addEntry(toggleHealthText);

        ColorEntry healthColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.health_color"), Settings.colorSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.color())
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(HEALTH.name(), newValue))
                .build();
        colorCategory.addEntry(healthColor);

        ColorEntry healthTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.health_text_color"), Settings.textColorSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.color())
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(HEALTH.name(), newValue))
                .build();
        textColorSettings.addEntry(healthTextColor);

        FloatListEntry healthAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.health_alpha"), Settings.alphaSettings.get(HEALTH.name()))
                .setDefaultValue(HEALTH.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(HEALTH.name(), newValue))
                .build();
        alphaCategory.addEntry(healthAlpha);

        /* * Status Effects * */
        ColorEntry poisonColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.poison_color"), Settings.colorSettings.get(HEALTH.name() + "_poison"))
                .setDefaultValue(Settings.POISON_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(HEALTH.name() + "_poison", newValue))
                .build();
        colorCategory.addEntry(poisonColor);

        ColorEntry witherColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.wither_color"), Settings.colorSettings.get(HEALTH.name() + "_wither"))
                .setDefaultValue(Settings.WITHER_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(HEALTH.name() + "_wither", newValue))
                .build();
        colorCategory.addEntry(witherColor);

        ColorEntry frostbiteColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.frostbite_color"), Settings.colorSettings.get(HEALTH.name() + "_frostbite"))
                .setDefaultValue(Settings.FROSTBITE_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(HEALTH.name() + "_frostbite", newValue))
                .build();
        colorCategory.addEntry(frostbiteColor);

        /* * Absorption * */
        BooleanListEntry toggleAbsorptionBar = entryBuilder.startBooleanToggle(Text.translatable("option.anakus_status_bars.toggle_absorption_bar"), Settings.shouldRenderSettings.get(HEALTH.name() + "_absorption"))
                .setDefaultValue(HEALTH.shouldRender())
                .setSaveConsumer(newValue -> Settings.shouldRenderSettings.replace(HEALTH.name() + "_absorption", newValue))
                .build();
        mainCategory.addEntry(toggleAbsorptionBar);

        ColorEntry absorptionColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.absorption_color"), Settings.colorSettings.get(HEALTH.name() + "_absorption"))
                .setDefaultValue(Settings.ABSORPTION_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.colorSettings.replace(HEALTH.name() + "_absorption", newValue))
                .build();
        colorCategory.addEntry(absorptionColor);

        ColorEntry absorptionTextColor = entryBuilder.startColorField(Text.translatable("option.anakus_status_bars.absorption_text_color"), Settings.textColorSettings.get(HEALTH.name() + "_absorption"))
                .setDefaultValue(Settings.ABSORPTION_COLOR_DEFAULT)
                .setSaveConsumer(newValue -> Settings.textColorSettings.replace(HEALTH.name() + "_absorption", newValue))
                .build();
        textColorSettings.addEntry(absorptionTextColor);

        FloatListEntry absorptionAlpha = entryBuilder.startFloatField(Text.translatable("option.anakus_status_bars.absorption_alpha"), Settings.alphaSettings.get(HEALTH.name() + "_absorption"))
                .setDefaultValue(HEALTH.alpha())
                .setMin(0.0f)
                .setMax(1.0f)
                .setTooltip(Text.translatable("option.anakus_status_bars.alpha_tooltip"))
                .setSaveConsumer(newValue -> Settings.alphaSettings.replace(HEALTH.name() + "_absorption", newValue))
                .build();
        alphaCategory.addEntry(absorptionAlpha);
    }

    @Override
    public String name() {
        return HEALTH.name();
    }

    private void getAbsorptionPercent() {
        assert ASBModUtils.getPlayer() != null;
        float absorption = ASBModUtils.getPlayer().getAbsorptionAmount();
        float absorptionMax = ASBModUtils.getPlayer().getMaxHealth();
        int maxProgress = 81;
        aProgress = Math.min(maxProgress, MathHelper.ceil(absorption / absorptionMax * maxProgress) + 2);
    }

    private void getHealthPercent() {
        assert ASBModUtils.getPlayer() != null;
        float health = ASBModUtils.getPlayer().getHealth();
        float healthMax = ASBModUtils.getPlayer().getMaxHealth();
        float ratio = Math.min(1, Math.max(0, health / healthMax));
        int maxProgress = 81;
        progress = Math.min(maxProgress, MathHelper.ceil(ratio * maxProgress) + 2);
    }
}

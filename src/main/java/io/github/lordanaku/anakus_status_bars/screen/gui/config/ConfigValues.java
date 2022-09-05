package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.text.Text;


import java.util.ArrayList;

public class ConfigValues{
    public static void buildPosition(ConfigCategory category, ConfigEntryBuilder builder) {
        StringListListEntry leftOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.left_order"), Settings.positionOrderSettings.get("left"))
                .setDefaultValue(Settings.leftOrderDefault)
                .setSaveConsumer(newValue -> Settings.positionOrderSettings.replace("left", new ArrayList<>(newValue)))
                .build();
        category.addEntry(leftOrder);

        StringListListEntry rightOrder = builder.startStrList(Text.translatable("option.anakus_status_bars.right_order"), Settings.positionOrderSettings.get("right"))
                .setDefaultValue(Settings.rightOrderDefault)
                .setSaveConsumer(newValue -> Settings.positionOrderSettings.replace("right", new ArrayList<>(newValue)))
                .build();
        category.addEntry(rightOrder);

        IntegerListEntry leftYOffset = builder.startIntField(Text.translatable("option.anakus_status_bars.left_y_offset"), Settings.positionOffsets.get("left_y_offset"))
                .setDefaultValue(40)
                .setSaveConsumer(newValue -> Settings.positionOffsets.replace("left_y_offset", newValue))
                .build();
        category.addEntry(leftYOffset);

        IntegerListEntry rightYOffset = builder.startIntField(Text.translatable("option.anakus_status_bars.right_y_offset"), Settings.positionOffsets.get("right_y_offset"))
                .setDefaultValue(40)
                .setSaveConsumer(newValue -> Settings.positionOffsets.replace("right_y_offset", newValue))
                .build();
        category.addEntry(rightYOffset);

        IntegerListEntry leftXOffset = builder.startIntField(Text.translatable("option.anakus_status_bars.left_x_offset"), Settings.positionOffsets.get("left_x_offset"))
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> Settings.positionOffsets.replace("left_x_offset", newValue))
                .build();
        category.addEntry(leftXOffset);

        IntegerListEntry rightXOffset = builder.startIntField(Text.translatable("option.anakus_status_bars.right_x_offset"), Settings.positionOffsets.get("right_x_offset"))
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> Settings.positionOffsets.replace("right_x_offset", newValue))
                .build();
        category.addEntry(rightXOffset);
    }
}

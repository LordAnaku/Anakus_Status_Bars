package net.anakusenko.anakus_status_bars.screen.gui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.anakusenko.anakus_status_bars.utils.LogHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFileHandler {
    public static void readFromConfig() {
        JsonObject root = new JsonObject();
        try (FileReader file = new FileReader(getConfigFile())) {
            root = JsonParser.parseReader(file).getAsJsonObject();
        } catch (IOException e) {
            LogHelper.error(e.getMessage());
        }

        JsonObject finalRoot = root;
        finalRoot.keySet().forEach(keyStr -> {
            switch (keyStr) {
                /* * Should Render Settings */
                case "enableHealthBar" -> Settings.enableHealthBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableHungerBar" -> Settings.enableHungerBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableSaturationBar" -> Settings.enableSaturationBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableExhaustionBar" -> Settings.enableExhaustionBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableArmorBar" -> Settings.enableArmorBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableBreathBar" -> Settings.enableBreathBar = finalRoot.get(keyStr).getAsBoolean();
                case "enableMountBar" -> Settings.enableMountBar = finalRoot.get(keyStr).getAsBoolean();
                /* * Color Settings */
                case "healthBarColor" -> Settings.healthBarColor = finalRoot.get(keyStr).getAsInt();
                case "hungerBarColor" -> Settings.hungerBarColor = finalRoot.get(keyStr).getAsInt();
                case "saturationBarColor" -> Settings.saturationBarColor = finalRoot.get(keyStr).getAsInt();
                case "exhaustionBarAlpha" -> Settings.exhaustionBarAlpha = finalRoot.get(keyStr).getAsInt();
                case "armorBarColor" -> Settings.armorBarColor = finalRoot.get(keyStr).getAsInt();
                case "breathBarColor" -> Settings.breathBarColor = finalRoot.get(keyStr).getAsInt();
                case "mountBarColor" -> Settings.mountBarColor = finalRoot.get(keyStr).getAsInt();
            }
        });
    }

    public static void writeToConfig() {
        JsonObject root = new JsonObject();

        /* * Should Render Settings */
        root.addProperty("enableArmorBar",Settings.enableArmorBar);
        root.addProperty("enableBreathBar",Settings.enableBreathBar);
        root.addProperty("enableExhaustionBar",Settings.enableExhaustionBar);
        root.addProperty("enableHungerBar",Settings.enableHungerBar);
        root.addProperty("enableHealthBar",Settings.enableHealthBar);
        root.addProperty("enableMountBar",Settings.enableMountBar);
        root.addProperty("enableSaturationBar",Settings.enableSaturationBar);
        /* * Color Settings */
        root.addProperty("armorBarColor",Settings.armorBarColor);
        root.addProperty("breathBarColor",Settings.breathBarColor);
        root.addProperty("exhaustionBarAlpha",Settings.exhaustionBarAlpha);
        root.addProperty("hungerBarColor",Settings.hungerBarColor);
        root.addProperty("healthBarColor",Settings.healthBarColor);
        root.addProperty("mountBarColor",Settings.mountBarColor);
        root.addProperty("saturationBarColor",Settings.saturationBarColor);

        try (FileWriter file = new FileWriter(getConfigFile())) {
            file.write(new GsonBuilder().setPrettyPrinting().create().toJson(root));
            file.flush();
        } catch (IOException e) {
            LogHelper.error(e.getMessage());
        }
    }

    public static File getConfigFile() {
        return FabricLoader.getInstance().getConfigDir().resolve("anakus_status_bars.json").toFile();
    }
}

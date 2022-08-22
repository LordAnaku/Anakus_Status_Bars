package io.github.lordanaku.anakus_status_bars.screen.gui.config;

import com.google.gson.*;
import io.github.lordanaku.anakus_status_bars.utils.LogHelper;
import io.github.lordanaku.anakus_status_bars.utils.ASBModUtils;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Map;

public class ConfigFileHandler {
    public static void readFromConfig() {
        JsonObject root = new JsonObject();
        try (FileReader file = new FileReader(getConfigFile())) {
            root = JsonParser.parseReader(file).getAsJsonObject();
        } catch (IOException e) {
            LogHelper.error(e.getMessage());
        }

        /* * Load settings * */
        try {
            if(root.has("render_settings")) {
                JsonObject object = root.get("render_settings").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    Settings.shouldRenderSettings.replace(entry.getKey(), entry.getValue().getAsBoolean());
                }
            }
            if(root.has("color_settings")) {
                JsonObject object = root.get("color_settings").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    Settings.colorSettings.replace(entry.getKey(), entry.getValue().getAsInt());
                }
            }
            if(root.has("alpha_settings")) {
                JsonObject object = root.get("alpha_settings").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    Settings.alphaSettings.replace(entry.getKey(), entry.getValue().getAsFloat());
                }
            }
            if(root.has("icon_settings")) {
                JsonObject object = root.get("icon_settings").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    Settings.iconSettings.replace(entry.getKey(), entry.getValue().getAsBoolean());
                }
            }
            if(root.has("render_side")) {
                JsonObject object = root.get("render_side").getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    Settings.positionSettings.replace(entry.getKey(), ASBModUtils.getStringList(entry.getValue().getAsJsonArray()));
                }
            }
        } catch (Exception e) {
            LogHelper.error(e.getMessage());
        }
    }

    public static void writeToConfig() {
        JsonObject root = new JsonObject();
        Gson gson = new Gson();
        /* * Save settings * */

        try {
            root.add("render_settings", gson.toJsonTree(Settings.shouldRenderSettings));
            root.add("color_settings", gson.toJsonTree(Settings.colorSettings));
            root.add("alpha_settings", gson.toJsonTree(Settings.alphaSettings));
            root.add("icon_settings", gson.toJsonTree(Settings.iconSettings));
            root.add("render_side", gson.toJsonTree(Settings.positionSettings));
        } catch (Exception e) {
            LogHelper.error(e.getMessage());
        }

        Settings.setupHudElements();

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

package net.anakusenko.anakus_status_bars.screen.gui.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.anakusenko.anakus_status_bars.utils.LogHelper;
import net.anakusenko.anakus_status_bars.utils.ModUtils;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

public class ConfigFileHandler {
    public static void readFromConfig() {
        JsonObject root = new JsonObject();
        try (FileReader file = new FileReader(getConfigFile())) {
            root = JsonParser.parseReader(file).getAsJsonObject();
        } catch (IOException e) {
            LogHelper.error(e.getMessage());
        }

        /* * Load settings * */
        if (Files.exists(getConfigFile().toPath())) {
            try {
                for (Field field : Settings.class.getDeclaredFields()) {
                    Type fieldType = field.getType();
                    if (root.has(field.getName())) {
                        if (boolean.class.equals(fieldType)) {
                            field.setBoolean(null, root.get(field.getName()).getAsBoolean());
                        } else if (int.class.equals(fieldType)) {
                            field.setInt(null, root.get(field.getName()).getAsInt());
                        } else if (float.class.equals(fieldType)) {
                            field.setFloat(null, root.get(field.getName()).getAsFloat());
                        } else if (List.class.equals(fieldType)) {
                            field.set(null, root.get(field.getName()).getAsJsonArray());
                        } else if (String.class.equals(fieldType)) {
                            field.set(null, root.get(field.getName()).getAsString());
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                LogHelper.error(e.getMessage());
            }
        }
    }

    public static void writeToConfig() {
        JsonObject root = new JsonObject();

        /* * Save settings * */
        for (Field field : Settings.class.getDeclaredFields()) {
            try {
                root.addProperty(field.getName(), field.get(null).toString());
            } catch (IllegalAccessException e) {
                LogHelper.error(e.getMessage());
            }
        }

        ModUtils.setupHudElements();

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

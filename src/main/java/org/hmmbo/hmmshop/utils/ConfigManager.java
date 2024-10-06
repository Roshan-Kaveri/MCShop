package org.hmmbo.hmmshop.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.hmmbo.hmmshop.HmmShop;

import java.io.File;
import java.io.IOException;


public class ConfigManager {
    public static YamlConfiguration ConfigFile;
    static HmmShop instance;

    public ConfigManager(HmmShop instance) {
        this.instance = instance;
       // ConfigFile = (YamlConfiguration) instance.getConfig();
        File qfile = new File(instance.getDataFolder(), "config.yml");
        if (!qfile.exists()) {
            instance.saveResource("config.yml", false);
        }
        ConfigFile = YamlConfiguration.loadConfiguration(qfile);
    }

    public static void update(YamlConfiguration c) {
        ConfigFile = c;
        File qfile = new File(instance.getDataFolder(), "config.yml");
        if (!qfile.exists()) {
            instance.saveResource("config.yml", false);
        }

        try {
            ConfigFile.save(qfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void saveConfig() {
        try {
            ConfigFile.save(instance.getDataFolder() + "/config.yml");
        } catch (Exception e) {
            instance.getLogger().severe("Could not save config to " + instance.getDataFolder() + "/config.yml");
            e.printStackTrace();
        }
    }

    public static void reloadConfig() {
        instance.reloadConfig();
        ConfigFile = (YamlConfiguration) instance.getConfig();
    }

    public static String Get(String path) {
        return ConfigFile.getString(path);
    }

    public static Integer GetInt(String path) {
        return ConfigFile.getInt(path);
    }

    public static Boolean GetB(String path) {
        return ConfigFile.getBoolean(path);
    }
}

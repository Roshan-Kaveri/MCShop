package org.hmmbo.hmmshop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.hmmbo.hmmshop.commands.ShopCommand;
import org.hmmbo.hmmshop.inventory.BuyMenu;
import org.hmmbo.hmmshop.inventory.ShopMenu;
import org.hmmbo.hmmshop.shop.ShopItemManager;
import org.hmmbo.hmmshop.utils.ConfigManager;
import org.hmmbo.hmmshop.utils.VaultUtils;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.io.File;

public final class HmmShop extends JavaPlugin {
    public static boolean DependencyVA = false;
    public static boolean DependencyIA = false;
    public static boolean DependencyOX = false;
    public static HmmShop instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            VaultUtils.setupVault(this, this);
            DependencyVA = true;
        }


        File cfile = new File(getDataFolder(), "config.yml");
        if (!cfile.exists()) {
            saveResource("config.yml", false);
        }
        File sfile = new File(getDataFolder(), "shop.yml");
        if (!sfile.exists()) {
            saveResource("shop.yml", false);
        }

        new ShopCommand(this);
        new ConfigManager(this);
        Bukkit.getPluginManager().registerEvents(new ShopMenu(), this);
        Bukkit.getPluginManager().registerEvents(new BuyMenu(), this);

        BukkitCommandHandler handler = BukkitCommandHandler.create(this);
        handler.register(new ShopCommand(this));
        new ShopItemManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

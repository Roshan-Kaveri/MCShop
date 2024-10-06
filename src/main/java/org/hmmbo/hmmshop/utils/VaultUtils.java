package org.hmmbo.hmmshop.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.hmmbo.hmmshop.HmmShop;


import java.util.ArrayList;
import java.util.List;

public class VaultUtils {
    private static Economy econ = null;

    public static Economy getEcon() {
        return econ;
    }

    public static void setupVault(HmmShop instance, Plugin plugin) {
        if (!setupEconomy(instance)) {
          instance.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", instance.getDescription().getName()));
           instance. getServer().getPluginManager().disablePlugin(plugin);
        }
    }


    private static boolean setupEconomy(HmmShop instance) {
        if (instance.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = instance.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static boolean deposit(Player player, Double money){
        Economy eco = VaultUtils.getEcon();
        eco.depositPlayer(player,money);
        player.sendMessage("depo " + money);
        return true;
    }
    public static boolean withdraw(Player player, Double money){


        Economy eco = VaultUtils.getEcon();
        double bal = eco.getBalance(player);
        if(bal < money){
            return false;
        }
        eco.withdrawPlayer(player,money);
        player.sendMessage("withdrawed " + money);
        return true;
    }

}

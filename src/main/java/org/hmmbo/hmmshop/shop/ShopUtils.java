package org.hmmbo.hmmshop.shop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.hmmbo.hmmshop.utils.VaultUtils;

public class ShopUtils {

    public static boolean buy_sell(Player player, int num, boolean buy, ItemStack item) {
        if (item!= null && item.getItemMeta() != null) {
            double price;


            if (buy) {
                price = item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.bpkey, PersistentDataType.DOUBLE);
                    boolean success = VaultUtils.withdraw(player, price * num);
                    if (!success) {
                        player.sendMessage(ChatColor.RED + "You don't have enough money to buy this item.");
                    }
                    return success;

            } else {
                price = item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.spkey, PersistentDataType.DOUBLE);
                    return VaultUtils.deposit(player, price * num);

            }
        }
        return false;
    }


}

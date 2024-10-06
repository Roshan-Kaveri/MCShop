package org.hmmbo.hmmshop.shop;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ShopItem {
    ItemStack shopItem;
    double buy_price;
    double sell_price;
    boolean canBuy;
    boolean canSell;
    String displayName;
    List<String> lore;

    public ShopItem(ItemStack shopItem, double buy_price, double sell_price, boolean canBuy, boolean canSell, String displayName, List<String> lore) {
        this.shopItem = shopItem;
        this.buy_price = buy_price;
        this.sell_price = sell_price;
        this.canBuy = canBuy;
        this.canSell = canSell;
        this.displayName = displayName;
        this.lore = lore;
    }

    public ItemStack getShopItem() {
        ItemMeta itemMeta = shopItem.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> l = new ArrayList<>();
        for (String s: lore) {
            s = s.replaceAll("%buy_price%",buy_price +"").replaceAll("%sell_price%",sell_price +"");
            l.add(s);
        }
        if(itemMeta.getLore() != null && !itemMeta.getLore().isEmpty()) {
            itemMeta.getLore().addAll(l);
        }else {
            itemMeta.setLore(l);
        }
        itemMeta.getPersistentDataContainer().set(ShopItemManager.bpkey, PersistentDataType.DOUBLE,buy_price);
        itemMeta.getPersistentDataContainer().set(ShopItemManager.spkey, PersistentDataType.DOUBLE,sell_price);

        shopItem.setItemMeta(itemMeta);

        return shopItem;
    }
}

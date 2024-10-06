package org.hmmbo.hmmshop.shop;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hmmbo.hmmshop.HmmShop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShopItemManager {
    List<ShopItem> itemList = new ArrayList<>();
    public static NamespacedKey spkey;
    public static NamespacedKey bpkey;
    public ShopItemManager(HmmShop main) {
         spkey = new NamespacedKey(main,"sell_price");
         bpkey = new NamespacedKey(main,"buy_price");
        this.itemList = loadItemList(main);
    }

    public List<ShopItem> loadItemList(HmmShop main){
        File file = new File(main.getDataFolder(), "shop.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        itemList.clear();
        if (yml.contains("ShopItems.")) {
            for (String key : yml.getConfigurationSection("ShopItems").getKeys(false)) {
                String item = key;

                /* ITEMSTACk */
                ItemStack itemStack = new ItemStack(Material.BEDROCK);
                if(Material.getMaterial(key) != null) {
                    itemStack = new ItemStack(Objects.requireNonNull(Material.getMaterial(item)));
                }

                /* Other */
                Double buy_price = yml.getDouble("ShopItems."+key+".buy_price");
                Double sell_price = yml.getDouble("ShopItems."+key+".sell_price");
                boolean canBuy = yml.getBoolean("ShopItems."+key+".canBuy");
                boolean canSell = yml.getBoolean("ShopItems."+key+".canSell");

                String displayNamee = yml.getString("ShopItems."+key+".display_name");
                List<String> lore = yml.getStringList("ShopItems."+key+".lore");

                ShopItem s = new ShopItem(itemStack,buy_price,sell_price,canBuy,canSell,displayNamee,lore);
                itemList.add(s);
            }
        }
        return itemList;
    }

    public List<ItemStack> getItemList() {
       List<ItemStack> list = new ArrayList<>();
       for (ShopItem item : itemList){
           list.add(item.getShopItem());
       }
       return list;
    }

    public void setItemList(List<ShopItem> itemList) {
        this.itemList = itemList;
    }
}

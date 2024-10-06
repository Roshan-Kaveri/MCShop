package org.hmmbo.hmmshop.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.hmmbo.hmmshop.shop.ShopItemManager;
import org.hmmbo.hmmshop.utils.InvUtils;
import org.hmmbo.hmmshop.utils.PageUtil;
import org.hmmbo.hmmshop.utils.VaultUtils;

import java.util.*;

public class ShopMenu implements Listener {
    static String name = "Shop Menu";
    static List<ItemStack>  itemslist;
    public static Inventory shopMenu(Player player,int page, List<ItemStack> items) {
        itemslist = items;
        Inventory blockmenu = Bukkit.createInventory(player, 9 * 6, name);

        blockmenu.setItem(44, InvUtils.createItemStacks(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.WHITE +"Next Page",
                "",
                ""
        ));
        blockmenu.setItem(36, InvUtils.createItemStacks(
                Material.RED_STAINED_GLASS_PANE,
                ChatColor.WHITE +"Previous Page",
                "",""
        ));
        blockmenu.setItem(45, InvUtils.createItemStacks(
                Material.PLAYER_HEAD,
                player.getDisplayName()
                ,
                ChatColor.WHITE + "About Him",
                ChatColor.WHITE +"A Super Cool Guy"

        ));

        blockmenu.setItem(53, InvUtils.createItemStacks(
                Material.BARRIER,
                ChatColor.RED +  "Close |  Exit ",
                "",
                ChatColor.WHITE + "Closes The Current Gui"

        ));

        ItemStack frame = InvUtils.createItemStacks(Material.PURPLE_STAINED_GLASS_PANE, "", "", "");
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 46, 47, 48, 49, 50, 51, 52}) {
            blockmenu.setItem(i, frame);
        }

        //   System.out.println(PageUtil.getpageitems(items,page,28));


        if(PageUtil.isPageValid(items,page-1,28)){
            Objects.requireNonNull(blockmenu.getItem(36)).setType(Material.GREEN_STAINED_GLASS_PANE);
        }
        if(PageUtil.isPageValid(items,page+1,28)){
            Objects.requireNonNull(blockmenu.getItem(44)).setType(Material.GREEN_STAINED_GLASS_PANE);
        }
        int k = 10;
        for(ItemStack s : PageUtil.getpageitems(items,page,28)) {
            if (k >= 44) {
                break;
            }
            if (Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 46, 47, 48, 50, 51, 52, 53, 45, 49).contains(k)) {
                k++;
                k++;
            }
            blockmenu.setItem(k,s);
            k++;
        }
        ItemMeta it = Objects.requireNonNull(blockmenu.getItem(44)).getItemMeta();
        Objects.requireNonNull(it).setLocalizedName(page+"");
        Objects.requireNonNull(blockmenu.getItem(44)).setItemMeta(it);

        // System.out.println();

        return blockmenu;
    }


    @EventHandler
    public void oninvcclick(InventoryClickEvent f) {
        if (ChatColor.translateAlternateColorCodes('&', f.getView().getTitle()).equals(name)) {
            f.setCancelled(true);
            for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 17, 26, 35, 46, 47, 48, 49, 50, 51, 52}) {
                if (f.getRawSlot() == i) {
                    return;
                }
            }
            int page = Integer.parseInt(f.getInventory().getItem(44).getItemMeta().getLocalizedName());
            Player p = (Player) f.getWhoClicked();
            switch (f.getRawSlot()) {
                case 36:
                    if (Objects.requireNonNull(f.getCurrentItem()).getType() == Material.GREEN_STAINED_GLASS_PANE) {

                        f.getWhoClicked().openInventory(shopMenu((Player) f.getWhoClicked(),
                                page - 1, itemslist));
                    }
                    break;

                case 44:
                    if (Objects.requireNonNull(f.getCurrentItem()).getType() == Material.GREEN_STAINED_GLASS_PANE) {
                        f.getWhoClicked().openInventory(shopMenu((Player) f.getWhoClicked(),
                                page + 1, itemslist));
                    }
                    break;
                case 53:

                    p.closeInventory();
                    break;
                case 45:
                    break;
                default:
                    if(f.isLeftClick()) {
                       p.openInventory( BuyMenu.shopMenu(p, true, f.getCurrentItem()));
                    } else if (f.isRightClick()) {
                        p.openInventory( BuyMenu.shopMenu(p, false, f.getCurrentItem()));
                    }


            }

        }
    }
}



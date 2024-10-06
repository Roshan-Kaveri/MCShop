package org.hmmbo.hmmshop.inventory;

import net.wesjd.anvilgui.AnvilGUI;
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
import org.hmmbo.hmmshop.HmmShop;
import org.hmmbo.hmmshop.shop.ShopItemManager;
import org.hmmbo.hmmshop.shop.ShopUtils;
import org.hmmbo.hmmshop.utils.InvUtils;
import org.hmmbo.hmmshop.utils.VaultUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuyMenu implements Listener {

    static String name = "Buy Menu";
    static String sellName = "Sell Menu";
    static List<ItemStack> itemsList;

    public static Inventory shopMenu(Player player, boolean buyMenu, ItemStack item) {
        Inventory blockMenu = Bukkit.createInventory(player, 9 * 6, (buyMenu) ? name : sellName);




        double price, price2;
        if (buyMenu) {
            price = item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.bpkey, PersistentDataType.DOUBLE);
        } else {
            price = item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.spkey, PersistentDataType.DOUBLE);
        }
        if (!buyMenu) {
            price2 = item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.bpkey, PersistentDataType.DOUBLE);
        } else {
            price2= item.getItemMeta().getPersistentDataContainer().get(ShopItemManager.spkey, PersistentDataType.DOUBLE);
        }










        blockMenu.setItem(36, InvUtils.createItemStacks(
                item,
                player.getDisplayName(),
                ChatColor.WHITE + "About Him",
                ChatColor.WHITE + "A Super Cool Guy"
        ));

        blockMenu.setItem(22, item);

        blockMenu.setItem(22+9, InvUtils.createItemStacks(
                Material.NAME_TAG,
                ChatColor.GREEN + "Custom Amoount",
                ChatColor.WHITE + "Enter Custom Amount",
                ChatColor.WHITE + ""
        ));



        blockMenu.setItem(23, InvUtils.createItemStacks(
                Material.GREEN_STAINED_GLASS_PANE,
                ChatColor.GREEN + "Buy 1",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price +"$"
        ));
        blockMenu.setItem(24, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 16),
                ChatColor.GREEN + "Buy 16",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price * 16 +"$"
        ));
        blockMenu.setItem(25, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 32),
                ChatColor.GREEN + "Buy 32",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price * 32 +"$"
        ));

        blockMenu.setItem(21, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_STAINED_GLASS, 1),
                ChatColor.GREEN + "Buy 1 Stack",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price * 64 +"$"
        ));
        blockMenu.setItem(20, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_STAINED_GLASS, 16),
                ChatColor.GREEN + "Buy 16 Stacks",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price * 16 * 64 +"$"
        ));
        blockMenu.setItem(19, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_STAINED_GLASS, 32),
                ChatColor.GREEN + "+ 32 Stacks",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price * 32 * 64 +"$"
        ));

        blockMenu.setItem(39, InvUtils.createItemStacks(
                new ItemStack(Material.GREEN_WOOL, 1),
                ChatColor.GREEN + "Sell Items",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price2 +"$"
        ));

        blockMenu.setItem(41, InvUtils.createItemStacks(
                new ItemStack(Material.RED_WOOL, 1),
                ChatColor.GREEN + "Buy Items",
                ChatColor.WHITE + "Price",
                ChatColor.WHITE + "" + price +"$"
        ));

        blockMenu.setItem(44, InvUtils.createItemStacks(
                Material.BARRIER,
                ChatColor.RED + "Close | Exit",
                "",
                ChatColor.WHITE + "Closes The Current GUI"
        ));

        ItemStack frame = InvUtils.createItemStacks(Material.PURPLE_STAINED_GLASS_PANE,"","","");
        for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,46,47,49,48,50,51,52}) {
            blockMenu.setItem(i ,frame);
        }

        return blockMenu;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        boolean isBuyMenu = false;
        if (ChatColor.stripColor(event.getView().getTitle()).equals(name)
                || ChatColor.stripColor(event.getView().getTitle()).equals(sellName)) {
            if (ChatColor.stripColor(event.getView().getTitle()).equals(name)) {
                isBuyMenu = true;
            }

            event.setCancelled(true);
            int[] validSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 17, 26, 35, 46, 47, 48, 49, 50, 51, 52};
            for (int i : validSlots) {
                if (event.getRawSlot() == i) {
                    return;
                }
            }
            ItemStack itemStack = event.getInventory().getItem(22);

            Player player = (Player) event.getWhoClicked();
            boolean status = false;
            int amount = itemStack.getAmount();
            switch (event.getRawSlot()) {
                case 23:
                    amount += 1;
                    break;
                case 24:
                    amount += 16;
                    break;
                case 25:
                    amount += 32;
                    break;
                case 21:
                    amount += 64;
                    break;
                case 20:
                    amount += 16 * 64;
                    break;
                case 19:
                    amount += 32 * 64;
                    break;
                case 44:
                    player.closeInventory();
                    return;
                case 31:
                    Inventory inv = event.getInventory();
                    boolean finalIsBuyMenu = isBuyMenu;
                    ItemStack originalItem = event.getCurrentItem(); // The item the player is buying/selling

                    new AnvilGUI.Builder()
                            .onClose(stateSnapshot -> {
                                player.sendMessage("Closed Inventory");
                            })
                            .onClick((slot, stateSnapshot) -> {
                                if (slot != AnvilGUI.Slot.OUTPUT) {
                                    if (slot == AnvilGUI.Slot.INPUT_LEFT || slot == AnvilGUI.Slot.INPUT_RIGHT) {
                                        String text = stateSnapshot.getText();
                                        try {
                                            int amount1 = Integer.parseInt(text);
                                            if (amount1 > 0) {
                                                double price;
                                                if (finalIsBuyMenu) {
                                                    price = originalItem.getItemMeta().getPersistentDataContainer().get(ShopItemManager.bpkey, PersistentDataType.DOUBLE);
                                                } else {
                                                    price = originalItem.getItemMeta().getPersistentDataContainer().get(ShopItemManager.spkey, PersistentDataType.DOUBLE);
                                                }

                                                    ItemStack pricedItem = new ItemStack(Material.PAPER);
                                                    ItemMeta pricedMeta = pricedItem.getItemMeta();
                                                    pricedMeta.setDisplayName(ChatColor.GOLD + "Total Price: " + (price * amount1));
                                                    pricedItem.setItemMeta(pricedMeta);


                                            }
                                        } catch (NumberFormatException e) {
                                            stateSnapshot.getPlayer().sendMessage(ChatColor.RED + "Please enter a valid integer.");
                                        }
                                    }
                                    return Collections.emptyList();
                                }
                                String text = stateSnapshot.getText();
                                try {
                                    int amount1 = Integer.parseInt(text);
                                    if (amount1 > 0) {
                                        boolean status1 = ShopUtils.buy_sell(player, amount1, finalIsBuyMenu , itemStack);
                                        if (status1) {
                                            originalItem.setAmount(amount1);
                                            // Reset the item amount
                                        } else {
                                            stateSnapshot.getPlayer().sendMessage(ChatColor.RED + "Transaction failed. Possibly insufficient funds.");
                                        }
                                    } else {
                                        stateSnapshot.getPlayer().sendMessage(ChatColor.RED + "Please enter a positive integer.");
                                    }
                                } catch (NumberFormatException e) {
                                    stateSnapshot.getPlayer().sendMessage(ChatColor.RED + "Please enter a valid integer.");
                                }
                                return Arrays.asList(AnvilGUI.ResponseAction.close(), AnvilGUI.ResponseAction.openInventory(BuyMenu.shopMenu(player,finalIsBuyMenu,itemStack)));
                            })
                            .text("Numbers Only")
                            .title("Enter Amount")
                            .plugin(HmmShop.instance)
                            .itemLeft(originalItem) // The item the player is buying/selling
                            .itemRight(new ItemStack(Material.PAPER)) // Default item on the right
                            .open(player);
                    break;
                case 39: itemStack.setAmount(1);

                    break;
                case 41:
                    if(isBuyMenu) {
                        if (event.getRawSlot() != 44 && amount != 0) {
                            status = ShopUtils.buy_sell(player, amount, isBuyMenu, itemStack);
                            if (status) {
                                itemStack.setAmount(amount);
                                player.getInventory().addItem(itemStack);
                            }
                        }
                    }else {
                        if (event.getRawSlot() != 44 && amount != 0) {
                            status = ShopUtils.buy_sell(player, amount, !isBuyMenu,itemStack);
                            if (status) {
                                itemStack.setAmount(amount);
                                if(player.getInventory().contains(itemStack)) {
                                    player.getInventory().remove(itemStack);
                                }else {
                                    player.sendMessage("u aint rich bro");
                                }
                            }
                        }
                    }
                    break;


            }
            event.getInventory().getItem(22).setAmount(amount);

        }
    }
}

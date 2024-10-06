package org.hmmbo.hmmshop.commands;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;
import org.hmmbo.hmmshop.HmmShop;
import org.hmmbo.hmmshop.inventory.ShopMenu;
import org.hmmbo.hmmshop.shop.ShopItemManager;
import revxrsal.commands.annotation.AutoComplete;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("Shop")
@CommandPermission(value = "", defaultAccess = PermissionDefault.OP)
@AutoComplete("display | incr | decr | update | insert | select")
public class ShopCommand {
    HmmShop instance;

    public ShopCommand(HmmShop instance) {
        this.instance = instance;
    }

    @AutoComplete("<player>")
    @Subcommand("menu")
    public void pnCommand(Player sender) {
        ShopItemManager s = new ShopItemManager(instance);
        sender.openInventory(ShopMenu.shopMenu(sender,1,s.getItemList()));
    }


}

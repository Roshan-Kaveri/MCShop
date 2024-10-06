package org.hmmbo.hmmshop.utils;

import io.th0rgal.oraxen.api.OraxenBlocks;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.mechanics.Mechanic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.hmmbo.hmmshop.HmmShop;

public class OraxenUtils {

   public static boolean isOraxenBlock(String id){
       if(HmmShop.DependencyOX){
           return OraxenBlocks.isOraxenBlock(id) || OraxenItems.exists(id);
       }
       return false;
   }

    public static boolean isOraxenBlock(ItemStack id){
        if(HmmShop.DependencyOX){
            if(OraxenItems.exists(id)){
                if(OraxenBlocks.isOraxenBlock(OraxenItems.getIdByItem(id))){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isOraxenBlock(Block id){
        if(HmmShop.DependencyOX){
            return OraxenBlocks.isOraxenBlock(id);
        }
        return false;
    }

   public static String getID(Block b){
       Mechanic mechanic = OraxenBlocks.getOraxenBlock(b.getBlockData());
       if (mechanic == null) {
           return null;
       }
       return mechanic.getItemID();
   }
    public static String getID(ItemStack b){
        Mechanic mechanic = OraxenBlocks.getOraxenBlock(OraxenBlocks.getOraxenBlockData(OraxenItems.getIdByItem(b)));
        if (mechanic == null) {
            return null;
        }
        return mechanic.getItemID();
    }

    public static void place(String id , Location l) {
        OraxenBlocks.place(id,l);
    }





    
}

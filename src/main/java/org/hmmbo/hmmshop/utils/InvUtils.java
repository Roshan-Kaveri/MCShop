package org.hmmbo.hmmshop.utils;

import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.hmmbo.hmmshop.HmmShop;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.hmmbo.hmmshop.utils.Messages.getHexMsg;


public class InvUtils {

    public  static ItemStack createItemStacks(Material material, String name, String line1,String line2,String line3, String line4 ,  String line5){
       if(material == null){
           ItemStack i = new ItemStack(Material.BEDROCK);
           ItemMeta im = i.getItemMeta();
           im.setDisplayName("Error in this" + name);
           i.setItemMeta(im);
           return i;
       }

        if ( material.isAir()) {
            material = Material.DRAGON_BREATH;
        }

        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.add(line4);
        lore.add(line5);
        im.setLore(lore);
        item.setItemMeta(im);
        return  item;
    }
    public  static ItemStack createItemStacks(Material material, String name, String line1,String line2,String line3, String line4){
        if(material == null){
            ItemStack i = new ItemStack(Material.BEDROCK);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("Error in this" + name);
            i.setItemMeta(im);
            return i;
        }
        if (material.isAir()) {
            material = Material.DRAGON_BREATH;
        ;
        }
        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.add(line4);
        im.setLore(lore);
        item.setItemMeta(im);
        return  item;
    }
    public  static ItemStack createItemStacks(Material material, String name, String line1,String line2,String line3){
        if(material == null){
            ItemStack i = new ItemStack(Material.BEDROCK);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("Error in this" + name);
            i.setItemMeta(im);
            return i;
        }
        if (material.isAir()) {
            material = Material.DRAGON_BREATH;

        }

        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);

        im.setLore(lore);
        item.setItemMeta(im);
        return  item;
    }

    public  static ItemStack createItemStacks(Material material, String name, String line1,String line2,String line3, List<String> list){
        if(material == null){
            ItemStack i = new ItemStack(Material.BEDROCK);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("Error in this" + name);
            i.setItemMeta(im);
            return i;
        }
        if (material.isAir()) {
            material = Material.DRAGON_BREATH;

        }
        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.addAll(list);
        im.setLore(lore);
        item.setItemMeta(im);
        return  item;
    }

    public static ItemStack createItemStacks(ItemStack items, String name, String line1, String line2, String line3, List<String> list) {
        ItemStack item = items;
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);
        lore.add(line3);
        lore.addAll(list);
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    public  static ItemStack createItemStacks(Material material, String name, String line1,String line2){
        if(material == null){
            ItemStack i = new ItemStack(Material.BEDROCK);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("Error in this" + name);
            i.setItemMeta(im);
            return i;
        }
        if (material.isAir()) {
            material = Material.DRAGON_BREATH;

        }
        ItemStack item = new ItemStack(material, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);

        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    public static ItemStack createItemStacks(ItemStack item, String name, String line1, String line2) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        List<String> lore = new ArrayList<>();
        lore.add(line1);
        lore.add(line2);

        im.setLore(lore);
        item.setItemMeta(im);
        return  item;
    }

    public static List<ItemStack> getMaterials(Integer Min , Integer Max , List<Integer> ignored , Inventory i){
        List<ItemStack> list = new ArrayList<>();
        for(int j= Min ; j <= Max; j++){
            if(ignored.contains(j)){ continue;}
            if(i.getItem(j) == null || i.getItem(j).getType() == Material.AIR ){
                continue;
            }
            list.add(i.getItem(j));
        }
        return list;
    }

    public static YamlConfiguration onAdd(List<ItemStack> list, YamlConfiguration yml, Player player) {
        for (ItemStack item : list) {
            if (yml.contains("Materials." + item.getType() + ".Enabled")) {

                Messages.sendMessage(player,
                        "This Block Already Exists : [" + item.getType().toString().toLowerCase() + "]",1);
                continue;
            }

            String itemname = item.getType().toString();


            if (itemname.equalsIgnoreCase("POTATO")) {
                itemname = "POTATOES";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("CARROT")) {
                itemname = "CARROTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BEETROOT")) {
                itemname = "BEETROOTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BAMBOO") ||
                    itemname.equalsIgnoreCase("SUGARCANE") ||
                    itemname.equalsIgnoreCase("CACTUS")) {
                yml.set("Materials." + itemname + ".isStem", true);
            } else if (itemname.equalsIgnoreCase("COCOA_BEANS")) {
                itemname = "COCOA";
                yml.set("Materials." + itemname + ".isCrop", true);
            }


            if (yml.contains("Materials." + itemname + ".Enabled")) {

                Messages.sendMessage(player,
                        "This Block Already Exists : [" + itemname.toLowerCase() + "]",1);

                continue;
            }


            yml.set("Materials." + itemname + ".Enabled", true);
            yml.set("Materials." + itemname + ".Delay", 20);
            yml.set("Materials." + itemname + ".Drop_Xp", 20);
            yml.set("Materials." + itemname + ".Tool_Damage", 0);
            yml.set("Materials." + itemname + ".Money_Reward", 0);
            yml.set("Materials." + itemname + ".DelayBlock", List.of("BEDROCK"));
            yml.set("Materials." + itemname + ".ReplaceBlock", List.of(itemname));
            player.sendMessage(itemname + " has been added successfully.");

        }
        return yml;
    }


    public static YamlConfiguration onIAAdd(List<ItemStack> list, YamlConfiguration yml, Player player) {
        for (ItemStack item : list) {

            CustomBlock customBlock = CustomBlock.byItemStack(item);
            String itemname = item.getType().toString();
            if (customBlock != null) {
                itemname = customBlock.getNamespacedID();
            }

            if (itemname.equalsIgnoreCase("POTATO")) {
                itemname = "POTATOES";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("Wheat")) {
                yml.set("Materials." + itemname + ".isCrop", true);
            }
            if (itemname.equalsIgnoreCase("CARROT")) {
                itemname = "CARROTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BEETROOT")) {
                itemname = "BEETROOTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BAMBOO") ||
                    itemname.equalsIgnoreCase("SUGARCANE") ||
                    itemname.equalsIgnoreCase("CACTUS")) {
                yml.set("Materials." + itemname + ".isStem", true);
            } else if (itemname.equalsIgnoreCase("COCOA_BEANS")) {
                itemname = "COCOA";
                yml.set("Materials." + itemname + ".isCrop", true);
            }

            if (yml.contains("Materials." + itemname + ".Enabled")) {

                Messages.sendMessage(player,
                        "This Block Already Exists : [" + itemname.toLowerCase() + "]",
                        1);

                continue;
            }

            yml.set("Materials." + itemname + ".Enabled", true);
            yml.set("Materials." + itemname + ".Delay", 20);
            yml.set("Materials." + itemname + ".Drop_Xp", 20);
            yml.set("Materials." + itemname + ".Tool_Damage", 0);
            yml.set("Materials." + itemname + ".Money_Reward", 0);
            yml.set("Materials." + itemname + ".DelayBlock", List.of("BEDROCK"));
            yml.set("Materials." + itemname + ".ReplaceBlock", Collections.singletonList(itemname));


            Messages.sendMessage(player,
                    itemname + " has been added successfully.",
                    2);
        }
        return yml;
    }

    public static YamlConfiguration onOXAdd(List<ItemStack> list, YamlConfiguration yml, Player player) {
        for (ItemStack item : list) {

            String itemname = item.getType().toString();
            if (OraxenUtils.getID(item) != null) {
                itemname = OraxenUtils.getID(item);
            }

            if (itemname.equalsIgnoreCase("POTATO")) {
                itemname = "POTATOES";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("Wheat")) {
                yml.set("Materials." + itemname + ".isCrop", true);
            }
            if (itemname.equalsIgnoreCase("CARROT")) {
                itemname = "CARROTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BEETROOT")) {
                itemname = "BEETROOTS";
                yml.set("Materials." + itemname + ".isCrop", true);
            } else if (itemname.equalsIgnoreCase("BAMBOO") ||
                    itemname.equalsIgnoreCase("SUGARCANE") ||
                    itemname.equalsIgnoreCase("CACTUS")) {
                yml.set("Materials." + itemname + ".isStem", true);
            } else if (itemname.equalsIgnoreCase("COCOA_BEANS")) {
                itemname = "COCOA";
                yml.set("Materials." + itemname + ".isCrop", true);
            }

            if (yml.contains("Materials." + itemname + ".Enabled")) {

                Messages.sendMessage(player,
                        "This Block Already Exists : [" + itemname.toLowerCase() + "]",
                        1);

                continue;
            }

            yml.set("Materials." + itemname + ".Enabled", true);
            yml.set("Materials." + itemname + ".Delay", 20);
            yml.set("Materials." + itemname + ".Drop_Xp", 20);
            yml.set("Materials." + itemname + ".Tool_Damage", 0);
            yml.set("Materials." + itemname + ".Money_Reward", 0);
            yml.set("Materials." + itemname + ".DelayBlock", List.of("BEDROCK"));
            yml.set("Materials." + itemname + ".ReplaceBlock", Collections.singletonList(itemname));


            Messages.sendMessage(player,
                    itemname + " has been added successfully.",
                    2);
        }
        return yml;
    }


    public static YamlConfiguration onReplace(List<String> list, YamlConfiguration yml ,String key) {
        List<String> ld = yml.getStringList("Materials."+key+".ReplaceBlock");
        List<String> nlist = new ArrayList<>();
        for(String l : list){
            if(l.length() == 1){
                l = l + " ; 1";
            }
            nlist.add(l);
        }
        ld.addAll(nlist);
        yml.set("Materials."+key+".ReplaceBlock",ld);
       return yml;
    }
    public static List<String> getReplaceList(YamlConfiguration yml ,String key) {
        return  yml.getStringList("Materials."+key+".ReplaceBlock");

    }

    public static List<String> getDelayList( YamlConfiguration yml ,String key) {
        return  yml.getStringList("Materials."+key+".DelayBlock");

    }

    public static YamlConfiguration onADD(List<String> list, YamlConfiguration yml ,String key) {
        List<String> ld = yml.getStringList("Materials."+key+".ReplaceBlock");
        List<String> nlist = new ArrayList<>();
        for(String l : list){
            if(l.length() == 1){
                l = l + " ; 1";
            }
            nlist.add(l);
        }
        ld.addAll(nlist);
        yml.set("Materials."+key+".ReplaceBlock",ld);
        return yml;
    }

    public static YamlConfiguration onDelay(List<String> list, YamlConfiguration yml ,String key) {
        List<String> ld = yml.getStringList("Materials."+key+".DelayBlock");
        ld.addAll(list);
        yml.set("Materials."+key+".DelayBlock",ld);
        return yml;
    }


    public static String extractStr(String regionSetting) {
        int startIndex = regionSetting.indexOf('[') + 1;
        int endIndex = regionSetting.indexOf(']');

        if (startIndex != -1 && endIndex != -1) {
            String extractedStr = regionSetting.substring(startIndex, endIndex).trim();
            return extractedStr;
        } else {
            return null;
        }
    }

    public static void ItemSave(HmmShop main , ItemStack item) {
        File itemfile = new File(main.getDataFolder(), "items.yml");
        if (!itemfile.exists()) {
            try {
                itemfile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        YamlConfiguration itemyml = YamlConfiguration.loadConfiguration(itemfile);
        itemyml.set(item.getType()+""+item.getAmount(), item);
        try {
            itemyml.save(itemfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<ItemStack> loadItemstack(YamlConfiguration yml , String type ) {
        List<ItemStack> ld = new ArrayList<>();
        List<String> slist =  yml.getStringList("Materials." + type + ".Drops");
        for(String ss : slist){
            String[] split = ss.split(" ; ");
            ItemStack k = null;
            if(Material.getMaterial(split[0] ) == null){
              //  k = LoadItem( split[0],type,yml);
            }else{
                k = new ItemStack(Objects.requireNonNull(Material.getMaterial(split[0])),1);
            }
           ld.add(k);
        }
        return ld;
    }

    public static String getPluralCrop(String key) {
        if (key.equalsIgnoreCase("POTATO")) {
            key = "POTATOES";
        } else if (key.equalsIgnoreCase("CARROT")) {
            key = "CARROTS";
        } else if (key.equalsIgnoreCase("BEETROOT")) {
            key = "BEETROOTS";
        } else if (key.equalsIgnoreCase("COCOA_BEANS")){
            key = "COCOA";
        }
        return key;
    }

    public static String getSingleCrop(String key) {
        if (key.equalsIgnoreCase("POTATOES")) {
            key = "POTATO";
        } else if (key.equalsIgnoreCase("CARROTS")) {
            key = "CARROT";
        } else if (key.equalsIgnoreCase("BEETROOTS")) {
            key = "BEETROOT";
        }else if (key.equalsIgnoreCase("COCOA")){
            key = "COCOA_BEANS";
        }
        return key;
    }

    public static List<String> LiToStr(List<String> rpstring) {
        List<String> ls = new ArrayList();
        for(String s : rpstring) {
            ls.add(getHexMsg("&e"+ s + "\n"));
        }
        return ls;
    }
}


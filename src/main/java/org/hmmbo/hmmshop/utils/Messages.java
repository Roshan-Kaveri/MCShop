package org.hmmbo.hmmshop.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.hmmbo.hmmshop.HmmShop;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;
import static org.hmmbo.hmmshop.HmmShop.instance;


public class Messages {


 public static String Other_Block_Break;
 public static String Required_Tool;
 public static String InventoryFull ;
 public static String Plugin_TurnedOFF ;
 public static String NotGrownCrops;
 public static String Disabled_Block_Break;
 public static String No_Region_Perms ;
 public static String Disabled_Region;
 public static String Money_Deposit;

  final String str = "%%__USER__%%";


 public static void loadMessages(HmmShop main) {
  File qfile = new File(main.getDataFolder(), "config.yml");
  if (!qfile.exists()) {
   main.saveResource("config.yml", false);
  }
  YamlConfiguration config = YamlConfiguration.loadConfiguration(qfile);

  // Load the language settings
  String lang = config.getString("Messages_Lang");
  ConfigurationSection langSection = config.getConfigurationSection(lang);
  if ( !config.getBoolean("Send_Messages") || langSection == null || langSection.getKeys(false).isEmpty() || "none".equalsIgnoreCase(lang)) {
   // Set all messages to null if the language is "none"
   InventoryFull = null;
   Other_Block_Break = null;
   Disabled_Block_Break = null;
   Required_Tool = null;
   Plugin_TurnedOFF = null;
   NotGrownCrops = null;
   No_Region_Perms = null;
   Money_Deposit = null;
   Disabled_Region = null;
  } else {
   InventoryFull = config.getString(lang + ".InventoryFull");
   Other_Block_Break = config.getString(lang + ".Other_Block_Break");
   Disabled_Block_Break = config.getString(lang + ".Disabled_Block_Break");
   Required_Tool = config.getString(lang + ".Required_Tool");
   Plugin_TurnedOFF = config.getString(lang + ".Plugin_TurnedOFF");
   NotGrownCrops = config.getString(lang + ".NotGrownCrops");
   No_Region_Perms = config.getString(lang + ".No_Region_Perms");
   Money_Deposit = config.getString(lang + ".Money_Deposit");
   Disabled_Region = config.getString(lang + ".Disabled_Region");
  }
 }

 public static  void log(String s){
  instance.getLogger().info(s);
 }
 public static  void warn(String s){
   instance.getLogger().warning(s);
 }


 public static void sendMessage(Player p , String s , Integer _012){
  if(s == null) {
   return;
  } else if (s.equalsIgnoreCase("null")) {
   return;
  }
  ChatColor ch = ChatColor.WHITE;


  String status = "";
//  switch (_012){
//   case 0 :  status =  "§lALERT!&r" ; ch =ChatColor.RED; break;
//   case 1 : status =  "§lWarn!&r"; ch = ChatColor.YELLOW; break;
//   case 2 : ch = ChatColor.GREEN; break;
//  }
 psend(ch +status + s, p);
 }


 public static void psend(String msg, Player p){
  if(msg == null || msg.equalsIgnoreCase("none") || msg.isEmpty()){
   return;
  }
  msg = ConfigManager.Get("prefix") +" &r" + msg.replaceAll("%player%", p.getDisplayName());
  msg = getHexMsg(msg);
 if(!ConfigManager.GetB("Message_Combo")) {
  p.sendMessage(msg);
 }else {
  psends(msg,p);
 }




 }


 static HashMap<Player , HashMap<String ,Integer>> msgMap = new HashMap<>();
 static HashMap<Player, BukkitTask> task = new HashMap<>();
 public static void psends(String msg, Player p) {
  if (msg == null || msg.equalsIgnoreCase("none") || msg.isEmpty()) {
   return;
  }

  final String finalMsg = msg;
  final String hexMsg = getHexMsg(finalMsg);

  synchronized (msgMap) {
   if (!msgMap.containsKey(p)) {
    p.sendMessage(hexMsg);
    HashMap<String, Integer> i = new HashMap<>();
    i.put(hexMsg, 1);
    msgMap.put(p, i);
   } else {
    HashMap<String, Integer> i = msgMap.get(p);
    if (!i.containsKey(hexMsg)) {
     // Send all saved messages first
     for (Map.Entry<String, Integer> entry : i.entrySet()) {
      String savedMsg = entry.getKey();
      int count = entry.getValue()-1;
      if(count != 0) {
       p.sendMessage(savedMsg + " [x" + count + "]");
      }
     }
     // Send the new message
     p.sendMessage(hexMsg);
     // Clear the saved messages and add the new message
     i.clear();
     i.put(hexMsg, 1);
    } else {
     i.put(hexMsg, i.get(hexMsg) + 1);

     if (!task.containsKey(p)) {
      BukkitTask bt = Bukkit.getScheduler().runTaskLaterAsynchronously(HmmShop.instance, () -> {
       synchronized (msgMap) {
        HashMap<String, Integer> innerMap = msgMap.get(p);
        if (innerMap != null) {
         for (Map.Entry<String, Integer> entry : innerMap.entrySet()) {
          String savedMsg = entry.getKey();
          int count = entry.getValue()-1;
          if(count !=0 ) {
           p.sendMessage(savedMsg + " [x" + count + "]");
          }
         }
         msgMap.remove(p);
        }
        task.remove(p);
       }
      }, 5 * 20);
      task.put(p, bt);
     }
    }
   }
  }
 }
 public static String getHexMsg(String message) {
  message = message.replaceAll("&", "§");
  final Pattern hexPattern = Pattern.compile("§#" + "([A-Fa-f0-9]{6})");
  Matcher matcher = hexPattern.matcher(message);
  StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
  while (matcher.find()) {
   String group = matcher.group(1);
   matcher.appendReplacement(buffer, COLOR_CHAR + "x"
           + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
           + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
           + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
   );
  }
  return matcher.appendTail(buffer).toString();
 }




}

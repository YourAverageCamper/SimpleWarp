
package com.gmail.zeusallmighty11.zwarp.Commands;


import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.ZWarp;
import com.gmail.zeusallmighty11.zwarp.Objects.Warp;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class CMD_SetWarp
{
    
    
    
    public static void perform(Player sender, String[] args)
    {
        String a = "§8[§3Warp§8]§r ";
        
        if (!sender.hasPermission("warp.setwarp"))
        {
            sender.sendMessage(a + "§cYou don't have permission to set warps!");
            return;
        }
        
        String type = "";
        for (String s : ZWarp.getInstance().getGroups())
            if (sender.hasPermission("warp.setwarp." + s))
                type = s;
        
        Player p = sender;
        int max = ZWarp.getInstance().getConfig().getInt("warp-limits." + type + "." + p.getWorld().getName());
        
        if (WarpPlayer.getPlayer(p).getWarps().size() >= max)
        {
            sender.sendMessage(a + "§cYou can not set any more warps!");
            return;
        }
        for (Warp ww : WarpPlayer.getPlayer(p).getWarps())
        {
            if (ww.getName().equalsIgnoreCase(args[1]))
            {
                sender.sendMessage(a + "§cWarp already exists!");
                return;
            }
        }
        Warp w = new Warp(p, args[1]);
        WarpPlayer.getPlayer(p).getWarps().add(w);
        p.sendMessage(a + "§aCreated warp: §6" + args[1] + "§a!");
        return;
    }
}

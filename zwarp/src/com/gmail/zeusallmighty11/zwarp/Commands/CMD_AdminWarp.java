
package com.gmail.zeusallmighty11.zwarp.Commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.Objects.Warp;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class CMD_AdminWarp implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender ss, Command arg1, String arg2, String[] args)
    {
        if (!(ss instanceof Player))
            return false;
        
        Player p = (Player) ss;
        
        if (!p.hasPermission("warp.admin"))
        {
            p.sendMessage("§cNope.");
            return false;
        }
        if (args.length < 2)
        {
            return false;
        }
        
        if (args.length >= 2)
        {
            if (args[0].equalsIgnoreCase("list"))
            {
                WarpPlayer wp = WarpPlayer.getPlayer(Bukkit.getServer().getPlayer(args[1]));
                if (wp == null)
                {
                    p.sendMessage("§cInvalid player.");
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < wp.getWarps().size(); i++)
                    sb.append(wp.getWarps().get(i).getName()).append(" ");
                p.sendMessage("§8[§3Warp§8] §eAvailable: §r" + sb.toString());
            }
            else
            {
                WarpPlayer wp = WarpPlayer.getPlayer(Bukkit.getServer().getPlayer(args[0]));
                for (Warp warp : wp.getWarps())
                {
                    if (args[1].equals(warp.getName()))
                    {
                        p.teleport(warp.getLocation());
                        p.sendMessage("§8[§3Warp§8] §aTeleporting. ");
                    }
                }
            }
        }
        
        return false;
    }
}

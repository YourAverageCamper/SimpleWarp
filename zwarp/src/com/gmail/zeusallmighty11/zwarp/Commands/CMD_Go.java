
package com.gmail.zeusallmighty11.zwarp.Commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.Events.WarpAttemptEvent;
import com.gmail.zeusallmighty11.zwarp.Objects.Warp;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class CMD_Go implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args)
    {
        if (s instanceof Player)
        {
            if (args.length >= 1)
            {
                for (Warp w : WarpPlayer.getPlayer((Player) s).getWarps())
                {
                    if (!args[0].equals(w.getName()))
                        continue;
                    Bukkit.getServer().getPluginManager().callEvent(new WarpAttemptEvent(w, (Player) s));
                }
            }
        }
        return false;
    }
    
}

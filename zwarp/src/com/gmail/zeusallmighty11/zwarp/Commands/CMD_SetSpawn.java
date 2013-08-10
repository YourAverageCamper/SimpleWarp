
package com.gmail.zeusallmighty11.zwarp.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.ZWarp;



public class CMD_SetSpawn implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args)
    {
        if (!(s instanceof Player))
            return false;
        if (!s.hasPermission("warp.setspawn"))
        {
            s.sendMessage("§cInvalid perms.");
            return false;
        }
        ZWarp.getInstance().getConfig().set("spawn-location.x", ((Player) s).getLocation().getX());
        ZWarp.getInstance().getConfig().set("spawn-location.y", ((Player) s).getLocation().getY());
        ZWarp.getInstance().getConfig().set("spawn-location.z", ((Player) s).getLocation().getZ());
        ZWarp.getInstance().getConfig().set("spawn-location.pitch", ((Player) s).getLocation().getPitch());
        ZWarp.getInstance().getConfig().set("spawn-location.yaw", ((Player) s).getLocation().getYaw());
        ZWarp.getInstance().getConfig().set("spawn-location.world", ((Player) s).getLocation().getWorld().getName());
        ZWarp.getInstance().saveConfig();
        s.sendMessage("§aSpawn set.");
        return false;
    }
}

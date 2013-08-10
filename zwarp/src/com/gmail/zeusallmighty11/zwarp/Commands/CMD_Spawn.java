
package com.gmail.zeusallmighty11.zwarp.Commands;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.ZWarp;



public class CMD_Spawn implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args)
    {
        if (s instanceof Player)
        {
            double x = ZWarp.getInstance().getConfig().getDouble("spawn-location.x");
            double y = ZWarp.getInstance().getConfig().getDouble("spawn-location.y");
            double z = ZWarp.getInstance().getConfig().getDouble("spawn-location.z");
            float pi = Float.parseFloat(ZWarp.getInstance().getConfig().get("spawn-location.pitch") + "");
            float yaw = Float.parseFloat(ZWarp.getInstance().getConfig().get("spawn-location.yaw") + "");
            String name = ZWarp.getInstance().getConfig().getString("spawn-location.world");
            final Location loc = new Location(Bukkit.getServer().getWorld(name), x, y, z);
            loc.setPitch(pi);
            loc.setYaw(yaw);
            
            
            final Player p = (Player) s;
            boolean wait = false;
            for (Entity ee : p.getNearbyEntities(30.0, 30.0, 30.0))
                if (ee instanceof Player)
                    wait = true;
            
            if (wait)
            {
                p.sendMessage("§8[§3Warp§8] §cPlease wait 10 seconds...");
                ZWarp.getInstance().getWarpers().add(p.getName());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ZWarp.getInstance(), new Runnable()
                {
                    
                    
                    @Override
                    public void run()
                    {
                        if (!ZWarp.getInstance().getWarpers().contains(p.getName()))
                            return;
                        ZWarp.getInstance().getWarpers().remove(p.getName());
                        p.teleport(loc);
                        p.sendMessage("§8[§3Warp§8] §aTeleporting...");
                    }
                }, 20L * 10);
            }
            else
            {
                p.teleport(loc);
                p.sendMessage("§8[§3Warp§8] §aTeleporting...");
            }
        }
        return false;
    }
    
}

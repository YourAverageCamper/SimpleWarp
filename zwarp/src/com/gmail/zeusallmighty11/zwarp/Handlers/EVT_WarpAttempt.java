
package com.gmail.zeusallmighty11.zwarp.Handlers;


import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.zeusallmighty11.zwarp.ZWarp;
import com.gmail.zeusallmighty11.zwarp.Events.WarpAttemptEvent;



public class EVT_WarpAttempt implements Listener
{
    
    
    @EventHandler
    public void onWarp(final WarpAttemptEvent e)
    {
        final Player p = e.getPlayer();
        
        if (ZWarp.getInstance().getWarpers().contains(p.getName()))
        {
            p.sendMessage("§8[§3Warp§8] §cYou are already warping!");
            return;
        }
        
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
                    p.teleport(e.getWarp().getLocation());
                    p.sendMessage("§8[§3Warp§8] §aTeleporting...");
                }
            }, 20L * 10);
        }
        else
        {
            p.teleport(e.getWarp().getLocation());
            p.sendMessage("§8[§3Warp§8] §aTeleporting...");
        }
        
    }
}


package com.gmail.zeusallmighty11.zwarp.Handlers;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.gmail.zeusallmighty11.zwarp.ZWarp;



public class EVT_PME implements Listener
{
    
    
    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        if (e.getFrom().getX() == e.getTo().getX() || e.getFrom().getY() == e.getTo().getY() || e.getFrom().getZ() == e.getTo().getZ())
            return;
        if (!ZWarp.getInstance().getWarpers().contains(e.getPlayer().getName()))
            return;
        
        e.getPlayer().sendMessage("§8[§3Warp§8] §cWarp cancelled due to movement.");
        ZWarp.getInstance().getWarpers().remove(e.getPlayer().getName());
    }
}

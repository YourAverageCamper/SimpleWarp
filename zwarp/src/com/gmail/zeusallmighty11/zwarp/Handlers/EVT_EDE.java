
package com.gmail.zeusallmighty11.zwarp.Handlers;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.gmail.zeusallmighty11.zwarp.ZWarp;



public class EVT_EDE implements Listener
{
    
    
    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if (!(e.getEntity() instanceof Player))
            return;
        Player p = (Player) e.getEntity();
        if (!ZWarp.getInstance().getWarpers().contains(p.getName()))
            return;
        p.sendMessage("§8[§3Warp§8] §cWarp cancelled due to damage");
        ZWarp.getInstance().getWarpers().remove(p.getName());
    }
}


package com.gmail.zeusallmighty11.zwarp.Handlers;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.zeusallmighty11.zwarp.ZWarp;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class EVT_PJE implements Listener
{
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if (WarpPlayer.getPlayer(p) == null)
        {
            WarpPlayer wp = new WarpPlayer(p.getName());
            wp.save();
            ZWarp.getInstance().getPlayers().put(p.getName(), wp);
        }
    }
}

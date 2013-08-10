
package com.gmail.zeusallmighty11.zwarp.Events;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.gmail.zeusallmighty11.zwarp.Objects.Warp;



public class WarpDeleteEvent extends Event
{
    
    
    private static final HandlerList handlers = new HandlerList();
    Warp warp;
    Player player;
    
    
    
    public WarpDeleteEvent(Warp warp, Player player)
    {
        this.warp = warp;
        this.player = player;
    }
    
    
    
    public static HandlerList getHandlerList()
    {
        return handlers;
    }
    
    
    
    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
    
    
    
    public Warp getWarp()
    {
        return warp;
    }
    
    
    
    public Player getPlayer()
    {
        return player;
    }
    
}

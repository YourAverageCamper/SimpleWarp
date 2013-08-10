
package com.gmail.zeusallmighty11.zwarp.Objects;


import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;



public class Warp implements Serializable
{
    
    
    private static final long serialVersionUID = 1L;
    String owner;
    String name;
    
    double x;
    double y;
    double z;
    float pitch;
    float yaw;
    String world;
    
    
    
    /*
     * Warp constructor 
     */
    public Warp(Player p, String name)
    {
        this.owner = p.getName();
        this.name = name;
        this.x = p.getLocation().getX();
        this.y = p.getLocation().getY();
        this.z = p.getLocation().getZ();
        this.pitch = p.getLocation().getPitch();
        this.yaw = p.getLocation().getYaw();
        this.world = p.getLocation().getWorld().getName();
    }
    
    
    
    /*
     * Grab owner as Player
     */
    public Player getOwner()
    {
        return Bukkit.getServer().getPlayer(owner);
    }
    
    
    
    /*
     * Returns name of warp
     */
    public String getName()
    {
        return name;
    }
    
    
    
    /*
     * Returns location
     */
    public Location getLocation()
    {
        Location loc = new Location(Bukkit.getServer().getWorld(world), x, y, z);
        loc.setPitch(pitch);
        loc.setYaw(yaw);
        return loc;
    }
    
}


package com.gmail.zeusallmighty11.zwarp.Objects;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.ZWarp;



public class WarpPlayer implements Serializable
{
    
    
    private static final long serialVersionUID = 1L;
    List<Warp> warps;
    String name;
    
    
    
    /*
     * Constructor
     */
    public WarpPlayer(String name)
    {
        this.name = name;
        this.warps = new ArrayList<Warp>();
    }
    
    
    
    /*
     * Save data to flat file
     */
    public void save()
    {
        try
        {
            File f = new File(ZWarp.getInstance().getDataFolder() + "/data/" + name);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(this);
            oos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    
    
    /*
     * Grab WarpPlayer object
     */
    public static WarpPlayer getPlayer(Player p)
    {
        return ZWarp.getInstance().getPlayers().get(p.getName());
    }
    
    
    
    /*
     * Grab list of warps
     */
    public List<Warp> getWarps()
    {
        return warps;
    }
    
    
    
    /*
     * Grab names
     */
    public String getName()
    {
        return name;
    }
    
}

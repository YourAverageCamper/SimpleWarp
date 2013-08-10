
package com.gmail.zeusallmighty11.zwarp;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.zeusallmighty11.zwarp.Commands.CMD_AdminWarp;
import com.gmail.zeusallmighty11.zwarp.Commands.CMD_Go;
import com.gmail.zeusallmighty11.zwarp.Commands.CMD_SetSpawn;
import com.gmail.zeusallmighty11.zwarp.Commands.CMD_Spawn;
import com.gmail.zeusallmighty11.zwarp.Commands.CMD_Warp;
import com.gmail.zeusallmighty11.zwarp.Handlers.EVT_EDE;
import com.gmail.zeusallmighty11.zwarp.Handlers.EVT_PJE;
import com.gmail.zeusallmighty11.zwarp.Handlers.EVT_PME;
import com.gmail.zeusallmighty11.zwarp.Handlers.EVT_WarpAttempt;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class ZWarp extends JavaPlugin
{
    
    
    /* = = = = = = = = = = = = = = = = = = = = = = */
    
    // instances
    private static ZWarp instance;
    // Files + Directories
    File rootDir;
    File dataDir;
    // lists/maps/sets
    Map<String, WarpPlayer> players;
    Set<String> groups;
    Set<String> warpers;
    
    
    
    /* = = = = = = = = = = = = = = = = = = = = = = */
    
    @Override
    public void onEnable()
    {
        // declare static instance
        instance = this;
        
        // initiate players
        players = new HashMap<String, WarpPlayer>();
        warpers = new HashSet<String>();
        
        // create directories
        rootDir = new File(getDataFolder() + "");
        if (!rootDir.exists())
            rootDir.mkdir();
        dataDir = new File(getDataFolder() + "/data/");
        if (!dataDir.exists())
            dataDir.mkdir();
        
        // config
        File config = new File(getDataFolder() + "/config.yml");
        if (!config.exists())
            saveDefaultConfig();
        
        // initiate groups
        groups = getConfig().getConfigurationSection("warp-limits").getKeys(false);
        
        // register events
        getServer().getPluginManager().registerEvents(new EVT_EDE(), this);
        getServer().getPluginManager().registerEvents(new EVT_PME(), this);
        getServer().getPluginManager().registerEvents(new EVT_WarpAttempt(), this);
        getServer().getPluginManager().registerEvents(new EVT_PJE(), this);
        
        // register commands
        getCommand("warp").setExecutor(new CMD_Warp());
        getCommand("spawn").setExecutor(new CMD_Spawn());
        getCommand("setspawn").setExecutor(new CMD_SetSpawn());
        getCommand("go").setExecutor(new CMD_Go());
        getCommand("awarp").setExecutor(new CMD_AdminWarp());
        
        // load all players
        for (File f : dataDir.listFiles())
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
                WarpPlayer p = (WarpPlayer) ois.readObject();
                ois.close();
                players.put(p.getName(), p);
            }
            catch (IOException | ClassNotFoundException ioe)
            {
                ioe.printStackTrace();
            }
    }
    
    
    
    @Override
    public void onDisable()
    {
        for (WarpPlayer p : players.values())
            p.save();
        // null instance to prevent memory leaks
        instance = null;
    }
    
    
    
    /* = = = = = = = = = = = = = = = = = = = = = = */
    
    /*
     * Grab main plugin instance
     */
    public static ZWarp getInstance()
    {
        return instance;
    }
    
    
    
    /*
     * Grab players map
     */
    public Map<String, WarpPlayer> getPlayers()
    {
        return players;
    }
    
    
    
    /*
     * Grabs all groups
     */
    public Set<String> getGroups()
    {
        return groups;
    }
    
    
    
    public Set<String> getWarpers()
    {
        return warpers;
    }
    
    /* = = = = = = = = = = = = = = = = = = = = = = */
    
}

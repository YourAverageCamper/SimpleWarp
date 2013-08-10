
package com.gmail.zeusallmighty11.zwarp.Commands;


import java.util.ConcurrentModificationException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.zeusallmighty11.zwarp.Objects.Warp;
import com.gmail.zeusallmighty11.zwarp.Objects.WarpPlayer;



public class CMD_Warp implements CommandExecutor
{
    
    
    @Override
    public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args)
    {
        if (!(s instanceof Player))
            return false;
        
        if (args.length < 1)
        {
            s.sendMessage("§8[§3Warp§8] §cInvalid command! ");
            s.sendMessage("§8[§3Warp§8] §3/warp §eset [name] §d- sets a warp");
            s.sendMessage("§8[§3Warp§8] §3/warp §edelete [name] §d- deletes a warp");
            s.sendMessage("§8[§3Warp§8] §3/warp §elist §d- list warps");
            s.sendMessage("§8[§3Warp§8] §3/go §e [name] §d- teleports you to warp");
            return false;
        }
        
        if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("list"))
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < WarpPlayer.getPlayer((Player) s).getWarps().size(); i++)
                    sb.append(WarpPlayer.getPlayer((Player) s).getWarps().get(i).getName()).append(" ");
                s.sendMessage("§8[§3Warp§8] §eAvailable: §r" + sb.toString());
                return false;
            }
        }
        else if (args.length == 2)
            if (args[0].equalsIgnoreCase("set"))
                CMD_SetWarp.perform((Player) s, args);
            else if (args[0].equalsIgnoreCase("delete"))
                try
                {
                    for (Warp w : WarpPlayer.getPlayer((Player) s).getWarps())
                    {
                        if (!args[1].equals(w.getName()))
                            continue;
                        WarpPlayer.getPlayer((Player) s).getWarps().remove(w);
                        s.sendMessage("§8[§3Warp§8] §aWarp deleted!");
                    }
                }
                catch (ConcurrentModificationException cme)
                {
                    
                }
        return false;
    }
}

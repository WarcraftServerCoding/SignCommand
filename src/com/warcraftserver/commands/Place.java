
package com.warcraftserver.commands;

import java.util.ArrayList;
import org.bukkit.entity.Player;

import com.warcraftserver.SignCommand;

public class Place {
    
    ArrayList<Player> placer = new ArrayList<Player>();
    SignCommand plugin;
    
    public Place(){
        this.plugin = SignCommand.getInstance();
    }
    
    public void command(Player player){
        if(!placer.contains(player)){
            placer.add(player);
            player.sendMessage("§a[§bWarCraft§a] §fI pity the fool who doesn't place his sign now.");
        }
        else{
            player.sendMessage("§a[§bWarCraft§a] §fSilly rabbit, you're already in sign placing mode. §a:3");
        }
    }
    
    public void removePlacer(Player player){
        if(placer.contains(player)){placer.remove(player);
        }
    }
    
    public boolean checkPlayer(Player player){
        if(placer.contains(player)){
            return true;
        }
        else{
            return false;
        }
    }



}

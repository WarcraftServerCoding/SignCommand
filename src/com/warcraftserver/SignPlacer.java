package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import com.warcraftserver.commands.CommandListener;
import java.util.logging.Logger;

public class SignPlacer implements Listener{
    public static Logger log = Logger.getLogger("MineCraft");
    SignCommand plugin;

    public SignPlacer(){
        this.plugin = SignCommand.getInstance();
    }
    
    @EventHandler()
    public void onPlaceChange(SignChangeEvent event){
        Player player = event.getPlayer();
            String[] lines = event.getLines();
            if(this.plugin.getManager().isSCSign(lines[0])){
            
                if(this.plugin.permCheck(player, "SignCommand.place")){
                    this.plugin.getManager().getPlace().removePlacer(player);
                    player.sendMessage("§a[§bWarCraft§a] §fSign Created.");
                    return;
                }
                if(!this.plugin.permCheck(player, "SignCommand.place")){
                    player.sendMessage("§a[§bWarCraft§a] §fYou do not have permission to place this sign.");
                    event.setCancelled(true);
                    return;
                }
                player.sendMessage("Not a Correct Sign.");
            }
            
        
        
    }   
    
    
}
    
    

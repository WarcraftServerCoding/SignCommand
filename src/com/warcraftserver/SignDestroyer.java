
package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class SignDestroyer implements Listener{
    
    SignCommand plugin;
//    private static Manager manager;
//    SignBase signbase = new SignBase(plugin.getInstance());
    
    public SignDestroyer(SignCommand plugin){
        this.plugin = plugin;
//        manager = this.plugin.getInstance().getManager();
    }
    
    
    @EventHandler()
    public void onBlockBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        boolean isSign = false;
        Sign sign = null;
        if(block.getState() instanceof Sign){
                BlockState blocker = block.getState();
                sign = (Sign)blocker;
                isSign = true;
            }
        if(isSign){
            
            Player player = event.getPlayer();
            String[] lines = sign.getLines();
            if(parseName(lines[0]) != null && this.plugin.getManager().isSCSign(parseName(lines[0]))){
            
                if(plugin.permCheck(player, "SignCommand.place")){
                    this.plugin.getManager().getPlace().removePlacer(player);
                    player.sendMessage("§a[§bWarCraft§a] §fSign Created.");
                    return;
                }
                if(!plugin.permCheck(player, "SignCommand.place")){
                    player.sendMessage("§a[§bWarCraft§a] §fYou do not have permission to place this sign.");
                    event.setCancelled(true);
                    return;
                }
        }
        
        
        
    }
    
    
    
}
    
    public String parseName(String line){
        if(line.contains("[") && line.contains("]")){
            line.replace("[", "");
            line.replace("]", "");
            return line;
        }
        return null;
    }
    
}

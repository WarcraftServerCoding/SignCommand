package com.warcraftserver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.warcraftserver.Manager;
import com.warcraftserver.SignCommand;

public class refresh {
    
    SignCommand plugin;
    Manager manager;
    
    public refresh(){
        plugin = SignCommand.getInstance();
        
    }

    public void command(Player player) {
//       PERFORMS THE RELOAD METHOD IN THE MANAGER TO RELOAD SIGNDATABASE.TXT CONFIGURATIONS
            if(plugin.permCheck(player, "SignCommand.place")){
                this.plugin.getManager().reloadComSign();
                player.sendMessage("§a[§bWarCraft§a] §fSignCommand Configs Reloaded!");
            
        }
    }

    
}

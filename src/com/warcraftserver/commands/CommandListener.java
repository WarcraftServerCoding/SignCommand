package com.warcraftserver.commands;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import java.io.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.warcraftserver.SignCommand;
import com.warcraftserver.Manager;


public class CommandListener implements ExecutorInterface{
    
    public static Logger log = Logger.getLogger("Minecraft");
    SignCommand plugin;
//    Manager manager = SignCommand.getInstance().getManager();
    
    public CommandListener(){
        this.plugin = SignCommand.getInstance();
    }
    
    
//    
//    COMMAND LISTENER
//    

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandString, String[] cmd) {
//          CONVERTS COMMANDSENDER TO AN OBJECT OF THE PLAYER CLASS
            Player player = null;
            if(sender instanceof Player){
                player = (Player)sender;
            }
            if(!plugin.permCheck(player, "SignCommand.place")){
                sender.sendMessage("§a[§bWarCraft§a] §fYou don't have permission for that.");
                return true;
            }
            if(plugin.permCheck(player, "SignCommand.place") && cmd.length > 0){
//          CHECKS TO MAKE SURE ARGUMENTS WERE ADDED. IF NO ARGUMENTS FOUND, IT TELLS YOU THE SYNTAX OF THE COMMAND
            if(cmd.length == 1){
                if(cmd[0].equalsIgnoreCase("add")){
                    sender.sendMessage("§a[§bWarCraft§a] §fCorrect Usage is: /sc add [string]");
                    return true;
                }
                if(cmd[0].equalsIgnoreCase("command")){
                    sender.sendMessage("§a[§bWarCraft§a] §fCorrect Usage is: /sc command <arg> <arg> <arg> etc.");
                    return true;
                }
                if(cmd[0].equalsIgnoreCase("place")){
                    this.plugin.getManager().getPlace().command(player);
                }
//          CANCELS THE CURRENT COMMAND SAVE FUNCTION                
                if(cmd[0].equalsIgnoreCase("cancel")){
                    this.plugin.getManager().getAddCommand().cancel(sender);
                }
            }


//                
//        GETS THE STRING TO REFER TO TO GET THE COMMAND        
//                
                if(cmd[0].equalsIgnoreCase("add") || cmd[0].equalsIgnoreCase("command")){
                    this.plugin.getManager().getAddCommand().command(sender, player, cmd);
                    
                
                }
                
//            }
        }
            invalidCommand(sender, commandString);
        return false;
    }

    @Override
    public void invalidCommand(CommandSender sender, String paramString) {
        sender.sendMessage("§a[§bWarCraft§a] §fCorrect Usages are:");
        sender.sendMessage("§a    - §f/sc add [string]");
        sender.sendMessage("§a    - §f/sc command [arg] [arg] [arg]");
        sender.sendMessage("§a    - §f/sc cancel");
    }
    
}

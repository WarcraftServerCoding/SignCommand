
package com.warcraftserver.commands;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.warcraftserver.SignCommand;

public class AddCommand {
    HashMap<CommandSender,String> list = new HashMap<CommandSender,String>();
    public static Logger log = Logger.getLogger("Minecraft");
    SignCommand plugin;
    
    public AddCommand(SignCommand plugin){
        this.plugin = plugin;
    }
    
    
    public void command(CommandSender sender, Player player, String[] cmd){
 //                
//        GETS THE STRING TO REFER TO TO GET THE COMMAND        
//                
        if(cmd[0].equalsIgnoreCase("add")){
//                  CHECKS IF THE PLAYER IS ALREADY IN THE PROCESS OF ADDING A COMMAND
            if(list.containsKey(player)){
                sender.sendMessage("§a[§bWarCraft§a] §fYou are already in the process of adding a command.");
                sender.sendMessage("§a    - §fType '/sc cancel' to cancel the action.");
                return;
            }
//                  REBUILDS THE STRING INT STRING FORM AND STORES IN HASHMAP TO REFER TO LATER
            StringBuilder sb = new StringBuilder();
            for(int i = 1 ; i < cmd.length ; i++){
                if(cmd.length-1 == i){
                    sb.append(cmd[i]);}
                else{
                    sb.append(cmd[i]).append(" ");}
            }
            list.put(sender,sb.toString()
                    );
            sender.sendMessage("§a[§bWarCraft§a] §fNow enter the command: /sc command <arg> <arg> <arg> etc.");
            return;
        }
//                
//       GETS THE COMMAND TO DEFER TO
//                
        if (cmd[0].equalsIgnoreCase("command")){
//                  CHECKS TO MAKE SURE THE PLAYER HAS ALREADY STARTED ADDING A COMMAND
            if(!list.containsKey(player)){
                sender.sendMessage("§a[§bWarCraft§a] §fYou have not properly started the process of adding a command.");
                sender.sendMessage("§a    - §fType '/sc add [string]' to add a String to reference a command.");
                return;
            }
//                  REBUILDS THE COMMAND TO STRING FORM FOR WRITING TO THE FILE
            StringBuilder sb = new StringBuilder();
            for(int i = 1 ; i < cmd.length ; i++){
                if(cmd.length-1 == i){
                    sb.append(cmd[i]);}
                else{
                    sb.append(cmd[i]).append(" ");}
            }
            Write(list.get(sender),sb.toString(),player);// SENDS ARGUMENTS TO WRITE METHOD FOR WRITING TO FILE
            list.remove(sender);
            return;


        }
    }
    
    public void cancel(CommandSender sender){
//        REMOVES THE PLAYER FROM ALL LISTS TO CANCEL COMMAND/ALIAS SAVING
        if(list.containsKey(sender)){
            list.remove(sender);
            sender.sendMessage("§a[§bWarCraft§a] §fCommand Cancelled.");
            return ;}
        if(!list.containsKey(sender)){
            sender.sendMessage("§a[§bWarCraft§a] §fNo Command to cancel.");
            return ;}
    }
    
//    
//    WRITES THE STRING AND COMMAND TO COMMAND.TXT
//    STRING AND COMMAND ARE SEPARATED BY ":"
//     
     
    public void Write(String string, String command, Player player){
//        CREATE OUR FILE
        File file = new File("plugins/SignCommand/Commands.txt");
        try{
//            THIS PART CREATES A READER AND ADDS EACH LINE TO A STRINGBUILDER
//            SO THAT WE CAN REWRITE IT TO THE FILE. IF THIS ISN'T DONE AND YOU 
//            JUST WRITE THE NEW LINE IT WILL WRITE OVER THE WHOLE FILE AND LOSE
//            ALL THE OTHER INFORMATIONS STORED
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder toWrite = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                toWrite.append(line).append("\r\n");
                line = reader.readLine();
            }
            reader.close();
            toWrite.append(string).append(":").append(command);
//            CREATES A FILEWRITER AND WRITES TO FILE
            FileWriter writer = new FileWriter(file);
            writer.write(toWrite.toString());
            writer.close();
            player.sendMessage("§a[§bWarCraft§a] §fCommand data sucessfully stored.");
        }
        catch(IOException ex){
            log.severe("[SignCommand] Error writing to Commands.txt:  " + ex.toString());
        }
    }
    
    
}

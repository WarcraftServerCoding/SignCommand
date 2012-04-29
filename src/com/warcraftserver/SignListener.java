package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import com.warcraftserver.ComSign;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.block.Action;
import org.bukkit.block.BlockState;

public class SignListener implements Listener {
    public static Logger log;
    SignCommand plugin;

    
    public SignListener(){
//        GETS THE CURRENT INSTANCE OF SIGNCOMMAND THIS IS NECESSARY SO THAT WE CAN ACCESS CURRENT VARIABLES FROM OTHER CLASSES
        this.plugin = SignCommand.getInstance();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRightClicked(PlayerInteractEvent event){
        
        Block block = event.getClickedBlock();
        boolean isSign = false;
        Sign sign = null;
        Player player = event.getPlayer();
//        CHECKS IF THE BLOCK CLICKED IS A SIGN, SO WE DON'T GET ERRORS TRY/CATCH STATEMENTS CATCHES ANY NULLPOINTEREXCEPTIONS
        try{
            if(block.getState() instanceof Sign){
                    BlockState blocker = block.getState();
                    sign = (Sign)blocker;
                    isSign = true;
            }
        }
        catch(NullPointerException ex){}
        
        if(isSign){
            String[] lines = sign.getLines();
//            CREATES AN OBJECT OF THE ComSign CLASS, RETURNS NULL IF IT ISN'T A SIGNCOMMAND SIGN
            ComSign comsign = this.plugin.getManager().getComSign(sign);
//            CHECKS THAT THIS IS INDEED A SIGNCOMMAND SIGN SO WE DON'T GET ERRORS
            if(comsign != null){
//              EXECUTES THE CURRENT COMMAND ON THE SIGN
                if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    player.performCommand(Reader(Parser(lines[2])));
                }


//              CHANGES THE COMMAND ON THE SIGN TO THE NEXT IN LIST VIA THE MANAGER
                if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    String newline = comsign.nextCommand(lines[0], lines[2]);
                    sign.setLine(2, newline);
//                    IF YOU DON'T UPDATE THE SIGN IT WONT WORK
                    sign.update();
                    player.sendMessage("§a[§bWarCraft§a] §fYou have selected command§a:  §b" + Reader(Parser(lines[2])));
                    player.sendMessage("§a    - §fRight-Click this sign to execute.");
                    return;
                }
            }
        }
        
    }

     
        
            
    
//    
//    METHOD TO SEE IF THE STRING IS IN COMMANDS.TXT IF NOT IT RETURNS THE COMMAND SENT
//    
    public String Reader(String string){
        String command = string;
//        CREATS A FILE
        File file = new File("plugins/SignCommand/Commands.txt");
        try{
//            CREATES A FILE READER AND READS THE FIRST LINE
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
//                GETS THE ALIAS AND SPLITS IT TO RETURN THE COMMAND IT REFERS TO
                String[] split = line.split(":");
                if(split[0].equalsIgnoreCase(string)){
                    command = split[1];
                }
//                READS NEXT LINE AND CONTINUES IN THE LOOP
                line = reader.readLine();
            }
//            CLOSES THE FILEREADER, THIS IS VERY VERY VERY IMPORTANT, TRUST ME
            reader.close();
        }
        catch(IOException ex){
            log.severe("[SignCommand] Error reading from Commands.txt:  " + ex.toString());
        }
//        RETURNS COMMAND
        return command;
    }
    
//    PARSER FOR PARSING COMMANDS INSIDE OF < > ON A SIGN
    public String Parser(String line){
        if(line.contains("<") && line.contains(">")){
            String[] line1 = line.split("<");
            if (line1.length >= 1){
                String[] line2 = line1[1].split(">");
                if(line2.length >= 1){
                    return line2[0];
                }
        }
        }
        return line;
    }
}

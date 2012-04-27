package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import com.warcraftserver.ComSign;
import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Logger;
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
    HashMap<Player, String> ploc = new HashMap<Player, String>();
    HashMap<Player, Integer> curline = new HashMap<Player, Integer>();
    
    public SignListener(){
        this.plugin = SignCommand.getInstance();
    }
    
    @EventHandler()
    public void onRightClicked(PlayerInteractEvent event){
        
        Block block = event.getClickedBlock();
        boolean isSign = false;
        Sign sign = null;
        Player player = event.getPlayer();
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
            ComSign comsign = this.plugin.getManager().getComSign(sign);
            if(comsign != null){

                if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                    player.performCommand(Reader(Parser(lines[2])));
                }



                if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                    String newline = comsign.nextCommand(lines[0], lines[2]);
                    sign.setLine(2, newline);
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
        File file = new File("plugins/SignCommand/Commands.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
                String[] split = line.split(":");
                if(split[0].equalsIgnoreCase(string)){
                    command = split[1];
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException ex){
            log.severe("[SignCommand] Error reading from Commands.txt:  " + ex.toString());
        }
        return command;
    }
    
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

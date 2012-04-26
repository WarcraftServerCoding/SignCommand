package com.warcraftserver;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ComSign {
    Sign sign;
    String[] lines;
    String name;
    SignCommand plugin;
//    private static Manager manager = SignCommand.getInstance().getManager();
    
    
    public ComSign(Sign sign){
        this.sign = sign;
        this.lines = sign.getLines();
        this.name = lines[0];
        this.plugin = SignCommand.getInstance();
    }
    
    public String nextCommand(String key, String value){
        return this.plugin.getManager().getNextLine(key, value);
    }
    
    public void executeCommand(Player player){
        
    }
    
    public boolean isSCSign(String line){
        String newline = parseName(line);
        if(newline != null && this.plugin.getManager().isSCSign(newline)){ return true;}
        return false;
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

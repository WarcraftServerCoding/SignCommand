
package com.warcraftserver;

import com.warcraftserver.commands.*;
import com.warcraftserver.SignFile.Loader;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.block.Sign;

public final class Manager {
    private static SignCommand plugin;
    private static ComSign comsign;
     public static Logger log = Logger.getLogger("MineCraft");
    private Loader loader = new Loader();
    HashMap<String, String> signs = new HashMap<String, String>();
    
    public Manager (SignCommand plugin){
//        this.plugin = SignCommand.getInstance();
        this.plugin = plugin;
    }
    
    public void addComSign(String key, String value){
        signs.put(key, value);
    }
    
    public void removeComSign(String key){
        if(signs.containsKey(key)){
            signs.remove(key);
        }
    }
    
    public void reloadComSign(){
        signs.clear();
        loader.loadSignBase();
    }
    
    public ComSign getComSign(Sign sign){
        ComSign cmsign = new ComSign(sign);
        String[] lines = sign.getLines();
        if(cmsign.isSCSign(lines[0])){return cmsign;}
        return null;
    }
    
    public Place getPlace(){
        return SignCommand.getInstance().getPlace();
    }
    
    public AddCommand getAddCommand(){
        return SignCommand.getInstance().getAddCommand();
    }
    
    public String getNextLine(String key, String value){
        String[] commands = signs.get(key).split(",");
        int loc = 0;
        for(int i = 0; i < commands.length; i++){
            if(commands[i].equalsIgnoreCase(value)){
                if(i+1 < commands.length){
                    loc = i + 1;}
            }
        }
        return commands[loc];
    }
    
    public boolean isSCSign(String name){
        if(signs.containsKey(name)){
            return true;
        }
        return false;
    }
    
}

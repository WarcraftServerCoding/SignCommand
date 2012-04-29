
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
//    THIS CREATES A HASHMAP THAT STORES THE NAME OF  THE SIGN AND THE 
//    ALIASES OR COMMANDS THAT GO ON THEM
    HashMap<String, String> signs = new HashMap<String, String>();
    
    public Manager (SignCommand plugin){
        this.plugin = plugin;
    }
//    ADDS A COMSIGN TO THE HASHMAP
    public void addComSign(String key, String value){
        signs.put(key, value);
    }
//    REMOVES A COMSIGN FROM THE HASHMAP, THOUGH I DON'T THINK I EVER USE THIS METHOD
    public void removeComSign(String key){
        if(signs.containsKey(key)){
            signs.remove(key);
        }
    }
//    CLEARS THE LOADED SIGN COMMAND SIGNS AND THEN RELOADS THE FROM THE SIGNDATABASE.TXT
    public void reloadComSign(){
        signs.clear();
        loader.loadSignBase();
    }
//    CREATES A NEW COMSIGN IF ITS A SIGN COMMAND SIGN IF NOT IT RETURNS NULL
    public ComSign getComSign(Sign sign){
        ComSign cmsign = new ComSign(sign);
        String[] lines = sign.getLines();
        if(cmsign.isSCSign(lines[0])){return cmsign;}
        return null;
    }
//    GETS THE PLACE CLASS
    public Place getPlace(){
        return SignCommand.getInstance().getPlace();
    }
//    GETS THE REFRESH CLASS
    public refresh getRefresh(){
        return SignCommand.getInstance().getRefresh();
    }
//    GETS THE ADDCOMMAND CLASS
    public AddCommand getAddCommand(){
        return SignCommand.getInstance().getAddCommand();
    }
//    RETURNS THE NEXT COMMAND TO GO ON A COMMAND SIGN WHEN LEFT CLICKED
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
//    CHECKS TO SEE IF THE SIGN IS A SIGN COMMAND SIGN
//    IF IT IS THEN IT RETURNS TRUE, IF NOT IT RETURNS FALSE
    public boolean isSCSign(String name){
        if(signs.containsKey(name)){
            return true;
        }
        return false;
    }
    
}

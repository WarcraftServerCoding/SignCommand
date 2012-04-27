
package com.warcraftserver.SignFile;

import com.warcraftserver.SignCommand;
import java.io.*;
import java.util.logging.Logger;

public class SignBase {
    
    Loader loader;
    File dir = new File("plugins/SignCommand");
    File file = new File("plugins/SignCommand/SignDataBase.txt");
    Logger log = Logger.getLogger("Minecraft");
    SignCommand plugin;
    
    public SignBase(){
        this.plugin = SignCommand.getInstance();
        loader = new Loader();
    }
    
    
    public void initialize(){
        try{
            if(!file.exists()){
                dir.mkdirs();
                file.createNewFile();
            }
            
        }
        catch(IOException ex){log.severe("[SignCommand]  Error Creating SignDataBase.txt");}
        loader.loadSignBase();
    }
    
    

}

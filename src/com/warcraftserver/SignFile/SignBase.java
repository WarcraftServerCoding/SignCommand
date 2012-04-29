
package com.warcraftserver.SignFile;

import com.warcraftserver.SignCommand;
import java.io.*;
import java.util.logging.Logger;

public class SignBase {
    
    Loader loader;
//    CREATES OUR FILE AND DIRECTORY
    File dir = new File("plugins/SignCommand");
    File file = new File("plugins/SignCommand/SignDataBase.txt");
    Logger log = Logger.getLogger("Minecraft");
    SignCommand plugin;
    
    public SignBase(){
        this.plugin = SignCommand.getInstance();
        loader = new Loader();
    }
    
    
    public void initialize(){
//        CREATES THE FILE AND DIRECTORY IF THEY DON'T EXIST
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

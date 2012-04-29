
package com.warcraftserver.SignFile;

import java.io.*;
import java.util.logging.Logger;
import com.warcraftserver.SignCommand;
import com.warcraftserver.Manager;

public class Loader {
    
    File dir = new File("plugins/SignCommand");
    File file = new File("plugins/SignCommand/SignDataBase.txt");
    Logger log = Logger.getLogger("Minecraft");
    SignCommand plugin;
    
    public Loader(){
        this.plugin = SignCommand.getInstance();
        
    }
    
    public void loadSignBase(){
//        READING THE FILE AND ADDING IT TO THE HASHMAP IN THE MANAGER FOR ACCESS LATER
        try{
//            CREATE OUR FILEREADER
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while(line != null){
                String[] split = line.split(":");
//                METHOD THAT ACTUALLY PUTS THE INFORMATION IN THE HASHMAP
                this.plugin.getManager().addComSign(split[0], split[1]);
//                READS NEXT LINE AND CONTINUES LOOP
                line = reader.readLine();
            }
            reader.close();
        }
        catch(IOException ex){log.severe("[SignCommand]  Error adding sign to SignDataBase.txt");}
    }
    
    
    
}

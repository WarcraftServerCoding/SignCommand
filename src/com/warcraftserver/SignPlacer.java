
package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import com.warcraftserver.commands.CommandListener;
import java.util.logging.Logger;

public class SignPlacer implements Listener{
    public static Logger log = Logger.getLogger("MineCraft");
    SignCommand plugin;
//    private static Manager manager = SignCommand.getInstance().getManager();
//    SignBase signbase = new SignBase(plugin.getInstance());
//    CommandListener cl = new CommandListener(SignCommand.getInstance());
    
    public SignPlacer(){
        this.plugin = SignCommand.getInstance();
    }
    
    @EventHandler()
    public void onPlaceChange(SignChangeEvent event){
//        Block block = event.getBlock();
//        Sign sign = event.
//        boolean isSign = false;
//        Sign sign = null;
//        if(block.getState() instanceof Sign){
//                BlockState blocker = block.getState();
//                sign = (Sign)blocker;
//                isSign = true;
//            }
        Player player = event.getPlayer();
//        if(isSign){
//            String[] lines = sign.getLines();
            String[] lines = event.getLines();
//            String name = parseName(lines[0]);
//            String name = lines[0];
            if(this.plugin.getManager().isSCSign(lines[0])){
            
                if(this.plugin.permCheck(player, "SignCommand.place")){
                    this.plugin.getManager().getPlace().removePlacer(player);
                    player.sendMessage("§a[§bWarCraft§a] §fSign Created.");
                    return;
                }
                if(!this.plugin.permCheck(player, "SignCommand.place")){
                    player.sendMessage("§a[§bWarCraft§a] §fYou do not have permission to place this sign.");
                    event.setCancelled(true);
                    return;
                }
                player.sendMessage("Not a Correct Sign.");
            }
            
            
//        }
        
        
    }
    
    public String parseName(String line){
        if(line.contains((CharSequence)"[") && line.contains((CharSequence)"]")){
            String newline = line;
            newline.replace((CharSequence)"[", (CharSequence)"");
            newline.replace((CharSequence)"]", (CharSequence)""); 
            return newline;
        }
        return line;
    }
    
//      public String parseName(String line) {
//    if ((line.contains((String)"[")) && (line.contains((String)"]"))) {
//      String[] line1 = line.split((String)"[");
//      if (line1.length >= 1) {
//        String[] line2 = line1[1].split((String)"]");
//        if (line2.length >= 1) {
//          return line2[0];
//        }
//      }
//    }
//    return line;
//  }

    
    
    
}
    
    

package com.warcraftserver;

import com.warcraftserver.SignFile.SignBase;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import net.milkbowl.vault.permission.Permission;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import java.io.File;
import java.io.IOException;
import org.bukkit.command.PluginCommand;
import com.warcraftserver.commands.*;


public class SignCommand extends JavaPlugin{
        public static Logger log;
        private static SignCommand instance;
        private Listener signlistener;
        private Listener signplacer;
        private Listener signdestroyer;
        private SignBase signbase;
        private  CommandListener cListener;
        public  Manager signmanager;
        private  Place place;
        private  AddCommand addcommand;
        File file = new File("plugins/SignCommand/Commands.txt");
        File dir = new File("plugins/SignCommand/");
        
        public void onEnable(){ 
            instance = this;
            cListener = new CommandListener();
            signdestroyer = new SignDestroyer(this);
            signbase = new SignBase();
            signplacer = new SignPlacer();
            signlistener = new SignListener();
            signmanager = new Manager(this);
            place = new Place();
            addcommand = new AddCommand(this);
            log = this.getLogger();
            log.info(String.format("[%s] Enabled Version %s", getDescription().getName(), getDescription().getVersion()));
            Bukkit.getServer().getPluginManager().registerEvents(signlistener, this);
            Bukkit.getServer().getPluginManager().registerEvents(signplacer, this);
            Bukkit.getServer().getPluginManager().registerEvents(signdestroyer, this);
//          REGISTER COMMANDS
            getCommand("Sign-Command").setExecutor(cListener);
            signbase.initialize();
//          CHECKS IF COMMANDS.TXT EXITS; IF IT DOESN'T, ITS CREATES ONE
            if(!file.exists()){
                try{
                    dir.mkdirs();
                    file.createNewFile();
                }
                catch(IOException ex){
                    log.severe("[SignCommand] Error creating Commands.txt:  " + ex.toString());
                }
            }
        }
     
        public void onDisable(){ 
            log.info("SignCommand has been disabled");
        }
        
        public static SignCommand getInstance(){
            return instance;
        }
        
        public Manager getManager(){
            return signmanager;
        }
        
        public  CommandListener getCL(){
            return cListener;
        }
        
        public Place getPlace(){
            return place;
        }
        
        public  AddCommand getAddCommand(){
            return addcommand;
        }
        
        public boolean permCheck(Player player, String permission){

            PermissionManager pex = PermissionsEx.getPermissionManager();
            return pex.has(player, permission);
        
        }


    
}

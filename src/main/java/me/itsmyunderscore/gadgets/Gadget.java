package me.itsmyunderscore.gadgets;

import me.itsmyunderscore.SpigotPlugin;
import me.itsmyunderscore.managers.SpigotManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Gadget {
    protected final static long duration = 200L;
    protected SpigotPlugin main;

    public Gadget( SpigotPlugin main) {
        this.main = main;
    }

    protected void started(){
        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                stop();
            }
        }, duration);
    }

    public void start(Player player, Location location){

    }

    public void stop(){

    }

}

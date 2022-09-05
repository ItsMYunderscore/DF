package me.itsmyunderscore.events;

import me.itsmyunderscore.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class SpigotListener implements Listener {

    protected SpigotPlugin main;

    public SpigotListener(SpigotPlugin main) {
        this.main = main;

        Bukkit.getPluginManager().registerEvents(this,main);
    }
}

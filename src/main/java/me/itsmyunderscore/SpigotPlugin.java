package me.itsmyunderscore;

import lombok.Getter;
import me.itsmyunderscore.events.PlayerListener;
import me.itsmyunderscore.managers.DanceFloorManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SpigotPlugin extends JavaPlugin {

    private DanceFloorManager danceFloorManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        danceFloorManager = new DanceFloorManager(this);
        new PlayerListener(this);
        this.getCommand("test").setExecutor(new TestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

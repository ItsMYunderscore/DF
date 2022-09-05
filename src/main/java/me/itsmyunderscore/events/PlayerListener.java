package me.itsmyunderscore.events;

import me.itsmyunderscore.SpigotPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener extends SpigotListener {
    public PlayerListener(SpigotPlugin main) {
        super(main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!main.getDanceFloorManager().getCooldown().containsKey(event.getPlayer().getName())) {
            main.getDanceFloorManager().getCooldown().put(event.getPlayer().getName(), 0L);
        }
    }
}

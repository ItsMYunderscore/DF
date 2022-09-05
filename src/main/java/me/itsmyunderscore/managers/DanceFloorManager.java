package me.itsmyunderscore.managers;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import me.itsmyunderscore.SpigotPlugin;
import me.itsmyunderscore.events.GadgetListener;
import org.bukkit.Location;

import java.util.Map;
import java.util.Set;

@Getter
public class DanceFloorManager extends SpigotManager {

    private Set<Location> activeLocations = Sets.newHashSet();
    private Map<String, Long> cooldown = Maps.newHashMap();
    private Map<Integer, String> entities = Maps.newHashMap();


    public DanceFloorManager(SpigotPlugin main) {
        super(main);

        new GadgetListener(main, this);
    }
}

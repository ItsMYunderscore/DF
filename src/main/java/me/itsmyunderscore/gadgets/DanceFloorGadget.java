package me.itsmyunderscore.gadgets;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import me.itsmyunderscore.Constants;
import me.itsmyunderscore.SpigotPlugin;
import me.itsmyunderscore.managers.DanceFloorManager;
import me.itsmyunderscore.utils.ColorUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DanceFloorGadget extends Gadget {

    private DanceFloorManager manager;

    private Map<Location, Material> previousBlocks = Maps.newHashMap();
    private Set<Location> blocks = Sets.newHashSet();

    public DanceFloorGadget(DanceFloorManager manager, SpigotPlugin main, Player player, Location location) {
        super(main);

        this.manager = manager;

        start(player, location);
    }

    @Override
    public void start(Player player, Location location) {
        if (!changeBlocks(location)) {
            player.sendMessage(ChatColor.RED + "That's an invalid location!");

            removeGadgetBlocks();

            return;
        }

        spawnParticle(location.getBlock().getLocation().clone().add(0.5,2,0.5),0);

        manager.getCooldown().put(player.getName(), System.currentTimeMillis() + Constants.COOLDOWN);

        blocks.forEach(loc -> {
            manager.getActiveLocations().add(loc);
        });

        //todo: Play custom song here

        started();
    }

    @Override
    public void stop() {
        removeGadgetBlocks();
    }

    private boolean changeBlocks(Location location) {
        World world = location.getWorld();

        if (world == null) {
            Bukkit.getLogger().warning("World is null for some reason :(");
            return false;
        }

        for (int x = -4; x < 5; x++) {
            for (int z = -4; z < 5; z++) {
                Location targetLocation = location.clone().add(x, -1, z);

                Block targetBlock = targetLocation.getBlock();

                if (targetBlock.getType() == Material.AIR) {
                    return false;
                }

                if (manager.getActiveLocations().contains(targetLocation)) {
                    return false;
                }

                Location secondaryLocation = targetLocation.clone().add(0,1,0);

                //Logging blocks on top of clay layer
                previousBlocks.put(secondaryLocation, secondaryLocation.getBlock().getType());
                blocks.add(secondaryLocation);

                //Logging clay layer
                previousBlocks.put(targetLocation, targetBlock.getType());
                blocks.add(targetLocation);

                secondaryLocation.getBlock().setType(Material.AIR);

                targetBlock.setType(Material.STAINED_CLAY);
                targetBlock.setData(ColorUtils.getRandomColor().getData());
            }
        }

        location.getBlock().setType(Material.JUKEBOX);

        return true;
    }

    private void removeGadgetBlocks() {
        blocks.forEach(location -> {
            location.getBlock().setType(previousBlocks.get(location));

            manager.getActiveLocations().remove(location);
        });
    }

    private void spawnParticle(Location location, int count) {
        if (count == duration / 2L) {
            return;
        }

        int i = count + 1;

        location.getWorld().playEffect(location, Effect.NOTE, 100000, 15);

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                spawnParticle(location, i);
            }
        }, 2L);
    }
}

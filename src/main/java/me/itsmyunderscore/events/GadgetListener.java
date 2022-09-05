package me.itsmyunderscore.events;

import me.itsmyunderscore.SpigotPlugin;
import me.itsmyunderscore.gadgets.DanceFloorGadget;
import me.itsmyunderscore.managers.DanceFloorManager;
import me.itsmyunderscore.utils.ActionUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GadgetListener extends SpigotListener {
    private DanceFloorManager manager;

    public GadgetListener(SpigotPlugin main, DanceFloorManager manager) {
        super(main);

        this.manager = manager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().getType() != Material.JUKEBOX && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().toLowerCase().equals("dance floor")) {
            return;
        }
        event.setCancelled(true);

        Player player = event.getPlayer();

        if (manager.getCooldown().get(player.getName()) > System.currentTimeMillis()) {
            player.sendMessage(ChatColor.RED + "You are still on cooldown");
            return;
        }

        TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation().add(player.getLocation().getDirection()), TNTPrimed.class);
        tnt.setCustomName("Dance Floor");

        manager.getEntities().put(tnt.hashCode(), player.getName());

        ActionUtils.velocity(tnt, player.getLocation().getDirection(), 0.6, false, 0, 0.2, 1);

        /*          PLACING BY CLICKING ON GROUND

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }


        new DanceFloorGadget(manager, main,  player, event.getClickedBlock().getLocation().clone().add(0,1,0)); */
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (!event.getEntity().getCustomName().equals("Dance Floor")) {
            return;
        }

        event.setYield(0);
        event.setCancelled(true);

        new DanceFloorGadget(manager, main,  Bukkit.getPlayer(manager.getEntities().get(event.getEntity().hashCode())), event.getLocation());
    }
}

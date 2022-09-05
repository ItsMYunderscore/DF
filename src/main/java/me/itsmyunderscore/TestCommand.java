package me.itsmyunderscore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = ((Player) sender);

        ItemStack itemStack = new ItemStack(Material.JUKEBOX,1);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Dance Floor");

        itemStack.setItemMeta(itemMeta);

        player.getLocation().getWorld().dropItem(player.getLocation(), itemStack);
        return true;
    }
}

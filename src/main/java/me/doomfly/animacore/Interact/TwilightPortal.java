package me.doomfly.animacore.Interact;

import me.doomfly.animacore.AnimaCore;
import me.doomfly.animacore.Load;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Locale;

public class TwilightPortal implements Listener {
    public TwilightPortal(AnimaCore main) {
        Bukkit.getPluginManager().registerEvents(this, (Plugin)main);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (Load.config.get("TwilightEnabled").toLowerCase().equals("false")) { return; }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = event.getPlayer();
            Location loc = new Location(Bukkit.getWorld("DIM7"), p.getLocation().getX(), 40.0D, p.getLocation().getZ());
            if (event.getClickedBlock() == null)
                return;
            if (event.getClickedBlock().getType() == null)
                return;
            if (event.getClickedBlock().getType().toString().equals(Load.config.get("TwilightItem")))
                p.teleport(loc);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (Load.config.get("TwilightEnabled").toLowerCase().equals("false")) { return; }
        Player p = event.getPlayer();
        Block b = event.getBlock();
        if (b.getType().toString().equals(Load.config.get("TwilightItem"))) {
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.getMaterial(Load.config.get("TwilightItem"))) });
            event.setCancelled(true);
            Bukkit.getServer().getName();
        }
    }
}

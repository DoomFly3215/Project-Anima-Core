package me.doomfly.animacore.Commands;

import java.util.ArrayList;
import me.doomfly.animacore.AnimaCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {
    public DebugCommand(AnimaCore main) {
        Bukkit.getPluginCommand("debug").setExecutor(this);
    }

    public String func(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("debug") &&
                args.length > 1) {
            double x, y, z;
            ArrayList<Entity> entities;
            Location l;
            Player p = (Player)sender;
            switch (args[0]) {
                case "entities":
                    if (!p.hasPermission("ac.command.debug"))
                        return false;
                    x = 25.0D;
                    y = 25.0D;
                    z = 25.0D;
                    if (args.length >= 2)
                        x = Integer.parseInt(args[1]);
                    if (args.length >= 3)
                        y = Integer.parseInt(args[2]);
                    if (args.length >= 4)
                        z = Integer.parseInt(args[3]);
                    entities = new ArrayList<>(p.getNearbyEntities(x, y, z));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bEntities:"));
                    if (entities.size() == 0) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo entities within range."));
                        return false;
                    }
                    for (Entity entity : entities)
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + entity
                                .getName() + " &f-> [x:" +
                                Math.round(entity.getLocation().getX()) + ", y:" +
                                Math.round(entity.getLocation().getY()) + ", z:" +
                                Math.round(entity.getLocation().getZ()) + "]"));
                    break;
                case "player":
                    l = p.getLocation();
                    p.sendMessage(func("&bUser Info&f: " + p.getName()));
                    p.sendMessage(func("&f"));
                    p.sendMessage(func("Name: &7" + p.getDisplayName()));
                    p.sendMessage(func("Location: &7x" + l.getX() + " y" + l.getY() + " z" + l.getZ()));
                    break;
            }
        }
        return false;
    }
}

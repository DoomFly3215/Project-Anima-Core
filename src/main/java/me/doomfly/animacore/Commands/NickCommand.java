package me.doomfly.animacore.Commands;

import me.doomfly.animacore.AnimaCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {
    public NickCommand(AnimaCore main) {
        Bukkit.getPluginCommand("rename").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("rename")) {
            if (args.length > 2)
                return false;
            if (!sender.hasPermission("ac.command.rename"))
                return false;
            int am = args.length;
            switch (am) {
                case 1:
                    Player p = (Player)sender;
                    p.setDisplayName(args[0]);
                    p.sendMessage("You have set your nickname to: " + args[0]);
                    return false;
                case 2:
                    p = Bukkit.getPlayer(args[0]);
                    p.setDisplayName(args[1]);
                    p.sendMessage("User: " + p.getName() + " | Set to: " + args[1]);
                    return false;
            }
            Player p = (Player)sender;
            p.sendMessage("/nick <nickname/username> [<nickname>]");
        }
        return false;
    }
}

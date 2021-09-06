package me.doomfly.animacore.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.doomfly.animacore.AnimaCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor {
    public PermCommand(AnimaCore main) {
        Bukkit.getPluginCommand("dtest").setExecutor(this);
        Bukkit.getPluginCommand("permy").setExecutor(this);
    }

    public static int intFromPerm(String file, String uuid, String perm) {
        Pattern pattern = Pattern.compile("(" + uuid + ")](\\s.+)*((\\s)+" + perm + ": (\\d+))");
        Matcher matcher = pattern.matcher(file);
        if (matcher.find())
            return Integer.parseInt(matcher.group(5));
        return -1;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equals("permy") &&
                sender instanceof org.bukkit.command.ConsoleCommandSender)
            fuck(args, args[0]);
        if (cmd.getName().equals("dtest")) {
            Player p = (Player)sender;
            sender.sendMessage(p.getInventory().getItemInMainHand().getType().toString());
        }
        return false;
    }

    public void fuck(String[] args, String type) {
        String perm;
        switch (type) {
            case "claims":
                perm = "ftbutilities.claims.max_chunks";
                break;
            case "chunks":
                perm = "ftbutilities.chunkloader.max_chunks";
                break;
            case "homes":
                perm = "ftbutilities.homes.max";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        String op = Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString();
        String opf = op.replaceAll("-", "");
        ArrayList<String> l = new ArrayList<>();
        File f = new File("./local/ftbutilities/players.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null)
                l.add(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int amount = intFromPerm(String.join("\n", (Iterable)l), opf, perm);
        switch (args[2]) {
            case "add":
                if (amount == -1)
                    break;
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ranks set_permission " + args[1] + " " + perm + " " + (amount +
                        Integer.parseInt(args[3])));
                return;
            case "subtract":
                if (amount == -1)
                    break;
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ranks set_permission " + args[1] + " " + perm + " " + (amount -
                        Integer.parseInt(args[3])));
                return;
        }

        switch (type) {
            case "claims":
                if (amount == -1) { amount = (30 + Integer.parseInt(args[3])); }
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ranks set_permission " + args[1]  + " " + perm + " " + amount);
                return;
            case "chunks":
                if (amount == -1) { amount = (Integer.parseInt(args[3])); }
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ranks set_permission " + args[1]  + " " + perm + " " + amount);
                return;
            case "homes":
                if (amount == -1) { amount = (1 + Integer.parseInt(args[3])); }
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "ranks set_permission " + args[1]  + " " + perm + " " + amount);
                return;
        }
        throw new IllegalStateException("Unexpected value: " + type);
    }
}

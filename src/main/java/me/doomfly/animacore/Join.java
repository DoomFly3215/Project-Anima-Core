package me.doomfly.animacore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class Join implements Listener {
    HashMap<String, Boolean> notWhitelisted = new HashMap<>();

    public Join(AnimaCore main) {
        Bukkit.getPluginManager().registerEvents(this, (Plugin)main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws SQLException {
        Player p = event.getPlayer();
        String uuid = p.getUniqueId().toString();
        PreparedStatement query = Load.mySQL.getConnection().prepareStatement("SELECT * FROM `" + Load.config.get("Database") + "`.`" + Load.config.get("UsersTable") + "` WHERE `" + Load.config.get("UsersTable") + "`.`mcuuid` = ? LIMIT 1;");
        query.setString(1, uuid);
        ResultSet rs = query.executeQuery();
        event.setJoinMessage("");
        if (MySQL.rowCount(rs) == 0) {
            this.notWhitelisted.put(uuid, Boolean.valueOf(true));
            p.kickPlayer(Load.config.get("NotWhitelisted"));
            return;
        }
        int Whitelisted = rs.getInt("whitelisted");
        if (Whitelisted == -1) {
            this.notWhitelisted.put(uuid, Boolean.valueOf(true));
            p.kickPlayer(Load.config.get("Declined"));
            return;
        }
        if (Whitelisted == 0) {
            this.notWhitelisted.put(uuid, Boolean.valueOf(true));
            if (rs.getInt("whiteliststate") < 3) {
                p.kickPlayer(Load.config.get("NotFinished"));
            } else {
                p.kickPlayer(Load.config.get("NotReviewed"));
            }
            return;
        }
        String message = null;
        if (p.hasPermission("ac.owner")) {
            message = Load.config.get("ownerMessage");
            if (Load.config.get("ownerLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.admin")) {
            message = Load.config.get("adminMessage");
            if (Load.config.get("adminLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.donator")) {
            message = Load.config.get("donatorMessage");
            if (Load.config.get("donatorLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.vip")) {
            message = Load.config.get("vipMessage");
            if (Load.config.get("vipLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        message = Load.config.get("defaultMessage");
        if (Load.config.get("defaultLines").equals("false")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
        } else {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
        }
        return;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        event.setQuitMessage("");
        if (this.notWhitelisted.containsKey(p.getUniqueId().toString())) {
            this.notWhitelisted.remove(p.getUniqueId().toString());
            return;
        }
        String message = null;
        if (p.hasPermission("ac.owner")) {
            message = Load.config.get("ownerLeaveMessage");
            if (Load.config.get("ownerLeaveLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.admin")) {
            message = Load.config.get("adminLeaveMessage");
            if (Load.config.get("adminLeaveLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.donator")) {
            message = Load.config.get("donatorLeaveMessage");
            if (Load.config.get("donatorLeaveLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        if (p.hasPermission("ac.vip")) {
            message = Load.config.get("vipLeaveMessage");
            if (Load.config.get("vipLeaveLines").equals("false")) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            }
            return;
        }
        message = Load.config.get("defaultLeaveMessage");
        if (Load.config.get("defaultLeaveLines").equals("false")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
        } else {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replaceAll("%displayName%", p.getDisplayName())));
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
        }
        return;
    }
}

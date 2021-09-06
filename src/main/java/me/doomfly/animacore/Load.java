package me.doomfly.animacore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;
import java.util.HashMap;

public class Load {

    private Plugin plugin = AnimaCore.getPlugin(AnimaCore.class);

    public static HashMap<String, String> config = new HashMap<>();

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private String encoding;
    private Boolean ssl;

    public static MySQL mySQL = null;

    public Load() throws SQLException {
        LoadFiles(false);
        LoadFromFiles();
        this.host = Load.config.get("Host");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Host"));
        this.port = Load.config.get("Port");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Port"));
        this.database = Load.config.get("Database");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Database"));
        this.username = Load.config.get("Username");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Username"));
        this.password = Load.config.get("Password");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Password"));
        this.encoding = Load.config.get("Encoding");
        Bukkit.getServer().getConsoleSender().sendMessage(Load.config.get("Encoding"));
        this.ssl = (Load.config.get("SSL").toLowerCase().equals("true"));
        if (mySQL != null) {
            mySQL.close();
        }
        mySQL = new MySQL(this.host, this.port, this.database, this.username, this.password, this.encoding, this.ssl);
    }

    public void LoadFromFiles() {
        String data = plugin.getDataFolder().toString();
        String configString = data + "/config.yml";
        // Loading Database
        config.put("Host", Files.GetStringinFile(configString, "Database.Host"));
        config.put("Port", Files.GetStringinFile(configString, "Database.Port"));
        config.put("Database", Files.GetStringinFile(configString, "Database.Database"));
        config.put("Username", Files.GetStringinFile(configString, "Database.Username"));
        config.put("Password", Files.GetStringinFile(configString, "Database.Password"));
        config.put("Encoding", Files.GetStringinFile(configString, "Database.Encoding"));
        config.put("SSL", Files.GetStringinFile(configString, "Database.SSL"));
        config.put("UsersTable", Files.GetStringinFile(configString, "Database.UsersTable"));
        // Loading Whitelist Messages
        config.put("NotWhitelisted", Files.GetStringinFile(configString, "Whitelist.Messages.NotWhitelisted"));
        config.put("Declined", Files.GetStringinFile(configString, "Whitelist.Messages.Declined"));
        config.put("NotFinished", Files.GetStringinFile(configString, "Whitelist.Messages.NotFinished"));
        config.put("NotReviewed", Files.GetStringinFile(configString, "Whitelist.Messages.NotReviewed"));
        // Loading Join Messages
        config.put("ownerMessage", Files.GetStringinFile(configString, "Join.Messages.Owner.Message"));
        config.put("ownerLines", Files.GetStringinFile(configString, "Join.Messages.Owner.Lines"));
        config.put("adminMessage", Files.GetStringinFile(configString, "Join.Messages.Admin.Message"));
        config.put("adminLines", Files.GetStringinFile(configString, "Join.Messages.Admin.Lines"));
        config.put("donatorMessage", Files.GetStringinFile(configString, "Join.Messages.Donator.Message"));
        config.put("donatorLines", Files.GetStringinFile(configString, "Join.Messages.Donator.Lines"));
        config.put("vipMessage", Files.GetStringinFile(configString, "Join.Messages.VIP.Message"));
        config.put("vipLines", Files.GetStringinFile(configString, "Join.Messages.VIP.Lines"));
        config.put("defaultMessage", Files.GetStringinFile(configString, "Join.Messages.Default.Message"));
        config.put("defaultLines", Files.GetStringinFile(configString, "Join.Messages.Default.Lines"));
        // Loading Leave Messages
        config.put("ownerLeaveMessage", Files.GetStringinFile(configString, "Leave.Messages.Owner.Message"));
        config.put("ownerLeaveLines", Files.GetStringinFile(configString, "Leave.Messages.Owner.Lines"));
        config.put("adminLeaveMessage", Files.GetStringinFile(configString, "Leave.Messages.Admin.Message"));
        config.put("adminLeaveLines", Files.GetStringinFile(configString, "Leave.Messages.Admin.Lines"));
        config.put("donatorLeaveMessage", Files.GetStringinFile(configString, "Leave.Messages.Donator.Message"));
        config.put("donatorLeaveLines", Files.GetStringinFile(configString, "Leave.Messages.Donator.Lines"));
        config.put("vipLeaveMessage", Files.GetStringinFile(configString, "Leave.Messages.VIP.Message"));
        config.put("vipLeaveLines", Files.GetStringinFile(configString, "Leave.Messages.VIP.Lines"));
        config.put("defaultLeaveMessage", Files.GetStringinFile(configString, "Leave.Messages.Default.Message"));
        config.put("defaultLeaveLines", Files.GetStringinFile(configString, "Leave.Messages.Default.Lines"));
        // Twilight Portal
        config.put("TwilightItem", Files.GetStringinFile(configString, "TwilightPortal.Item"));
        config.put("TwilightEnabled", Files.GetStringinFile(configString, "TwilightPortal.Enabled"));
    }

    public void LoadFiles(boolean replace) {
        plugin.saveResource("config.yml", replace);
    }

}

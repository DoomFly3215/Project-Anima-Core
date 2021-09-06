package me.doomfly.animacore;

import me.doomfly.animacore.Commands.DebugCommand;
import me.doomfly.animacore.Commands.NickCommand;
import me.doomfly.animacore.Commands.PermCommand;
import me.doomfly.animacore.Interact.TwilightPortal;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class AnimaCore extends JavaPlugin  {

    public void onEnable() {
        try { new Load(); } catch (SQLException throwables) {
            Bukkit.getConsoleSender().sendMessage("balls"); }
        new PermCommand(this);
        new Join(this);
        new NickCommand(this);
        new TwilightPortal(this);
        new DebugCommand(this);
    }

    public void onDisable() {}
}

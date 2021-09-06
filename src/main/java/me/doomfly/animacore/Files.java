package me.doomfly.animacore;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {

    public static Boolean FileExists(String file, Boolean b) {
        File f = new File(file);
        if (f.exists()) {
            return true;
        } else {
            if (b.equals(true)) {
                try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
                return true;
            }
        }
        return false;
    }

    public static void FileDelete(String file, String reason) {
        File f = new File(file);
        f.delete();
    }

    public static void SetStringinFile(String file, String key, String value) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        data.set(key, value);
        try { data.save(f); } catch (IOException e) { e.printStackTrace(); }
    }

    public static void SetIntinFile(String file, String key, int value) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        data.set(key, value);
        try { data.save(f); } catch (IOException e) { e.printStackTrace(); }
    }

    public static void SetBoolinFile(String file, String key, boolean value) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        data.set(key, value);
        try { data.save(f); } catch (IOException e) { e.printStackTrace(); }
    }

    public static String GetStringinFile(String file, String key) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        String s = "";
        if (data.getString(key) == null) {
            s = "NA";
        } else { s = data.getString(key).toString(); }
        return s;
    }

    public static int GetIntinFile(String file, String key) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        return data.getInt(key);
    }

    public static Boolean GetBoolinFile(String file, String key) {
        File f = new File(file);
        FileConfiguration data = YamlConfiguration.loadConfiguration(f);
        return data.getBoolean(key);
    }

    public static List GetKeys(String file) {
        File f = new File(file);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(f);
        List<String> l1 = new ArrayList<String>();
        l1.addAll(data.getConfigurationSection("").getKeys(true));
        return l1;
    }

    public static List GetValuesFromKeys(String file) {
        File f = new File(file);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(f);
        List<String> l1 = new ArrayList<String>();
        l1.addAll(data.getConfigurationSection("").getKeys(true));
        List<String> l2 = new ArrayList<String>();
        for (String l : l1) {
            l2.add(Files.GetStringinFile(f.getAbsolutePath(), l));
        }
        return l2;
    }
}

package ru.lynceus.pvpClumps;

import org.bukkit.plugin.java.JavaPlugin;

public final class XPClumps extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new XPListener(), this);
    }
}
package com.milan25.deathmessagebroadcasting;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathMessageBroadcasting extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("DeathMessageBroadcasting plugin loaded");

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new DMBEvents(this), this);
    }
}

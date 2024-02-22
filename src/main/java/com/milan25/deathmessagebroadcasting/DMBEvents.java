package com.milan25.deathmessagebroadcasting;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DMBEvents implements Listener {

    private final DeathMessageBroadcasting plugin;

    public DMBEvents(DeathMessageBroadcasting plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnTest(PlayerDeathEvent event) {
        World world = event.getEntity().getWorld();
        String worldName = world.getName();
        String deathMessage = event.getDeathMessage();

        String worldSetting = plugin.getConfig().getString("worlds." + worldName, "global");

        if (deathMessage == null) {
            return;
        }

        switch (worldSetting) {
            case "local":
                for (Player p : world.getPlayers()) {
                    p.sendMessage(deathMessage);
                }
                break;
            case "disable":
            case "disabled":
                event.setDeathMessage(null);
                break;
            default:
            case "global":
                Bukkit.broadcastMessage(deathMessage);
                break;
        }
        event.setDeathMessage(null);
    }
}

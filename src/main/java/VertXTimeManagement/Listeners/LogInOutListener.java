package VertXTimeManagement.Listeners;

import VertXTimeManagement.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LogInOutListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Main.getLoginHandler().timeIn(uuid);
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        Main.getLoginHandler().timeOut(uuid);
        if (Main.getAFKHandler().getDataWrapper(uuid) == null) {
            //todo: this ignores the case in which a player logs in goes afk, but the dataWrapper is unavailable because of a long delay,
            return;
        }
        if (Main.getAFKHandler().getDataWrapper(player.getUniqueId()).isDoing()) {
            Main.getAFKHandler().timeOut(uuid);
        }
    }
}

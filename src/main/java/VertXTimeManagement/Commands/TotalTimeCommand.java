package VertXTimeManagement.Commands;

import VertXTimeManagement.Main.Main;
import VertXTimeManagement.Storage.DataWrapper;
import VertXTimeManagement.Storage.PublicDataContainer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TotalTimeCommand extends CommandAsset {

    public TotalTimeCommand(String commandName, String permission) {
        super(commandName, permission, AllowableUserType.PLAYER);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        CompletableFuture<PublicDataContainer> dataFuture = Main.getLoginHandler().getBasicData(uuid);
        dataFuture.thenApplyAsync((data) -> {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                @Override
                public void run() {

                    if (Bukkit.getOnlinePlayers().contains(player)) {

                        long milliseconds = data.getTotalTime();
                        long seconds = (milliseconds / 1000) % 60;
                        long minutes = (milliseconds / 60000);

                        player.sendMessage(ChatColor.BOLD + "Total Time: " + ChatColor.GRAY + "" + minutes + "m " + seconds + "s ");
                    }
                }
            }, 20L);
            return null;
        });
    }
}

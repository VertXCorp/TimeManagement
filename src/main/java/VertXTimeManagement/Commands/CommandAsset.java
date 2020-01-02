package VertXTimeManagement.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class CommandAsset implements CommandExecutor {

    private String commandName, permission;
    private AllowableUserType allowedUserType;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (this.allowedUserType == AllowableUserType.CONSOLE && !(commandSender instanceof ConsoleCommandSender))
            return false;

        if (this.allowedUserType == AllowableUserType.PLAYER && !(commandSender instanceof Player))
            return false;

        if (getPermission() != null && !commandSender.hasPermission(getPermission()))
            return false;

        execute(commandSender, strings);

        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    public String getPermission() {
        return permission;
    }

    public CommandAsset(String commandName, String permission, AllowableUserType allowedUserType) {
        this.commandName = commandName;
        this.permission = permission;
        this.allowedUserType = allowedUserType;
    }

    public AllowableUserType getAllowableUserTypes() {
        return allowedUserType;
    }

    public String getCommandName() {
        return commandName;
    }

}

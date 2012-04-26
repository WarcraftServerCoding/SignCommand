
package com.warcraftserver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract interface ExecutorInterface extends CommandExecutor{
    public abstract boolean onCommand(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString);

    public abstract void invalidCommand(CommandSender paramCommandSender, String paramString);
}

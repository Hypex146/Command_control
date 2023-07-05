package plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import plugin.CommandСontrol;

public class MainCommand implements CommandExecutor {
	private CommandСontrol main_plugin_;
	// configuration
	// constants
	
	public MainCommand(CommandСontrol main_plugin) {
		this.main_plugin_ = main_plugin;
	}
	
	private boolean reloadCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("command_control.admin") && !(sender instanceof ConsoleCommandSender)) {
			sender.sendMessage("You don't have permission to do this!");
			return false;
		}
		main_plugin_.loadFullConfig();
		main_plugin_.checkEnableStatus();
		sender.sendMessage("The configuration was successfully reloaded!");
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equals("reload")) {
				return reloadCommand(sender, command, label, args);
			}
		}
		sender.sendMessage("Error in command syntax!");
		return false;
	}
	
}

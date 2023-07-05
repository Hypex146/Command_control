package plugin.managers;

import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.RemoteServerCommandEvent;

import plugin.CommandСontrol;
import plugin.utilities.CCConfigurator;
import plugin.utilities.LogLevel;

public class CommandManager {
	private CommandСontrol main_plugin_;
	// configuration
	private List<String> rmt_cmd_white_list_;
	// constants
	
	public CommandManager(CommandСontrol main_plugin) {
		main_plugin_ = main_plugin;
	}
	
	public void loadConfig() {
		loadRemoteCommandWhiteList();
	}
	
	private void loadRemoteCommandWhiteList() {
		FileConfiguration cfg = main_plugin_.getConfig();
		CCConfigurator cc_cfg = main_plugin_.getCCConfigurator();
		ConfigurationSection remote_section = cc_cfg.getConfigurationSection(cfg, "Remote_command");
		rmt_cmd_white_list_ = cc_cfg.getStringList(remote_section, "white_list");
	}
	
	public void processRemoteCommandEvent(RemoteServerCommandEvent event) {
		String command = event.getCommand();
		boolean res = false;
		for (String reg_ex : rmt_cmd_white_list_) {
			Pattern pattern = Pattern.compile(reg_ex);
			Matcher matcher = pattern.matcher(command);
			if (matcher.find()) {
				res = true;
				break;
			}
		}
		if (!res) {
			event.setCancelled(true);
			main_plugin_.getCCLogger().log(LogLevel.STANDART, Level.WARNING, "Remote command execution canceled: " + command);
		} else {
			main_plugin_.getCCLogger().log(LogLevel.STANDART, Level.INFO, "Remote command is executed: " + command);
		}
	}
	
}

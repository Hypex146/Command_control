package plugin;

import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.commands.MainCommand;
import plugin.listeners.CommandEventListener;
import plugin.managers.CommandManager;
import plugin.utilities.CCConfigurator;
import plugin.utilities.CCLogger;
import plugin.utilities.LogLevel;

public class CommandСontrol extends JavaPlugin{
	private FileConfiguration config_;
	private CCConfigurator configurator_;
	private CCLogger logger_;
	private CommandManager command_manager_;
	// configuration
	private boolean enable_;
	private boolean enable_greeting_;
	// constants
	private final String plugin_name_ = "Command_control";
	
	public CommandСontrol() {
		configurator_=  new CCConfigurator(this);
		logger_ = new CCLogger(this, LogLevel.STANDART);
		command_manager_ = new CommandManager(this);
	}
	
	public String getPluginName() {
		return plugin_name_;
	}
	
	public boolean isEnable() {
		return enable_;
	}
	
	public CCConfigurator getCCConfigurator() {
		return configurator_;
	}
	
	public CCLogger getCCLogger() {
		return logger_;
	}
	
	public CommandManager getCommandManager() {
		return command_manager_;
	}
	
	public void loadFullConfig() {
		saveDefaultConfig();
		reloadConfig();
		loadMainParams();
		command_manager_.loadConfig();
		saveConfig();
	}
	
	private void loadMainParams() {
		config_ = getConfig();
		ConfigurationSection main_section = configurator_.getConfigurationSection(config_, "Main_settings");
		LogLevel log_level = LogLevel.toEnum(configurator_.getString(main_section, 
				"log_level", LogLevel.STANDART.toString()));
		if (log_level == null) {
			configurator_.setString(main_section, "log_level", LogLevel.STANDART.toString());
			log_level = LogLevel.STANDART;
		}
		logger_.setLogLevel(log_level);
		enable_ = configurator_.getBoolean(main_section, "enable", true);
		enable_greeting_ = configurator_.getBoolean(main_section, "enable_greeting", true);
	}
	
	public boolean checkEnableStatus() {
		logger_.log(LogLevel.DEBUG, Level.INFO, "Checking whether the plugin should work.");
		if (enable_ == true) { 
			logger_.log(LogLevel.DEBUG, Level.INFO, "The plugin should work");
			return true; 
			}
		logger_.log(LogLevel.DEBUG, Level.INFO, "The plugin should not work. Disabling the plugin.");
		getServer().getPluginManager().disablePlugin(this);
		logger_.log(LogLevel.STANDART, Level.INFO, "The plugin is disabled.");
		return false;
	}
	
	private void printGreetingInConsole() {
		String greeting = "\n===========================\n"
				+ "|   |     ___   ___        \n"
				+ "|   |\\   /|  |  |     \\  / \n"
				+ "|===| \\ / |__|  |__    \\/  \n"
				+ "|   |  |  |     |      /\\  \n"
				+ "|   |  |  |     |__   /  \\ \n"
				+ "===========================";
		logger_.log(LogLevel.STANDART, Level.INFO, greeting);
	}
	
	@Override
	public void onEnable() {
		loadFullConfig();
		if (!checkEnableStatus()) { return; }
		if (enable_greeting_) { printGreetingInConsole(); }
		getCommand("command_control").setExecutor(new MainCommand(this));
		getServer().getPluginManager().registerEvents(new CommandEventListener(this), this);
	}
	
}

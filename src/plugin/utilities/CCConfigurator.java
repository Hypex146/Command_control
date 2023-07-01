package plugin.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import plugin.CommandСontrol;


public class CCConfigurator {
	private CommandСontrol main_plugin_;
	// configuration
	// constants
	
	public CCConfigurator(CommandСontrol main_plugin) {
		main_plugin_ = main_plugin;
	}
	
	public FileConfiguration getCustomConfig(String pathToFile) {
		File configFile = new File(main_plugin_.getDataFolder()+"/"+pathToFile);
		File folderFile = configFile.getParentFile();
		if (!folderFile.exists()) {
			folderFile.mkdirs();
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.INFO, "Ñîçäàíà ïàïêà: " + folderFile.getAbsolutePath());
		}
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.INFO, "Ñîçäàí ôàéë: " + configFile.getAbsolutePath());
			} catch (IOException e) {
				main_plugin_.getCCLogger().log(LogLevel.MINIMAL, Level.SEVERE, "Íå óäàëîñü ñîçäàòü ôàéë (IOException): "
						+configFile.getAbsolutePath());
				e.printStackTrace();
				return null;
			}
		}
		YamlConfiguration customConfig = new YamlConfiguration();
		try {
			customConfig.load(configFile);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.INFO, "Ñ÷èòàí ôàéë: " + configFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			main_plugin_.getCCLogger().log(LogLevel.MINIMAL, Level.SEVERE, "Íå óäàëîñü ñ÷èòàòü ôàéë (FileNotFoundException): "
					+configFile.getAbsolutePath());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			main_plugin_.getCCLogger().log(LogLevel.MINIMAL, Level.SEVERE, "Íå óäàëîñü ñ÷èòàòü ôàéë (IOException): "
					+configFile.getAbsolutePath());
			e.printStackTrace();
			return null;
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			main_plugin_.getCCLogger().log(LogLevel.MINIMAL, Level.SEVERE, "Íå óäàëîñü ñ÷èòàòü ôàéë (InvalidConfigurationException): "
					+configFile.getAbsolutePath());
			e.printStackTrace();
			return null;
		}
		return customConfig;
	}
	
	public int saveCustomConfig(String pathToFile, FileConfiguration configToSave) {
		File configFile = new File(main_plugin_.getDataFolder()+"/"+pathToFile);
		try {
			configToSave.save(configFile);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.INFO, "Ñîõðàí¸í ôàéë: "+configFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			main_plugin_.getCCLogger().log(LogLevel.MINIMAL, Level.SEVERE, 
					"Íå óäàëîñü ñîõðàíèòü ôàéë: (IOException) "+configFile.getAbsolutePath());
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public boolean getBoolean(ConfigurationSection config, String field, boolean defaultValue) {
		if (!config.isBoolean(field)) {
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Íåâåðíî âûñòàâëåíî ïîëå \"" + field + "\" â êîíôèãå");
			config.set(field, defaultValue);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Ïîëå \"" + field + "\" óñòàíîâëåíî ïî óìîë÷àíèþ");
		}
		return config.getBoolean(field);
	}
	
	public void setBoolean(ConfigurationSection config, String field, boolean value) {
		config.set(field, value);
	}
	
	public int getInt(ConfigurationSection config, String field, int defaultValue) {
		if (!config.isInt(field)) {
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Íåâåðíî âûñòàâëåíî ïîëå \"" + field + "\" â êîíôèãå");
			config.set(field, defaultValue);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Ïîëå \"" + field + "\" óñòàíîâëåíî ïî óìîë÷àíèþ");
		}
		main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
				"Ïîëå \"" + field + "\" óñòàíîâëåíî êàê ïîëàãàåòñÿ");
		return config.getInt(field);
	}
	
	public void setInt(ConfigurationSection config, String field, int value) {
		config.set(field, value);
	}
	
	public String getString(ConfigurationSection config, String field, String defaultValue) {
		if (!config.isString(field)) {
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Íåâåðíî âûñòàâëåíî ïîëå \"" + field + "\" â êîíôèãå");
			config.set(field, defaultValue);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Ïîëå \"" + field + "\" óñòàíîâëåíî ïî óìîë÷àíèþ");
		}
		return config.getString(field);
	}
	
	public void setString(ConfigurationSection config, String field, String value) {
		config.set(field, value);
	}
	
	public double getDouble(ConfigurationSection config, String field, double defaultValue) {
		if (!config.isDouble(field)) {
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Íåâåðíî âûñòàâëåíî ïîëå \"" + field + "\" â êîíôèãå");
			config.set(field, defaultValue);
			main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
					"Ïîëå \"" + field + "\" óñòàíîâëåíî ïî óìîë÷àíèþ");
		}
		return config.getDouble(field);
	}
	
	public void setDouble(ConfigurationSection config, String field, double value) {
		config.set(field, value);
	}
	
	public List<String> getStringList(ConfigurationSection config, String field){
		List<String> stringList;
		stringList = config.getStringList(field);
		if (stringList.size()>0) {
			return stringList;
		}
		main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
				"Íåâåðíî âûñòàâëåíî ïîëå \"" + field + "\" â êîíôèãå");
		config.set(field, stringList);
		main_plugin_.getCCLogger().log(LogLevel.DEBUG, Level.WARNING, 
				"Ïîëå \"" + field + "\" óñòàíîâëåíî ïî óìîë÷àíèþ");
		return stringList;
	}
	
	public ConfigurationSection getConfigurationSection(ConfigurationSection config, String path) {
		if (!config.isConfigurationSection(path)) {
			return config.createSection(path);
		}
		return config.getConfigurationSection(path);
	}
	
	public boolean hasConfigurationSection(ConfigurationSection config, String path) {
		return config.isConfigurationSection(path);
	}
	
}
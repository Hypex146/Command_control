package plugin.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;

import plugin.CommandСontrol;

public class CommandEventListener implements Listener {
	private CommandСontrol main_plugin_;
	// configuration
	// constants
	
	public CommandEventListener(CommandСontrol main_plugin) {
		main_plugin_ = main_plugin;
	}
	
	@EventHandler
	public void onPlayerDeath(RemoteServerCommandEvent event) {
		main_plugin_.getCommandManager().processRemoteCommandEvent(event);
	}
	
}

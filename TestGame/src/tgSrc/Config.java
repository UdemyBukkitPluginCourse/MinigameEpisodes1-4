package tgSrc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static TGMain main;
	
	public Config(TGMain main) {
		Config.main = main;
		
		main.getConfig().options().copyDefaults();
		main.saveDefaultConfig();
	}

	public static FileConfiguration getConfig() {
		return main.getConfig();
	}
	
	public static int getRequiredPlayers() { // The amount of players required for the queue to proceed and  eventually start the game
		return getConfig().getInt("required-players");
	}
	
	public static int getCountdownSeconds() { // The amount of seconds it begins counting down from after the required amount of players has joined
		return getConfig().getInt("countdown-seconds");
	}
	
	public static Location getLobbySpawn() { // Get the lobby spawn (where users are returned to after the game is over)
		return new Location(Bukkit.getWorld(getConfig().getString("lobby.world")),
				getConfig().getInt("lobby.x"), 
				getConfig().getInt("lobby.y"),
				getConfig().getInt("lobby.z"),
				getConfig().getInt("lobby.pitch"),
				getConfig().getInt("lobby.yaw"));
				
	}
	
	public static Location getArenaSpawn(int id) { // Get the spawn point of the arena in question
		return new Location(
				Bukkit.getWorld(getConfig().getString("arenas." + id + ".world")),
				getConfig().getInt("arenas." + id + ".x"), 
				getConfig().getInt("arenas." + id + ".y"),
				getConfig().getInt("arenas." + id + ".z"),
				getConfig().getInt("arenas." + id + ".pitch"),
				getConfig().getInt("arenas." + id + ".yaw"));
				
	}
	
	public static int getArenaAmount() {
		return main.getConfig().getConfigurationSection("arenas.").getKeys(false).size();
	}
}

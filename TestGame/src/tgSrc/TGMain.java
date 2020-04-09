package tgSrc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TGMain extends JavaPlugin{

	private static TGMain instance;
	
	@Override
	public void onEnable() {
		TGMain.instance = this;
		
		new Config(this); // Initialize CONFIG BEFORE MANAGER (becuse manager uses methods from config)
		new Manager();
		
		getCommand("arena").setExecutor(new ArenaCmd());
		
		Bukkit.getPluginManager().registerEvents(new GameListener(), this);
	}
	
	public static TGMain getInstance() { return instance; }
}

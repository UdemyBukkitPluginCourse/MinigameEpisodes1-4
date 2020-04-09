package tgSrc;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Game {

	String filler = "§d§m§l------------------------------------";
	
	private Arena arena;
	private HashMap<UUID, Integer> points;
	
	public Game(Arena arena) {
		this.arena = arena;
		this.points = new HashMap<UUID, Integer>();
	}
	
	public void start() {
		arena.setState(GameState.LIVE);
		
		arena.sendMessage(filler + "\n§aThe game has started! \n§6The first player to break §b20 §6blocks wins! \n" + filler);
		
		for(UUID uuid : arena.getPlayers()) {
			points.put(uuid, 0);
		}
	}
	
	public void addPoint(Player p) {
		int po = points.get(p.getUniqueId()) + 1;
		
		if(po == 20) {
			arena.sendMessage(filler + "\n§6" + p.getName() + " §awins! \n" + filler);
			
			arena.reset();
			return;
		}
		
		points.put(p.getUniqueId(), po);
	}
}

package tgSrc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Manager {

	private static ArrayList<Arena> arenas;
	
	public Manager() {
		arenas = new ArrayList<Arena>();
		
		for (int i = 0; i <= (Config.getArenaAmount() - 1); i++) {
			arenas.add(new Arena(i));
		}
		
	}
	
	
	public static List<Arena> getArenas() { return arenas; }
	
	public static boolean isPlaying(Player p) { // Used to check if the player in question is in a game
		for(Arena arena : arenas) {
			if(arena.getPlayers().contains(p.getUniqueId())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isRecruiting(int id) {
		return getArena(id).getState() == GameState.RECRUITING;
	}
	
	public static Arena getArena(Player p) {
		for (Arena arena : arenas) {
			if(arena.getPlayers().contains(p.getUniqueId())){
				return arena;
			}
		}
		
		return null;
	}
	
	public static Arena getArena(int id) { // Gets the arena using the id of an arena provided, if there is no arena with such id, it returns null
		for (Arena arena : arenas) {
			if(arena.getID() == id) {
				return arena;
			}
		}
		
		return null;
	}
}

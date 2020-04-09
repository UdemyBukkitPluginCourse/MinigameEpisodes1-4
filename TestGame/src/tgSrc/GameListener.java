package tgSrc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if (Manager.isPlaying(p) && Manager.getArena(p).getState().equals(GameState.LIVE)) {
			p.sendMessage("§a+1 §bpoint");
			
			Manager.getArena(p).getGame().addPoint(p);
		}
	}
	
}

package tgSrc;

import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{

	private Arena arena;
	private int seconds;
	
	public Countdown(Arena arena) {
		this.arena = arena;
		this.seconds = Config.getCountdownSeconds();
	}
	
	public void begin() {
		arena.setState(GameState.COUNTDOWN);
		this.runTaskTimer(TGMain.getInstance(), 0, 20);
	}
	
	@Override
	public void run() {
		if(seconds == 0) {
			cancel();
			arena.start(); // Starts the game when the countdown reaches 0 seconds
			return;
		} 
		
		if (seconds % 10 == 0 || seconds <= 5) { // Runs every 30 seconds and every second less then 10 seconds left
			if(seconds == 1) {
				arena.sendMessage("§bGame will start in §61 §bsecond"); 
			} else {
				arena.sendMessage("§bGame will start in §6" + seconds + " §bseconds");
			}
		} 
		
		if(arena.getPlayers().size() < Config.getRequiredPlayers()) {
			cancel();
			arena.setState(GameState.RECRUITING);
			arena.sendMessage("§cThere are not enough players to start the match! \n§6Waiting for more §bplayers");
			return;
		}
		
		seconds--;
	}
}

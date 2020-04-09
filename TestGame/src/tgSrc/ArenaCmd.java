package tgSrc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCmd implements CommandExecutor{

	String filler = "§d§m§l------------------------------------";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
				sender.sendMessage(filler + "\n§bHere is a list of current arenas");
				for (Arena arena : Manager.getArenas()) {
					sender.sendMessage("\n §6- §b" + arena.getID());
				}
				sender.sendMessage("\n" + filler);
			} else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
				if(Manager.isPlaying(p)) {
					Manager.getArena(p).removePlayer(p);
					sender.sendMessage("§cLeft arena §6" + Manager.getArena(p).getID());
				} else {
					sender.sendMessage("§cYou aren't in an arena right now!");
				}
			} else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
				try {
					int id = Integer.parseInt(args[1]);
					
					if(id >= 0 && id <= (Config.getArenaAmount() - 1)) {
						if(Manager.isRecruiting(id)) {
							Manager.getArena(id).addPlayer(p);
							
							sender.sendMessage("§aYou are now in arena §6" + id);
						} else {
							sender.sendMessage("§cYou cannot join arena §6" + id + " §cright now");
						}
					} else {
						sender.sendMessage("§6" + args[1] + " §bis not a valid arena");
					}
				} catch (NumberFormatException x) {
					sender.sendMessage("§6" + args[1] + " §bis not a valid arena");
				}
			} else {
				sender.sendMessage(filler + "\n§aAvailable commands: \n§6- §b/arena list §cList all available arenas \n§6- §b/arena leave §cLeave your current arena \n §6- §b/arena join §e[id] §cJoin an arena \n" + filler);
			}
		} else {
			sender.sendMessage("§cConsole is not allowed to use this command!");
		}
		
		return true;
	}

}

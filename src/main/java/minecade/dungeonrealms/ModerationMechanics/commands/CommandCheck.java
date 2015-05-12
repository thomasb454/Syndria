package minecade.dungeonrealms.ModerationMechanics.commands;

import minecade.dungeonrealms.RestrictionMechanics.RestrictionMechanics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCheck implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		Player p = null;
//		if(sender instanceof Player){
//			p = (Player) sender;
//			
//		}else {
//			return false;
//		}
//		switch(RestrictionMechanics.zone_type.get(p.getName())){
//		case "safe":
//			p.sendMessage(ChatColor.GREEN + "                " + ChatColor.BOLD + "*** SAFE ZONE (DMG-OFF) ***");
//	        break;
//	        
//		}
//		
//		
		return true;
	}
	
}

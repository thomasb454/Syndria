package minecade.dungeonrealms.DonationMechanics.commands;

import minecade.dungeonrealms.Main;
import minecade.dungeonrealms.DonationMechanics.DonationMechanics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGiveSub implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player ps = null;
		if(sender instanceof Player) {
			ps = (Player) sender;
			if(!(ps.isOp())) { return true; }
		}
		String p_name = args[0];
		
		String current_rank = DonationMechanics.getRank(p_name);
		
		if(!current_rank.equalsIgnoreCase("default")) {
			// Don't let them overwrite pmod, sub+, sub++ ... just give them additional forum group.
			return true;
		}
		
		DonationMechanics.setRank(p_name, "sub");
		DonationMechanics.addSubscriberDays(p_name, 30, false);
		DonationMechanics.sendPacketCrossServer("[forum_group]" + p_name + ":" + 75, -1, true);
		DonationMechanics.sendPacketCrossServer("[rank_map]" + p_name + ":" + "sub", -1, true);
		if(ps != null) {
			ps.sendMessage(ChatColor.GREEN + "Set " + p_name + " to SUBSCRIBER.");
		}
		Main.log.info("[DonationMechanics] Set user " + p_name + " to SUBSCRIBER.");
		return true;
	}
	
}
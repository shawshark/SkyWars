package me.shawshark.skywars;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerListener implements Listener {
	
	public main m;
	public Boolean twoplayersJoined = false;
	
	public PlayerListener(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PermissionUser user = PermissionsEx.getUser(p);
		
		/* send them to the hub if the game has already started, plus don't run the rest of the event. */
		if(m.isGame) {
			/* make user perform /server hub */
			p.performCommand("server hub");
			/* cancel out the join message */
			e.setJoinMessage(null);
			/* Dont run rest of code. */
			return;
		}
		
		int playersOnline = m.getServer().getOnlinePlayers().length;
		int playersMax = m.getServer().getMaxPlayers();
		
		/* if there is 2 players online. */
		if(playersOnline == 2) {
			/* if this is the first time they have. */
			if(!twoplayersJoined) {
				/* set boolean to true. */
				twoplayersJoined = true;
				// run an early start task..
			}
		}
		
		String joinMessage = user.getPrefix() + p.getName() + ChatColor.BLUE + " has joined the game!" + ChatColor.GRAY + 
				"(" + ChatColor.RED + playersOnline + ChatColor.GRAY + "/" + ChatColor.RED + playersMax + ChatColor.GRAY + ")";
		
		/* Set the join message eg.. [Owner] shawshark has joined the game (10/12) */
		e.setJoinMessage(joinMessage);
		
		/* Chooses there spawn location. */
		chooseSpawn(p, playersOnline);
	}
	
	public void chooseSpawn(Player p, int online) {
		/* search though all the spawns..*/
		for ( Spawns s : m.spawns ) {
			/* get the spawn id thats the same as the player count. */
			if( online == s.id ) {
				
				Location loc = s.loc;
				/* teleport player. */
				p.teleport(loc);
				/* stop search. */
				return;
			}
		}
	}

}

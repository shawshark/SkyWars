package me.shawshark.skywars;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Tasks {
	
	public main m;
	public BukkitTask countDown;
	public int count;
	
	public Tasks(main m) {
		this.m = m;
	}
	
	public void earlyStart() {
		count = 160; // 2 min.
		Bukkit.broadcastMessage(m.prefix + ChatColor.YELLOW + " The game will start in 2 minutes!");
		countDown();
	}
	
	public void countDown() {
		countDown = new BukkitRunnable() {
			@Override
			public void run() {
				/* if timer = 0 */
				if(count == 0) {
					// start game.
					
					/* cancel this task. */
					cancel();
					
					/* return from here. */
					return;
				}
				
				if(count == 60) {
					Bukkit.broadcastMessage(m.prefix + ChatColor.YELLOW + " The game will start in 1 minute!");
				}
				
				else if(count == 30) {
					Bukkit.broadcastMessage(m.prefix + ChatColor.YELLOW + " The game will start in 30 seconds!");
				}
				
				else if(count == 15) {
					Bukkit.broadcastMessage(m.prefix + ChatColor.YELLOW + " The game will start in 15 seconds!");
				}
				
				else if(count < 10 && count > 0) {
					Bukkit.broadcastMessage(m.prefix + ChatColor.YELLOW + " The game will start in " + count + " seconds!");
				}
				
				/* get all online players. */
				for ( Player p : m.getServer().getOnlinePlayers() ) {
					if(BarAPI.hasBar(p)) {
						/* if players has bar from before, remove it. */
						BarAPI.removeBar(p);
					}
					/* prepare the message */
					String message = ChatColor.YELLOW + "The game will start in " + count + " seconds!";
					/* set the message. */
					BarAPI.setMessage(p, message);
					if(count > 100) {
						/* if the count timer is over 100, always set the boss health to 100 */
						BarAPI.setHealth(p, 100);
					} else {
						/* if the count is under 100, set the boss health to the current timer. */
						BarAPI.setHealth(p, count);
					}
				}
				
				/* take 1 from the count */
				count--;
			}
		}.runTaskTimer(m, 20, 20);
	}
}

package me.shawshark.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
				
				/* take 1 from the count */
				count--;
			}
		}.runTaskTimer(m, 20, 20);
	}
}

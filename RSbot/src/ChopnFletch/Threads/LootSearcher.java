package ChopnFletch.Threads;

import java.util.List;
import java.util.Random;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.NEST;


public class LootSearcher extends Thread implements Runnable {

	int waitTime;
	ClientContext ctx;
	boolean lead = false;
	
	@Override
	public void run() {
		while(true) {
			List<GroundItem> drops = ctx.groundItems.get();
			
			for(GroundItem i: drops) {
				for(NEST n: NEST.values()) {
					if(i.id() == n.getId()) {
						
						i.click();
						Condition.sleep(500);
						
						while(ctx.players.local().inMotion()) {
							Condition.sleep(100);
						}
						
					}
				}
			}
			
			Condition.sleep(waitTime);
		}
		
	}
	
	public LootSearcher(int waitTime, ClientContext ctx) {
		this.waitTime 	= waitTime;
		this.ctx		= ctx;
	}
}

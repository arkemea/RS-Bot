package ChoppyFletchy;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Menu;
import org.powerbot.script.rt4.Menu.Command;

public class Fletch extends Task<ClientContext> {
	private int knifeId 		= 946;
	private int logId			= LOG.NORMAL.getLogId();
	private int featherId 		= 314;
	private int arrowShaftId 	= 52;
	
	public Fletch(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {
		return		ctx.inventory.select().count() == 28
				&& 	ctx.inventory.select().id(knifeId).poll().valid()
				&&	ctx.inventory.select().id(ChopnFletch.logToCut).poll().valid();
		
	}

	@Override
	public void execute() {

		if(ChopnFletch.fletch == 1) {
			if(ctx.inventory.select().id(LOG.NORMAL.getLogId()).poll().valid()) {
				cutArrowShaft();
			}
			
			if(ctx.inventory.select().id(featherId).poll().valid() && ctx.inventory.select().id(arrowShaftId).poll().valid()) {
				makeHeadlessArrow();
			}
		} else if(ChopnFletch.fletch == 2) {
			fletchShortbow();
		} else if(ChopnFletch.fletch == 3) {
			fletchLongbow();
		}
		
	}
	
	public void cutArrowShaft() {
		
		while(true) {
			
			
			Item knife 	= ctx.inventory.select().id(knifeId).poll();
			Item log 	= ctx.inventory.select().id(logId).poll();
			
			if (!log.valid()) {
				break;
			}
			
			if(ctx.players.local().animation() == -1) {
			
				ChopnFletch.status = "Fletching";
				
				knife.interact("Use");
				log.interact("Use");	
			}
			
			for(int i = 0; i < 4; i++) {
				Component arrowShaftComponent = ctx.widgets.widget(305).component(9);
				
				if(arrowShaftComponent.valid()) {
					arrowShaftComponent.interact(false, "Make 10");
				} else {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {}	
				}		
			}	
		}			
	}
	
	public void makeHeadlessArrow() {
		while(true) {
			Item feather 	= ctx.inventory.select().id(featherId).poll();
			Item arrowShaft = ctx.inventory.select().id(arrowShaftId).poll();
			
			if (!feather.valid() || !arrowShaft.valid()) {
				break;
			}
			
			feather.interact("Use");
			arrowShaft.interact("use");
			
			for(int i = 0; i < 4; i++) {
				Component makeHeadlessArrow = ctx.widgets.widget(582).component(5);
				
				if(makeHeadlessArrow.valid()) {
					makeHeadlessArrow.interact(false, "Make 10 sets");
					
					ChopnFletch.status = "Fletching";
					
					try {
						Thread.sleep(13000);
					} catch (InterruptedException e) {}	
					
					break;
				} else {
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {}	
				}		
			}
		}
	}
	
	public void fletchShortbow() {
		
		while(true) {
			
			Item knife 	= ctx.inventory.select().id(knifeId).poll();
			Item log 	= ctx.inventory.select().id(ChopnFletch.logToCut).poll();
			
			if (!log.valid()) {
				break;
			}
			
			if(ctx.players.local().animation() == -1) {
			
				ChopnFletch.status = "Fletching";
				
				if(!ctx.widgets.widget(304).component(8).valid()) {
					knife.interact("Use");
					log.interact("use");
				}
					
			}
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					Component shortbowComponent = ctx.widgets.widget(304).component(8);
					if(shortbowComponent.valid()) {
						shortbowComponent.interact(false, "Make 10");
						return true;
					}
					
					return false;
				}
			}, 500, 4);	
					
		}
	}
	
	public void fletchLongbow() {
		
		while(true) {
					
					Item knife 	= ctx.inventory.select().id(knifeId).poll();
					Item log 	= ctx.inventory.select().id(ChopnFletch.logToCut).poll();
					
					if (!log.valid()) {
						break;
					}
					
					if(ctx.players.local().animation() == -1) {
					
						ChopnFletch.status = "Fletching";
				
					if(!ctx.widgets.widget(304).component(8).valid()) {
						knife.interact("Use");
						log.interact("use");
					}	
			}
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					Component shortbowComponent = ctx.widgets.widget(304).component(10);
					if(shortbowComponent.valid()) {
						shortbowComponent.interact(false, "Make 10");
						return true;
					}
					
					return false;
				}
			}, 500, 4);
		}
	}
}


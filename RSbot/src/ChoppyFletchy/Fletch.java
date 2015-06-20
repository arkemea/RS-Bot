package ChoppyFletchy;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.ItemQuery;

public class Fletch extends Task<ClientContext> {
	private int knifeId 		= 946;
	private int logId			= LOG.NORMAL.getLogId();
	private int featherId 		= 314;
	private int arrowShaftId 	= 52;
	
	private BotSetting bSetting;
	
	public Fletch(ClientContext ctx) {
		super(ctx);
	}
	
	public Fletch(ClientContext ctx, BotSetting bSetting) {
		super(ctx);
		this.bSetting = bSetting;
	}
	
	@Override
	public boolean activate() {
		
		return		ctx.inventory.count() == 28
				&& 	ctx.inventory.select().id(knifeId).poll().valid();

		
	}

	@Override
	public void execute() {
		
		if(bSetting.getFletch() == 1) {
			if(ctx.inventory.select().id(LOG.NORMAL.getLogId()).poll().valid()) {
				cutArrowShaft();
			}
			
			if(ctx.inventory.select().id(featherId).poll().valid() && ctx.inventory.select().id(arrowShaftId).poll().valid()) {
				makeHeadlessArrow();
			}
		} else if(bSetting.getFletch() == 2) {
			fletchShortBow();
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
			
				ChopnFletcher.status = "Fletching";
				
				knife.interact("Use");
				log.interact("use");	
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
				return;
			}
			
			feather.interact("Use");
			arrowShaft.interact("use");
			
			for(int i = 0; i < 4; i++) {
				Component makeHeadlessArrow = ctx.widgets.widget(582).component(5);
				
				if(makeHeadlessArrow.valid()) {
					makeHeadlessArrow.interact(false, "Make 10 sets");
					
					ChopnFletcher.status = "Fletching";
					
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
	
	public void fletchShortBow() {
		
		
		
	}
	
	
}

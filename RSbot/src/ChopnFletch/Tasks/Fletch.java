package ChopnFletch.Tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.*;
import org.powerbot.script.rt4.*;
import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.Log;

public class Fletch extends Task<ClientContext> {
	private int knifeId 		= 946;
	private int featherId 		= 314;
	private int arrowShaftId 	= 52;

	private int logId, fletch;
	
	public Fletch(ClientContext ctx, int fletch, int logId) {
		super(ctx);
		this.logId 	= logId;
		this.fletch = fletch;
		
	}
	
	@Override
	public boolean activate() {
		return		ctx.inventory.select().count() == 28
				&& 	ctx.inventory.select().id(knifeId).poll().valid()
				&&	ctx.inventory.select().id(logId).poll().valid();
		
	}

	@Override
	public void execute() {

		if(fletch == 1) {
			if(ctx.inventory.select().id(Log.NORMAL.getLogId()).poll().valid()) {
				cutArrowShaft();
			}
			
			if(ctx.inventory.select().id(featherId).poll().valid() && ctx.inventory.select().id(arrowShaftId).poll().valid()) {
				makeHeadlessArrow();
			}
		} else if(fletch == 2) {
			fletchShortbow();
		} else if(fletch == 3) {
			fletchLongbow();
		}
		
	}
	
	public void cutArrowShaft() {
		
		Component arrowShaftComponent = ctx.widgets.widget(305).component(9);
		Component makeXComponent	= ctx.widgets.widget(162).component(33);
		
		Item knife 					= ctx.inventory.select().id(knifeId).poll();
		Item log 					= ctx.inventory.select().id(logId).poll();
		
		if (!log.valid()) {
			return;
		}
		
		ChopnFletch.status = "Fletching";
		
		if(arrowShaftComponent.valid()) {
			
			if(ctx.menu.opened()) {

				ctx.menu.click(new Filter<MenuCommand>() {
					
					@Override
					public boolean accept(MenuCommand command) {
						return command.toString().toLowerCase().startsWith("make x");
					}
				});
				
				Condition.wait(new Callable<Boolean>() {

					@Override
					public Boolean call() {
						
						if(makeXComponent.visible()) {
							ctx.input.sendln("234");
							Condition.sleep(500);
							return true;
						}
						return false;
					}
				}, 200, 50);
				

			} else {
				arrowShaftComponent.click(false);
				
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() {
						return ctx.menu.opened();
					}
				}, 50, 50);
			}
			
		} else if(ctx.players.local().animation() == -1) {
			knife.interact("Use");
			log.interact("use");
		}	
					
	}
	
	public void makeHeadlessArrow() {
		
		Component makeHeadlessArrow = ctx.widgets.widget(582).component(5);
		
		while(true) {
			Item feather 	= ctx.inventory.select().id(featherId).poll();
			Item arrowShaft = ctx.inventory.select().id(arrowShaftId).poll();
			
			if (!feather.valid() || !arrowShaft.valid()) {
				break;
			}

			
			Condition.wait(() -> {
				
				if(makeHeadlessArrow.valid()) {
					makeHeadlessArrow.interact(false, "make 10 sets");
					ChopnFletch.status = "Fletching";
					return true;
				} else {
					feather.interact("Use");
					arrowShaft.interact("use");
				}
				
				return false;
			}, 50, 50);
			
			Condition.sleep(10000);
			
		}
		
	}
	
	public void fletchShortbow() {
		
		Component shortbowComponent = ctx.widgets.widget(304).component(8);
		Component makeXComponent	= ctx.widgets.widget(162).component(33);
		
		Item knife 					= ctx.inventory.select().id(knifeId).poll();
		Item log 					= ctx.inventory.select().id(logId).poll();
		
		if (!log.valid()) {
			return;
		}
		
		ChopnFletch.status = "Fletching";
		
		if(shortbowComponent.valid()) {
			
			if(ctx.menu.opened()) {

				ctx.menu.click(new Filter<MenuCommand>() {
					
					@Override
					public boolean accept(MenuCommand command) {
						return command.toString().toLowerCase().startsWith("make x");
					}
				});
				
				Condition.wait(new Callable<Boolean>() {

					@Override
					public Boolean call() {
						
						if(makeXComponent.visible()) {
							ctx.input.sendln("234");
							Condition.sleep(500);
							return true;
						}
						return false;
					}
				}, 200, 50);
				

			} else {
				shortbowComponent.click(false);
				
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() {
						return ctx.menu.opened();
					}
				}, 50, 50);
			}
			
		} else if(ctx.players.local().animation() == -1) {
			knife.interact("Use");
			log.interact("use");
		}	
	}
	
	public void fletchLongbow() {
		Component longbowComponent 	= ctx.widgets.widget(304).component(10);
		Component makeXComponent	= ctx.widgets.widget(162).component(33);
		
		
	
		Item knife 					= ctx.inventory.select().id(knifeId).poll();
		Item log 					= ctx.inventory.select().id(logId).poll();
		
		if (!log.valid()) {
			return;
		}
		
		ChopnFletch.status = "Fletching";
		
		if(longbowComponent.valid()) {
			
			if(ctx.menu.opened()) {

				ctx.menu.click(new Filter<MenuCommand>() {
					
					@Override
					public boolean accept(MenuCommand command) {
						return command.toString().toLowerCase().startsWith("make x");
					}
				});
				
				Condition.wait(new Callable<Boolean>() {

					@Override
					public Boolean call() {
						
						if(makeXComponent.visible()) {
							ctx.input.sendln("234");
							Condition.sleep(500);
							return true;
						}
						return false;
					}
				}, 200, 50);
				

			} else {
				longbowComponent.click(false);
				
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() {
						return ctx.menu.opened();
					}
				}, 50, 50);
			}
			
		} else if(ctx.players.local().animation() == -1) {
			knife.interact("Use");
			log.interact("use");
		}		
	
	}
}


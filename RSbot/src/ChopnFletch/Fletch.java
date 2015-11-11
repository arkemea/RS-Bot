package ChopnFletch;

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
		Component arrowShaftComponent = ctx.widgets.widget(305).component(9);
		Component makeXComponent	= ctx.widgets.widget(162).component(33);
		
		while(true) {
			
			
			Item knife 	= ctx.inventory.select().id(knifeId).poll();
			Item log 	= ctx.inventory.select().id(logId).poll();
			
			if (!log.valid()) {
				break;
			}
			
			ChopnFletch.status = "Fletching";
			
			Condition.wait(() -> {
				 if(arrowShaftComponent.valid()) {
					if(ctx.menu.opened()) {
						ctx.menu.click((command) -> {
							System.out.println(command.toString());
							return command.toString().toLowerCase().startsWith("make x");
						});
						Condition.wait(() -> {
							if(makeXComponent.visible()) {
								ctx.input.sendln("234");
								return true;
							}
							return false;
						}, 200, 50);
						
						Condition.wait(()-> {
							if(ctx.players.local().animation() == 1) {
								return true;
							}
							return false;
						},50, 50);
						return true;
					} else {
						arrowShaftComponent.click(false);
						Condition.wait(() -> ctx.menu.opened(), 50, 50);
					}
				} else if(ctx.players.local().animation() == -1) {
					knife.interact("Use");
					log.interact("use");
				}
				return false;
			},200, 50);		
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
		
		while(true) {
			
			Item knife 					= ctx.inventory.select().id(knifeId).poll();
			Item log 					= ctx.inventory.select().id(ChopnFletch.logToCut).poll();
			
			if (!log.valid()) {
				break;
			}
			ChopnFletch.status = "Fletching";
		
			Condition.wait(() -> {
				 if(shortbowComponent.valid()) {
					if(ctx.menu.opened()) {
						ctx.menu.click((command) -> {
							System.out.println(command.toString());
							return command.toString().toLowerCase().startsWith("make x");
						});
						Condition.wait(() -> {
							if(makeXComponent.visible()) {
								ctx.input.sendln("234");
								return true;
							}
							return false;
						}, 200, 50);
						
						Condition.wait(()-> {
							if(ctx.players.local().animation() == 1) {
								return true;
							}
							return false;
						},50, 50);
						return true;
					} else {
						shortbowComponent.click(false);
						Condition.wait(() -> ctx.menu.opened(), 50, 50);
					}
				} else if(ctx.players.local().animation() == -1) {
					knife.interact("Use");
					log.interact("use");
				}
				return false;
			},200, 50);		
		}
	}
	
	public void fletchLongbow() {
		Component longbowComponent 	= ctx.widgets.widget(304).component(10);
		Component makeXComponent	= ctx.widgets.widget(162).component(33);
		
		while(true) {
			
			Item knife 					= ctx.inventory.select().id(knifeId).poll();
			Item log 					= ctx.inventory.select().id(ChopnFletch.logToCut).poll();
			
			if (!log.valid()) {
				break;
			}
			
			ChopnFletch.status = "Fletching";
		
			Condition.wait(() -> {
				 if(longbowComponent.valid()) {
					if(ctx.menu.opened()) {
						ctx.menu.click((command) -> {
							System.out.println(command.toString());
							return command.toString().toLowerCase().startsWith("make x");
						});
						Condition.wait(() -> {
							if(makeXComponent.visible()) {
								ctx.input.sendln("234");
								return true;
							}
							return false;
						}, 200, 50);
						
						Condition.wait(()-> {
							if(ctx.players.local().animation() == 1) {
								return true;
							}
							return false;
						},50, 50);
						return true;
					} else {
						longbowComponent.click(false);
						Condition.wait(() -> ctx.menu.opened(), 50, 50);
					}
				} else if(ctx.players.local().animation() == -1) {
					knife.interact("Use");
					log.interact("use");
				}
				return false;
			},200, 50);			
		}
	}
}


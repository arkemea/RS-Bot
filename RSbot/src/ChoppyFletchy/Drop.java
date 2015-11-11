package ChoppyFletchy;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
//TODO Rewrite this class, shit is hard to look at
public class Drop extends Task<ClientContext> {
	
	public Drop(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return 		ctx.inventory.select().count() == 28;
	}

	@Override
	public void execute() {
		
		ChopnFletch.status = "Dropping";
		
		if(ChopnFletch.powerCut && ChopnFletch.fletch != 0) {
			dropBows();
			dropWrongLogs();
		} else if(ChopnFletch.powerCut) {
			dropAllLogs();
		} else if(!ChopnFletch.powerCut) {
			dropWrongLogs();
		}
	}
	
	public void dropLogs() {
		
		if(ChopnFletch.powerCut && ChopnFletch.fletch == 0) {
			dropAllLogs();
		} else if(ChopnFletch.powerCut && ChopnFletch.fletch != 0) {
			dropWrongLogs();
			dropBows();
		} else {
			dropWrongLogs();
		}
	}
	
	public void dropAllLogs() {
		
		for(Item i: ctx.inventory.select()) {
			for(LOG l: LOG.values()) {
				if(i.id() == l.getLogId()) {
					i.interact("Drop");
				}
			}
		}
	}
	
	public void dropWrongLogs() {
		
		for(Item i: ctx.inventory.select()) {
			for(LOG l: LOG.values()) {
				if(i.id() == l.getLogId() && l.getLogId() != ChopnFletch.logToCut) {
					i.interact("Drop");
				}
			}
		}
	}
	
	public void dropBows() {
		for(Item i: ctx.inventory.select()) {
			for(BOW l: BOW.values()) {
				if(i.id() == l.getBowId()) {
					i.interact("Drop");
				}
			}
		}
	}
}

package ChopnFletch.Tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;






import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.Bow;
import ChopnFletch.Enums.Log;

public class Drop extends Task<ClientContext> {
	
	private boolean powerCut;
	
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
		
		dropAllLogs();
		dropAllBows();
	}
	
	
	public void dropAllLogs() {
		
		for(Item i: ctx.inventory.select()) {
			for(Log l: Log.values()) {
				if(i.id() == l.getLogId()) {
					i.interact("Drop");
				}
			}
		}
	}
	
	public void dropAllBows() {
		for(Item i: ctx.inventory.select()) {
			for(Bow b: Bow.values()) {
				if(i.id() == b.getBowId()) {
					i.interact("Drop");
				}
			}
		}
	}
}

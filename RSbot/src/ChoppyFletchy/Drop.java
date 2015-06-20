package ChoppyFletchy;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Drop extends Task<ClientContext> {

	BotSetting bSetting;
	
	
	
	
	
	public Drop(ClientContext ctx, BotSetting bSetting) {
		super(ctx);
		this.bSetting = bSetting;
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		
	}
	
	public void dropLogs() {
		
		if(bSetting.powerCutting && bSetting.getFletch() == 0) {
			dropAllLogs();
		} else if(bSetting.powerCutting && bSetting.getFletch() != 0) {
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
				if(i.id() == l.getLogId() && l.getLogId() != bSetting.getLogToCut()) {
					i.interact("Drop");
				}
			}
		}
	}
	
	public void dropBows() {
		for(Item i: ctx.inventory.select()) {
			for(BOW l: BOW.values()) {
				if(i.id() == l.getBowId() && l.getBowId() != bSetting.getLogToCut()) {
					i.interact("Drop");
				}
			}
		}
		
	}

}

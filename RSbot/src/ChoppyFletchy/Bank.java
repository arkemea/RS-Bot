package ChoppyFletchy;

import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {

	public Bank(ClientContext ctx) {
		super(ctx);

	}

	@Override
	public boolean activate() {
		return ctx.inventory.count()==28;
	}

	@Override
	public void execute() {
		
	}

}

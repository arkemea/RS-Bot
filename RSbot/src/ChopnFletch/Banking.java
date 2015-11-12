package ChopnFletch;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Banking extends Task<ClientContext> {

	public Banking(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return 		ctx.inventory.select().count() == 28;
	}

	@Override
	public void execute() {
		
		ChopnFletch.status = "Banking";
		
		PathFinder mPF 			= new PathFinder(ctx);
		GameObject bankBooth 	= ctx.objects.select().id(ChopnFletch.bankToBank.getBankBoothId()).nearest().peek();
		
		if(ChopnFletch.bankToBank.getBankArea().contains(ctx.players.local().tile())) {
			
			if(bankBooth.inViewport() && !ctx.players.local().inMotion()) {
				bankBooth.click();	
				
				Condition.wait(() -> {
					if(ctx.bank.opened()) {
						bankAllBows();
						bankAllLogs();
						bankAllNests();
						ctx.bank.close();
						return true;
					}
					return false;
				}, 50, 50);
				
			} else {
				ctx.camera.turnTo(bankBooth);
			}
			
			
		} else {
			
			if(bankBooth.inViewport()) {
				if(!ctx.players.local().inMotion()) {
					ctx.movement.step(bankBooth);
				}
				
			} else if(mPF.playerDistanceTo(bankBooth.tile()) > 20) {
				mPF.moveTo(ChopnFletch.bankToBank.getSPOTS().getSpecificPath(ChopnFletch.pathToWalk).getReverseTilePath());
				
			} else {
				ctx.camera.turnTo(bankBooth);
				mPF.moveTo(ChopnFletch.bankToBank.getBankArea().getRandomTile());
			}
		}
	}
	
	
	public void bankAllLogs() { 
		for(LOG l: LOG.values()) {
			ctx.bank.deposit(l.getLogId(), Bank.Amount.ALL);
		}
	}	
		
	
	public void bankAllBows() {
		for(BOW b: BOW.values()) {
			ctx.bank.deposit(b.getBowId(), Bank.Amount.ALL);
		}
	}
	
	public void bankAllNests() {
		for(NEST n: NEST.values()) {
			ctx.bank.deposit(n.getId(), Bank.Amount.ALL);
		}
	}
}

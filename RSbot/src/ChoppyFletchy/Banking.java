package ChoppyFletchy;

import java.util.concurrent.Callable;

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
		
		PathFinder mPF = new PathFinder(ctx);
		mPF.moveToExact(ChopnFletch.bankToBank.getBankArea().getRandomTile());
		
		System.out.println(ChopnFletch.bankToBank.getBankArea().contains(ctx.players.local().tile()));
		
		if(ChopnFletch.bankToBank.getBankArea().contains(ctx.players.local().tile())) {
			
			GameObject bankBooth = ctx.objects.select().id(ChopnFletch.bankToBank.getBankBoothId()).nearest().peek();
			
			if(bankBooth.inViewport()) {
				bankBooth.click();	
			} else {
				ctx.camera.turnTo(bankBooth);
			}
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					if(ctx.bank.opened()) {
						System.out.println("Bankerino " + ChopnFletch.fletch);
						bankAllBows();
						bankAllLogs();
						ctx.bank.close();
						return true;
					}
					return false;
				}
			}, 500, 4);
			
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
}

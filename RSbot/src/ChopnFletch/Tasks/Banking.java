package ChopnFletch.Tasks;


import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import ChopnFletch.ChopnFletch;
import ChopnFletch.Enums.Banks;
import ChopnFletch.Enums.Bow;
import ChopnFletch.Enums.Log;
import ChopnFletch.Enums.Nest;

public class Banking extends Task<ClientContext> {
	
	Banks bankToBank;
	
	public Banking(ClientContext ctx, Banks bankToBank) {
		super(ctx);
		this.bankToBank = bankToBank;
	}

	@Override
	public boolean activate() {
		return 		ctx.inventory.select().count() == 28
					&& bankToBank.getBankArea().contains(ctx.players.local().tile());
	}

	@Override
	public void execute() {
		
		ChopnFletch.status = "Banking";

		GameObject bankBooth 	= ctx.objects.select().id(bankToBank.getBankBoothId()).nearest().peek();
		
		if(bankToBank.getBankArea().contains(ctx.players.local().tile())) {
			
			if(bankBooth.inViewport() && !ctx.players.local().inMotion()) {
				bankBooth.click();	
				
				Condition.wait(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						if(ctx.bank.opened()) {
							bankAllBows();
							bankAllLogs();
							bankAllNests();
							ctx.bank.close();
							return true;
						}
						return false;
					}
				}, 250, 8);
				
			} else {
				ctx.camera.turnTo(bankBooth);
				ctx.movement.step(bankBooth);
			}
			
		}
	} 
	
	public void bankAllLogs() { 
		for(Log l: Log.values()) {
			ctx.bank.deposit(l.getLogId(), Bank.Amount.ALL);
		}
	}	
		
	
	public void bankAllBows() {
		for(Bow b: Bow.values()) {
			ctx.bank.deposit(b.getBowId(), Bank.Amount.ALL);
		}
	}
	
	public void bankAllNests() {
		for(Nest n: Nest.values()) {
			ctx.bank.deposit(n.getId(), Bank.Amount.ALL);
		}
	}
}

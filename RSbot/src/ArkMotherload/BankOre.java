package ArkMotherload;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Inventory;

public class BankOre extends Task {

	public BankOre(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ArkMotherload.roundCount > 2;
	}

	@Override
	public void execute() {
		
		
		final GameObject sack = ctx.objects.select().id(26678).poll();
		
		System.out.println(sack.valid());
		
		if(!sack.valid()) {
			ArkMotherload.roundCount = 0;
		}
		
		
		if(sack.valid() && ctx.inventory.select().count() < 28) {
			
			if(MineArea.PICKUPAREA.getArea().contains(ctx.players.local().tile())) {
				ctx.movement.step(MineArea.PICKUPAREA.getArea().getRandomTile());
			} else {
				ctx.movement.step(sack);
			}
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() {
					
					if(MineArea.PICKUPAREA.getArea().contains(ctx.players.local().tile())
							&& !ctx.players.local().inMotion()) {
						
						clickSack(sack);
						return true;
					}
					
					return false;
				}
			}, 200, 10);
		}
		
		
		
		
		if(ctx.inventory.select().count() == 28 || !sack.valid() ) {
			ctx.movement.step(MineArea.BANKAREA.getArea().getRandomTile());
			
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() {
					
					if(MineArea.BANKAREA.getArea().contains(ctx.players.local().tile())) {
						bankInventory();
						
						return true;
					}
					
					return false;
				}
				
			},500, 12);
			
		}
		
	}
	
	public void bankInventory() {
		GameObject bankChest = ctx.objects.select().id(26707).poll();
		
		
		Condition.wait(new Callable<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				
				if(ctx.bank.opened()) {
					depositOre();
					depositGems();
					ctx.bank.close();
					return true;
				} else {
					bankChest.click();
				}
				return false;
			}
			
		}, 400, 10);
		
		
		
	}
	
	public void depositOre() {
		for(Ore o: Ore.values()) {
			ctx.bank.deposit(o.getID(), Bank.Amount.ALL);
		}
	}
	
	public void depositGems() {
		
	}
	
	public void clickSack(GameObject sack) {
		
		if(sack.valid()) {
			
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					
					GameObject sackCheck = ctx.objects.select().id(26678).poll();
					
					if(ctx.inventory.select().count() == 28) {
						return true;
					} else if(!sackCheck.valid()) {
						return true;
					}
					sack.interact("Search");
					return false;
				}
			}, 100, 10);
		}
	}
	
}

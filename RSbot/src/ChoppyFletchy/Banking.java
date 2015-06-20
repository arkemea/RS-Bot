package ChoppyFletchy;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Banking extends Task<ClientContext> {

	private Tile startingTile;
	
	public enum BANK {
		DEFAULT(new Tile(0,0)),
		DRAYNOR(new Tile(3093,3243));
		
		private Tile bankTile;
		
		BANK(Tile bankTile) {
			this.bankTile = bankTile;
		}
		
		public Tile getBankTile() {
			return bankTile;
		}
		
	}
	
	public Banking(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.inventory.count()==28;
	}

	@Override
	public void execute() {
		startingTile = ctx.players.local().tile();
		PathFinder mPF = new PathFinder(ctx);
		
		mPF.moveTo(getClosestBank().getBankTile());
		bankAllLogs();
		
	}
	
	public BANK getClosestBank() {
		
		PathFinder mPF = new PathFinder(ctx);
		BANK closestBank = BANK.DEFAULT;
		for(BANK b: BANK.values()) {
			if(mPF.playerDistanceTo(b.getBankTile()) <
				mPF.playerDistanceTo(closestBank.getBankTile())) {
				
				closestBank = b;
			}
			
		}
		
		return closestBank;
	}
	
	public void bankAllLogs() {
		
		GameObject bankBooth = ctx.objects.select().id(11744).nearest().limit(4).shuffle().poll();;
		
		System.out.println(bankBooth.valid());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}	
		
		bankBooth.click();
		
		if(ctx.bank.opened()) {
			for(LOG l: LOG.values()) {
				ctx.bank.deposit(l.getLogId(), Bank.Amount.ALL);
			}
		}
		
		if(ctx.inventory.count() < 28) {
			ctx.bank.close();
		}
	}	
}

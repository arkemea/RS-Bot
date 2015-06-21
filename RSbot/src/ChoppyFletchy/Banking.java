package ChoppyFletchy;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Banking extends Task<ClientContext> {

	private Tile startingTile;
	private BotSetting bSetting;
	
	public enum BANK {
		DEFAULT(new Tile(0,0), 0),
		DRAYNOR(new Tile(3093,3243), 11744),
		VARROCKEAST(new Tile(3253, 3421), 0),
		SEERSVILLAGE(new Tile(2725, 3491), 25808);
		
		private Tile bankTile;
		private int bankBoothId;
		
		BANK(Tile bankTile, int bankBoothId) {
			this.bankTile 		= bankTile;
			this.bankBoothId 	= bankBoothId;
		}
		
		public Tile getBankTile() {
			return bankTile;
		}
		
		public int getBankBoothId() {
			return bankBoothId;
		}
		
	}
	
	public Banking(ClientContext ctx, BotSetting bSetting) {
		super(ctx);
		this.bSetting = bSetting;
	}

	@Override
	public boolean activate() {
		return 		ctx.inventory.count() == 28
				|| 	ctx.inventory.count() == 0;
	}

	@Override
	public void execute() {
		System.out.println("banking");
		startingTile = ctx.players.local().tile();
		PathFinder mPF = new PathFinder(ctx);
		
		if(mPF.playerDistanceTo(getClosestBank().getBankTile()) > 5) {
			
			mPF.moveTo(getClosestBank().getBankTile());
		}
		
		bankAllLogs();
		bankAllBows();
		
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
		
		GameObject bankBooth = ctx.objects.select().id(getClosestBank().bankBoothId).nearest().limit(4).poll();;
		
		ctx.movement.step(bankBooth);
		
		System.out.println(bankBooth.valid());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		
		ctx.camera.turnTo(bankBooth);
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
	
	public void bankAllBows() {
		
		GameObject bankBooth = ctx.objects.select().id(getClosestBank().bankBoothId).nearest().limit(4).poll();;
		
		ctx.movement.step(bankBooth);
		
		System.out.println(bankBooth.valid());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}	
		
		bankBooth.click();
		
		if(ctx.bank.opened()) {
			for(BOW b: BOW.values()) {
				ctx.bank.deposit(b.getBowId(), Bank.Amount.ALL);
			}
		}
		
		if(ctx.inventory.count() < 28) {
			ctx.bank.close();
		}
	}
}

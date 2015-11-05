package ChoppyFletchy;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PathFinder extends Task<ClientContext> {

	public PathFinder(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		
		return true;
	}

	@Override
	public void execute() {
		
		
	}
	
	public Tile playerPosition() {
		return ctx.players.local().tile();
	}
	
	public double playerDistanceTo(Tile targetTile) {
		
		System.out.println(ctx.players.local().tile().x() + " " + ctx.players.local().tile().y() + " - " + targetTile.x() + " " + targetTile.y());
		
		return 
				Math.sqrt(
				Math.pow( Math.abs(ctx.players.local().tile().x()-targetTile.x()), 2) *
				Math.pow( Math.abs(ctx.players.local().tile().y()-targetTile.y()), 2)
				);
	}
	
	public double distanceBetween(Tile t1, Tile t2) {
		return 
				Math.sqrt(
				Math.pow( Math.abs(t1.x()-t2.x()), 2) *
				Math.pow( Math.abs(t1.y()-t2.y()), 2)
				);
	}
	
	public void moveTo(Tile targetTile) {
	
		while(true) {
			if(this.playerDistanceTo(targetTile) < 20 )
			{break;}
			else {
				ctx.movement.step(targetTile);
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {}	
			}
		}
		
	}
	public void moveToExact(Tile targetTile) {
		while(true) {
			if(this.playerDistanceTo(targetTile) < 5 )
			{break;}
			else {
				ctx.movement.step(targetTile);
				
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {}	
			}
		}
	}
}


package ChopnFletch.Tasks;


import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.Enums.Banks;
import ChopnFletch.Enums.Spots;

public class PathFinder extends Task<ClientContext> {
	
	private boolean direction; //true is from bank to spot, and false is from spot to bank.
	private Banks bankToBank;
	private Spots placeToBe;
	private int pathToWalk;
	private int distanceToAnchor;
	
	public PathFinder(ClientContext ctx) {
		super(ctx);
	}
	
	public PathFinder(ClientContext ctx, boolean direction, Banks bankToBank, int pathToWalk) {
		super(ctx);
		this.direction = direction;
		this.bankToBank = bankToBank;
		this.placeToBe = bankToBank.getSpots();
		this.pathToWalk = pathToWalk;
		distanceToAnchor = bankToBank.getSpots().getSpecificPath(pathToWalk).getDistanceToAnchor();
	}

	@Override
	public boolean activate() {
		
		if(direction) {
			return playerDistanceTo(placeToBe.getSpecificAnchor(pathToWalk)) > distanceToAnchor
					&& ctx.inventory.select().count() < 28;
		} else {
			return !bankToBank.getBankArea().contains(ctx.players.local().tile())
					&& ctx.inventory.select().count() == 28;
		}

	}

	@Override
	public void execute() {
		
		if(direction) {
			moveTo(placeToBe.getSpecificPath(pathToWalk).getTilePath());
		} else {
			moveTo(placeToBe.getSpecificPath(pathToWalk).getReverseTilePath());
		}
		
	}
	
	public Tile playerPosition() {
		return ctx.players.local().tile();
	}
	
	public double playerDistanceTo(Tile targetTile) {
		
		return 
				Math.sqrt(
				Math.pow( Math.abs(ctx.players.local().tile().x()-targetTile.x()), 2) +
				Math.pow( Math.abs(ctx.players.local().tile().y()-targetTile.y()), 2)
				);
	}
	
	public double distanceBetween(Tile t1, Tile t2) {
		return 
				Math.sqrt(
				Math.pow( Math.abs(t1.x()-t2.x()), 2) +
				Math.pow( Math.abs(t1.y()-t2.y()), 2)
				);
	}
	
	
	public void moveTo(final Tile targetTile) {
		while(true) {
					
			if(!ctx.movement.running() && ctx.movement.energyLevel() > 80) {
				ctx.movement.running(true);
			}
			
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					
					PathFinder mPF = new PathFinder(ctx);
					
					if(mPF.playerDistanceTo(targetTile) < 5) {
						return true;
					} else {
						ctx.movement.step(targetTile);
					}
					return false;
				}
				
			}, 3000, 8);
			
			if(this.playerDistanceTo(targetTile) < 5) {
				break;
			}
		}
	}
	
	public void moveTo(final Tile[] path) {
		
		int closestTile = 0;
		Tile highScore = new Tile(0,0);
		
		for(int i = 0; i < path.length; i++) {
			if(this.playerDistanceTo(highScore) > this.playerDistanceTo(path[i])) {
				highScore = path[i];
				closestTile = i;
			}
		}
		
		for(int i = closestTile; i < path.length; i++ ) {
			
				while(true) {
				if(!ctx.movement.running() && ctx.movement.energyLevel() > 80) {
					ctx.movement.running(true);
				}
				
				final int index = i;
				
				Condition.wait(new Callable<Boolean>() {

					@Override
					public Boolean call() throws Exception {
						
						PathFinder mPF = new PathFinder(ctx);
						
						if(mPF.playerDistanceTo(path[index]) < 5) {
							return true;
						} else if(!ctx.players.local().inMotion()) {
							ctx.movement.step(path[index]);
						}
						return false;
					}
					
				}, 200, 50);

				
				if(this.playerDistanceTo(path[i]) < 5) {
					break;
				}
			}
		}
	}
}


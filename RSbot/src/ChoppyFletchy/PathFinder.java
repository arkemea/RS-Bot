package ChoppyFletchy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
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
	
	
	public void moveTo(Tile targetTile) {
		while(true) {
					
			if(!ctx.movement.running() && ctx.movement.energyLevel() > 80) {
				ctx.movement.running(true);
			}
			
			Condition.wait(() -> {
				if(this.playerDistanceTo(targetTile) < 5) {
					return true;
				} else {
					ctx.movement.step(targetTile);
				}
				return false;
			},3000,8);
			
			if(this.playerDistanceTo(targetTile) < 5) {
				break;
			}
		}
	}
	
	public void moveTo(Tile[] path) {
		
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
				
				Condition.wait(() -> {
					if(this.playerDistanceTo(path[index]) < 5) {
						return true;
					} else if(!ctx.players.local().inMotion()) {
						ctx.movement.step(path[index]);
					}
					return false;
				}, 200, 50);
				
				if(this.playerDistanceTo(path[i]) < 5) {
					break;
				}
			}
		}
	}
}


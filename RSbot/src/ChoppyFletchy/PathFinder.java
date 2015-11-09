package ChoppyFletchy;

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
	public void moveToClose(Tile targetTile) {
		
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
	public void moveToExact(Tile targetTile) {
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
}


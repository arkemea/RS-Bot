package ChopnFletch.Tasks;

import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;

import ChopnFletch.ChopnFletch;

public class Chop extends Task<ClientContext>{
	private int clickCounter	= 0;
	private int[] treeToChop;
	private Tile anchor;
	private int distanceToAnchor;
	
	public Chop(ClientContext ctx, int[] treeToChop, Tile anchor, int distanceToAnchor) {
		super(ctx);
		this.treeToChop = treeToChop;
		this.anchor = anchor;
		this.distanceToAnchor = distanceToAnchor;
		
	}
	
	@Override
	public boolean activate() {
		return ctx.inventory.select().count() < 28;
	}

	
	@Override
	public void execute() {
		
		GameObject tree 		= ctx.objects.select().id(treeToChop).nearest().poll();
		final PathFinder mPF	= new PathFinder(ctx);
		
		if(mPF.distanceBetween(anchor, tree.tile()) > distanceToAnchor) {
			mPF.moveTo(anchor);
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					GameObject tree 	= ctx.objects.select().id(treeToChop).nearest().poll();
					
					if(mPF.distanceBetween(anchor,tree.tile()) > distanceToAnchor) {
						ChopnFletch.status = "No trees";
						System.out.println(mPF.distanceBetween(anchor,tree.tile()));
						return false;
					}
					return true;
				}
			}, 500, 10);
			 
		} else if(!(ctx.players.local().animation() == -1)) {
				ChopnFletch.status = "Chopping";
				
		} else {
			ChopnFletch.status = "Moving to tree";
			if(clickCounter > 5) {
				ctx.camera.angle(ctx.camera.yaw()+45);
				clickCounter = 0;
				
			} else if(tree.inViewport()) {
				tree.interact("Chop");
				tree.interact("use");
				clickCounter++;
				
			} else {
				ctx.movement.step(tree);
				ctx.camera.turnTo(tree);
				clickCounter = 0;
			}
			ctx.camera.pitch(true);
			
		}
	}
}

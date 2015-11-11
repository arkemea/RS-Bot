package ChoppyFletchy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.GroundItems;

public class Chop extends Task<ClientContext>{
	private int clickCounter	= 0;
	
	public Chop(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {
		return ctx.inventory.select().count() < 28;
	}

	
	@Override
	public void execute() {
		
		GameObject tree 		= ctx.objects.select().id(ChopnFletch.treeToChop).nearest().poll();
		PathFinder mPF			= new PathFinder(ctx);
		int distanceFromAnchor	= ChopnFletch.bankToBank.getSPOTS().getSpecificPath(ChopnFletch.pathToWalk).getDistanceFromAnchor();
		int distancetoAnchor	= ChopnFletch.bankToBank.getSPOTS().getSpecificPath(ChopnFletch.pathToWalk).getDistanceToAnchor();
		
		System.out.println(mPF.playerDistanceTo(tree.tile()) + " " + mPF.distanceBetween(ChopnFletch.anchor, tree.tile()));
		
		if(mPF.playerDistanceTo(ChopnFletch.anchor) > distanceFromAnchor) {
			mPF.moveToExact(ChopnFletch.bankToBank.getSPOTS().getSpecificPath(ChopnFletch.pathToWalk).getTilePath());
			
			
		} else if(mPF.distanceBetween(ChopnFletch.anchor, tree.tile()) > distancetoAnchor) {
			mPF.moveToClose(ChopnFletch.anchor);
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					GameObject tree 	= ctx.objects.select().id(ChopnFletch.treeToChop).nearest().poll();
					
					if(mPF.distanceBetween(ChopnFletch.anchor,tree.tile()) > distancetoAnchor) {
						System.out.println(mPF.distanceBetween(ChopnFletch.anchor,tree.tile()) +" no tree");
						ChopnFletch.status = "No trees";
						return false;
					}
					System.out.println("many tree");
					return true;
				}
			}, 500, 50);
			
		} else {
			
			if(ctx.players.local().inMotion()) {
				ChopnFletch.status = "Moving to tree";

			} else if(!(ctx.players.local().animation() == -1)) {
				ChopnFletch.status = "Chopping";
			}
			
			if(ctx.players.local().animation() == -1 && !ctx.players.local().inMotion()) {
				List<GroundItem> drops = ctx.groundItems.get();
				
				for(GroundItem i: drops) {
					for(NEST n: NEST.values()) {
						if(i.id() == n.getId()) {
							
							i.click();
							Condition.sleep(500);
							
							while(ctx.players.local().inMotion()) {
								Condition.sleep(100);
							}
							
						}
					}
				}
				
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
}

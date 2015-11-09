package ChoppyFletchy;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

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
		
		if(mPF.playerDistanceTo(tree.tile()) > 50) {
			
			mPF.moveToClose(ChopnFletch.anchor);
			
			Condition.wait(new Callable<Boolean>() {
				 
				@Override
				public Boolean call() throws Exception {
					GameObject tree 	= ctx.objects.select().id(ChopnFletch.treeToChop).nearest().poll();
					
					if(mPF.playerDistanceTo(tree.tile()) > 50) {
						System.out.println(mPF.playerDistanceTo(tree.tile()) +" no tree");
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
			
			if(ctx.players.local().animation() == -1 && ctx.players.local().inMotion() == false) {
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

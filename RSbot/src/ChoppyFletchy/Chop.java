package ChoppyFletchy;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;




public class Chop extends Task<ClientContext>{
	private int treeIds[]	 	= {1276, 1278};
	private int clickCounter	= 0;
	
	private BotSetting bSetting;
	
	public Chop(ClientContext ctx, BotSetting bSetting) {
		super(ctx);
		this.bSetting = bSetting;
	}
	
	@Override
	public boolean activate() {
		return ctx.inventory.select().count() < 28;
	}

	@Override
	public void execute() {

			if(!(ctx.players.local().animation() == -1)) {
				ChopnFletcher.status = "Chopping";
			} else if(ctx.players.local().inMotion()) {
				ChopnFletcher.status = "Moving to tree";
			}
			
			final GameObject tree 	= ctx.objects.select().id(bSetting.getTreeToChop()).nearest().poll();
			PathFinder mPF			= new PathFinder(ctx);
			
			if(mPF.distanceBetween(bSetting.getAnchor(), tree.tile()) > 100) {
				mPF.moveTo(bSetting.getAnchor());
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

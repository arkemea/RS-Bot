package ChopnFletch.Tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.ChopnFletch;

public class AntiAFK extends Task{
	
	public AntiAFK(ClientContext ctx) {
		super(ctx);
	}
	
	

	@Override
	public boolean activate() {
		return ChopnFletch.status.equals("Chopping");
	}

	@Override
	public void execute() {
		RandomEvent mRandomEvent = new RandomEvent(ctx);
		
		//TODO Figure what to do with this
	}
	
	class RandomEvent {
		
		private ClientContext ctx;
		
		RandomEvent(ClientContext ctx) {
			this.ctx = ctx;
		}
		
		public void doRandom() {
			switch(Random.nextInt(0, 0)) {
				case 0:
					moveCamera();
				break;
			}

					
			
		}
		
		public void moveCamera() {
			ctx.camera.angle(Random.nextInt(1, 360));
		}
		
	}
	
}

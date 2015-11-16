package ChopnFletch.Threads;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.ChopnFletch;

public class AntiAFK extends Thread implements Runnable {
	
	private ClientContext ctx;
	
	
	@Override
	public void run() {
		
		Random rng 			= new Random();
		RandomEvent rEvent 	= new RandomEvent(ctx);
		
		while(true) {
			if(ChopnFletch.status.equals("Chopping")) {
				try {
					Thread.sleep(rng.nextInt(5000, 15000));

				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				if(ChopnFletch.status.equals("Chopping")) {
					rEvent.doRandom();
				}
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}		
	}
	
	public AntiAFK(ClientContext ctx) {
		this.ctx = ctx;
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
			ctx.camera.angle(Random.nextInt(1, 20));
		}
		
	}
	
	
}

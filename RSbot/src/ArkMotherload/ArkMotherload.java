package ArkMotherload;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name = "ArkMotherload", description = "Script for mining in the Motherload minigame")


public class ArkMotherload extends PollingScript<ClientContext> implements MessageListener, PaintListener {
	
	public ArrayList<Task> taskList = new ArrayList<>();
	
	public static int roundCount	= 0;
	public static boolean goUp		= false;
	
	
	
	@Override
	public void start() {
		goUp = (ctx.skills.level(14) >= 72) ? true : false;
		
		taskList.add(new BankOre(ctx));
		
	}	
	
	@Override
	public void poll() {
		System.out.println(roundCount);
		
		for(Task t: taskList) {
			if(t.activate()) {
				t.execute();
			}
		}
		roundCount++;
		
	}
	
	@Override
	public void repaint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messaged(MessageEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	

}

	

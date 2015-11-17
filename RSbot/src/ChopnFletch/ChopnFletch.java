package ChopnFletch;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import ChopnFletch.Graphics.Gui;
import ChopnFletch.Graphics.StatGraphic;
import ChopnFletch.Tasks.Fletch;
import ChopnFletch.Tasks.LootSearcher;
import ChopnFletch.Tasks.Task;

@Script.Manifest(name = "ArkChopnFletch", description = "Chops any log with fletching, banking and powercutting support")

public class ChopnFletch extends PollingScript<ClientContext> implements MessageListener, PaintListener  {
	
	public static ArrayList<Task> taskList 	= new ArrayList<Task>();
	public static String status				= "Starting bot";
	public static boolean run				= true;
	
	public long startTime					= System.currentTimeMillis();
	public StatGraphic mGraphic				= new StatGraphic(ctx, startTime);
	


	@Override
	public void start() {
		
		Gui settings = new Gui(ctx);
		settings.setVisible(true);

		status = "Bot starting ...";
		
	}
	
	@Override
	public void poll() {
		
		for(Task<?> task: taskList) {
			if(task.activate()) {
				task.execute();
				
				if(task instanceof Fletch) {
					break;
				}
				
			}
		}
	}

	@Override
	public void repaint(Graphics g) {
		mGraphic.inputGraphic(g);
		
	}

	@Override
	public void messaged(MessageEvent arg0) {
		mGraphic.inputData(arg0.text());
		
	}
	
	
	
}



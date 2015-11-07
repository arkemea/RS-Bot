package ChoppyFletchy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name = "ArkChopnFletch", description = "Chops any log with fletching and banking support")

public class ChopnFletch extends PollingScript<ClientContext> implements MessageListener, PaintListener  {
	
	private ArrayList<Task> taskList 	= new ArrayList<Task>();
	
	private long startTime				= System.currentTimeMillis();
	
	public StatGraphic mGraphic			= new StatGraphic(ctx, startTime);
	static String status				= "Starting bot";
	
	public static Tile anchor 			= new Tile(2726,3481);
	public static int logToCut 			= LOG.MAPLE.getLogId();
	public static int treeToChop[]		= TREE.MAPLE.getTreeIds();
	public static int fletch			= 0;
	public static BANK bankToBank		= BANK.DEFAULT;
	
	public static boolean powerCut 		= false;
	public static boolean startScript   = false;
	
	@Override
	public void start() {
		
		Gui settings = new Gui(ctx);
		settings.setVisible(true);
		
		while(!startScript) {
			Condition.sleep(100);
		}
		status = "Bot started";

		
		if(powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx), new Drop(ctx)));
		} else if(powerCut && fletch != 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx), new Fletch(ctx), new Drop(ctx)));
		} else if(!powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx), new Banking(ctx)));
		} else if(!powerCut && fletch != 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx),new Fletch(ctx), new Banking(ctx)));
		}
		
		
	}
	
	@Override
	public void poll() {
		
		for(Task<?> task: taskList) {
			if(task.activate()) {
				task.execute();
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



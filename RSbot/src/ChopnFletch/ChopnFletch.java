package ChopnFletch;

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

import ChopnFletch.Enums.BANK;
import ChopnFletch.Enums.LOG;
import ChopnFletch.Enums.TREE;
import ChopnFletch.Graphics.Gui;
import ChopnFletch.Graphics.StatGraphic;
import ChopnFletch.Tasks.Banking;
import ChopnFletch.Tasks.Chop;
import ChopnFletch.Tasks.Drop;
import ChopnFletch.Tasks.Fletch;
import ChopnFletch.Tasks.Task;
import ChopnFletch.Threads.AntiAFK;
import ChopnFletch.Threads.LootSearcher;

@Script.Manifest(name = "ArkChopnFletch", description = "Chops any log with fletching, banking and powercutting support")

public class ChopnFletch extends PollingScript<ClientContext> implements MessageListener, PaintListener  {
	
	private ArrayList<Task> taskList 	= new ArrayList<Task>();
	
	private long startTime				= System.currentTimeMillis();
	
	public StatGraphic mGraphic			= new StatGraphic(ctx, startTime);
	public static String status				= "Starting bot";
	
	public static Tile anchor 			= new Tile(2726,3481);
	public static int logToCut 			= LOG.MAPLE.getLogId();
	public static int treeToChop[]		= TREE.MAPLE.getTreeIds();
	public static int fletch			= 0;
	public static BANK bankToBank		= BANK.DEFAULT;
	public static int pathToWalk		= 0;
	
	
	public static boolean powerCut 		= false;
	public static boolean startScript   = false;
	
	@Override
	public void start() {
		
		Gui settings 			= new Gui(ctx);
		
		Thread mLootSearcher 	= new Thread(new LootSearcher(2000,ctx));
		Thread mAntiAFK 		= new Thread(new AntiAFK(ctx));
		settings.setVisible(true);
		
		while(!startScript) {
			Condition.sleep(100);
		}
		status = "Bot started";
		
		mLootSearcher.start();
		mAntiAFK.start();
		
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
				
				if(task instanceof Fletch) {
					System.out.println("test");
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



package ChoppyFletchy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.powerbot.script.Condition;
import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Players;

@Script.Manifest(name = "ArkChopnFletch", description = "Chops any log with fletching and banking support")

public class ChopnFletcher extends PollingScript<ClientContext> implements MessageListener, PaintListener  {
	
	private ArrayList<Task> taskList 	= new ArrayList<Task>();
	
	private long startTime				= System.currentTimeMillis();
	
	public StatGraphic mGraphic			= new StatGraphic(ctx, startTime);
	static String status				= "Starting bot";
	
	public Tile anchor 					= ctx.players.local().tile();
	public static int logToCut 			= LOG.MAPLE.getLogId();
	public static int treeToChop[]		= TREE.MAPLE.getTreeIds();
	public static int fletch			= 2;
	public static boolean powerCut 		= false;
	public static boolean startScript   = false;
	
	@Override
	public void start() {
		
		Gui settings = new Gui(ctx, taskList);
		settings.setVisible(true);
		
		while(!startScript) {
			Condition.sleep(100);
		}
		status = "Bot started";
		
		System.out.println(fletch);
		BotSetting bSetting = new BotSetting(anchor, logToCut, treeToChop, fletch, powerCut);
		
		if(powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Drop(ctx, bSetting)));
		} else if(powerCut && fletch != 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Fletch(ctx, bSetting), new Drop(ctx,bSetting)));
		} else if(!powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Banking(ctx, bSetting)));
		} else if(!powerCut && fletch != 0) {
			
			taskList.add(new Chop(ctx, bSetting));
			taskList.add(new Fletch(ctx, bSetting));
			taskList.add(new Banking(ctx, bSetting));
		}
		
		
	}
	
	@Override
	public void poll() {
		
		for(Task task: taskList) {
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



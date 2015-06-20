package ChoppyFletchy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

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

@Script.Manifest(name = "ChopnFletcher", description = "Chops any log with fletching and banking support")

public class ChopnFletcher extends PollingScript<ClientContext> implements MessageListener, PaintListener  {
	
	private long startTime				= System.currentTimeMillis();
	private ArrayList<Task> taskList 	= new ArrayList<Task>();
	public StatGraphic mGraphic			= new StatGraphic(ctx, startTime);
	static String status				="Starting bot";
	
	@Override
	public void start() {
		
		Tile anchor 		= ctx.players.local().tile();
		int logToCut 		= LOG.WILLOW.getLogId();
		int treeToChop[]	= TREE.WILLOW.getTreeIds();
		int fletch			= 0;
		boolean powerCut 	= false;
		
		BotSetting bSetting = new BotSetting(anchor, logToCut, treeToChop, fletch, powerCut);
		
		if(powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Drop(ctx, bSetting)));
		} else if(powerCut && fletch != 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Fletch(ctx, bSetting), new Drop(ctx,bSetting)));
		} else if(!powerCut && fletch == 0) {
			boolean addAll = taskList.addAll(Arrays.asList(new Chop(ctx, bSetting), new Banking(ctx)));
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



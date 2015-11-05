package ChoppyFletchy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
	static String status				=	"Starting bot";
	public static boolean isDone		= false;
	
	public JFrame frame;
	
	public Tile anchor 					= ctx.players.local().tile();
	public static int logToCut 			= LOG.MAPLE.getLogId();
	public static int treeToChop[]		= TREE.MAPLE.getTreeIds();
	public static int fletch			= 2;
	public static boolean powerCut 		= false;
	
	@Override
	public void start() {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
		
		while(!isDone)  {
			System.out.println("der");
		}
		
		frame.setEnabled(false);
		
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
	
	private void createAndShowGUI() {
	       //Create and set up the window.
	       frame = new JFrame("ComboBoxDemo");
	       frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	       //Create and set up the content pane.
	       JComponent newContentPane = new SettingGUI();
	       newContentPane.setOpaque(true); //content panes must be opaque
	       frame.setContentPane(newContentPane);

	       //Display the window.
	       frame.pack();
	       frame.setVisible(true);
	   }
	
}



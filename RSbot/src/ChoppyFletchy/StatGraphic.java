package ChoppyFletchy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Skills;

public class StatGraphic extends Task<ClientContext> {
	
	private long startTime = 0;
	
	private int woodcuttingXPStart 	= ctx.skills.experience(8);
	private int fletchingXPStart	= ctx.skills.experience(9);
	private int itemFletched		= 0;
	private int logChopped			= 0;
	
	private int xpTable[] 			=
		{0,0,83,174,276,388,512,650,801,969,1154,1358,1584,1833,2107,2411,2746,
			3115,3523,3973,4470,5018,5624,6291,7028,7842,8740,9730,10824,12031,
			13363,14833,16456,18247,20224,22406,24815,27473,30408,33648,37224,41171,
			45529,50339,55649,61512,67983,75127,83014,91721,101333,111945,123660,
			136594,150872,166636,184040,203254,224466,247886,273742,302288,333804,
			368599,407015,449428,496254,547953,605032,668051,737627,814445,899257,
			992895,1096278,1210421,1336443,1475581,1629200,1798808,1986068,2192818,
			2421087,2673114,2951373,3258594,3597792,3972294,4385776,4842295,5346332,
			5902831,6517253,7195629,7944614,8771558,9684577,10692629,11805606,13034431
		};
	
	
	public String runTime() {
		DecimalFormat nf = new DecimalFormat("00");
		long millis = System.currentTimeMillis() - startTime;
		long hours = millis / (1000 * 60 * 60);
		millis -= hours * (1000 * 60 * 60);
		long minutes = millis / (1000 * 60);
		millis -= minutes * (1000 * 60);
		long seconds = millis / 1000;
		return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	}
	
	public StatGraphic(ClientContext ctx, long time) {
		super(ctx);
		this.startTime = time;
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
	}
	
	public void inputGraphic(Graphics g) {
		
		try {
			g.drawImage(ImageIO.read(getClass().getResource("/art/bg1.png")), 0, 0, null);
			g.drawImage(ImageIO.read(getClass().getResource("/art/fletchingIcon.png")),170,380,null);
			g.drawImage(ImageIO.read(getClass().getResource("/art/woodcuttingIcon.png")), 320, 380, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.lang.IllegalArgumentException IAe) {
			System.out.println("hehe");
		}
		g.setFont(new Font("arial Regular", 1, 12));
		
		g.setColor(Color.WHITE);
		
		//Fletching
		g.drawString("Runtime", 252, 356);
		g.drawString("Items fletched", 217, 374);
		g.drawString("Xp earned", 240, 392);
		g.drawString("Xp/hour", 254, 410);
		
		//Woodcutting
		g.drawString("Status", 392, 356);
		g.drawString("Logs cut", 380, 374);
		g.drawString("Xp earned", 370, 392);
		g.drawString("Xp/hour", 384, 410);
		
		g.setColor(new Color(0x32fcd8));
		
		//Fletching
		g.drawString(runTime(), 305, 356);
		g.drawString(String.valueOf(itemFletched), 305, 374);
		g.drawString(getFletchingXPTotalPaint(), 305, 392);
		g.drawString(getFletchingXPHourPaint(), 305, 410);
		
		//Woodcutting
		g.drawString(ChopnFletcher.status, 435, 356);
		g.drawString(String.valueOf(logChopped), 435, 374);
		g.drawString(getWoodcuttingXPTotalPaint(), 435, 392);
		g.drawString(getWoodcuttingXPHourPaint(), 435, 410);
		
		drawFletchingBar(g);
		drawWoodcuttingBar(g);
	}
	
	public void inputData(String data) {
		
		String fletchString1 = "cut the wood";
		String fletchString2 = "attach feathers to";
		String woodcuttingString = "You get some";
		
		if(data.contains(fletchString1) || data.contains(fletchString2)) {
			itemFletched++;
		}
		
		if(data.contains(woodcuttingString)) {
			logChopped++;
		}
		
		
	}
	
	public String getWoodcuttingXPHourPaint() {
		double woodcuttingXPGained = ctx.skills.experience(8) - woodcuttingXPStart;
		
		if(woodcuttingXPGained == 0) {
			return "0";
		}
		
		return 	String.valueOf((int)(woodcuttingXPGained / 
				((double)(System.currentTimeMillis()-startTime)/3600000)));
	}
	
	public String getWoodcuttingXPTotalPaint() {
		return String.valueOf(ctx.skills.experience(8) - woodcuttingXPStart);
	}
	
	public String getFletchingXPHourPaint() {
		double fletchingXPGained = ctx.skills.experience(9) - fletchingXPStart;
		
		if(fletchingXPGained == 0) {
			return "0";
		}
		
		return 	String.valueOf((int)(fletchingXPGained / 
				((double)(System.currentTimeMillis()-startTime)/3600000)));
	}
	
	public String getFletchingXPTotalPaint() {
		return String.valueOf(ctx.skills.experience(9) - fletchingXPStart);
	}
	
	public void drawFletchingBar(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawRect(240, 428, 100, 10);
		
		g.setColor(Color.GREEN);
		g.fillRect(240, 428, getPercentIntoNextLevel(9)+1, 11);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 11));
		g.drawString(String.valueOf(getPercentIntoNextLevel(9)) + "%", 280, 438);
		
	}
	
	public void drawWoodcuttingBar(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawRect(390, 428, 100, 10);
		
		g.setColor(Color.GREEN);
		g.fillRect(390, 428, getPercentIntoNextLevel(8)+1, 11);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", 1, 11));
		g.drawString(String.valueOf(getPercentIntoNextLevel(8)) + "%", 430, 438);
		
	}
	
	public int getPercentIntoNextLevel(int skillID) {
		int currentLevel = ctx.skills.level(skillID);
		
		double xpIntoLevel = ctx.skills.experience(skillID)-xpTable[ctx.skills.level(skillID)];
		double xpTotalLevel = (xpTable[ctx.skills.level(skillID)+1])-xpTable[ctx.skills.level(skillID)];
		
		double percentToNextLevel = ((xpIntoLevel/xpTotalLevel)*100);
		
		return (int) percentToNextLevel;
		}
}

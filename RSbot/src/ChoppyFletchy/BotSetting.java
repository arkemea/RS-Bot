package ChoppyFletchy;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class BotSetting {

	private Tile anchor = new Tile(0,0);

	private Bank bankToBank;	
	
	private int logToCut = 1511;
	private int fletch = 0;
	//0 dont fletch, 1 arrowshafts, 2 shortbow, 3 longbow
	
	boolean powerCutting;
	
	public BotSetting() {
			
	}
	
	public BotSetting(Tile anchor, Bank bankToBank, int logToCut, int fletch, boolean powerCutting) {
		this.anchor 		= anchor;
		this.bankToBank 	= bankToBank;
		this.logToCut 		= logToCut;
		this.fletch 		= fletch;
		this.powerCutting 	= powerCutting;
	}
	
	public Tile getAnchor() {
		return anchor;
	}
	
	public Bank getBankToBank() {
		return bankToBank;
	}
	
	public int getLogToCut() {
		return logToCut;
	}

	public int getFletch() {
		return fletch;
	}
	
	public boolean getPowerCutting() {
		return powerCutting;
	}
	
	public void setAnchor(Tile anchor) {
		this.anchor = anchor;
	}
	
	public void setBankToBank(Bank bankToBank) {
		this.bankToBank = bankToBank;
	}
	
	public void setLogToCut(int logToCut) {
		this.logToCut = logToCut;
	}
	
	public void setFletch(int fletch) {
		this.fletch = fletch;
	}
	
	public void setPowerCutting(boolean powerCutting) {
		this.powerCutting = powerCutting;
	}
	
}


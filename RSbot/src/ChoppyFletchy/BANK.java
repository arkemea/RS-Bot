package ChoppyFletchy;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum BANK {
	DEFAULT(new Area(new Tile(0,0), new Tile(0,0)), 0),
	DRAYNOR(new Area(new Tile(0,0), new Tile(0,0)), 11744),
	VARROCKEAST(new Area(new Tile(0,0), new Tile(0,0)), 0),
	SEERSVILLAGE(new Area(new Tile(2721,3493), new Tile(2729,3490)), 25808);
	
	private Area bankArea;
	private int bankBoothId;
	
	BANK(Area bankArea, int bankBoothId) {
		this.bankArea 		= bankArea;
		this.bankBoothId 	= bankBoothId;
	}
	
	public Area getBankArea() {
		return bankArea;
	}
	
	public int getBankBoothId() {
		return bankBoothId;
	}
	
}
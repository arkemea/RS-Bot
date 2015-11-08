package ChoppyFletchy;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum BANK {
	DEFAULT(new Area(new Tile(0,0), new Tile(0,0)), 0, null),
	DRAYNOR(new Area(new Tile(0,0), new Tile(0,0)), 11744, null),
	VARROCKEAST(new Area(new Tile(0,0), new Tile(0,0)), 0, null),
	SEERSVILLAGE(new Area(new Tile(2723,3496), new Tile(2727,3491)), 25808, SPOTS.SEERSVILLAGE);
	
	private Area bankArea;
	private int bankBoothId;
	private SPOTS treeSpots;
	
	BANK(Area bankArea, int bankBoothId, SPOTS treeSpots) {
		this.bankArea 		= bankArea;
		this.bankBoothId 	= bankBoothId;
		this.treeSpots		= treeSpots;	}
	
	public Area getBankArea() {
		return bankArea;
	}
	
	public int getBankBoothId() {
		return bankBoothId;
	}
	
	public SPOTS getSPOTS() {
		return treeSpots;
	}
	
}
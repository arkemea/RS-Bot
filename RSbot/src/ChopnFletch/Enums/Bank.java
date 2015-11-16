package ChopnFletch.Enums;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum Bank {
	DEFAULT(new Area(new Tile(0,0), new Tile(0,0)), 0, null),
	DRAYNOR(new Area(new Tile(3091,3241), new Tile(3094,3246)), 11744, Spots.DRAYNOR),
	GRANDEXCHANGE(new Area(new Tile(3167,3488), new Tile(3168,3491)), 10060, Spots.GRANDEXCHANGE),
	VARROCKEAST(new Area(new Tile(3257,3419), new Tile(3250,3422)), 11748, Spots.VARROCKEAST),
	SEERSVILLAGE(new Area(new Tile(2721,3494), new Tile(2729,3490)), 25808, Spots.SEERSVILLAGE),
	CATHERBY(new Area(new Tile(2812,3442), new Tile(2807,3438)),11744, Spots.CATHERBY);
	
	private Area bankArea;
	private int bankBoothId;
	private Spots treeSpots;
	
	Bank(Area bankArea, int bankBoothId, Spots treeSpots) {
		this.bankArea 		= bankArea;
		this.bankBoothId 	= bankBoothId;
		this.treeSpots		= treeSpots;	}
	
	public Area getBankArea() {
		return bankArea;
	}
	
	public int getBankBoothId() {
		return bankBoothId;
	}
	
	public Spots getSpots() {
		return treeSpots;
	}
	
}
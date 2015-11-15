package ArkMotherload;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public enum MineArea {
	
	BANKAREA(new Area(new Tile(3759,5667), new Tile(3758,5665))),
	PICKUPAREA(new Area(new Tile(3750,5660), new Tile(3747,5657)));
	
	private Area tiles;
	
	MineArea(Area tiles) {
		this.tiles = tiles;
	}
	
	public Area getArea() {
		return tiles;
	}
}

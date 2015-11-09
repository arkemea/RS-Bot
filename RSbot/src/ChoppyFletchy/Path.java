package ChoppyFletchy;

import org.powerbot.script.Tile;


public class Path {
	
	private Tile[] tilePath;
	

	
	public Path(Tile tilePath) {
		this.tilePath 			= new Tile[] {tilePath};
	}
	
	public Path(Tile... tilePath) {
		this.tilePath = tilePath;
	}
	
	public void setTilePath(Tile... tilePath) { 
		this.tilePath = tilePath;
	}
	
	public Tile[] getTilePath() {
		return tilePath;
	}
	
	public Tile[] getReverseTilePath() {
		
		 Tile[] reversed = new Tile[tilePath.length];
		    for (int i=0; i<tilePath.length; i++) {
		        reversed[i] = tilePath[tilePath.length - 1 - i];
		    }
		    return reversed;	
	}
	
	public String toString() {
		
		String s = "";
		
		for(Tile t: tilePath) {
			s += t.x() + "," + t.y() + "  ";
		}
		
		return s;
	}
}

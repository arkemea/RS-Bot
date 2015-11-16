package ChopnFletch.Tasks;

import org.powerbot.script.Tile;


public class Path {
	
	private Tile[] tilePath;
	private int distanceToAnchor, distanceFromAnchor;

	
	public Path( int distanceFromAnchor, int distancetoAnchor, Tile tilePath) {
		this.tilePath 			= new Tile[] {tilePath};
		this.distanceFromAnchor = distanceFromAnchor;
		this.distanceToAnchor	= distancetoAnchor;
	}
	
	public Path(int distanceFromAnchor, int distancetoAnchor, Tile... tilePath) {
		this.tilePath = tilePath;
		this.distanceFromAnchor = distanceFromAnchor;
		this.distanceToAnchor	= distancetoAnchor;
	}
	
	public Path(Tile... tilePath) {
		this.tilePath = tilePath;
	}
	
	public void setTilePath(Tile... tilePath) { 
		this.tilePath = tilePath;
	}
	
	public void setDistanceToAnchor(int distanceToAnchor) {
		this.distanceToAnchor = distanceToAnchor;
	}
	
	public void setDistanceFromAnchor(int distanceFromAnchor) {
		this.distanceFromAnchor = distanceFromAnchor;
	}
	
	public Tile[] getTilePath() {
		return tilePath;
	}
	
	public int getDistanceFromAnchor() {
		return distanceFromAnchor;
	}
	
	public int getDistanceToAnchor() {
		return distanceToAnchor;
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

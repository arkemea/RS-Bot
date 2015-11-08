package ChoppyFletchy;

import org.powerbot.script.Tile;

public enum SPOTS {
	
	SEERSVILLAGE(new Tile(2728,3477), new Tile(2718,3479), new Tile(0,0),  new Tile(2726,3501), new Tile(2709,3462), new Tile(0,0));
	
	private Tile[] anchor;
	
	SPOTS(Tile... anchor) {
		this.anchor = anchor;
	}

	public Tile[] getAnchors() {
		return anchor;
	}
	
	public Tile getSpecificAnchor(int i) {
		return anchor[i];
	}
	
	
	
}
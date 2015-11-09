package ChoppyFletchy;

import org.powerbot.script.Tile;

public enum SPOTS {
	
	DRAYNOR(new Tile(3082,3268), new Tile(3100,3286), new Tile(3087,3234), null, null, null),
	SEERSVILLAGE(new Tile(2728,3477), new Tile(2718,3479), new Tile(2714,3510),  new Tile(2726,3501), new Tile(2709,3462), new Tile(2702,3398)),
	GRANDEXCHANGE(new Tile(3187,3503), null, null, null, new Tile(3213,3503), null);
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
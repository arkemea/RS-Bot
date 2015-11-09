package ChoppyFletchy;

import org.powerbot.script.Tile;

public enum SPOTS {
	
	VARROCKEAST(
			new Path[] {
					new Path( new Tile(3280,3418)),
					new Path( new Tile(3280,3427)),
					null,
					null,
					new Path(new Tile(3253,3421), new Tile(3259,3428), new Tile(3273,3429), new Tile(3280,3441), new Tile(3279,3455), new Tile(3272,3468), new Tile(3267,3481)), 
					null},
					
			new Tile[] {
					new Tile(3280,3418),
					new Tile(3280,3427),
					null,
					null,
					new Tile(3267,3481),
					null}),
					
	DRAYNOR(
			new Path[] {
					new Path(new Tile(3082,3268)),
					new Path(new Tile(3100,3286)),
					new Path(new Tile(3087,3234)),
					null,
					null,
					null},
			
			new Tile[] { 
					new Tile(3082,3268), 
					new Tile(3100,3286), 
					new Tile(3087,3234), 
					null, 
					null, 
					null}),
	
	SEERSVILLAGE(
			new Path[] {
					new Path (new Tile(2728,3477)),
					new Path (new Tile(2718,3479)), 
					new Path (new Tile(2714,3510)), 
					new Path (new Tile(2726,3501)), 
					new Path (new Tile(2709,3462)), 
					new Path (new Tile(2702,3398))},
			
			new Tile[] {
					new Tile(2728,3477),
					new Tile(2718,3479),
					new Tile(2714,3510),
					new Tile(2726,3501),
					new Tile(2709,3462),
					new Tile(2702,3398)}),
	
	GRANDEXCHANGE(
			new Path[] {		
					new Path (new Tile(3187,3503)),
					null,
					null,
					null,
					new Path (new Tile(3213,3503)),
					null},
			new Tile[] {
					new Tile(3187,3503),
					null,
					null,
					null,
					new Tile(3213,3503)});
	
	private Path[] pathList;
	private Tile[] anchor;
	
	
	SPOTS(Path[] pathList, Tile[] anchor) {
		this.pathList = pathList;
		this.anchor = anchor;
	}

	public Tile[] getAnchors() {
		return anchor;
	}
	
	public Tile getSpecificAnchor(int i) {
		return anchor[i];
	}
	
	public Path[] getPaths() {
		return pathList;
	}
	
	public Path getSpecificPath(int i) {
		return pathList[i];
	}
	
	
	
}
package ChoppyFletchy;

import org.powerbot.script.Tile;

public enum SPOTS {
	
	VARROCKEAST(
			new Path[] {
					new Path(30, 20, new Tile(3253,3421), new Tile(3259,3428), new Tile(3273,3428), new Tile(3275,3439)),
					new Path(10, 10, new Tile(3253,3421), new Tile(3259,3428), new Tile(3281,3428)),
					null,
					null,
					new Path(30, 18, new Tile(3253,3421), new Tile(3259,3428), new Tile(3274,3428), new Tile(3284,3439), new Tile(3284,3459), new Tile(3269,3472)), 
					null},
					
			new Tile[] {
					new Tile(3276,3444),
					new Tile(3280,3427),
					null,
					null,
					new Tile(3267,3481),
					null}),
					
	DRAYNOR(
			new Path[] {
					new Path(20, 20, new Tile(3093,3243), new Tile(3104,3235)),
					new Path(10, 10, new Tile(3093,3243), new Tile(3104,3235)),
					new Path(15, 15, new Tile(3093,3243), new Tile(3087,3236)),
					null,
					new Path(50, 50,new Tile(3093,3243), new Tile(3101,3237), new Tile(3140,3232), new Tile(3144, 3247)),
					null},
			
			new Tile[] { 
					new Tile(3104,3235), 
					new Tile(3115,3229), 
					new Tile(3087,3234), 
					null, 
					new Tile(3148,3251), 
					null}),
	
	SEERSVILLAGE(
			new Path[] {
					new Path (10, 10, new Tile(2725,3492), new Tile(2727,3481)),
					new Path (10, 10, new Tile(2725,3492), new Tile(2718,3485)), 
					new Path (20, 20, new Tile(2725,3492), new Tile(2719,3502)), 
					new Path (10, 10, new Tile(2725,3492), new Tile(2728,3500)), 
					new Path (15, 15, new Tile(2725,3492), new Tile(2726,3476), new Tile(2720,3467)), 
					new Path (15, 15, new Tile(2725,3492), new Tile(2726,3476), new Tile(2720,3467), new Tile(2722,3446), new Tile(2718,3426), new Tile(2714,3398))},
			
			new Tile[] {
					new Tile(2725,3477),
					new Tile(2718,3479),
					new Tile(2715,3511),
					new Tile(2725,3502),
					new Tile(2710,3463),
					new Tile(2702,3398)}),
	
	GRANDEXCHANGE(
			new Path[] {		
					new Path (15, 15, new Tile(3168,3490), new Tile(3187,3503)),
					null,
					null,
					null,
					new Path (30, 20, new Tile(3168,3490), new Tile(3180,3490), new Tile(3193,3489), new Tile(3200,3502), new Tile(3206,3502)),
					null},
			new Tile[] {
					new Tile(3187,3503),
					null,
					null,
					null,
					new Tile(3213,3503),
					null}),
	CATHERBY(
			new Path[] {
					null,
					null,
					new Path (15, 15, new Tile(2809,3440), new Tile(2790,3433), new Tile(2771,3436),  new Tile(2771,3432)),
					null,
					new Path (15, 15, new Tile(2809,3440), new Tile(2790,3433), new Tile(2771,3436),  new Tile(2771,3432)),
					null},
			new Tile[] {
					null,
					null,
					null,
					null,
					new Tile(2762,3430),
					null});
	
	private Path[] pathList;
	private Tile[] anchor;
	
	SPOTS(Path[] pathList, Tile[] anchor) {
		this.pathList 			= pathList;
		this.anchor 			= anchor;
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
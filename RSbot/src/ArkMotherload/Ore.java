package ArkMotherload;

public enum Ore {
	
	GOLDENNUGGET(12012),
	COPPER(0),
	TIN(0),
	IRON(440),
	COAL(453),
	GOLD(444),
	MITHRIL(0),
	ADAMAND(0),
	RUNE(0);
	
	private int id;
	
	Ore(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
}

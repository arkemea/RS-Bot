package ChopnFletch.Enums;

public enum Nest {
	
	EMPTYNEST(5075),
	SEEDNEST(5073),
	RINGNEST(5074);
	
	private int id;
	
	Nest(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}

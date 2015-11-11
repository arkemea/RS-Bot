package ChopnFletch;

public enum NEST {
	
	EMPTYNEST(5075),
	SEEDNEST(5073),
	RINGNEST(5074);
	
	private int id;
	
	NEST(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}

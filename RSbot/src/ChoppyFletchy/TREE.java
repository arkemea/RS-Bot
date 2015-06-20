package ChoppyFletchy;

public enum TREE {
	NORMAL(1245,2245);
	
	
	
	private int treeIds[];
	
	TREE(int... treeIds) {
		this.treeIds = treeIds;
	}
	
	public int[] getLogIds() {
		return treeIds;
	}
	
	public int getLogId(int i) {
		return treeIds[i];
	}
	
}

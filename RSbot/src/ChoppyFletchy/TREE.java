package ChoppyFletchy;

public enum TREE {
	NORMAL(1276,1278),
	OAK(11756),
	WILLOW(11755,11759,11763,11761),
	MAPLE(11762),
	YEW(11758),
	MAGIC(0);
	
	private int treeIds[];
	
	TREE(int... treeIds) {
		this.treeIds = treeIds;
	}
	
	public int[] getTreeIds() {
		return treeIds;
	}
	
	public int getLogId(int i) {
		return treeIds[i];
	}
}

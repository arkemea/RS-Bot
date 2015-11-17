package ChopnFletch.Enums;

public enum Tree {
	NORMAL("Normal",1276,1278),
	OAK("Oak",11756),
	WILLOW("Willow",11755,11759,11763,11761),
	MAPLE("Maple",11762),
	YEW("Yew",11758),
	MAGIC("Magic",11764);
	
	private int treeIds[];
	private String name;
	
	Tree(String name, int... treeIds) {
		this.treeIds = treeIds;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int[] getTreeIds() {
		return treeIds;
	}
	
}

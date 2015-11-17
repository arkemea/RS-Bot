package ChopnFletch.Enums;

public enum Log {
	  NORMAL("Normal",1511),
	  OAK("Oak",1521),
	  WILLOW("Willow",1519),
	  MAPLE("Maple",1517),
	  YEW("Yew",1515),
	  MAGIC("Magic",1513);
  
  private int logId;
  private String name;
  
  Log(String name,int id) {
    logId = id;
    this.name = name;
  }

  public int getLogId() {
    return logId;
  }
  public String getName() {
	  return name;
  }
}


package ChopnFletch.Enums;

public enum Log {
	  NORMAL(1511),
	  OAK(1521),
	  WILLOW(1519),
	  MAPLE(1517),
	  YEW(1515),
	  MAGIC(1513);
  
  private int logId;
  
  Log(int id) {
    logId = id;
  }

  public int getLogId() {
    return logId;
  }
}


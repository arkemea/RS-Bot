package ChopnFletch.Enums;

public enum LOG {
	  NORMAL(1511),
	  OAK(1521),
	  WILLOW(1519),
	  MAPLE(1517),
	  YEW(1515),
	  MAGIC(1513);
  
  private int logId;
  
  LOG(int id) {
    logId = id;
  }

  public int getLogId() {
    return logId;
  }
}


package ChopnFletch.Enums;

public enum Bow {
	  OAKSHORT(54),
	  OAKLONG(56),
	  WILLOWSHORT(60),
	  WILLOWLONG(58),
	  MAPLESHORT(64),
	  MAPLELONG(62),
	  YEWSHORT(68),
	  YEWLONG(66),
	  MAGICSHORT(72),
	  MAGICLONG(74);
  
  private int bowId;
  
  Bow(int id) {
    bowId = id;
  }

  public int getBowId() {
    return bowId;
  }
  
}
package ChoppyFletchy;

public enum BOW {
	  OAKSHORT(54),
	  OAKLONG(56),
	  WILLOWSHORT(60),
	  WILLOWLONG(58),
	  MAPLESHORT(64),
	  MAPLELONG(66),
	  YEWSHORT(68),
	  YEWLONG(70),
	  MAGICSHORT(72),
	  MAGICLONG(74);
  
  private int bowId;
  
  BOW(int id) {
    bowId = id;
  }

  public int getBowId() {
    return bowId;
  }
  
}
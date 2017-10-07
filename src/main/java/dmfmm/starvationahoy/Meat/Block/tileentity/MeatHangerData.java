package dmfmm.starvationahoy.Meat.Block.tileentity;

import dmfmm.starvationahoy.api.Meat.IMeatType;

/**
 * Created by dmf444 on 10/6/2017. Code originally written for StarvationAhoy.
 */
public class MeatHangerData implements IMeatType{
  public static final int  MEATTYPE_COW = 1, MEATTYPE_PIG = 2, MEATTYPE_CHICK = 3, MEATTYPE_SHEEP = 4, MEATTYPE_RABBIT = 5;

  public static final IMeatType EMPTY = new MeatHangerData(0);
  public static final IMeatType COW = new MeatHangerData(MEATTYPE_COW);
  public static final IMeatType PIG = new MeatHangerData(MEATTYPE_PIG);
  public static final IMeatType CHICKEN = new MeatHangerData(MEATTYPE_CHICK);
  public static final IMeatType SHEEP = new MeatHangerData(MEATTYPE_SHEEP);
  public static final IMeatType RABBIT = new MeatHangerData(MEATTYPE_RABBIT);


  private final int id;

  public MeatHangerData(int type){
    this.id = type;
  }

  @Override
  public int getMeatID() {
    return id;
  }



  public enum MeatStates{
    EMPTY(0),
    NORMAL(1),
    SKINNED(2),
    ROTTEN(3);

    private int state;

    MeatStates(int i){
      this.state = i;
    }

    public int getState(){
      return this.state;
    }

  }
}

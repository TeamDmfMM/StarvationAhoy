package dmfmm.starvationahoy.meat.block.tileentity;

/**
 * Created by dmf444 on 10/6/2017. Code originally written for StarvationAhoy.
 */
public class MeatHangerData{

  public static final int  MEATTYPE_COW = 1, MEATTYPE_PIG = 2, MEATTYPE_CHICK = 3, MEATTYPE_SHEEP = 4, MEATTYPE_RABBIT = 5;

  public enum MeatType {
    NO_ANIMAL(0),
    COW(MEATTYPE_COW),
    PIG(MEATTYPE_PIG),
    CHICKEN(MEATTYPE_CHICK),
    SHEEP(MEATTYPE_SHEEP),
    RABBIT(MEATTYPE_RABBIT);

    private int id;
    MeatType(int i){
      this.id = i;
    }

    public int getMeatID(){
      return this.id;
    }
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

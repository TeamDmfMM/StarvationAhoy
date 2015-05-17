package dmfmm.StarvationAhoy.Meat.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dmfmm.StarvationAhoy.Core.util.SALog;
import dmfmm.StarvationAhoy.Meat.ModuleMeat;

/**
 * Created by DMF444 for Starvation Ahoy. All rights
 * reserved. Code may be copied if credit is given to
 * source. Any code derived code is under their respective
 * licences.
 */
public class event_meatCutRoaster {

    @SubscribeEvent
    public void roasterCut(MeatCutEvent.SpitRoast e){
        if(e.meattype == ModuleMeat.MEATTYPE_COW){
            SALog.fatal("A Cow Was Made");
        } else if(e.meattype == ModuleMeat.MEATTYPE_PIG){
            SALog.fatal("This is a Piggy");
        } else if(e.meattype == ModuleMeat.MEATTYPE_CHICK){
            SALog.fatal("Here Chicky!");
        }
    }
}

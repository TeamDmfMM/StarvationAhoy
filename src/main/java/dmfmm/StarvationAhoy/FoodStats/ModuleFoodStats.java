package dmfmm.StarvationAhoy.FoodStats;

import dmfmm.StarvationAhoy.FoodStats.DebugKeyBind.OverlayHungerDebug;
import dmfmm.StarvationAhoy.FoodStats.DietaryHistoryManage.DietaryHistoryEvents;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class ModuleFoodStats {

    public static void preInit(){




    }
    public static void init(){


        MinecraftForge.EVENT_BUS.register(new DietaryHistoryEvents());
        MinecraftForge.EVENT_BUS.register(new OverlayHungerDebug());


    }
    public static void postInit(){





    }

    public static void serverStart(){

        PlayerInstanceHolder.instance.reload();

    }

    public static void serverStop(){
        PlayerInstanceHolder.instance.save();
    }


}

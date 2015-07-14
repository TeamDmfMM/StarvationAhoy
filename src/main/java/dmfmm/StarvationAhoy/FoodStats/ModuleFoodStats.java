package dmfmm.StarvationAhoy.FoodStats;

import cpw.mods.fml.common.FMLCommonHandler;
import dmfmm.StarvationAhoy.FoodStats.DietaryHistoryManage.DietaryHistoryEvents;

/**
 * Created by mincrmatt12. Do not copy this or you will have to face
 * our legal team. (dmf444)
 */
public class ModuleFoodStats {

    public static void preInit(){




    }
    public static void init(){


        FMLCommonHandler.instance().bus().register(new DietaryHistoryEvents());


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
